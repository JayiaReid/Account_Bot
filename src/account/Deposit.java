/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package account;

/**
 *
 * @author jayia
 */
public class Deposit implements Runnable{
    private Account account;
    
    public Deposit(Account account) {
        this.account = account;
    }
//    deposits repeatedly
     @Override 
        public void run() {
            try {
                while (true) {
                    account.deposit((int)(Math.random() * 100) + 1000);
                    Thread.sleep(1000); //delays to let the withdraw execute
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
}
