package com.dilfer.terraria.discord.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestRunner
{
    private HttpClient client;

    public HttpRequestRunner(HttpClient client)
    {
        this.client = client;
    }

    public <T> T getResponse(URI endPoint, Class<T> responseType) throws InterruptedException
    {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(endPoint)
                .build();
        try {
            HttpResponse<T> response =
                    client.send(request, GsonBodyHandler.gsonBodyHandler(responseType));

            return response.body();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
