package io.github.danielthedev.jdecompiler.attributes;

import io.github.danielthedev.jdecompiler.constants.Constant;
import io.github.danielthedev.jdecompiler.constants.Constant.Utf8;

public class Attribute {

	private Constant.Utf8 attribute_name_index;
	private byte[] info;
	
	public Attribute(Utf8 attribute_name_index, byte[] info) {
		this.attribute_name_index = attribute_name_index;
		this.info = info;
	}

	@Override
	public String toString() {
		return "Attribute [attribute_name_index=" + attribute_name_index + "]";
	}

	
}
