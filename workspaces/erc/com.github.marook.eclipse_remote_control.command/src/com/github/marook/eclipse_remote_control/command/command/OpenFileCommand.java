package com.github.marook.eclipse_remote_control.command.command;

import java.io.File;

public class OpenFileCommand extends Command {
	
	private static final long serialVersionUID = 8033220711744562095L;

	public static final String ID = OpenFileCommand.class.getName();
	
	private File file;
	
	public OpenFileCommand() {
		super(ID);
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
}
