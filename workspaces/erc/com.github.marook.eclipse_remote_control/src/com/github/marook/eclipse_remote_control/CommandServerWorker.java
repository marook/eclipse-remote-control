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

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.marook.eclipse_remote_control.command.serialize.ICommandDecoder;
import com.github.marook.eclipse_remote_control.command.serialize.impl.serialize.SerializeCommandDecoder;
import com.github.marook.eclipse_remote_control.run.runner.ICommandRunner;
import com.github.marook.eclipse_remote_control.run.runner.impl.simple.SimpleCommandRunner;

public class CommandServerWorker extends Thread {
	
	private static final Logger LOGGER = Logger.getLogger(CommandServerWorker.class.getName());
	
	private final ICommandDecoder commandDecoder =
		new SerializeCommandDecoder();
	
	private final ICommandRunner commandRunner =
		new SimpleCommandRunner();
	
	private void launchClientWorker(final Socket s){
		final CommandWorker w =
			new CommandWorker(s, commandDecoder, commandRunner);
		
		w.start();
	}
	
	@Override
	public void run() {
		try{
			final ServerSocket server = new ServerSocket(53343);
			
			LOGGER.log(Level.INFO, "Command server worker running.");
			
			while(true){
				final Socket client = server.accept();
				
				launchClientWorker(client);
			}
		}
		catch(final Exception e){
			LOGGER.log(Level.SEVERE, "Command server aborted.", e);
		}
	}
	
}
