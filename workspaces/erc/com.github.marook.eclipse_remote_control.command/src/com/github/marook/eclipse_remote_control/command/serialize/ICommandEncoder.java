package com.github.marook.eclipse_remote_control.command.serialize;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;

public interface ICommandEncoder {
	
	public ObjectOutput createEncoder(OutputStream out) throws IOException;
	
}
