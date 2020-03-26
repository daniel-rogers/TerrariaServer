package com.dilfer.terraria.discord;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;

public class TerrariaServerLogoutTest
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
        client.logout().block();
    }
}
