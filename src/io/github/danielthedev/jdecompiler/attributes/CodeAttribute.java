package io.github.danielthedev.jdecompiler.attributes;

import io.github.danielthedev.jdecompiler.attributes.exceptions.Exception;

public class CodeAttribute {

	private int max_stack;
	private int max_locals;
	private byte[] code;
	
	private Exception[] exceptions;
	private Attribute[] attributes;
}
