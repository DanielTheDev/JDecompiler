package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;

import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_Float extends Constant {

	public static final short TAG = 4;
	
	private final float value;

	public CONSTANT_Float(float value) {
		super(TAG);
		this.value = value;
	}
	
	public CONSTANT_Float(ClassInputStream in) throws IOException {
		super(TAG);
		this.value = in.readFloat();
	}

	public float getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CONSTANT_Float [value=" + value + "]";
	}
	
	
	
}
