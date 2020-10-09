package edu.temple.cis.c3238.banksim;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 * @author Modified by Alexa Delacenserie
 * @author Modified by Tarek Elseify
 */
public class Account {

    private volatile int balance;
    private final int id;
    private final Bank myBank;			//Adding private myBank variable

    public Account(Bank myBank, int id, int initialBalance) //adding mybank to the constructor
    {
    	this.myBank = myBank;
        this.id = id;
        balance = initialBalance;
    }

    public int getBalance() 
    {
        return balance;
    }
    
    //Added waitForAvailableFunds()
    public void waitForAvailableFunds(int amount) 
    {
		while (myBank.isOpen() && amount >= balance)
		{
			try 
			{
				wait();
			}
			catch (InterruptedException ex)
			{
				//Nothing
			}
		}
				
	}
    
    public synchronized boolean withdraw(int amount) 
    {
        if (amount <= balance) 
        {
            int currentBalance = balance;
            Thread.yield(); // Try to force collision
            int newBalance = currentBalance - amount;
            balance = newBalance;
            return true;
        } 
        else 
        {
            return false;
        }
    }

    public synchronized void deposit(int amount) 
    {
        int currentBalance = balance;
        Thread.yield();   // Try to force collision
        int newBalance = currentBalance + amount;
        balance = newBalance;
        notifyAll();
    }
    
    @Override
    public String toString() {
        return String.format("Account[%d] balance %d", id, balance);
    }
}
