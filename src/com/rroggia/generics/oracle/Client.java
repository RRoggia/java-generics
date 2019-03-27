package com.rroggia.generics.oracle;

public class Client {

	public static void main(String[] args) {
		Box<Integer> box = new Box<Integer>();
		box.set(10);
		Integer integer = box.get();
		System.out.println(integer);
	}

}
