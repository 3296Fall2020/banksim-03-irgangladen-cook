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

//Adding imports
import java.util.concurrent.Semaphore;

public class TestThread extends Thread
{
    
    private final Bank bank;
    Semaphore semaphore = null;		// Adding semaphores
    Semaphore signal = null;		// Adding semaphores
    
    
    public TestThread (Bank b, Semaphore semaphore)
    {
    	bank = b;
    	this.semaphore = semaphore;
    }// end TestThread()
    
    @Override
    public void run()
    {
    	while (bank.isOpen())
    	{
    		//increment the semaphore
    		try
    		{
    			semaphore.acquire(10);
    		}//end try()
    		catch (InterruptedException ex)
    		{
    			//not doing anything here
    		}//end catch()
    		if (bank.shouldTest())
    		{
    			bank.test();
    		}//end if()
    		//decrement the semaphore
    		semaphore.release(10);
    		
    		try
    		{
    			sleep(1);
    		}//end try
    		catch (InterruptedException ex)
    		{
    			//empty
    		}//end catch
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
