package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_UTF8 extends Constant {

	public static final short TAG = 1;
	
	private final String name;

	public CONSTANT_UTF8(String name) {
		super(TAG);
		this.name = name;
	}
	
	public CONSTANT_UTF8(ClassInputStream in) throws IOException {
		super(TAG);
		int length = in.readUnsignedShort();
		this.name = new String(in.readNBytes(length), StandardCharsets.UTF_8);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "CONSTANT_UTF8 [name=" + name + "]";
	}
	
	
}
