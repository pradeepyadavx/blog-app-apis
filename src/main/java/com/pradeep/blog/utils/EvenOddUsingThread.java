package com.pradeep.blog.utils;

public class EvenOddUsingThread  implements Runnable {
	
	Object object= null;
	static int i=1;
	
	public EvenOddUsingThread(Object object) {
		this.object=object;
	}

	@Override
	public void run() {
		while(i<=10) {
			if(i%2==0 && Thread.currentThread().getName().equals("even")) {
				
				
				synchronized (object) {
					System.out.println(Thread.currentThread().getName()+" :"+i);
					i++;
					try {
						object.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
			
			if(i%2!=0 && Thread.currentThread().getName().equals("odd")) {
				synchronized (object) {
					System.out.println(Thread.currentThread().getName()+":"+i);
					i++;
					object.notify();
				}
			}
			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Object object= new Object();
		
		Runnable th1= new EvenOddUsingThread(object);
		Runnable th2= new EvenOddUsingThread(object);
		
		new Thread(th1,"even").start();
		new Thread(th2,"odd").start();
		
	}
	
	

}
