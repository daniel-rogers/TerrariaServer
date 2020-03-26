# Terraria Server Orchestration

The intent of this repo is to provide a cost effective way of running a Terraria server in AWS on EC2, and allow interaction with the server remotely, via a Discord bot. The Discord bot does not know about AWS, but uses simple HTTP code to talk to a domain which is backed by API Gateway, invoking AWS Lambda to perform EC2 API calls.

## Modules
This repo is multi-facted and contains many different Gradle modules for different purposes.

### [aws](./aws/README.md)
The `core` module combines the other modules to form high-level abstractions for the entire Discord Bot API. This is the module most users will want when making bots.

### [discordBot](./discordBot/README.md)
The `rest` module provides a low-level HTTP client specifically for Discord which properly handles Discord's [ratelimiting system](https://discordapp.com/developers/docs/topics/rate-limits).

### [docker](./docker/README.md)
The `gateway` module provides a low-level WebSocket client for interacting with the [Discord Gateway](https://discordapp.com/developers/docs/topics/gateway).

### [http-api](./http-api/README.md)
The `voice` module provides a client to manipulate audio through [Voice Connections](https://discordapp.com/developers/docs/topics/voice-connections).

