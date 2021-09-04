package io.github.danielthedev.jdecompiler.constants;

public abstract class Constant {
	
	public static class Class extends Constant {
		
		private int name_index;

		public Class(int name_index) {
			this.name_index = name_index;
		}
		
		
	}
	
	public static class Fieldref extends Constant {
		
		private int class_index;
		private int name_and_type_index;
		
		public Fieldref(int class_index, int name_and_type_index) {
			this.class_index = class_index;
			this.name_and_type_index = name_and_type_index;
		}
		
		
	}

	public static class Methodref extends Constant {
		
		private int class_index;
		private int name_and_type_index;
		
		public Methodref(int class_index, int name_and_type_index) {
			this.class_index = class_index;
			this.name_and_type_index = name_and_type_index;
		}

	}
	
	public static class InterfaceMethodref extends Constant {
		
		private int class_index;
		private int name_and_type_index;
		
		public InterfaceMethodref(int class_index, int name_and_type_index) {
			this.class_index = class_index;
			this.name_and_type_index = name_and_type_index;
		}
		
		
	}
	
	public static class String extends Constant {
		
		private int string_index;

		public String(int string_index) {
			this.string_index = string_index;
		}
		
	}
	
	public static class Integer extends Constant {
		
		private int number;

		public Integer(int number) {
			this.number = number;
		}
		
		
	}
	
	public static class Float extends Constant {
		
		private float number;

		public Float(float number) {
			this.number = number;
		}
		
		
	}
	
	public static class Long extends Constant {
		
		private long number;

		public Long(long number) {
			this.number = number;
		}
		
	}
	
	public static class Double extends Constant {
		
		private double number;

		public Double(double number) {
			this.number = number;
		}
		
	}
	
	public static class NameAndType extends Constant {
		
		private int name_index;
		private int descriptor_index;
		
		public NameAndType(int name_index, int descriptor_index) {
			this.name_index = name_index;
			this.descriptor_index = descriptor_index;
		}
		
	}
	
	public static class Utf8 extends Constant {
		
		private java.lang.String string;

		public Utf8(java.lang.String string) {
			this.string = string;
		}

		@Override
		public java.lang.String toString() {
			return "Utf8 [string=" + string + "]";
		}
		
		
	}
	
	public static class MethodHandle extends Constant {
		
		private int reference_kind;
		private int reference_index;
		
		public MethodHandle(int reference_kind, int reference_index) {
			this.reference_kind = reference_kind;
			this.reference_index = reference_index;
		}
		
	}
	
	public static class MethodType extends Constant {
		
		private int descriptor_index;

		public MethodType(int descriptor_index) {
			this.descriptor_index = descriptor_index;
		}
			
	}
	
	public static class InvokeDynamic extends Constant {
		
		private int bootstrap_method_attr_index;
		private int name_and_type_index;
		
		public InvokeDynamic(int bootstrap_method_attr_index, int name_and_type_index) {
			this.bootstrap_method_attr_index = bootstrap_method_attr_index;
			this.name_and_type_index = name_and_type_index;
		}
		
		
	}
}
