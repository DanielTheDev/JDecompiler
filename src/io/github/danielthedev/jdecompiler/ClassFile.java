package io.github.danielthedev.jdecompiler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import io.github.danielthedev.jdecompiler.constants.Constant;
import io.github.danielthedev.jdecompiler.constants.ConstantEnum;
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
	
	public ClassFile(JClassInputStream in) throws IOException {
		this.magic = in.readUnsignedInt();
		this.minor_version = in.readUnsignedShort();
		this.major_version = in.readUnsignedShort();
		
		int constant_pool_count = in.readUnsignedShort();
		
		this.constant_pool = new ArrayList<>(constant_pool_count);
		
		for(int index = 0; index < constant_pool_count; index++) {
			ConstantEnum constantEnum = ConstantEnum.getById(in.readUnsignedByte());
			if(constantEnum.isSkip()) index++;
			this.constant_pool.add(constantEnum.instantiate(in));
		}
		
		this.access_flags = AccessFlag.convertToList(AccessFlag.Class.class, in.readUnsignedShort());
		
		System.out.println(this.constant_pool);
		
		
		
		this.this_class = this.getConstant(in.readUnsignedShort());
		this.super_class = this.getConstant(in.readUnsignedShort());
	}
	
	public <K extends Constant> K getConstant(int index) {
		return (K) this.constant_pool.get(index - 1);
	}
}
