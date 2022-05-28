package com.pradeep.blog.utils;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EvenoddusingCompletableFuture {
	
	
	private static Object object= new Object();
	
	
	private static IntPredicate even= i-> i%2==0;
	private static IntPredicate odd=i -> i%2 !=0;
	
	public static void print(IntPredicate condIntPredicate) {
		IntStream.range(1, 10).filter(condIntPredicate).forEach(EvenoddusingCompletableFuture::execute);
	}
	
	
	public static void execute(int no) {
		synchronized (object) {
			try {
				System.out.println(Thread.currentThread().getName() +" :"+ no);
				object.notify();
				object.wait();
			} catch (Exception e) {
				e.printStackTrace();			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		CompletableFuture.runAsync(() -> EvenoddusingCompletableFuture.print(odd));
		CompletableFuture.runAsync(() -> EvenoddusingCompletableFuture.print(even));
		Thread.sleep(1000);
	}

}
