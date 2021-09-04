package io.github.danielthedev.jdecompiler;

import java.util.ArrayList;
import java.util.List;

public interface AccessFlag {

	public static enum Class implements AccessFlag {
		
		PUBLIC(0x0001),
		FINAL(0x0010),
		SUPER(0x0020),
		INTERFACE(0x0200),
		ABSTRACT(0x0400),
		SYNTHETIC(0x1000),
		ANNOTATION(0x2000),
		ENUM(0x4000);
		
		private final int bitmask;
		
		Class(int bitmask) {
			this.bitmask = bitmask;
		}

		@Override
		public int getBitmask() {
			return this.bitmask;
		}
	
	}
	
	public static enum Field implements AccessFlag {
		
		PUBLIC(0x0001),
		PRIVATE(0x0002),
		PROTECTED(0x0004),
		STATIC(0x0008),
		FINAL(0x0010),
		VOLATILE(0x0040),
		TRANSIENT(0x0080),
		SYNTHETIC(0x1000),
		ENUM(0x4000);
		
		private final int bitmask;
		
		Field(int bitmask) {
			this.bitmask = bitmask;
		}

		@Override
		public int getBitmask() {
			return this.bitmask;
		}
	
	}
	
	public static enum Method implements AccessFlag {
		
		PUBLIC(0x0001),
		PRIVATE(0x0002),
		PROTECTED(0x0004),
		STATIC(0x0008),
		FINAL(0x0010),
		SYNCHRONIZED(0x0020),
		BRIDGE(0x0040),
		VARARGS(0x0080),
		NATIVE(0x0100),
		ABSTRACT(0x0400),
		STRICT(0x0800),
		SYNTHETIC(0x1000);
		
		private final int bitmask;
		
		Method(int bitmask) {
			this.bitmask = bitmask;
		}

		@Override
		public int getBitmask() {
			return this.bitmask;
		}
	
	}
	
	public int getBitmask();
	

	public static <K extends AccessFlag> List<K> convertToList(java.lang.Class<K> type, int bitmask) {
		List<K> list = new ArrayList<K>();
		for(K flag : type.getEnumConstants()) {
			if((bitmask & flag.getBitmask()) == flag.getBitmask()) {
				list.add(flag);
			}
		}
		
		return list;
	}

}
