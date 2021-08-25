package io.github.danielthedev.jdecompiler.classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import io.github.danielthedev.jdecompiler.AccessFlag;
import io.github.danielthedev.jdecompiler.Reference;
import io.github.danielthedev.jdecompiler.ReferenceLink;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_Class;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_Double;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_Fieldref;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_Float;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_Integer;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_InterfaceMethodref;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_InvokeDynamic;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_Long;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_MethodHandle;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_MethodType;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_Methodref;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_NameAndType;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_String;
import io.github.danielthedev.jdecompiler.constants.CONSTANT_UTF8;
import io.github.danielthedev.jdecompiler.constants.Constant;
import io.github.danielthedev.jdecompiler.fields.Field;

public class ClassFile {

	private long magic;
	private int minor_version;
	private int major_version;
	
	private Constant[] constants;
	private AccessFlag[] access_flags;
	
	private CONSTANT_Class this_class;
	private CONSTANT_Class super_class;
	
	private CONSTANT_Class[] interfaces;
	
	
	
	public ClassFile(long magic, int minor_version, int major_version, Constant[] constants, AccessFlag[] access_flags,
			CONSTANT_Class this_class, CONSTANT_Class super_class, CONSTANT_Class[] interfaces) {
		super();
		this.magic = magic;
		this.minor_version = minor_version;
		this.major_version = major_version;
		this.constants = constants;
		this.access_flags = access_flags;
		this.this_class = this_class;
		this.super_class = super_class;
		this.interfaces = interfaces;
	}



	public static ClassFile fromInputStream(InputStream input) throws IOException {
		long magic = 0;
		int minor_version = 0;
		int major_version = 0;
		Constant[] constants = null;
		AccessFlag[] access_flags = null;
		CONSTANT_Class this_class = null;
		CONSTANT_Class super_class = null;
		CONSTANT_Class[] interfaces = null;
		
		try(ClassInputStream in = new ClassInputStream(input)) {		
			magic = in.readUnsignedInt();
			if(magic == 0xCAFEBABE) {
				minor_version = in.readUnsignedShort();
				major_version = in.readUnsignedShort();
				int constant_pool_count = in.readUnsignedShort() - 1;
				constants = new Constant[constant_pool_count];
				
				for(int t = 0; t < constant_pool_count; t++) {
					
					short tag = in.readUnsignedByte();
					constants[t] = switch (tag) {
						case CONSTANT_UTF8.TAG: yield new CONSTANT_UTF8(in);
						case CONSTANT_Integer.TAG: yield new CONSTANT_Integer(in);								
						case CONSTANT_Float.TAG: yield new CONSTANT_Float(in);								
						case CONSTANT_Long.TAG: yield new CONSTANT_Long(in);								
						case CONSTANT_Double.TAG: {
							CONSTANT_Double con = new CONSTANT_Double(in);
							short empty = in.readUnsignedByte();
							yield con;
						}					
						case CONSTANT_Class.TAG: yield new CONSTANT_Class(in);
						case CONSTANT_String.TAG: yield new CONSTANT_String(in);
						case CONSTANT_Fieldref.TAG: yield new CONSTANT_Fieldref(in);
						case CONSTANT_Methodref.TAG: yield new CONSTANT_Methodref(in);
						case CONSTANT_InterfaceMethodref.TAG: yield new CONSTANT_InterfaceMethodref(in);
						case CONSTANT_NameAndType.TAG: yield new CONSTANT_NameAndType(in);
						case CONSTANT_MethodHandle.TAG: yield new CONSTANT_MethodHandle(in);
						case CONSTANT_MethodType.TAG: yield new CONSTANT_MethodType(in);
						case CONSTANT_InvokeDynamic.TAG: yield new CONSTANT_InvokeDynamic(in);
						default:
							throw new IllegalArgumentException("Unexpected value: " + tag);
					};
				}
				
				for(Constant constant : constants) {
					if(constant instanceof ReferenceLink) {
						Reference<?>[] list = ((ReferenceLink)constant).getReferences();
						for(Reference<?> reference : list) {
							if(!reference.isSpecial()) {
								reference.setValue(constants[reference.getReference()-1]);
							}
						}
					}
				}
				
				access_flags = AccessFlag.getByBitmask(in.readUnsignedShort());
				this_class = new CONSTANT_Class(new Reference<>(in.readUnsignedShort()));
				this_class.getName().setValue(constants[this_class.getName().getReference()-1]);
				
				int super_class_index = in.readUnsignedShort();
				if(super_class_index != 0) {
					super_class = (CONSTANT_Class) constants[super_class_index-1];
				}
				
				int interfaces_count = in.readUnsignedShort();
				interfaces = new CONSTANT_Class[interfaces_count];
				for(int t = 0; t < interfaces_count; t++) {
					interfaces[t] = (CONSTANT_Class) constants[in.readUnsignedShort()-1];
				}
				
				int field_count = in.readUnsignedShort();
				
				for(int t = 0; t < field_count; t++) {
					AccessFlag[] accessflags = AccessFlag.getByBitmask(in.readUnsignedShort());
					Reference<CONSTANT_UTF8> name = new Reference<>(constants[in.readUnsignedShort()-1]);
					Reference<CONSTANT_UTF8> description = new Reference<>(constants[in.readUnsignedShort()-1]);
					Field field = new Field(accessflags, name, description);
					
					int count = in.readUnsignedShort();
					System.out.println(count);
					
					System.out.println(constants[in.readUnsignedShort()]);
					
					int attribute_length = 0;
					break;
				}
				
			}
			return new ClassFile(magic, minor_version, major_version, constants, access_flags, this_class, super_class, interfaces);
		}
	}



	@Override
	public String toString() {
		return "ClassFile [magic=" + Integer.toHexString((int) magic) + "\nminor_version=" + minor_version + "\nmajor_version=" + major_version
				+ "\nconstants=" + constants.length + "\naccess_flags=" + Arrays.toString(access_flags)
				+ "\nthis_class=" + this_class + "\nsuper_class=" + super_class + "\ninterfaces="
				+ Arrays.toString(interfaces) + "]";
	}

	
	
	
}
