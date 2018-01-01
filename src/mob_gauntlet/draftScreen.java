/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mob_gauntlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javafx.scene.input.MouseButton;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.io.PrintWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



   
public class draftScreen {
    private static int drafts = 1;
    //public static Character;
    
    
    public static int getHumans(){              //Accessor for number of humans
        return drafts;
    }
    
    public draftScreen(){        
        JFrame background = new JFrame("Drafting Party");
        
        background.setSize(800, 700);
        background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setLayout(null);
        
       
        JButton selection1 = new JButton();
        selection1.setBounds(50, 100, 200, 300);
        background.add(selection1);
        
        JButton selection2 = new JButton();
        selection2.setBounds(300, 100, 200, 300);
        background.add(selection2);
        
        JButton selection3 = new JButton();
        selection3.setBounds(550, 100, 200, 300);
        background.add(selection3);
        
        
        JLabel selectionLbl = new JLabel("Please pick a character to add to your party.");
        selectionLbl.setBounds(275, 50, 400, 20);
        background.add(selectionLbl);
        
        JButton back = new JButton("Quit");
        back.setBounds(600, 600, 100, 40);
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
        
        JLabel characterInfo = new JLabel("<html>" + ("Assassins are the masters of stealth. They have high dmg and inflict debuffs."
                + "\nClerics are natural-born healers. They have low dmg."
                + "\nPaladins wield the holy light. They are tanky and have lots of buffs."
                + "\nSamurai are swift nimble masters of the sword. They have high damage."
                + "\nSorcerers seek the dark magics. They have extremely high dmg and debuffs, but no defenses."
                + "\nStormcallers control the wind at their will. They are a jack of all trades.").replaceAll("\\n", "<br>") + "</html>");
        characterInfo.setBounds(130, 400, 550, 200);
        background.add(characterInfo);
        
        background.setVisible(true);
        
        Character choice1 = generateCharacter();
        Character choice2 = generateCharacter();
        Character choice3 = generateCharacter();
        String tempName = "";
        if (drafts == 2){
            try {
                FileReader fileInput = new FileReader("src\\jackkevin_finalproject\\Character1Info.txt");
                BufferedReader reader = new BufferedReader(fileInput);
                tempName = reader.readLine();
            } catch (Exception e){

            }
            while ((choice1.getName()).equals(tempName)){
                choice1 = generateCharacter();
            }
            
            while ((choice2.getName()).equals(tempName)){
                choice2 = generateCharacter();
            }
            
            while ((choice3.getName()).equals(tempName)){
                choice3 = generateCharacter();
            }
        }
        
        
        String choice1String = characterStatsToString(choice1);
        String choice2String = characterStatsToString(choice2);
        String choice3String = characterStatsToString(choice3);
        
        selection1.setText("<html>" + choice1String.replaceAll("\\n", "<br>") + "</html>");
        selection2.setText("<html>" + choice2String.replaceAll("\\n", "<br>") + "</html>");
        selection3.setText("<html>" + choice3String.replaceAll("\\n", "<br>") + "</html>");
        
        addCharacterEventListener(selection1, background, choice1);
        addCharacterEventListener(selection2, background, choice2);
        addCharacterEventListener(selection3, background, choice3);
    }
    
    public static Character generateCharacter(){
        int style = (int)(Math.random() * ((6 - 1) + 1) + 1);
        int health = (int)(Math.random() * ((120 - 80) + 1) + 80);
        int attackStat = (int)(Math.random() * ((40 - 25) + 1) + 25);
        Character x;
        switch (style){
            case 1:
                x = new Cleric("Cleric", health, attackStat);
                break;
            case 2:
                x = new Sorcerer("Sorcerer", health, attackStat);
                break;
            case 3:
                x = new Stormcaller("Stormcaller", health, attackStat);
                break;
            case 4:
                x = new Assassin("Assassin", health, attackStat);
                break;
            case 5:
                x = new Paladin("Paladin", health, attackStat);
                break;
            case 6:
                x = new Samurai("Samurai", health, attackStat);
                break;
            default:
                x = new Stormcaller("Stormcaller", health, attackStat);
                break; 
        }
        return x;
    }
    
    public static String characterStatsToString(Character y){
        String output = "";
        if (y instanceof Cleric){
            output += "Name: " + ((Cleric)y).getName() 
                    + "\nMax Health: " + ((Cleric)y).getMaxHealth() 
                    + "\nMagic: " + ((Cleric)y).getMagic();
        } else if (y instanceof Sorcerer){
            output += "Name: " + ((Sorcerer)y).getName() 
                    + "\nMax Health: " + ((Sorcerer)y).getMaxHealth() 
                    + "\nMagic: " + ((Sorcerer)y).getMagic();
        } else if (y instanceof Stormcaller){
            output += "Name: " + ((Stormcaller)y).getName() 
                    + "\nMax Health: " + ((Stormcaller)y).getMaxHealth() 
                    + "\nMagic: " + ((Stormcaller)y).getMagic();
        } else if (y instanceof Assassin){
            output += "Name: " + ((Assassin)y).getName() 
                    + "\nMax Health: " + ((Assassin)y).getMaxHealth() 
                    + "\nAttack: " + ((Assassin)y).getStrength();
        } else if (y instanceof Paladin){
            output += "Name: " + ((Paladin)y).getName() 
                    + "\nMax Health: " + ((Paladin)y).getMaxHealth() 
                    + "\nAttack: " + ((Paladin)y).getStrength();
        } else if (y instanceof Samurai){
            output += "Name: " + ((Samurai)y).getName() 
                    + "\nMax Health: " + ((Samurai)y).getMaxHealth() 
                    + "\nAttack: " + ((Samurai)y).getStrength();
        }
        return output;
    }
    
    public static String characterToString(Character y){
        if (y instanceof Cleric){
            return ((Cleric)y).toString();
        } else if (y instanceof Sorcerer){
            return ((Sorcerer)y).toString();
        } else if (y instanceof Stormcaller){
            return ((Stormcaller)y).toString();
        } else if (y instanceof Assassin){
            return ((Assassin)y).toString();
        } else if (y instanceof Paladin){
            return ((Paladin)y).toString();
        } else if (y instanceof Samurai){
            return ((Samurai)y).toString();
        }
        return "whoops something died";
    }
    
    public static void addCharacterEventListener(JButton x, JFrame y, Character z){
        x.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { }
                @Override public void mouseExited (MouseEvent e) { }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) {
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        try{
                            File file;
                            if (drafts == 2){
                                file = new File("src\\jackkevin_finalproject\\Character2Info.txt");
                            } else {
                                file = new File("src\\jackkevin_finalproject\\Character1Info.txt");
                            }
                            file.getParentFile().mkdirs();
                            PrintWriter printWriter = new PrintWriter(file);
                            printWriter.println(characterToString(z));
                            printWriter.close();
                        } catch(Exception i){
                            System.out.println("something died");
                        }
                        if (drafts != 2){
                            drafts++;
                            y.setVisible(false);
                            new draftScreen();
                        } else {
                            drafts = 1;
                            y.setVisible(false);
                            Main.next = true;
                        }  
                    }
                }
        });
    }
}
/*/PrintWriter out = new PrintWriter("filename.txt");

Then, write your String to it, just like you would to any output stream:

out.println(text);*/

//Image img = ImageIO.read(getClass().getResource("flag.png"));

/*FileReader fileInput = new FileReader("src\\zhu_haberdasher\\HABERDASHER.txt");
            BufferedReader reader = new BufferedReader(fileInput);*/

/*FileReader fileInput = new FileReader(file);
                            BufferedReader reader = new BufferedReader(fileInput);
                            System.out.println(reader.readLine()); 
                            file.delete(); */