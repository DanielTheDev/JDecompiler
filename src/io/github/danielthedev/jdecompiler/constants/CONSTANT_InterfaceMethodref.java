package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.ReferenceLink;
import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_InterfaceMethodref extends Constant implements ReferenceLink {

	public static final short TAG = 11;
	
	private final Reference<CONSTANT_Class> classIndex;
	private final Reference<CONSTANT_NameAndType> nameAndTypeIndex;
	
	public CONSTANT_InterfaceMethodref(Reference<CONSTANT_Class> classIndex, Reference<CONSTANT_NameAndType> nameAndTypeIndex) {
		super(TAG);
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	public CONSTANT_InterfaceMethodref(ClassInputStream in) throws IOException {
		super(TAG);
		this.classIndex = new Reference<CONSTANT_Class>(in.readUnsignedShort());
		this.nameAndTypeIndex = new Reference<CONSTANT_NameAndType>(in.readUnsignedShort());
	}

	public Reference<CONSTANT_Class> getClassIndex() {
		return classIndex;
	}

	public Reference<CONSTANT_NameAndType> getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

	@Override
	public Reference<?>[] getReferences() {
		return new Reference<?>[] {this.classIndex, this.nameAndTypeIndex};
	}
	
	@Override
	public String toString() {
		return "CONSTANT_InterfaceMethodref [classIndex=" + classIndex + ", nameAndTypeIndex=" + nameAndTypeIndex + "]";
	}
	
	
}
