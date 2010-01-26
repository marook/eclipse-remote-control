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

package com.github.marook.eclipse_remote_control.run.runner.impl.simple;

import java.util.HashMap;
import java.util.Map;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.run.runner.ICommandRunner;
import com.github.marook.eclipse_remote_control.run.runner.impl.simple.atom.AbstractAtomCommandRunner;
import com.github.marook.eclipse_remote_control.run.runner.impl.simple.atom.OpenFileCommandRunner;

public class SimpleCommandRunner implements ICommandRunner {
	
	private static final Map<String, AbstractAtomCommandRunner> ATOM_RUNNERS =
		new HashMap<String, AbstractAtomCommandRunner>();
	
	private static void putAtomRunner(final AbstractAtomCommandRunner cr){
		ATOM_RUNNERS.put(cr.getCommandId(), cr);
	}
	
	static {
		putAtomRunner(new OpenFileCommandRunner());
	}

	@Override
	public void execute(final Command cmd) throws Exception {
		final ICommandRunner cr = ATOM_RUNNERS.get(cmd.getCommandId());
		
		if(cr == null){
			// TODO log that we can't handle this command
		}
		
		cr.execute(cmd);
	}

}
