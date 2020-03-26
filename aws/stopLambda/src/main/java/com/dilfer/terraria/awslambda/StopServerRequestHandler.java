package com.dilfer.terraria.awslambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dilfer.terraria.http.api.StopServerResponse;

public class StopServerRequestHandler implements RequestHandler<Void, StopServerResponse>
{
    @Override
    public StopServerResponse handleRequest(Void input, Context context)
    {
        return new StopServerResponse();
    }
}
