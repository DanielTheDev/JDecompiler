package io.github.danielthedev.jdecompiler.constants;

import io.github.danielthedev.jdecompiler.Reference;

public class CONSTANT_Methodref {

	private final Reference<CONSTANT_Class> classIndex;
	private final Reference<CONSTANT_NameAndType> nameAndTypeIndex;
	
	public CONSTANT_Methodref(Reference<CONSTANT_Class> classIndex, Reference<CONSTANT_NameAndType> nameAndTypeIndex) {
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	
}
