package io.github.danielthedev.jdecompiler.constants;

import io.github.danielthedev.jdecompiler.ClassInputStream;
import io.github.danielthedev.jdecompiler.Reference;

public class CONSTANT_MethodType extends Constant {

	private final Reference<CONSTANT_UTF8> description;

	public CONSTANT_MethodType(Reference<CONSTANT_UTF8> description) {
		this.description = description;
	}

	public CONSTANT_MethodType(ClassInputStream in) {
		this.description = new Reference<>(in.read(2));
	}
}
