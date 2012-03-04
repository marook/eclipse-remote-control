package com.github.marook.eclipse_remote_control.client.parser;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.command.command.ExternalToolsCommand;

public class ExecuteCommandParser extends CommandParser {

	@Override
	public String getName() {
		return "execute_command";
	}

	@Override
	public String getUsage() {
		return "[command memento]";
	}

	@Override
	public Command parseCommand(final String[] args) {
		if(args.length < 2){
			throw new CommandParseException("Expected 1 argument.");
		}
		
		final ExternalToolsCommand cmd = new ExternalToolsCommand();
		cmd.setConfigurationName(args[1]);
		
		return cmd;
	}

}
