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

package com.github.marook.eclipse_remote_control;

import java.io.ObjectInput;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.command.serialize.ICommandDecoder;
import com.github.marook.eclipse_remote_control.run.runner.ICommandRunner;

public class CommandWorker extends Thread {
	
	private final static Logger LOGGER =
		Logger.getLogger(CommandWorker.class.getName());
	
	private final Socket socket;
	
	private final ICommandDecoder commandDecoder;
	
	private final ICommandRunner commandRunner;
	
	public CommandWorker(final Socket socket,
			final ICommandDecoder commandDecoder,
			final ICommandRunner commandRunner){
		if(socket == null){
			throw new NullPointerException();
		}
		if(commandDecoder == null){
			throw new NullPointerException();
		}
		if(commandRunner == null){
			throw new NullPointerException();
		}
		
		this.socket = socket;
		this.commandDecoder = commandDecoder;
		this.commandRunner = commandRunner;
	}
	
	@Override
	public void run() {
		try{
			final ObjectInput cmdIn =
				commandDecoder.createDecoder(socket.getInputStream());
			
			while(true){
				final Command cmd = (Command) cmdIn.readObject();
				
				commandRunner.execute(cmd);
			}
		}
		catch(final Exception e){
			LOGGER.log(Level.SEVERE, "Command worker aborted.", e);
		}
	}

}
