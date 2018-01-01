/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mob_gauntlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class endScreen {
    public endScreen(){
        JFrame background = new JFrame("You lost!");
        
        background.setSize(300, 400);
        background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setLayout(null);
        
        JLabel lose = new JLabel("You lost!");
        lose.setFont(new Font(lose.getName(), Font.PLAIN, 50)); 
        lose.setBounds(50, 20, 300, 200);
        background.add(lose);
        
        JLabel text = new JLabel("Your score: ");
        text.setFont(new Font(text.getName(), Font.PLAIN, 20)); 
        text.setBounds(75, 100, 300, 200);
        background.add(text);
        
        JLabel score = new JLabel("0" + battleScreen.level);
        score.setFont(new Font(score.getName(), Font.PLAIN, 50)); 
        score.setBounds(100, 140, 300, 200);
        background.add(score);
        
        //Quit button
        JButton back = new JButton("Quit");
        back.setBounds(160, 300, 80, 40);
        background.add(back);
        
        back.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { }
                @Override public void mouseExited (MouseEvent e) { }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { 
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        System.exit(0);
                    }
                }
        });
        background.setVisible(true);
    }
}
