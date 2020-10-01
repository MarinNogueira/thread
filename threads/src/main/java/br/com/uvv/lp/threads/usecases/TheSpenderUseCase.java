package br.com.uvv.lp.threads.usecases;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;
import br.com.uvv.lp.threads.gateways.operations.OperationsGateway;
import br.com.uvv.lp.threads.gateways.operations.OperationsGatewayImpl;

public class TheSpenderUseCase implements Runnable {
	
	private OperationsGatewayImpl operationsGateway = new OperationsGatewayImpl();
	
	private Lock lock;

	private Operation spender;
	
	private Account account;
	
	public TheSpenderUseCase(final Operation spender, final Account account, final Lock lock) {
		this.spender = spender;
		this.account = account;
		this.lock = lock;
	}

	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			operationsGateway.spend(this.spender, this.account, this.lock);		
		}
	}
	
	
	
}
