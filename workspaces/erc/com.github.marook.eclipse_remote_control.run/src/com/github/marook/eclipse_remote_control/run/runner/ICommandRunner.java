package com.github.marook.eclipse_remote_control.run.runner;

import com.github.marook.eclipse_remote_control.command.command.Command;

public interface ICommandRunner {
	
	public void execute(Command cmd) throws Exception;

}
