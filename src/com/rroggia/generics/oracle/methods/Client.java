package com.rroggia.generics.oracle.methods;

public class Client {
	public static void main(String[] args) {

		Pair<Integer, String> p1 = new Pair<>(1, "apple");
		Pair<Integer, String> p2 = new Pair<>(2, "pear");

		System.out.println(Util.<Integer, String>compare(p1, p2));
		System.out.println(Util.compare(p1, p2));

	}
}
