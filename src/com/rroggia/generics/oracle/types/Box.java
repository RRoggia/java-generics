package com.rroggia.generics.oracle.types;

public class Box<T> {

	private T t;

	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return this.t;
	}

}
