package com.dilfer.terraria.discord;

import com.dilfer.terraria.discord.commands.Commands;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class TerrariaServerBot
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            throw new RuntimeException("Incorrect arguments supplied. This class requires a single argument which is the bot's token.");
        }

        String token = args[0];
        DiscordClientBuilder builder = new DiscordClientBuilder(token);
        DiscordClient client = builder.build();
        try
        {
            for (Commands command : Commands.values())
            {
                client.getEventDispatcher().on(MessageCreateEvent.class)
                        .map(MessageCreateEvent::getMessage)
                        .filter(TerrariaServerBot::messageIsFromNormalUser)
                        .filter(message -> command.getCommandString().equalsIgnoreCase(message.getContent().orElse("")))
                        .flatMap(Message::getChannel)
                        .flatMap(command::run)
                        .subscribe();
            }

            client.login().block();
        }
        finally
        {
            client.logout();
        }
    }

    private static boolean messageIsFromNormalUser(Message message)
    {
        return message.getAuthor().map(user -> !user.isBot()).orElse(false);
    }
}
