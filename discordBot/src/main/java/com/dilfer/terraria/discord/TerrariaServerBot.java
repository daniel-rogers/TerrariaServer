package com.dilfer.terraria.discord;

import com.dilfer.terraria.discord.commands.Commands;
import com.dilfer.terraria.discord.http.HttpRequestRunner;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.net.http.HttpClient;

public class TerrariaServerBot
{
    private static final Logger logger = LoggerFactory.getLogger(TerrariaServerBot.class);

    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            throw new RuntimeException("Incorrect arguments supplied. This class requires a single argument which is the bot's token.");
        }

        String token = args[0];
        DiscordClientBuilder builder = new DiscordClientBuilder(token);
        DiscordClient client = builder.build();
        HttpRequestRunner httpRequestRunner = new HttpRequestRunner(HttpClient.newHttpClient());

        try
        {
            for (Commands command : Commands.values())
            {
                client.getEventDispatcher().on(MessageCreateEvent.class)
                        .map(MessageCreateEvent::getMessage)
                        .filter(TerrariaServerBot::messageIsFromNormalUser)
                        .filter(message -> command.getCommandString().equalsIgnoreCase(message.getContent().orElse("")))
                        .flatMap(Message::getChannel)
                        .subscribe(messageChannel -> runCommand(httpRequestRunner, command, messageChannel),
                                   throwable -> logger.error(throwable.getMessage()));
            }

            client.login().block();
        }
        finally
        {
            logger.info("About to log out.");
            client.logout().block();
            logger.info("Finished logging out.");
        }
    }

    private static void runCommand(HttpRequestRunner httpRequestRunner, Commands command, MessageChannel messageChannel) {
        try
        {
            command.run(messageChannel, httpRequestRunner);
        }
        catch (InterruptedException e)
        {
            Flux.error(e);
        }
    }

    private static boolean messageIsFromNormalUser(Message message)
    {
        return message.getAuthor().map(user -> !user.isBot()).orElse(false);
    }
}
