package br.com.uvv.lp.threads.gateways.operations;

import java.util.concurrent.locks.Lock;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;
//metodo para chamada da thread spender
public class OperationsGatewayImpl {
	
	public void spend(final Operation spender, final Account account, final Lock lock) {
		
		try { //tenta colocar a thread em sleep com o valor padrao definido, caso nao consiga, interrompe a thread e imprime o codigo da excecao
			Thread.sleep(spender.getSleep());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); 
			e.printStackTrace();
		}
		//trava, verifica se o saldo é maior que zero, remove a quantia determinada, printa as informacoes e por fim destrava a conta
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
		}else {//caso o saldo seja menor ou igual a zero, destrava a conta para uso de outras threads
			lock.unlock();
			threadSync(spender, account);		

		}
	}
	//metodo para a chamada da thread smart
	public void smart(final Operation smart, final Account account, final Lock lock) {

		try {//tenta colocar a thread em sleep com o valor padrao definido, caso nao consiga, interrompe a thread e imprime o codigo da excecao
			Thread.sleep(smart.getSleep());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		//trava, verifica se o saldo é maior que zero, remove a quantia determinada, printa as informacoes e por fim destrava a conta
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
		}else {//caso o saldo seja menor ou igual a zero, destrava a conta para uso de outras threads
			lock.unlock();
			threadSync(smart, account);
		}
	}
	//metodo para chamada da thread saver
	public void save(final Operation save, final Account account, final Lock lock) {

		try {//tenta colocar a thread em sleep com o valor padrao definido, caso nao consiga, interrompe a thread e imprime o codigo da excecao
			Thread.sleep(save.getSleep());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		//trava, verifica se o saldo é maior que zero, remove a quantia determinada, printa as informacoes e por fim destrava a conta
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
		} else {//caso o saldo seja menor ou igual a zero, destrava a conta para uso de outras threads
			lock.unlock();
			threadSync(save, account);
		}



	}

	//metodo para chamada da thread sponsor
	public synchronized void sponsor(final Operation sponsor, final Account account, final Lock lock) {

		try {//tenta colocar a thread em sleep com o valor padrao definido, caso nao consiga, interrompe a thread e imprime o codigo da excecao
			Thread.sleep((15000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		//trava, verifica se o saldo é igual a zero, adiciona a quantia determinada, printa as informacoes e por fim destrava a conta
		lock.lock();
		try {//tenta colocar a thread em sleep com o valor padrao definido, caso nao consiga, interrompe a thread e imprime o codigo da excecao
			if(account.getBalance() == 0) {
				account.setBalance(account.getBalance() + sponsor.getPull());
				sponsor.setNumberOfPulls(sponsor.getNumberOfPulls() + 1);
				sponsor.setBalance(sponsor.getBalance() - sponsor.getPull());
				System.out.println("\n\nThread: " + Thread.currentThread().getName() + " - Accounts new Balance: " + account.getBalance());
				System.out.println("Threads new Balance: " + sponsor.getBalance() + "\n\n");
				threadSync(sponsor, account);
			}

		} finally {//destrava a conta para uso de outras threads
			lock.unlock();
		}
	}

	private void threadSync(final Operation operation, final Account account) {

		synchronized (account) {

			if(Thread.currentThread().getName().equals("The Sponsor")) {

				account.notifyAll(); //notifica caso a thread atual seja a sponsor
				
			} else {
				System.out.println(Thread.currentThread().getName() + " Number of Pulls: " + operation.getNumberOfPulls());
				System.out.println("Balance: " + operation.getBalance());
				//se thread atual for diferente de sponsor, imprime o numero de saques feitos por essa thread e o saldo atual da conta
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