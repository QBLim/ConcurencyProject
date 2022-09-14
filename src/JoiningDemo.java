class Education implements Runnable {

	@Override
	public void run() {
		System.out.println("Education has started...");
		System.out.println("Education is going on...");
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		System.out.println("Education is completed...");
	}
		
}

class Marriage implements Runnable {

	private Thread t;
	public Marriage(Thread t) {
		this.t = t;
	}
	
	@Override
	public void run() {
		try 
		{
			t.join(); //joining to end of another thread
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Wedding ceremony has started...");
		System.out.println("Married...");
	}
	
}

public class JoiningDemo {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Education());
		Thread t2 = new Thread(new Marriage(t1));
		t2.start();
		t1.start();
	}

}
