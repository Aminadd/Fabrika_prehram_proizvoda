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
import java.awt.Color;
import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Traka implements CircularBuffer
{ 
    private static final int vel= 20;
    public int[] traka;
    private int in, out;
    public Semaphore mutex;
    private Semaphore imamesta;
    private Semaphore imaproizvoda;
    private int ukupno;
    public JButton[] buttons;
    public JLabel label2;

    public Traka(JButton[] button, JLabel labels) { 
        traka = new int[vel];
        buttons = button;
        label2 = labels;
        for(int i=0; i<vel; i++) {
            traka[i] = 0;
        }
        in = 0;
        out = 0;
        ukupno=0;
        mutex=new Semaphore(1);
        imamesta = new Semaphore(1);
        imaproizvoda = new Semaphore(0);
    }

    Traka() {
    }
	
    public void Stampaj()
    {
        System.out.print("Stanje na traci: ");
        for(int i=0;i<this.vel;i++)
        {
                System.out.print(traka[i] + ", ");
        }
    }

    public void insert(int k) 
    { 	
        while(ukupno>=vel-4)
        {
            try {
                    imamesta.acquire();
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
        }
        try {
                mutex.acquire();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        for(int i=0;i<5;i++)
        {
        traka[in] = k;
        buttons[in].setBackground(Color.RED);
        buttons[in].setOpaque(true);
        label2.setIcon(new ImageIcon("C:/Users/Amina/Documents/NetBeansProjects/Fabrika_pr_proizvoda/src/fabrika_pr_proizvoda/images.png"));
        in = (in + 1) % vel;
        ukupno++;
        }
        System.out.println("Producer dodao 5 pa ima " + ukupno + " objekata u baferu");
        imaproizvoda.release();
        Stampaj();
        mutex.release();
    }

    public void remove() { 

        while(ukupno<3)
        {
        try {
                imaproizvoda.acquire();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        }

        try {
                mutex.acquire();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        for(int i=0;i<3;i++)
        {
                traka[out]=0;
                buttons[out].setBackground(new JButton().getBackground());
                label2.setIcon(new ImageIcon("C:/Users/Amina/Documents/NetBeansProjects/Fabrika_pr_proizvoda/src/fabrika_pr_proizvoda/download.jpg"));
                out = (out + 1) % vel;
                ukupno--;
        }
        System.out.println("Consumer uzeo pa ima " + ukupno + " objekata u baferu");
        if(ukupno<=15)
        {
        imamesta.release();
        }
        Stampaj();
        mutex.release();
    }

    @Override
    public void insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

