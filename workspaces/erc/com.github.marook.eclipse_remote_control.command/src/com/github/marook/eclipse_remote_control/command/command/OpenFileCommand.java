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

package com.github.marook.eclipse_remote_control.command.command;


public class OpenFileCommand extends Command {
	
	private static final long serialVersionUID = 1165973614269252565L;

	public static final String ID = OpenFileCommand.class.getName();
	
	private String fileName;
	private int lineNumber;
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public OpenFileCommand() {
		super(ID);
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}
	
}
