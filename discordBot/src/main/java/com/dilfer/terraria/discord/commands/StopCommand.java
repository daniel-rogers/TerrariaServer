package com.dilfer.terraria.discord.commands;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import reactor.core.publisher.Mono;

public class StopCommand implements Command
{
    @Override
    public Mono<Message> run(MessageChannel messageChannel)
    {
        return messageChannel.createMessage("I am the stop command.");
    }
}
