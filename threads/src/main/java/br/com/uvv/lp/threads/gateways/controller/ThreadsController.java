package br.com.uvv.lp.threads.gateways.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;
import br.com.uvv.lp.threads.usecases.ThreadUseCaseOrquestrator;

public class ThreadsController {
	//cria uma instancia de ThreadUseCaseOrquestrator
	private ThreadUseCaseOrquestrator threadUseCaseOrquestrator = new ThreadUseCaseOrquestrator();

	public void startOperations() {
		//cria uma instancia de Account
		final Account account = new Account();
		//instancia um Array List
		final List<Operation> operations = new ArrayList<>();
				
		//Spender 
		operations.add(new Operation()); //adiciona um objeto Operation ao ArrayList
		operations.get(0).setBalance(0); //seta os valores determinados para a thread do tipo Spender (Pull, Sleep)
		operations.get(0).setPull(10);
		operations.get(0).setNumberOfPulls(0);
		operations.get(0).setSleep(300L);
		
		//Smart
		operations.add(new Operation()); //Igual ao caso anterior
		operations.get(1).setBalance(0);
		operations.get(1).setPull(50);
		operations.get(1).setNumberOfPulls(0);
		operations.get(1).setSleep(600L);
		
		//Saver
		operations.add(new Operation()); //Igual ao caso anterior
		operations.get(2).setBalance(0);
		operations.get(2).setPull(5);
		operations.get(2).setNumberOfPulls(0);
		operations.get(2).setSleep(1200L);
		
		//Sponsor
		operations.add(new Operation()); //Igual ao caso anterior
		operations.get(3).setBalance(0);
		operations.get(3).setPull(100);
		operations.get(3).setNumberOfPulls(0);
		//Seta o saldo, ID aleatorio e nome do objeto Account
		account.setBalance(1000); 
		account.setNumber((int) (Math.random() * 10000));
		account.setUser("Larissa Pimentel Viana Randow");
		//cria um Array de threads
		final Thread thread[] = new Thread[4];
		
		thread[0] = new Thread();

		thread[1] = new Thread();
		
		thread[2] = new Thread();	
		
		thread[3] = new Thread();	
		//chama o metodo operate do objeto ThreadUseCaseOrquestrator passando como parametros o array thread, o objeto Account e o ArrayList Operations
		this.threadUseCaseOrquestrator.operate(thread, account, operations);

	}
	
}
