package br.com.uvv.lp.threads.usecases;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;

public class ThreadUseCaseOrquestrator {
	
	public void operate(final Thread[] thread, final Account account, final List<Operation> operations) {
		//instancia um Lock para as threads
		final Lock lock = new ReentrantLock();
		//cria uma thread e instancia cada tipo de Use case, passa como parametros o arraylist operations, account e lock
		thread[0] = new Thread(new TheSpenderUseCase(operations.get(0), account, lock), "The Spender");
		thread[1] = new Thread(new TheSmartUseCase(operations.get(1), account, lock), "The Smart");
		thread[2] = new Thread(new TheSavingUseCase(operations.get(2), account, lock), "The Saver");
		thread[3] = new Thread(new TheSponsorUseCase(operations.get(3), account, lock), "The Sponsor");
		//inicia cada uma das threads
		for (int i = 0; i < 4; i++){
	         thread[i].start();
	    }
		
	}
	
}
