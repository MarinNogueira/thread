package br.com.uvv.lp.threads.usecases;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;
import br.com.uvv.lp.threads.gateways.operations.OperationsGateway;
import br.com.uvv.lp.threads.gateways.operations.OperationsGatewayImpl;

public class TheSavingUseCase implements Runnable {

	private OperationsGatewayImpl operationsGateway = new OperationsGatewayImpl();
	
	private Lock lock = new ReentrantLock();
	
	private Operation save;
	
	private Account account;
	
	public TheSavingUseCase(final Operation save, final Account account, final Lock lock) {
		this.save = save;
		this.account = account;
		this.lock = lock;
	}

	public void run() {
		while(!Thread.currentThread().isInterrupted()) {	
			operationsGateway.save(this.save, this.account, this.lock);		
		}
	}
	
}
