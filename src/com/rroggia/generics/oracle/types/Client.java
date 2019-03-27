package com.rroggia.generics.oracle.types;

public class Client {

	public static void main(String[] args) {
		System.out.println("Box of Integer");
		Box<Integer> box = new Box<Integer>();
		box.set(10);
		Integer integer = box.get();
		System.out.println(integer);

		System.out.println(
				"its possible to replace the actual type parameter to an empty bracket, java can determine/infer its type");
		box = new Box<>();

		OrderedPair<String, Integer> p1 = new OrderedPair<>("renan", 26);
		OrderedPair<String, String> p2 = new OrderedPair<>("roggia", "world");

		System.out.println("keys: " + p1.getKey() + ", " + p2.getKey());
		System.out.println("values: " + p1.getValue() + ", " + p2.getValue());
	}

}
