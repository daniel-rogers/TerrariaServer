package com.dilfer.terraria.discord.commands;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.MessageChannel;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelpCommand implements Command
{
    @Override
    public Mono<Message> run(MessageChannel messageChannel)
    {
        List<Commands> commands = Arrays.stream(Commands.values())
                .filter(cmd -> !Commands.help.equals(cmd))
                .collect(Collectors.toList());

        List<String> commandInformationStrings = commands.stream()
                .map(command -> String.format("%s -> %s\n", command.getCommandString(), command.getInfoMessage()))
                .collect(Collectors.toList());

        StringBuilder outputStringBuilder = new StringBuilder().append("Here is the list of available commands! \n\n");

        commandInformationStrings.forEach(outputStringBuilder::append);
        return messageChannel.createMessage(outputStringBuilder.toString());
    }
}
