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
