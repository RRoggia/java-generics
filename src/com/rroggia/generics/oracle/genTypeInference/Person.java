package com.rroggia.generics.oracle.genTypeInference;

public class Person implements Reader, Writer {

	@Override
	public void write() {
		System.out.println("Person writing");
	}

	@Override
	public void read() {
		System.out.println("Person reading");
	}

	@Override
	public String toString() {
		return "Person []";
	}

}
