package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;

import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_Double extends Constant {

	public static final short TAG = 6;
	
	private final double value;

	public CONSTANT_Double(double value) {
		super(TAG);
		this.value = value;
	}
	
	public CONSTANT_Double(ClassInputStream in) throws IOException {
		super(TAG);
		long high_bytes = in.readUnsignedInt();
		long low_bytes = in.readUnsignedInt();
		this.value = ((long) high_bytes << 32) + low_bytes;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CONSTANT_Double [value=" + value + "]";
	}
	
}
