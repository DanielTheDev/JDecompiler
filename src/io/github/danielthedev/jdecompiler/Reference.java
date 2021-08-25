package io.github.danielthedev.jdecompiler;

public class Reference<V> {
	
	private final int refference;
	private V value;
	
	public Reference(int index) {
		this.refference = index;
	}
	
	public boolean isEmpty() {
		return this.value == null;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}

	public int getRefference() {
		return refference;
	}
}
