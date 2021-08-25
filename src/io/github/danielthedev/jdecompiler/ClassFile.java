package io.github.danielthedev.jdecompiler;

import io.github.danielthedev.jdecompiler.constants.CONSTANT_Class;
import io.github.danielthedev.jdecompiler.constants.Constant;

public class ClassFile {

	private int magic;
	private short minor_version;
	private short major_version;
	
	private Constant[] contants;
	private AccessFlag[] access_flags;
	
	private CONSTANT_Class this_class;
	private CONSTANT_Class super_class;
	
	private CONSTANT_Class[] interfaces;
}
