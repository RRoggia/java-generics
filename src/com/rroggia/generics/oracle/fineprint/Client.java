package com.rroggia.generics.oracle.fineprint;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		List<String> ls = new ArrayList<String>();
		List<Integer> li = new ArrayList<Integer>();

		System.out.println(li.getClass() == ls.getClass());

		System.out.println("Declaration allows generics");
		List<String>[] lsa;

		System.out.println(
				"It doesn't allow the component type of an array object to be a type variable or parameterized type, unless it is wildcard (unbounded)");
		List<?>[] lsa2 = new List<?>[10];

	}

}
