package io.github.danielthedev.jdecompiler.constants;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.constants.enums.MethodHandle;

public class CONSTANT_MethodHandle {

	private final MethodHandle handle;
	private final Reference<Constant> reference;
	
	public CONSTANT_MethodHandle(MethodHandle handle, Reference<Constant> reference) {
		this.handle = handle;
		this.reference = reference;
	}
	
}
