package com.rroggia.generics.oracle.literals;

import java.util.ArrayList;
import java.util.Collection;

public class Client {

	public static void main(String[] args) {
		Client client = new Client();

		System.out.println("Requires an annonymous class of the Factory.");
		Collection<EmpInfo> sqlResult1 = client.select(new Factory<EmpInfo>() {
			public EmpInfo make() {
				return new EmpInfo();
			};
		}, "select *");
		System.out.println(sqlResult1);

		System.out.println("Requires a concrete class of the Factory");
		Collection<EmpInfo> sqlResult2 = client.select(new EmpInfoFactory(), "select *");
		System.out.println(sqlResult2);

		System.out.println(
				"Because we have the Class Literals, we don't need the factory anymore. We can use the reflection API to instantiate the class.");
		Collection<EmpInfo> sqlResult3 = Client.select(EmpInfo.class, "select *");
		System.out.println(sqlResult3);

	}

	public <T> Collection<T> select(Factory<T> factory, String statement) {
		Collection<T> result = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			T item = factory.make();
			result.add(item);
		}
		return result;
	}

	public static <T> Collection<T> select(Class<T> type, String statement) {
		Collection<T> result = new ArrayList<>();

		try {
			for (int i = 0; i < 5; i++) {
				T item = type.newInstance();
				result.add(item);
			}
		} catch (IllegalAccessException | InstantiationException e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
	}
}

interface Factory<T> {
	T make();
}

class EmpInfoFactory implements Factory<EmpInfo> {
	@Override
	public EmpInfo make() {
		return new EmpInfo();
	}
}

class EmpInfo {

}