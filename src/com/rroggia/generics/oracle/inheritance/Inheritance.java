package com.rroggia.generics.oracle.inheritance;

import com.rroggia.generics.oracle.types.Box;

public class Inheritance {

	public static void main(String[] args) {
		Object someObject = new Object();
		Integer someInteger = new Integer(10);
		someObject = someInteger;

		Inheritance a = new Inheritance();

		a.someMethod(someInteger);
		a.someMethod(new Double(10.1));

		Box<Number> box = new Box<>();
		box.set(new Integer(20));
		box.set(new Double(20.1));

		System.out.println("The Box<Integer> is not a subtype of Box<Number>");
		a.doSomething(new Box<Number>());
		// a.doSomething(new Box<Integer>()); //compile error

	}

	public void someMethod(Number n) {
	}

	public void doSomething(Box<Number> boxOfNumber) {

	}

}
