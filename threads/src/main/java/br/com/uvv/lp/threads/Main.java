package br.com.uvv.lp.threads;

import br.com.uvv.lp.threads.gateways.controller.ThreadsController;


public class Main 
{    
	public static void main( String[] args )
    {
		//instancia um objeto threadsController
		final ThreadsController threadsController = new ThreadsController();
		//chama o metodo startOperations do threadsController				
		threadsController.startOperations();
		
    }
}
