package com.github.marook.eclipse_remote_control.client.parser;

import java.util.Arrays;

import com.github.marook.eclipse_remote_control.command.command.Command;

public abstract class CommandParser {

	public abstract String getName();

	public abstract String getUsage();

	/**
	 * @throws CommandParseException
	 *             Is thrown if command can not be parsed from supplied
	 *             arguments.
	 */
	public abstract Command parseCommand(String[] args);

	protected void handleCommandRunError(final String[] args, final Exception e) {
		throw new RuntimeException(
				"Can't run command " + Arrays.toString(args), e);
	}

}
