package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_String extends Constant {

	public static final short TAG = 8;
	
	private final Reference<CONSTANT_UTF8> string;

	public CONSTANT_String(Reference<CONSTANT_UTF8> string) {
		super(TAG);
		this.string = string;
	}

	public CONSTANT_String(ClassInputStream in) throws IOException {
		super(TAG);
		this.string = new Reference<CONSTANT_UTF8>(in.readUnsignedShort());
	}

	public Reference<CONSTANT_UTF8> getString() {
		return string;
	}

	@Override
	public String toString() {
		return "CONSTANT_String [string=" + string + "]";
	}
	
	
}
