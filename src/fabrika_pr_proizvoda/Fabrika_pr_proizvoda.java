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
public class Fabrika_pr_proizvoda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Throwable { 
        Fabrika fabrika = new Fabrika();
        fabrika.setVisible(true);
        fabrika.t1.start();
        fabrika.t2.start();
    }
    
}
