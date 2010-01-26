package com.github.marook.eclipse_remote_control.run.runner.impl.simple.atom;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.run.runner.ICommandRunner;

public abstract class AbstractAtomCommandRunner implements ICommandRunner {
	
	private final String commandId;
	
	protected AbstractAtomCommandRunner(final String commandId){
		if(commandId == null){
			throw new NullPointerException();
		}
		
		this.commandId = commandId;
	}
	
	public String getCommandId(){
		return commandId;
	}
	
	protected abstract void internalExecute(final Command cmd) throws Exception;

	@Override
	public void execute(final Command cmd) throws Exception {
		if(!commandId.equals(cmd.getCommandId())){
			throw new IllegalArgumentException("Can't execute command of type " + cmd.getClass());
		}
		
		internalExecute(cmd);
	}

}
