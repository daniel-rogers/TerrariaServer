package com.dilfer.terraria.discord.commands;

import com.dilfer.terraria.discord.http.HttpRequestRunner;
import com.dilfer.terraria.http.api.StartServerResponse;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import reactor.core.publisher.Mono;

import java.net.URI;

public class StartCommand implements Command
{
    @Override
    public Mono<Message> run(MessageChannel messageChannel,
                             HttpRequestRunner httpRequestRunner) throws InterruptedException
    {
        String response = httpRequestRunner.getResponse(URI.create("http://checkmyip.amazonaws.com"), String.class);
        return messageChannel.createMessage(response.toString());
    }
}
