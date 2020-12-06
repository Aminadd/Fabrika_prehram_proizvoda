/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrika_pr_proizvoda;

/**
 *
 * @author Amina
 */
public class Aparat implements Runnable
{ 
    private Traka traka;
    public Aparat(Traka t) { 
        this.traka = t;
    }
    public void run() { 
        while (true) { 
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            traka.remove();
            }
        }
    }
