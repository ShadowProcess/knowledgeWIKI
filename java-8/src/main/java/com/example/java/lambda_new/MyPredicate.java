package com.example.java.lambda_new;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
