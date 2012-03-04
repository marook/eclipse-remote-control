package com.github.marook.eclipse_remote_control.client.parser;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.command.command.OpenFileCommand;

public class OpenFileCommandParser extends CommandParser {

	public String getName() {
		return "open_file";
	}

	public String getUsage() {
		return "[file] [[line number]]";
	}

	private static int parseLineNumber(final String lineNumberStr) {
		try {
			return Integer.parseInt(lineNumberStr);
		} catch (NumberFormatException e) {
			throw new CommandParseException("Can't parse line number: " + lineNumberStr);
		}
	}
	
	public Command parseCommand(final String[] args) {
		if (args.length < 2 || args.length > 3) {
			throw new CommandParseException("Expecting 1 or 2 arguments");
		}

		final int lineNumber = args.length < 3 ? 1 : parseLineNumber(args[2]);
		final String fileName = args[1];

		final OpenFileCommand cmd = new OpenFileCommand();
		cmd.setFileName(fileName);
		cmd.setLineNumber(lineNumber);

		return cmd;
	}

}
