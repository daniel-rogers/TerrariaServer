package com.dilfer.terraria.discord.commands;

import com.dilfer.terraria.discord.http.HttpRequestRunner;
import com.dilfer.terraria.http.api.StopServerResponse;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import reactor.core.publisher.Mono;

import java.net.URI;

public class StopCommand implements Command
{
    @Override
    public Mono<Message> run(MessageChannel messageChannel,
                             HttpRequestRunner httpRequestRunner) throws InterruptedException
    {
        StopServerResponse response = httpRequestRunner.getResponse(URI.create("https://dilfer.com/terraria/stop"), StopServerResponse.class);
        return messageChannel.createMessage(response.toString());
    }
}
