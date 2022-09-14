
class JointAccount {
	public int balance = 100;
	
	public void withdraw(int amount) {
		balance = balance - amount;
	}
}

class Job1 implements Runnable {

	private JointAccount acct;
	
	public Job1(JointAccount acct) {
		this.acct =acct;
	}
	
	private /*synchronized*/ void startWithdrawProcess(int amount) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " has entered method startWithdrawProcess");
		System.out.println(name + " is checking the balance");
		
		synchronized(acct) { 	
			if(amount < acct.balance) {
				System.out.println(name + " has checked the balance");
				System.out.println(name + " has sufficient balance");
				System.out.println(name + " is going to withdraw $" + amount);
				try 
				{
					Thread.sleep(5000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				acct.withdraw(amount);
				System.out.println(name + " has withdrawn $" + amount);
			} 
			else {
				System.out.println(name + " has insufficient balance!");
			} //if
		} //synchronized (end of method)
		System.out.println(name + " is exiting method startWithdrawProcess");
	}
	
	@Override
	public void run() {
		startWithdrawProcess(75);
		if (acct.balance < 0) {
			System.out.println("!!!ERROR!!!");
		}
	}
}

public class HusbandWifeDemo {

	public static void main(String[] args) {
		JointAccount acct = new JointAccount();
		Job1 job = new Job1(acct);
		Thread t1 = new Thread(job);
		Thread t2 = new Thread(job);
		
		t1.setName("Husband");
		t2.setName("Wife");
		
		t2.start();
		t1.start();
		
	}

}
