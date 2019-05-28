package com.rroggia.generics.javaeffective.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class Client {

	public static void main(String[] args) {
		try {
			Collection stamps = new ArrayList();
			stamps.add("not an stamp");

			for (Iterator iterator = stamps.iterator(); iterator.hasNext();) {
				Stamp stamp = (Stamp) iterator.next();
			}
		} catch (ClassCastException e) {
			System.out.println("thrown ClassCastException");
		}

		Collection<Stamp> stamps = new ArrayList<>();
		// stamps.add("aa"); // compilation error, only allows to add Stamp

		System.out.println("Raw Type: Enables corrupting the array by adding elements of other types");
		numberOfElementsInCommonRawType(new HashSet(), new HashSet());

		System.out.println("Unbounded wildcard: Only allows adding null references.");
		numberOfElementsInCommonGeneric(new HashSet<>(), new HashSet<>());

	}

	public static int numberOfElementsInCommonGeneric(Set<?> s1, Set<?> s2) {
		int counter = 0;

		for (Object o1 : s1)
			if (s2.contains(o1))
				counter++;

		return counter;
	}

	public static int numberOfElementsInCommonRawType(Set s1, Set s2) {
		int counter = 0;

		for (Object o1 : s1)
			if (s2.contains(o1))
				counter++;

		return counter;
	}
}

class Stamp {

}
