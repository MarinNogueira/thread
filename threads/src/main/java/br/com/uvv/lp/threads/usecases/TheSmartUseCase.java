package br.com.uvv.lp.threads.usecases;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;
import br.com.uvv.lp.threads.gateways.operations.OperationsGateway;
import br.com.uvv.lp.threads.gateways.operations.OperationsGatewayImpl;

public class TheSmartUseCase implements Runnable {
	
	private OperationsGatewayImpl operationsGateway = new OperationsGatewayImpl();
	
	private Lock lock;
	
	private Operation smart;
	
	private Account account;
	
	public TheSmartUseCase(final Operation smart, final Account account, final Lock lock) {
		this.smart = smart;
		this.account = account;
		this.lock = lock;
	}

	public void run() {
		while(!Thread.currentThread().isInterrupted()) {		
			operationsGateway.smart(this.smart, this.account, lock);		
		}
	}
	
}
