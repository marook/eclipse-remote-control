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

package com.github.marook.eclipse_remote_control.run.runner.impl.simple.atom;

import java.io.File;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.github.marook.eclipse_remote_control.command.command.Command;
import com.github.marook.eclipse_remote_control.command.command.OpenFileCommand;

public class OpenFileCommandRunner extends AbstractAtomCommandRunner {

	public OpenFileCommandRunner() {
		super(OpenFileCommand.ID);
	}

	@Override
	protected void internalExecute(final Command cmd) throws Exception {
		final OpenFileCommand c = (OpenFileCommand) cmd;
		
		final File f = new File(c.getFileName());
		
		if(!f.exists()){
			// TODO abort / show message?
			
			return;
		}
		
		if(!f.isFile()){
			// TODO abort / show message?
			
			return;
		}
		
		final IFileStore fileStore =
			EFS.getLocalFileSystem().getStore(f.toURI());
	    final IWorkbench workbench = PlatformUI.getWorkbench();
	    workbench.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				final IWorkbenchWindow activeWorkbenchWindow =
					workbench.getActiveWorkbenchWindow();
				final IWorkbenchPage page =
			    	activeWorkbenchWindow.getActivePage();
			 
			    try {
					IDE.openEditorOnFileStore(page, fileStore);
				}
			    catch(final PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
