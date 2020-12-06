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
public class Radnik implements Runnable
{ 
    private Traka traka;
    public Radnik(Traka traka) { 
                    this.traka = traka;
    }
    public void run() { 
        int i=0;
        while (true) { 
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            traka.insert(1);
        }
    }
}
