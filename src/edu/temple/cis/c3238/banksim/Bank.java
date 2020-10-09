package edu.temple.cis.c3238.banksim;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 * @author Modified by Alexa Delacenserie
 * @author Modified by Tarek Elseify
 */

public class Bank {

    public static final int NTEST = 10;
    private final Account[] accounts;
    private long numTransactions = 0;
    private final int initialBalance;
    private final int numAccounts;
    private boolean open;
    ReentrantLock oneLock;
    
    public Bank(int numAccounts, int initialBalance) 
    {
    	this.open = true;
        this.initialBalance = initialBalance;
        this.numAccounts = numAccounts;
        accounts = new Account[numAccounts];
        oneLock = new ReentrantLock();
        for (int i = 0; i < accounts.length; i++) 
        {
            accounts[i] = new Account(this, i, initialBalance);
        }
        numTransactions = 0;
    }

    public void transfer(int from, int to, int amount) 
    {
    	accounts[from].waitForAvailableFunds(amount);
    	if(!open)
    	{
    		return;
    	}
    	oneLock.lock();
    	try
    	{
    		accounts[from].withdraw(amount);
    		accounts[to].deposit(amount);
    	}
    	finally
    	{
    		oneLock.unlock();
    	}
    	
//    	if (!open)
//    	{
//    		return;
//    	}
//        if (accounts[from].withdraw(amount)) 
//        {
//            accounts[to].deposit(amount);
//        }
        
 //       if (shouldTest()) test();
    }

    public void test() 
    {
    	oneLock.lock();
    	try
    	{
    		int totalBalance = 0;
    		for (Account account : accounts) 
    		{
    			System.out.printf("%-30s %s%n", 
    					Thread.currentThread().toString(), account.toString());
    			totalBalance += account.getBalance();
    		}
    		System.out.printf("%-30s Total balance: %d\n", Thread.currentThread().toString(), totalBalance);
    		if (totalBalance != numAccounts * initialBalance) 
    		{
    			System.out.printf("%-30s Total balance changed!\n", Thread.currentThread().toString());
    			System.exit(0);
    		} 
    		else 
    		{
    			System.out.printf("%-30s Total balance unchanged.\n", Thread.currentThread().toString());
    		}
    	}
    	finally
    	{
    		oneLock.unlock();
    	}
    }

    public int getNumAccounts() {
        return numAccounts;
    }

    
    
    public boolean shouldTest() {
        return ++numTransactions % NTEST == 0;
    }
    
    //Added closeBank() 
    public void closeBank()
    {
    	synchronized (this)
    	{
    		open = false;
    	}
    	for (Account account : accounts)
    	{
    		synchronized(account)
    		{
    			account.notifyAll();
    		}
    	}
    }

    //Added isOpen()
	public synchronized boolean isOpen() 
	{
		return open;
	}

}
