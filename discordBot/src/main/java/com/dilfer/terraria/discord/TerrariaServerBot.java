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
import reactor.core.publisher.Mono;

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

        DiscordClientBuilder builder = new DiscordClientBuilder(args[0]);
        DiscordClient client = builder.build();
        HttpRequestRunner httpRequestRunner = new HttpRequestRunner(HttpClient.newHttpClient());

        addLogoutShutdownHook(client);
        registerCommands(client, httpRequestRunner);
        loginAndBlockThread(client);
    }

    private static void addLogoutShutdownHook(DiscordClient client) {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            logger.info("W: interrupt received, logging out.");
            client.logout().block();
            logger.info("Successfully logged out.");
        }));
    }

    private static void registerCommands(DiscordClient client, HttpRequestRunner httpRequestRunner) {
        logger.info("Registering all commands.");
        for (Commands command : Commands.values())
        {
            client.getEventDispatcher().on(MessageCreateEvent.class)
                    .map(MessageCreateEvent::getMessage)
                    .filter(TerrariaServerBot::messageIsFromNormalUser)
                    .filter(message -> command.getCommandString().equalsIgnoreCase(message.getContent().orElse("")))
                    .flatMap(Message::getChannel)
                    .flatMap(messageChannel -> runCommand(httpRequestRunner, command, messageChannel))
                    .subscribe();
        }
    }

    private static boolean messageIsFromNormalUser(Message message)
    {
        return message.getAuthor().map(user -> !user.isBot()).orElse(false);
    }

    private static Mono<Message> runCommand(HttpRequestRunner httpRequestRunner, Commands command, MessageChannel messageChannel)
    {
        try
        {
            return command.run(messageChannel, httpRequestRunner);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            return Mono.empty();
        }
    }

    private static void loginAndBlockThread(DiscordClient client)
    {
        logger.info("Logging in.");
        client.login().block();
    }
}
