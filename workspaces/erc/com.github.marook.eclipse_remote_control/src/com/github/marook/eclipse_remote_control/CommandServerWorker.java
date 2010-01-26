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
