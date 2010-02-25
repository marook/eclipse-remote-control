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

package com.github.marook.eclipse_remote_control.client;

import java.io.ObjectOutput;
import java.io.PrintStream;
import java.net.Socket;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.command.command.ExternalToolsCommand;
import com.github.marook.eclipse_remote_control.command.command.OpenFileCommand;
import com.github.marook.eclipse_remote_control.command.serialize.ICommandEncoder;
import com.github.marook.eclipse_remote_control.command.serialize.impl.serialize.SerializeCommandEncoder;

public class Client {

	private static void printUsage(final PrintStream out){
		out.println("Possible commands are:");
		out.println("  open_file [file]");
		out.println("  execute_command [command memento]");
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
			cmd.setFileName(args[1]);
			
			fireCommand(cmd);
		}
		else if("execute_command".equals(command)){
			if(args.length < 2){
				printUsage(System.err);
				
				System.exit(1);
				
				return;
			}
			
			final ExternalToolsCommand cmd = new ExternalToolsCommand();
			cmd.setConfigurationMemento(args[1]);
			
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
