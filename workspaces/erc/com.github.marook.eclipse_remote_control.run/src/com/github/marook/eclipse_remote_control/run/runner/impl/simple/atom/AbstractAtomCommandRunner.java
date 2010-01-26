/*
 * Copyright 2010 Markus Pielmeier
 *
 * This file is part of eclipse remote control.
 *
 * eclipse remote control is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * eclipse remote control is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with eclipse remote control.  If not, see <http://www.gnu.org/licenses/>.
 */

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
