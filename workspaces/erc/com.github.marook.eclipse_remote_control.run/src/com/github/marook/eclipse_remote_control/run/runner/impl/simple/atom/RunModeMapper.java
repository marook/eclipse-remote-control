package com.github.marook.eclipse_remote_control.run.runner.impl.simple.atom;

import org.eclipse.debug.core.ILaunchManager;

import com.github.marook.eclipse_remote_control.command.command.ExternalToolsCommandRunMode;

public class RunModeMapper {

	public String getRunMode(final ExternalToolsCommandRunMode runMode) {
		switch (runMode) {
		case RUN:
			return ILaunchManager.RUN_MODE;
		case DEBUG:
			return ILaunchManager.DEBUG_MODE;
		case PROFILE:
			return ILaunchManager.PROFILE_MODE;
		default:
			throw new IllegalArgumentException("Unknown runMode: " + runMode);
		}
	}

}
