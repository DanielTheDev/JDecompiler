package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;
import java.sql.Ref;
import java.util.Arrays;
import java.util.Collection;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.ReferenceLink;
import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_MethodType extends Constant implements ReferenceLink {

	public static final short TAG = 16;
	
	private final Reference<CONSTANT_UTF8> description;

	public CONSTANT_MethodType(Reference<CONSTANT_UTF8> description) {
		super(TAG);
		this.description = description;
	}

	public CONSTANT_MethodType(ClassInputStream in) throws IOException {
		super(TAG);
		this.description = new Reference<>(in.readUnsignedShort());
	}

	public Reference<CONSTANT_UTF8> getDescription() {
		return description;
	}
	
	@Override
	public Reference<?>[] getReferences() {
		return new Reference<?>[] {this.description};
	}

	@Override
	public String toString() {
		return "CONSTANT_MethodType [description=" + description + "]";
	}
	
	
}
