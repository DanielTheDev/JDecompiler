package io.github.danielthedev.jdecompiler;

import java.util.ArrayList;
import java.util.List;

public interface AccessFlag {

	public static enum Class implements AccessFlag {
		
		ACC_PUBLIC(0x0001),
		ACC_FINAL(0x0010),
		ACC_SUPER(0x0020),
		ACC_INTERFACE(0x0200),
		ACC_ABSTRACT(0x0400),
		ACC_SYNTHETIC(0x1000),
		ACC_ANNOTATION(0x2000),
		ACC_ENUM(0x4000);
		
		private final int bitmask;
		
		Class(int bitmask) {
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
