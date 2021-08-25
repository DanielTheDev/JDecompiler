package io.github.danielthedev.jdecompiler.attributes.exceptions;

import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.attributes.Attribute;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_Class;

public class Exception {

	private int start_pc;
	private int end_pc;
	private int handler_pc;
	private Reference<CONSTANT_Class> catch_type;
	
}
