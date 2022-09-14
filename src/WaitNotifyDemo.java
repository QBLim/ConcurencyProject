class Account {
	public int balance = 0;
	
	public void withdraw(int amount) {
		balance = balance - amount;
	}
	
	public void deposit(int amount) {
		balance = balance + amount;
	}
}

class Depositor implements Runnable {

	private Account acct;
	public Depositor(Account acct) {
		this.acct = acct;
	}
	
	@Override
	public void run() {		
		synchronized(acct) {
			acct.deposit(100);
			acct.notifyAll();
		}					
	}	
}

class Withdrawal implements Runnable {
	
	private Account acct;
	public Withdrawal(Account acct) {
		this.acct = acct;
	}
	
	@Override
	public void run() {
		synchronized(acct) {
		try 
		{
			acct.wait(); //temporary sleep until notify
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		acct.withdraw(100);
			if(acct.balance < 0) {
				System.out.println("A Big Problem!");
			}
		}
	}	
}

public class WaitNotifyDemo {

	public static void main(String[] args) {
		Account acct = new Account();		
		Depositor d = new Depositor(acct);
		Withdrawal w = new Withdrawal(acct);
		
		Thread t1 = new Thread(d);
		Thread t2 = new Thread(w);
		
		t1.setName("Bill");
		t2.setName("Melinda");
		
		t2.start();
		t1.start();
	}
}
