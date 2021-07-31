package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main extends JPanel {
    static ArrayList<snak> blocks = new ArrayList();

    public static void defsnak(ArrayList<snak> blocks){
        for (int i = 0, f = 180; i <= 4; i++, f-=15)
            blocks.add(new snak(f, 180));
    }

    public static void change(ArrayList<snak> blocks){
        for (int i = blocks.size() - 1; i > 0; i--) {
            blocks.get(i).x = blocks.get(i - 1).x;
            blocks.get(i).y = blocks.get(i - 1).y;
        }
    }


    public Main(){
        JFrame fr = new JFrame("snakes");
        fr.add(this);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.setSize(1000,1000);
        fr.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                if (String.valueOf(e.getKeyChar()).equals("w")){
                    change(blocks);
                    blocks.get(0).y-=15;

                }
                if (String.valueOf(e.getKeyChar()).equals("a")){
                    change(blocks);
                    blocks.get(0).x-=15;
                }
                if (String.valueOf(e.getKeyChar()).equals("s")){
                    change(blocks);
                    blocks.get(0).y+=15;
                }

                if (String.valueOf(e.getKeyChar()).equals("d")){
                    change(blocks);
                    blocks.get(0).x+=15;
                    //blocks.get(0).x+=15;
                }


                repaint();

            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
        repaint();
    }

    public static void main(String[] args) {
        defsnak(blocks);
        new Main();
    }

    @Override
    public void paintComponent( Graphics g ){
        super.paintComponent(g);

        /*
        first for draws lines
        */
        for (int i = 0; i < 1000; i+=15) {
            g.drawLine(i,0,i,1000);
            g.drawLine(0,i,1000, i);
        }
/*
рисует змейку
*/

        for (int i = 0; i < blocks.size(); i++){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 0){
                g.setColor(Color.red);
                g.fillRect(blocks.get(i).x, blocks.get(i).y,15,15);
                g.setColor(Color.BLACK);
            } else {
                g.fillRect(blocks.get(i).x, blocks.get(i).y,15,15);
            }

        }
/*
Условие проигрыша
 */

        for (int i = 1; i < blocks.size(); i++)
            if (blocks.get(i).x == blocks.get(0).x && blocks.get(i).y == blocks.get(0).y)
                g.fillRect(0,0,1000,1000);


    }

    public static void move(String let, ArrayList<snak>blocks){
        while(let.equals("d")){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blocks.get(0).x+=15;
        }



    }
}
