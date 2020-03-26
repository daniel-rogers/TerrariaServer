package com.dilfer.terraria.awslambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dilfer.terraria.http.api.StartServerResponse;

public class StartServerRequestHandler implements RequestHandler<Void, StartServerResponse>
{
    @Override
    public StartServerResponse handleRequest(Void input, Context context)
    {
        return new StartServerResponse();
    }
}
