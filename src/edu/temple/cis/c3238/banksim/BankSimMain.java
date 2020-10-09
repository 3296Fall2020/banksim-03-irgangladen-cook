package edu.temple.cis.c3238.banksim;

//import java.util.concurrent.Semaphore;
/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 * @author Modified by Alexa Delacenserie
 * @author Modified by Tarek Elseify
 */
public class BankSimMain {

    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) throws InterruptedException 
    {
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        Thread[] threads = new Thread[NACCOUNTS];
        //Semaphore semaphore = new Semaphore(NACCOUNTS);
        Thread testThread = new TestThread(b);

        // Start a thread for each account.
        for (int i = 0; i < NACCOUNTS; i++) 
        {
            threads[i] = new TransferThread(b, i, INITIAL_BALANCE);
            threads[i].start();
        }
        testThread.start();
        

        //b.test();
        System.out.printf("%-30s Bank transfer is in process.\n", Thread.currentThread().toString());

          
    }
}


