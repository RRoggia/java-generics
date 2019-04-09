package com.rroggia.generics.oracle.inheritance;

public interface SecondLevelInterface<U, T> extends FirstLevelInterface<T> {

	T compareUAndReturnT(U u);

}
