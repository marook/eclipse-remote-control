package com.github.marook.eclipse_remote_control.client.parser;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.command.command.ExternalToolsCommand;
import com.github.marook.eclipse_remote_control.command.command.ExternalToolsCommandRunMode;

public class ExecuteCommandParser extends CommandParser {

	@Override
	public String getName() {
		return "execute_command";
	}

	@Override
	public String getUsage() {
		return "[command memento] [[run mode]] (run mode can be RUN, DEBUG or PROFILE)";
	}

	@Override
	public Command parseCommand(final String[] args) {
		if(args.length < 2 || args.length > 3){
			throw new CommandParseException("Expected 1 or 2 argument.");
		}
		
		final ExternalToolsCommand cmd = new ExternalToolsCommand();
		cmd.setConfigurationName(args[1]);
		
		if(args.length >= 3){
			cmd.setRunMode(parseRunMode(args[2]));
		}
		
		return cmd;
	}

	private ExternalToolsCommandRunMode parseRunMode(final String runModeName) {
		for(final ExternalToolsCommandRunMode runMode : ExternalToolsCommandRunMode.values()){
			if(runMode.name().equals(runModeName)){
				return runMode;
			}
		}
		
		throw new CommandParseException("Unknown run mode: " + runModeName);
	}

}
