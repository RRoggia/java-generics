package com.rroggia.generics.oracle.inheritance;

public class ConcreteClass<U, T> implements SecondLevelInterface<U, T> {

	private T t;
	private U u;

	public ConcreteClass(U u, T t) {
		this.u = u;
		this.t = t;
	}

	@Override
	public void compareTAndAssign(T t) {
		if (!isEqual(this.t, t))
			this.t = t;
	}

	@Override
	public T methodB() {
		compareUAndReturnT(this.u);
		return this.t;
	}

	@Override
	public T compareUAndReturnT(U u) {
		isEqual(u, this.u);
		return this.t;
	}

	private <G> boolean isEqual(G g1, G g2) {
		if (g1.equals(g2)) {
			System.out.println("isEqual");
			return true;
		} else {
			System.out.println("not equal");
			return false;
		}
	}
}
