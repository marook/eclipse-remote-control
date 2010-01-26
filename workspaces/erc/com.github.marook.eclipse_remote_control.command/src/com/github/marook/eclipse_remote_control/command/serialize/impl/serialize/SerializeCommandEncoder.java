package com.github.marook.eclipse_remote_control.command.serialize.impl.serialize;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.github.marook.eclipse_remote_control.command.serialize.ICommandEncoder;

public class SerializeCommandEncoder implements ICommandEncoder {

	@Override
	public ObjectOutput createEncoder(OutputStream out) throws IOException {
		return new ObjectOutputStream(out);
	}

}
