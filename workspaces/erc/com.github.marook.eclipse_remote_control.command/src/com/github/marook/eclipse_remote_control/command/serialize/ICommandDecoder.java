package com.github.marook.eclipse_remote_control.command.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;

public interface ICommandDecoder {
	
	public ObjectInput createDecoder(InputStream in) throws IOException;

}
