package br.com.uvv.lp.threads.gateways.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;
import br.com.uvv.lp.threads.usecases.ThreadUseCaseOrquestrator;

public class ThreadsController {

	private ThreadUseCaseOrquestrator threadUseCaseOrquestrator = new ThreadUseCaseOrquestrator();

	public void startOperations() {
		
		final Account account = new Account();
		
		final List<Operation> operations = new ArrayList<>();
				
		//Spender
		operations.add(new Operation());
		operations.get(0).setBalance(0);
		operations.get(0).setPull(10);
		operations.get(0).setNumberOfPulls(0);
		operations.get(0).setSleep(300L);
		
		//Smart
		operations.add(new Operation());
		operations.get(1).setBalance(0);
		operations.get(1).setPull(50);
		operations.get(1).setNumberOfPulls(0);
		operations.get(1).setSleep(600L);
		
		//Saver
		operations.add(new Operation());
		operations.get(2).setBalance(0);
		operations.get(2).setPull(5);
		operations.get(2).setNumberOfPulls(0);
		operations.get(2).setSleep(1200L);
		
		//Sponsor
		operations.add(new Operation());
		operations.get(3).setBalance(0);
		operations.get(3).setPull(100);
		operations.get(3).setNumberOfPulls(0);
		
		account.setBalance(1000);
		account.setNumber((int) (Math.random() * 10000));
		account.setUser("Larissa Pimentel Viana Randow");
		
		final Thread thread[] = new Thread[4];
		
		thread[0] = new Thread();

		thread[1] = new Thread();
		
		thread[2] = new Thread();	
		
		thread[3] = new Thread();	
		
		this.threadUseCaseOrquestrator.operate(thread, account, operations);

	}
	
}
