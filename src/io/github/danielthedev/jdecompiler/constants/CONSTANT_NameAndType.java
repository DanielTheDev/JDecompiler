package io.github.danielthedev.jdecompiler.constants;

import io.github.danielthedev.jdecompiler.Reference;

public class CONSTANT_NameAndType {

	private final Reference<CONSTANT_UTF8> name;
	private final Reference<CONSTANT_UTF8> description;
	
	public CONSTANT_NameAndType(Reference<CONSTANT_UTF8> name, Reference<CONSTANT_UTF8> description) {
		this.name = name;
		this.description = description;
	}
	
	
	
}
