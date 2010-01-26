package com.github.marook.eclipse_remote_control.command.command;

import java.io.Serializable;

public abstract class Command implements Serializable {
	
	private static final long serialVersionUID = 3636069047144478112L;
	
	private final String commandId;
	
	protected Command(final String commandId){
		if(commandId == null){
			throw new NullPointerException();
		}
		
		this.commandId = commandId;
	}
	
	public String getCommandId(){
		return commandId;
	}

}
