/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis.c3238.banksim;

/**
 *
 * @author talia
 * @author Modified by wild_
 */


public class TestThread extends Thread
{
    
    private final Bank bank;
    
    
    public TestThread (Bank b)
    {
    	bank = b;
    }// end TestThread()
    
    @Override
    public void run()
    {
    	while (bank.isOpen())
    	{
    		if (bank.shouldTest())
    		{
    			bank.test();
    		}
    		try
    		{
    			sleep(1);
    		}
    		catch (InterruptedException ex)
    		{
    			//nothing
    		}
    	}//end while()
    }//end run()
    
    
    
    
    
    
    
    
    
    
    
//    public TestThread(Bank b){
//        bank = b;
//    }
//    
//    @Override
//    public void run(){
//        bank.test();
//        
//    }
}
