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

public class ExternalToolsCommand extends Command {
	
	private static final long serialVersionUID = -2445519253513913731L;

	public static final String ID = ExternalToolsCommand.class.getName();
	
	private String configurationName;
	
	private ExternalToolsCommandRunMode runMode = ExternalToolsCommandRunMode.RUN;
	
	public ExternalToolsCommand() {
		super(ID);
	}
	
	public String getConfigurationName() {
		return configurationName;
	}
	
	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
	
	public ExternalToolsCommandRunMode getRunMode() {
		return runMode;
	}
	
	public void setRunMode(ExternalToolsCommandRunMode runMode) {
		this.runMode = runMode;
	}

}
