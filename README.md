# Terraria Server Orchestration

The intent of this repo is to provide a cost effective way of running a Terraria server in AWS on EC2, and allow interaction with the server remotely, via a Discord bot. The Discord bot does not know about AWS, but uses simple HTTP code to talk to a domain which is backed by API Gateway, invoking AWS Lambda to perform EC2 API calls.

## Modules
This repo is multi-facted and contains many different Gradle modules for different purposes.

### [aws](./aws/README.md)
The `aws` module includes all of the Lambda functions which will be interacting with EC2 and invoked via API Gateway

### [discordBot](./discordBot/README.md)
The `discordBot` module provides a [Discord4J](https://github.com/Discord4J/Discord4J) implementation of a discord bot.

### [docker](./docker/README.md)
The `docker` module provides a docker file which installs Terraria server on a Ubuntu machine.

### [http-api](./http-api/README.md)
The `http-api` module provides common Java PoJo objects used for Http return types. These objects are returned via AWS Lambda to the Discord bot, and then output to the client as a discord message.
