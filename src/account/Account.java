/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package account;

import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jayia
 */
public class Account {

    // Create a new lock
    private static Lock lock = new ReentrantLock();
    private final int LIMIT = 15000;

    // Create a condition
    private static Condition newDeposit = lock.newCondition();
//    private static Condition newWid = lock.newCondition();

    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        lock.lock();
//            only withdraws if balance is greater than the withdrawal amount
        try {
            while (balance < amount) {
                System.out.println("\t\t\tWait for a deposit");
                newDeposit.await(); //waits for the deposit
            }
            balance -= amount;
            System.out.println("\t\t\tWithdraw " + amount
                    + "\t\t\t\t" + getBalance());
            
            newDeposit.signalAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            if (balance + amount > LIMIT) {
            System.out.println("Deposit exceeds insurance limit of $" + LIMIT);
            newDeposit.await(); // Wait for withdrawal to reduce balance
        }
            balance += amount;
            System.out.println("Deposit " + amount
                    + "\t\t\t\t\t\t\t" + getBalance());

//                signals the thread waiting on this one
            newDeposit.signalAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }

}
