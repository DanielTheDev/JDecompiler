package io.github.danielthedev.jdecompiler.fields;

import java.util.Arrays;

import io.github.danielthedev.jdecompiler.AccessFlag;
import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_UTF8;

public class Field {

	private final AccessFlag[] accessflags;
	private final Reference<CONSTANT_UTF8> name;
	private final Reference<CONSTANT_UTF8> description;
	
	public Field(AccessFlag[] accessflags, Reference<CONSTANT_UTF8> name, Reference<CONSTANT_UTF8> description) {
		this.accessflags = accessflags;
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Field [accessflags=" + Arrays.toString(accessflags) + ", name=" + name + ", description=" + description
				+ "]";
	}
	
	
}
