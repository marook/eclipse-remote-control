package com.github.marook.eclipse_remote_control.command.serialize.impl.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import com.github.marook.eclipse_remote_control.command.serialize.ICommandDecoder;

public class SerializeCommandDecoder implements ICommandDecoder {

	@Override
	public ObjectInput createDecoder(InputStream in) throws IOException {
		return new ObjectInputStream(in);
	}

}
