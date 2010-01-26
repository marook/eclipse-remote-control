package com.github.marook.eclipse_remote_control.client;

import java.io.File;
import java.io.ObjectOutput;
import java.io.PrintStream;
import java.net.Socket;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.command.command.OpenFileCommand;
import com.github.marook.eclipse_remote_control.command.serialize.ICommandEncoder;
import com.github.marook.eclipse_remote_control.command.serialize.impl.serialize.SerializeCommandEncoder;

public class Client {

	private static void printUsage(final PrintStream out){
		out.println("Possible commands are:");
		out.println("  open_file [file]");
	}
	
	private static void fireCommand(final Command cmd){
		try{
			final Socket s = new Socket("localhost", 53343);
			
			final ICommandEncoder ce = new SerializeCommandEncoder();
			final ObjectOutput cmdEncoder = ce.createEncoder(s.getOutputStream());
			cmdEncoder.writeObject(cmd);
			
			cmdEncoder.flush();
			cmdEncoder.close();
		}
		catch(final Exception e){
			e.printStackTrace(System.err);
			
			System.exit(1);
		}
	}
	
	public static void main(final String[] args) {
		if(args.length < 1){
			printUsage(System.err);
			
			System.exit(1);
			
			return;
		}
		
		final String command = args[0];
		if("open_file".equals(command)){
			if(args.length < 2){
				printUsage(System.err);
				
				System.exit(1);
				
				return;
			}
			
			final OpenFileCommand cmd = new OpenFileCommand();
			cmd.setFile(new File(args[1]));
			
			fireCommand(cmd);
		}
		else{
			System.err.println("Unknown command: " + command);
			
			printUsage(System.err);
			
			System.exit(1);
			
			return;
		}
	}
	
}
