package com.rroggia.generics.javaeffective.item28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Client {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");

		System.out.println("Chooser1 - Requires cast, can throw runtime exception");
		Chooser1 chooser1 = new Chooser1(list);
		String string1 = (String) chooser1.choose();
		System.out.println(string1);

		System.out.println(
				"Chooser2 - Don't require cast, compiler cannot ensure type safety, could suppress warning and add comment");
		Chooser2<String> chooser2 = new Chooser2<>(list);
		String string2 = chooser2.choose();
		System.out.println(string2);

		System.out.println("Chooser3 - Don't require cast, compiler ensures type safety");
		Chooser3<String> chooser3 = new Chooser3<>(list);
		String string3 = chooser3.choose();
		System.out.println(string3);

		System.out.println("Chooser4 - Same as 3, but with the flexibility rules item 31");
		List<Integer> integers = Arrays.asList(3, 2, 1);
		Chooser4<Number> chooser4 = new Chooser4<>(integers);
		Number choose = chooser4.choose();
		System.out.println(choose);

	}

}

class Chooser1 {
	private final Object[] choiceArray;

	public Chooser1(Collection choices) {
		choiceArray = choices.toArray();
	}

	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceArray[rnd.nextInt(choiceArray.length)];
	}
}

class Chooser2<T> {
	private final T[] choiceArray;

	public Chooser2(Collection<T> choices) {
		choiceArray = (T[]) choices.toArray();
	}

	public T choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceArray[rnd.nextInt(choiceArray.length)];
	}
}

class Chooser3<T> {
	private final List<T> choiceList;

	public Chooser3(Collection<T> choices) {
		choiceList = new ArrayList<>(choices);
	}

	public T choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceList.get(rnd.nextInt(choiceList.size()));
	}
}

class Chooser4<T> {
	private final List<T> choiceList;

	public Chooser4(Collection<? extends T> choices) {
		choiceList = new ArrayList<>(choices);
	}

	public T choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceList.get(rnd.nextInt(choiceList.size()));
	}
}
