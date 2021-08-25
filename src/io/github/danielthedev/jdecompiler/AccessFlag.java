package io.github.danielthedev.jdecompiler;

import java.util.ArrayList;
import java.util.List;

public enum AccessFlag {

	ACC_PUBLIC(0x0001),
	ACC_PRIVATE(0x0002),
	ACC_PROTECTED(0x0004),
	ACC_STATIC(0x0008),
	ACC_FINAL(0x0010),
	ACC_SUPER(0x0020),
	ACC_VOLATILE(0x0040),
	ACC_TRANSIENT(0x080),
	ACC_INTERFACE(0x0200),
	ACC_ABSTRACT(0x0400),
	ACC_SYNTHETIC(0x1000),
	ACC_ANNOTATION(0x2000),
	ACC_ENUM(0x4000);
	
	private final int bitmask;

	private AccessFlag(int bitmask) {
		this.bitmask = bitmask;
	}

	public int getBitmask() {
		return bitmask;
	}
	
	public static AccessFlag[] getByBitmask(int bitmask) {
		List<AccessFlag> list = new ArrayList<>();
		for(AccessFlag flag : values()) {
			if((bitmask & flag.bitmask) == flag.bitmask) {
				list.add(flag);
			}
		}
		return list.toArray(new AccessFlag[list.size()]);
	}
}
