/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package account;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 *
 * @author jayia
 */
public class Main {
    
    private static Account account = new Account();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Thread pool for 2 threads
        ExecutorService executor = Executors.newCachedThreadPool();
//        Thread t1  = new Thread(new Deposit(account));
//        Thread t2  = new Thread(new Withdraw(account));
        executor.execute(new Deposit(account));
        executor.execute(new Withdraw(account));
        executor.shutdown();

        System.out.println("Thread 1\t\tThread 2\t\tBalance");
    }
    
}
