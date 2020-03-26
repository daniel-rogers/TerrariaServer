package com.dilfer.terraria.awslambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dilfer.terraria.http.api.ServerInfoResponse;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ServerInfoRequestHandler implements RequestHandler<Void, ServerInfoResponse>
{
    @Override
    public ServerInfoResponse handleRequest(Void input, Context context)
    {
        return new ServerInfoResponse();
    }
}
