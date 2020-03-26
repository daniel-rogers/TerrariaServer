package com.dilfer.terraria.discord.commands;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import reactor.core.publisher.Mono;

public enum Commands
{
    start("pls start", "Used to start the Terraria server and returns the IP address used to connect.", new StartCommand()),
    stop("pls stop", "Used to stop the server to save Dilfer money.", new StopCommand()),
    info("pls info", "Returns general information about the current state of the server.", new InfoCommand()),
    help("pls help", "Prints out all of the available commands.", new HelpCommand());

    private String commandString;
    private String infoMessage;
    private Command command;

    Commands(String commandString, String infoMessage, Command command)
    {

        this.commandString = commandString;
        this.infoMessage = infoMessage;
        this.command = command;
    }

    public String getCommandString()
    {
        return commandString;
    }

    public String getInfoMessage()
    {
        return infoMessage;
    }

    public Mono<Message> run(MessageChannel messageChannel)
    {
        return command.run(messageChannel);
    }
}
