package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.ReferenceLink;
import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_Class extends Constant implements ReferenceLink {
	
	public static final short TAG = 7;
	
	private final Reference<CONSTANT_UTF8> name;

	public CONSTANT_Class(Reference<CONSTANT_UTF8> name) {
		super(TAG);
		this.name = name;
	}
	
	public CONSTANT_Class(ClassInputStream in) throws IOException {
		super(TAG);
		this.name = new Reference<CONSTANT_UTF8>(in.readUnsignedShort());
	}

	public Reference<CONSTANT_UTF8> getName() {
		return name;
	}

	@Override
	public String toString() {
		return "CONSTANT_Class [name=" + name + "]";
	}

	@Override
	public Reference<?>[] getReferences() {
		return new Reference<?>[] {this.name};
	}
}
