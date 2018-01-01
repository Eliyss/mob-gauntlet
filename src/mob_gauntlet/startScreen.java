/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mob_gauntlet;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Kevin
 */
public class startScreen {
    
    public startScreen(){
        JFrame background = new JFrame("Duh duh duh duh duh ♪");
        
        background.setSize(600, 500);
        background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setLayout(null);
        
        JLabel name = new JLabel("I'm All Alone Games");
        name.setFont(new Font(name.getName(), Font.PLAIN, 50)); 
        name.setBounds(75, 50, 600, 200);
        background.add(name);
        
        JLabel presents = new JLabel("Presents...");
        presents.setBounds(250, 220, 600, 50);
        background.add(presents);
        
        JLabel title = new JLabel("Gauntet of Bosses");
        title.setFont(new Font(title.getName(), Font.PLAIN, 30)); 
        title.setBounds(150, 280, 400, 50);
        background.add(title);
        
        background.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        background.setVisible(false);
        Main.next = true;
    }
}
