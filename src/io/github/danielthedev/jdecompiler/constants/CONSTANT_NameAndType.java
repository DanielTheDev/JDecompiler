package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.ReferenceLink;
import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_NameAndType extends Constant implements ReferenceLink {

	public static final short TAG = 12;
	
	private final Reference<CONSTANT_UTF8> name;
	private final Reference<CONSTANT_UTF8> description;
	
	public CONSTANT_NameAndType(Reference<CONSTANT_UTF8> name, Reference<CONSTANT_UTF8> description) {
		super(TAG);
		this.name = name;
		this.description = description;
	}
	
	public CONSTANT_NameAndType(ClassInputStream in) throws IOException {
		super(TAG);
		this.name = new Reference<CONSTANT_UTF8>(in.readUnsignedShort());
		this.description = new Reference<CONSTANT_UTF8>(in.readUnsignedShort());
	}

	public Reference<CONSTANT_UTF8> getName() {
		return name;
	}

	public Reference<CONSTANT_UTF8> getDescription() {
		return description;
	}

	@Override
	public Reference<?>[] getReferences() {
		return new Reference<?>[] {this.name, this.description};
	}
	
	@Override
	public String toString() {
		return "CONSTANT_NameAndType [name=" + name + ", description=" + description + "]";
	}
	
	
}
