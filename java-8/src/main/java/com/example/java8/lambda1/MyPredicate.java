package com.example.java8.lambda1;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
