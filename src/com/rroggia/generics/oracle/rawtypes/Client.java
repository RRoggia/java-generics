package com.rroggia.generics.oracle.rawtypes;

import com.rroggia.generics.oracle.types.Box;

public class Client {

	public static void main(String[] args) {

		System.out.println("Creating raw type generates a warning");
		Box rawBox = new Box();
		Box<String> parameterizedBox = new Box<>();
		rawBox = parameterizedBox;

		System.out
				.println("calling a method from a raw type (assigned from a parameterized type) generates an warning");
		rawBox.set(10);

		rawBox = new Box();
		parameterizedBox = new Box<>();
		System.out.println("moving raw to parameterized generates an uncheked conversion");
		parameterizedBox = rawBox;

	}

}
