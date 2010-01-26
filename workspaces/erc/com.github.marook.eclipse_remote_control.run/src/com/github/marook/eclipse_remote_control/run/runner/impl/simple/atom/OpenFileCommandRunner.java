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
		
		final File f = c.getFile();
		
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
