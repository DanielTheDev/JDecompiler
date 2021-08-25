package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;

import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_Long extends Constant {

	public static final short TAG = 5;
	
	private final long value;

	public CONSTANT_Long(long value) {
		super(TAG);
		this.value = value;
	}
	
	public CONSTANT_Long(ClassInputStream in) throws IOException {
		super(TAG);
		long high_bytes = in.readUnsignedInt();
		long low_bytes = in.readUnsignedInt();
		this.value = ((long) high_bytes << 32) + low_bytes;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CONSTANT_Long [value=" + value + "]";
	}
	
	
}
