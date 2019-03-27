package com.rroggia.generics.oracle.bounded;

public class BiggerBox implements InterfaceA, InterfaceB {

	@Override
	public void methodInterfaceB() {
		System.out.println("interface a");
	}

	@Override
	public void methodInterfaceA() {
		System.out.println("interface b");
	}

}
