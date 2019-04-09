package com.rroggia.generics.oracle.inheritance;

public class Client {

	public static void main(String[] args) {
		ConcreteClass<Integer, String> concreteClass = new ConcreteClass<>(2, "roggia");
		doSomethgin(concreteClass, "renan");

	}

	public static <T> T doSomethgin(FirstLevelInterface<T> firstLevelInterface, T somethingA) {
		firstLevelInterface.compareTAndAssign(somethingA);
		firstLevelInterface.methodB();
		return firstLevelInterface.methodB();
	}

}
