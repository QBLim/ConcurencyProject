class Job implements Runnable {	
//class Job extends Thread {	
	public void method2() {
		String name = Thread.currentThread().getName();
		for (int j = 100; j < 2000; j++) {
			System.out.println(name + "=" + j);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {	
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		method2();
	}
}

public class ConcurrencyDemo {
	
	public void method1() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 1000; i++) {
			System.out.println(name + "=" + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}	
	
	public static void main(String[] args) {
		ConcurrencyDemo cd = new ConcurrencyDemo();
		Job job = new Job();
		Thread t1 = new Thread(job);
		t1.setName("mythread");
		t1.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		cd.method1();
//		cd.method2();
	
	}

}
