package com.rroggia.generics.oracle.genTypeInference;

public class Developer implements Writer, Reader {

	@Override
	public void read() {
		System.out.println("developer reader");
	}

	@Override
	public void write() {
		System.out.println("developer writer");

	}

	@Override
	public String toString() {
		return "Developer []";
	}

}
