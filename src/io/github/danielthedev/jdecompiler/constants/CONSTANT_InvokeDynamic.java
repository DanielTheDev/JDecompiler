package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;
import java.sql.Ref;
import java.util.Arrays;
import java.util.Collection;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.ReferenceLink;
import io.github.danielthedev.jdecompiler.classes.ClassInputStream;

public class CONSTANT_InvokeDynamic extends Constant implements ReferenceLink {

	public static final short TAG = 18;
	
	private final Reference<Object[]> bootstrapMethodAttribute;
	private final Reference<CONSTANT_NameAndType> nameAndType;
	
	public CONSTANT_InvokeDynamic(Reference<Object[]> bootstrapMethodAttribute, Reference<CONSTANT_NameAndType> nameAndType) {
		super(TAG);
		this.bootstrapMethodAttribute = bootstrapMethodAttribute;
		this.nameAndType = nameAndType;
	}

	public CONSTANT_InvokeDynamic(ClassInputStream in) throws IOException {
		super(TAG);
		this.bootstrapMethodAttribute = new Reference<Object[]>(in.readUnsignedShort(), true);
		this.nameAndType = new Reference<CONSTANT_NameAndType>(in.readUnsignedShort());
	}

	public Reference<Object[]> getBootstrapMethodAttribute() {
		return bootstrapMethodAttribute;
	}

	public Reference<CONSTANT_NameAndType> getNameAndType() {
		return nameAndType;
	}
	
	@Override
	public Reference<?>[] getReferences() {
		return new Reference<?>[] {this.bootstrapMethodAttribute, this.nameAndType};
	}

	@Override
	public String toString() {
		return "CONSTANT_InvokeDynamic [bootstrapMethodAttribute=" + bootstrapMethodAttribute + ", nameAndType="
				+ nameAndType + "]";
	}
	
	
}
