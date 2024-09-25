/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package account;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jayia
 */
public class Withdraw implements Runnable{
    private Account account;
            
    public Withdraw(Account account) {
        this.account = account;
    }
//    repeatedly withdraws from account
    @Override
        public void run() {
            while (true) {
                account.withdraw((int)(Math.random() * 100) + 1000);
                try {
                    Thread.sleep(1000); //delays to let the withdraw execute
                } catch (InterruptedException ex) {
                    Logger.getLogger(Withdraw.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
}
