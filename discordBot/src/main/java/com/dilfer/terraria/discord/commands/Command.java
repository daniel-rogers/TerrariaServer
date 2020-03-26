package com.dilfer.terraria.discord.commands;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface Command
{
    Mono<Message> run(MessageChannel messageChannel);
}
