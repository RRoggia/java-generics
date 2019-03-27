package com.rroggia.generics.oracle.bounded;

public class Client {
	public static void main(String[] args) {
		Box<Integer> box = new Box<>();
		box.set(10);

		System.out.println("box.inspect(\"string\"); //gives an compilation error");
		// box.inspect("string");

		box.inspect(10);

		System.out.println("Multiple bound -> restricts to a class that extends all the interfaces and class");
		MultipleBound<BiggerBox> a = new MultipleBound<>();
		a.doSomething(new BiggerBox());
	}
}
