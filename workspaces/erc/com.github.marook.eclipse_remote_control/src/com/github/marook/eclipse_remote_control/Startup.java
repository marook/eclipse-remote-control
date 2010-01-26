package com.github.marook.eclipse_remote_control;

import org.eclipse.ui.IStartup;

public class Startup implements IStartup {

	@Override
	public void earlyStartup() {
		final CommandServerWorker w = new CommandServerWorker();
		w.start();
	}

}
