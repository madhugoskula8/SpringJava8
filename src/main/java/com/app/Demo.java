package com.app;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {

	public static void main(String[] args) {
		System.out.println(isPrime(5));
		primeSqrt(10);
	}

	private static void primeSqrt(int n) {
		Stream.iterate(1, i->i+1).filter(Demo::isPrime).peek(System.out::println).map(Math::sqrt).limit(n).forEach(System.out::println);
		
	}

	private static boolean isPrime(int number) {
		return number>1 && IntStream.range(2, number).noneMatch(n->number%n==0);
		
	}
}
