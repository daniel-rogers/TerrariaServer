package com.dilfer.terraria.discord.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.http.HttpResponse;
import java.nio.charset.Charset;

public class GsonBodyHandler<T> implements HttpResponse.BodyHandler<T>
{
    private static final Gson GSON = new GsonBuilder().create();
    private final Class<T> responseClass;

    private GsonBodyHandler(Class<T> responseClass)
    {
        this.responseClass = responseClass;
    }

    public static <T> GsonBodyHandler<T> gsonBodyHandler(Class<T> responseClass)
    {
        return new GsonBodyHandler<>(responseClass);
    }

    @Override
    public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo)
    {
        return HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofString(Charset.defaultCharset()), string -> GSON.fromJson(string, responseClass));
    }
}
