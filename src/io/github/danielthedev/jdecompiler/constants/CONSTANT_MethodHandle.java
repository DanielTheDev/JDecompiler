package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.ReferenceLink;
import io.github.danielthedev.jdecompiler.classes.ClassInputStream;
import io.github.danielthedev.jdecompiler.constants.enums.MethodHandle;

public class CONSTANT_MethodHandle extends Constant implements ReferenceLink {

	public static final short TAG = 15;
	
	private final MethodHandle handle;
	private final Reference<Constant> reference;
	
	public CONSTANT_MethodHandle(MethodHandle handle, Reference<Constant> reference) {
		super(TAG);
		this.handle = handle;
		this.reference = reference;
	}
	
	public CONSTANT_MethodHandle(ClassInputStream in) throws IOException {
		super(TAG);
		this.handle = MethodHandle.getByID(in.readUnsignedByte());
		this.reference = new Reference<Constant>(in.readUnsignedShort());
	}

	public MethodHandle getHandle() {
		return handle;
	}

	public Reference<Constant> getReference() {
		return reference;
	}
	
	@Override
	public Reference<?>[] getReferences() {
		return new Reference<?>[] {this.reference};
	}

	@Override
	public String toString() {
		return "CONSTANT_MethodHandle [handle=" + handle + ", reference=" + reference + "]";
	}
	
	
}
