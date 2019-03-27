package com.rroggia.generics.oracle.bounded;

public class MultipleBound<T extends InterfaceA & InterfaceB> {

	public void doSomething(T t) {
		t.methodInterfaceA();
		t.methodInterfaceB();
	}

}
