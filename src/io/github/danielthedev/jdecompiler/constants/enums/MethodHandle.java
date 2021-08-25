package io.github.danielthedev.jdecompiler.constants.enums;

import java.util.stream.Stream;

public enum MethodHandle {
	
	REF_GETFIELD(1),
	REF_GETSTATIC(2),
	REF_PUTFIELD(3),
	REF_PUTSTATIC(4),
	REF_INVOKE_VIRTUAL(5),
	REF_INVOKE_STATIC(6),
	REF_INVOKE_SPECIAL(7),
	REF_NEW_INVOKE_SPECIAL(8),
	REF_INVOKE_INTERFACE(9);
	
	private final int index;


	MethodHandle(int index) {
		this.index = index;
	}
	
	public static MethodHandle getByID(int id) {
		return Stream.of(values()).filter(s->s.index == id).findFirst().get();
	}
}
