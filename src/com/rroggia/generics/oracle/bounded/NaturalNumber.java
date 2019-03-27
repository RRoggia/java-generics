package com.rroggia.generics.oracle.bounded;

public class NaturalNumber<T extends Integer> {

	// because Integer is final, doesn't make sense to use a bounded type parameter,
	// you could directly make it an Integer
	private T n;

	public NaturalNumber(T n) {
		this.n = n;
	}

	public boolean isEven() {
		return n.intValue() % 2 == 0;
	}

}
