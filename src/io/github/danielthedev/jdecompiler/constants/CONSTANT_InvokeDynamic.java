package io.github.danielthedev.jdecompiler.constants;

import io.github.danielthedev.jdecompiler.Reference;

public class CONSTANT_InvokeDynamic {

	private short ref;
	private final Reference<CONSTANT_NameAndType> nameAndType;
	
	public CONSTANT_InvokeDynamic(short ref, Reference<CONSTANT_NameAndType> nameAndType) {
		this.ref = ref;
		this.nameAndType = nameAndType;
	}
	
	
	
}
