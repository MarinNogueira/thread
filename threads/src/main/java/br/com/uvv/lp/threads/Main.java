package br.com.uvv.lp.threads;

import br.com.uvv.lp.threads.gateways.controller.ThreadsController;


public class Main 
{
	
    
	public static void main( String[] args )
    {
		
		final ThreadsController threadsController = new ThreadsController();
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		threadsController.startOperations();
		
    }
}
