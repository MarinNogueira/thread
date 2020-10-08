package br.com.uvv.lp.threads.gateways.operations;

import java.util.concurrent.locks.Lock;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;

public class OperationsGatewayImpl {
	
	public void spend(final Operation spender, final Account account, final Lock lock) {
		
		try {
			Thread.sleep(spender.getSleep());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

		lock.lock();
		if(account.getBalance() != 0) {
			if(account.getBalance() - spender.getPull() >= 0) {
				account.setBalance(account.getBalance() - spender.getPull());
				spender.setNumberOfPulls(spender.getNumberOfPulls() + 1);
				spender.setBalance(spender.getBalance() + spender.getPull());
				System.out.println("Thread: " + Thread.currentThread().getName() + " - Account Balance: " + account.getBalance());
				System.out.println("Threads new Balance: " + spender.getBalance() + "\n\n");
			} 
			lock.unlock();
		}else {
			lock.unlock();
			threadSync(spender, account);		

		}
	}

	public void smart(final Operation smart, final Account account, final Lock lock) {

		try {
			Thread.sleep(smart.getSleep());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

		lock.lock();
		if(account.getBalance() != 0) {
			if(account.getBalance() - smart.getPull() >= 0) {
				account.setBalance(account.getBalance() - smart.getPull());
				smart.setNumberOfPulls(smart.getNumberOfPulls() + 1);
				smart.setBalance(smart.getBalance() + smart.getPull());
				System.out.println("Thread: " + Thread.currentThread().getName() + " - Account Balance: " + account.getBalance());
				System.out.println("Threads new Balance: " + smart.getBalance() + "\n\n");
			} 
			lock.unlock();
		}else {
			lock.unlock();
			threadSync(smart, account);
		}
	}

	public void save(final Operation save, final Account account, final Lock lock) {

		try {
			Thread.sleep(save.getSleep());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

		lock.lock();
		if(account.getBalance() != 0){
			if(account.getBalance() - save.getPull() >= 0) {
				account.setBalance(account.getBalance() - save.getPull());
				save.setNumberOfPulls(save.getNumberOfPulls() + 1);
				save.setBalance(save.getBalance() + save.getPull());
				System.out.println("Thread: " + Thread.currentThread().getName() + " - Accounts new Balance: " + account.getBalance());
				System.out.println("Threads new Balance: " + save.getBalance() + "\n\n");
			}		
			lock.unlock();
		} else {
			lock.unlock();
			threadSync(save, account);
		}



	}


	public synchronized void sponsor(final Operation sponsor, final Account account, final Lock lock) {

		try {
			Thread.sleep((15000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

		lock.lock();
		try {
			if(account.getBalance() == 0) {
				account.setBalance(account.getBalance() + sponsor.getPull());
				sponsor.setNumberOfPulls(sponsor.getNumberOfPulls() + 1);
				sponsor.setBalance(sponsor.getBalance() - sponsor.getPull());
				System.out.println("\n\nThread: " + Thread.currentThread().getName() + " - Accounts new Balance: " + account.getBalance());
				System.out.println("Threads new Balance: " + sponsor.getBalance() + "\n\n");
				threadSync(sponsor, account);
			}

		} finally {
			lock.unlock();
		}
	}

	private void threadSync(final Operation operation, final Account account) {

		synchronized (account) {

			if(Thread.currentThread().getName().equals("The Sponsor")) {

				account.notifyAll();
				
			} else {
				System.out.println(Thread.currentThread().getName() + " Number of Pulls: " + operation.getNumberOfPulls());
				System.out.println("Balance: " + operation.getBalance());
				
				try {
					account.wait();

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
				}
				
			}
			
		}
	}


}