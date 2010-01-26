
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
