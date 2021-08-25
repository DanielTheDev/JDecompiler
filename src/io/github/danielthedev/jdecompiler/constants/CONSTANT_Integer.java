package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;

import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_Integer extends Constant {

	public static final short TAG = 3;
	
	private final int value;

	public CONSTANT_Integer(int value) {
		super(TAG);
		this.value = value;
	}
	
	public CONSTANT_Integer(ClassInputStream in) throws IOException {
		super(TAG);
		this.value = in.readInteger();
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CONSTANT_Integer [value=" + value + "]";
	}
	
	
}
