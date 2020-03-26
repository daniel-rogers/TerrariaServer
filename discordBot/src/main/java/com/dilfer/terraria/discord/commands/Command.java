package com.dilfer.terraria.discord.commands;

import com.dilfer.terraria.discord.http.HttpRequestRunner;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import reactor.core.publisher.Mono;

public interface Command
{
    Mono<Message> run(MessageChannel messageChannel,
                      HttpRequestRunner httpRequestRunner) throws InterruptedException;
}
