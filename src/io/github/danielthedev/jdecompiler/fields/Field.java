package io.github.danielthedev.jdecompiler.fields;

import java.util.List;

import io.github.danielthedev.jdecompiler.AccessFlag;
import io.github.danielthedev.jdecompiler.attributes.Attribute;
import io.github.danielthedev.jdecompiler.constants.Constant;
import io.github.danielthedev.jdecompiler.constants.Constant.Utf8;

public class Field {

	private List<AccessFlag.Field> access_flags;
	
	private Constant.Utf8 this_class;
	private Constant.Utf8 descriptor_index;
	
	private List<? extends Attribute> attributes;

	public Field(List<AccessFlag.Field> access_flags, Utf8 this_class, Utf8 descriptor_index, List<? extends Attribute> attributes) {
		this.access_flags = access_flags;
		this.this_class = this_class;
		this.descriptor_index = descriptor_index;
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "Field [access_flags=" + access_flags + ", this_class=" + this_class + ", descriptor_index="
				+ descriptor_index + ", attributes=" + attributes + "]";
	}
	
	
}
