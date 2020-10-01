package br.com.uvv.lp.threads.usecases;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;
import br.com.uvv.lp.threads.gateways.operations.OperationsGatewayImpl;

public class TheSponsorUseCase implements Runnable{

	private OperationsGatewayImpl operationsGateway = new OperationsGatewayImpl();
	
	private Lock lock;

	private Operation sponsor;
	
	private Account account;
	
	public TheSponsorUseCase(final Operation sponsor, final Account account, final Lock lock) {
		this.sponsor = sponsor;
		this.account = account;
		this.lock = lock;
	}

	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			operationsGateway.sponsor(this.sponsor, this.account, this.lock);		
		}
	}
	
}

