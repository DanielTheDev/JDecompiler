package io.github.danielthedev.jdecompiler.constants;

import java.io.IOException;

import io.github.danielthedev.jdecompiler.JClassInputStream;

public enum ConstantEnum {

	CLASS((int) 0x07) {

		@SuppressWarnings("unchecked")
		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			return (K) new Constant.Class(in.readUnsignedShort());
		}
		
	},
	
	FIELDREF((int) 0x09) {

		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			int class_index = in.readUnsignedShort();
			int name_and_type_index = in.readUnsignedShort();
			return (K) new Constant.Fieldref(class_index, name_and_type_index);
		}
		
	},
	
	METHODREF((int) 0x0a) {

		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			int class_index = in.readUnsignedShort();
			int name_and_type_index = in.readUnsignedShort();
			return (K) new Constant.Fieldref(class_index, name_and_type_index);
		}

	},
	
	INTERFACEMETHODREF((int) 0x0b) {

		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			int class_index = in.readUnsignedShort();
			int name_and_type_index = in.readUnsignedShort();
			return (K) new Constant.Methodref(class_index, name_and_type_index);
		}

	},
	
	STRING((int) 0x08) {

		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			return (K) new Constant.String(in.readUnsignedShort());
		}

	},
	
	INTEGER((int) 0x03) {

		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			return (K) new Constant.Integer(in.readInteger());
		}

	},
	
	FLOAT((int) 0x04) {
		
		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			return (K) new Constant.Float(in.readFloat());
		}
	},
	
	LONG((int) 0x05, true) {

		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			long high_bytes = in.readUnsignedInt();
			long low_bytes = in.readUnsignedInt();
			return (K) new Constant.Long(((long) high_bytes << 32) + low_bytes);
		}
	},
	
	DOUBLE((int) 0x06, true) {

		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			long high_bytes = in.readUnsignedInt();
			long low_bytes = in.readUnsignedInt();
			return (K) new Constant.Double(((long) high_bytes << 32) + low_bytes);
		}
	},
	
	NAMEANDTYPE((int) 0x0c) {

		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			int name_index = in.readUnsignedShort();
			int descriptor_index = in.readUnsignedShort();
			return (K) new Constant.NameAndType(name_index, descriptor_index);
		}
	},
	
	UTF8((int) 0x01) {
		
		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			int length = in.readUnsignedShort();
			return (K) new Constant.Utf8(new String(in.readNBytes(length)));
		}
	},
	
	METHODHANDLE((int) 0x0f) {
		
		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			short reference_kind = in.readUnsignedByte();
			int reference_index = in.readUnsignedShort();
			
			return (K) new Constant.MethodHandle(reference_kind, reference_index);
		}
	},
	
	METHODTYPE((int) 0x10) {
		
		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			int descriptor_index = in.readUnsignedShort();
			
			return (K) new Constant.MethodType(descriptor_index);
		}
	},
	
	INVOKEDYNAMIC((int) 0x12) {
		
		@Override
		public <K extends Constant> K instantiate(JClassInputStream in) throws IOException {
			int bootstrap_method_attr_index = in.readUnsignedShort();
			int name_and_type_index = in.readUnsignedShort();
			
			return (K) new Constant.InvokeDynamic(bootstrap_method_attr_index, name_and_type_index);
		}
	};
	
	private final int id;
	private final boolean skip;
	
	public abstract <K extends Constant> K instantiate(JClassInputStream in) throws IOException;
	
	ConstantEnum(int id) {
		this(id, false);
	}
	
	ConstantEnum(int id, boolean skip) {
		this.id = id;
		this.skip = skip;
	}

	public int getId() {
		return id;
	}

	public boolean isSkip() {
		return skip;
	}

	public static ConstantEnum getById(short id) {
		ConstantEnum result = null;
		for(ConstantEnum constantEnum : values()) {
			if(constantEnum.getId() == id) {
				result = constantEnum;
				break;
			}
		}
		return result;
	}
	
}
