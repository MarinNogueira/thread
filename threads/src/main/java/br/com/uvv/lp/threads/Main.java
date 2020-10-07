package br.com.uvv.lp.threads;

import br.com.uvv.lp.threads.gateways.controller.ThreadsController;


public class Main 
{    
	public static void main( String[] args )
    {
		//teste
		final ThreadsController threadsController = new ThreadsController();
				
		threadsController.startOperations();
		
    }
}
