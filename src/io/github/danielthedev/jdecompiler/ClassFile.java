package io.github.danielthedev.jdecompiler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import io.github.danielthedev.jdecompiler.attributes.Attribute;
import io.github.danielthedev.jdecompiler.constants.Constant;
import io.github.danielthedev.jdecompiler.constants.ConstantEnum;
import io.github.danielthedev.jdecompiler.fields.Field;
import io.github.danielthedev.jdecompiler.methods.Method;
import io.github.danielthedev.jdecompiler.constants.Constant.Class;

public class ClassFile {

	private long magic;
	private int minor_version;
	private int major_version;
	
	private List<? extends Constant> constant_pool;
	
	private List<AccessFlag.Class> access_flags;
	
	private Constant.Class this_class;
	private Constant.Class super_class;
	
	private List<Constant.Class> interfaces;
	
	private List<Field> fields;
	private List<Method> methods;
	
	private List<Attribute> attributes;
	
	public ClassFile(JClassInputStream in) throws IOException {
		this.magic = in.readUnsignedInt();
		this.minor_version = in.readUnsignedShort();
		this.major_version = in.readUnsignedShort();
		
		int constant_pool_count = in.readUnsignedShort() - 1;
		
		this.constant_pool = new ArrayList<>(constant_pool_count);
		
		for(int index = 0; index < constant_pool_count; index++) {
			ConstantEnum constantEnum = ConstantEnum.getById(in.readUnsignedByte());
			if(constantEnum.isSkip()) index++;
			this.constant_pool.add(constantEnum.instantiate(in));
		}
		
		this.access_flags = AccessFlag.convertToList(AccessFlag.Class.class, in.readUnsignedShort());	
		this.this_class = this.getConstant(in.readUnsignedShort());
		
		
		this.super_class = this.getConstant(in.readUnsignedShort());
		
		int interfaces_count = in.readUnsignedShort();
		
		this.interfaces = new ArrayList<Constant.Class>(interfaces_count);
		
		for(int index = 0; index < interfaces_count; index++) {
			this.interfaces.add(this.getConstant(in.readUnsignedShort()));
		}
		
		int fields_count = in.readUnsignedShort();
		
		this.fields = new ArrayList<Field>(fields_count);
		
		for(int index = 0; index < fields_count; index++) {
			
			List<AccessFlag.Field> access_flags = AccessFlag.convertToList(AccessFlag.Field.class, in.readUnsignedShort());
			Constant.Utf8 name_index = this.getConstant(in.readUnsignedShort());
			Constant.Utf8 descriptor_index = this.getConstant(in.readUnsignedShort());
			
			int attributes_count = in.readUnsignedShort();
			
			List<Attribute> attributes = new ArrayList<Attribute>(attributes_count);

			for(int attr_index = 0; attr_index < attributes_count; attr_index++) {
				
				Constant.Utf8 attribute_name_index = this.getConstant(in.readUnsignedShort());
				byte[] info = in.readNBytes(in.readInteger());
				
				attributes.add(new Attribute(attribute_name_index, info));
			}
			
			
			this.fields.add(new Field(access_flags, name_index, descriptor_index, attributes));
		}
		
		int methods_count = in.readUnsignedShort();
		
		this.methods = new ArrayList<Method>(methods_count);
		
		for(int index = 0; index < methods_count; index++) {
			
			List<AccessFlag.Method> access_flags = AccessFlag.convertToList(AccessFlag.Method.class, in.readUnsignedShort());
			Constant.Utf8 name_index = this.getConstant(in.readUnsignedShort());
			Constant.Utf8 descriptor_index = this.getConstant(in.readUnsignedShort());
			
			int attributes_count = in.readUnsignedShort();
			
			List<Attribute> attributes = new ArrayList<Attribute>(attributes_count);

			for(int attr_index = 0; attr_index < attributes_count; attr_index++) {
				
				Constant.Utf8 attribute_name_index = this.getConstant(in.readUnsignedShort());
				byte[] info = in.readNBytes(in.readInteger());
				
				attributes.add(new Attribute(attribute_name_index, info));
			}
			
			
			this.methods.add(new Method(access_flags, name_index, descriptor_index, attributes));
		}
		
		
		int attributes_count = in.readUnsignedShort();

		this.attributes = new ArrayList<Attribute>(attributes_count);
		
		for(int index = 0; index < attributes_count; index++) {
			
			Constant.Utf8 attribute_name_index = this.getConstant(in.readUnsignedShort());
			byte[] info = in.readNBytes(in.readInteger());
			
			this.attributes.add(new Attribute(attribute_name_index, info));
		}

	}
	
	public <K extends Constant> K getConstant(int index) {
		if(index == 0) return null;
		return (K) this.constant_pool.get(index - 1);
	}
	
	public void printClass() {
		
		StringBuilder b = new StringBuilder();
		
		String full = ((Constant.Utf8)this.getConstant(this.this_class.getName_index())).getString();
				
		String[] parts = full.split("/");
		String pack = full.substring(0, full.length() - parts[parts.length-1].length() - 1).replace("/", ".");
		
		b.append("package ").append(pack).append(";\n\n");
		
		
		this.access_flags.forEach(s->b.append(s.name().toLowerCase()).append(" "));
		
		if(!(this.access_flags.contains(AccessFlag.Class.INTERFACE) || this.access_flags.contains(AccessFlag.Class.ENUM))) {
			b.append("class ").append(parts[parts.length-1]);
		}
		
		if(this.super_class != null) {
			b.append(" extends " + ((Constant.Utf8)this.getConstant(this.super_class.getName_index())).getString());
		}
		
		System.out.println(b);
		
	}
}
