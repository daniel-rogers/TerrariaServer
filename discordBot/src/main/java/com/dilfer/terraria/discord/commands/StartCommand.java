package com.dilfer.terraria.discord.commands;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StartCommand implements Command
{
    @Override
    public Mono<Message> run(MessageChannel messageChannel)
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest startRequest = HttpRequest
                .newBuilder()
                .uri(URI.create("http://checkip.amazonaws.com/"))
                .build();
        try
        {
            HttpResponse<String> response =
                    client.send(startRequest, HttpResponse.BodyHandlers.ofString());
            return messageChannel.createMessage("Your IP is " + response.body());
        }
        catch (IOException e)
        {
            return messageChannel.createMessage(e.getMessage());
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            return messageChannel.createMessage(e.getMessage());
        }
    }
}
