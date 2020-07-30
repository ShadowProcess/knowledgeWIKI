package com.example.java.day01.lambda1;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
