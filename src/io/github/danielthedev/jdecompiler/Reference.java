package io.github.danielthedev.jdecompiler;

import io.github.danielthedev.jdecompiler.constants.Constant;

public class Reference<V> {
	
	private final int reference;
	private final boolean special;
	private V value;
	
	public Reference(int index) {
		this(index, false);
	}
	
	public Reference(int index, boolean special) {
		this(index, special, null);
	}
	
	public Reference(Object value) {
		this(0, false, (V) value);
	}
	
	public Reference(int index, boolean special, V value) {
		this.reference = index;
		this.special = special;
		this.value = value;
	}
	
	public boolean isEmpty() {
		return this.value == null;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setValue(Object value) {
		this.value = (V) value;
	}
	

	public int getReference() {
		return reference;
	}

	public boolean isSpecial() {
		return special;
	}

	@Override
	public String toString() {
		if(value != null) return value.toString();
		return "Reference [reference=" + reference + "]";
	}
	

}
