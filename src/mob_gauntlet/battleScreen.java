/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mob_gauntlet;

//Imports in nessecary files
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

public class battleScreen {
    //variable declaration
    public static boolean turnDone = false;     //is player done turn
    public static int targetedEffect;           //is the spell targeted
    public static int turn = 0;                 //whose turn is it
    public static Character P1;                 //Player 1 character
    public static Character P2;                 //Player 2 character
    public static Mob E1;                       //enemy mob
    public static int level = 0;
    
    //constructor for the battlescreen
    public battleScreen(){        
        //temporary variables
        String tempName = "";                   //player's name
        int tempHealth = 0;                     //players health
        int tempOtherStat =  tempHealth =0;                  //players attack/strength

        //Readsfrom the data file to get P1's stats
        try {
            FileReader fileInput = new FileReader("src\\mob_gauntlet\\Character1Info.txt");
            BufferedReader reader = new BufferedReader(fileInput);
            tempName = reader.readLine();
            tempHealth = Integer.parseInt(reader.readLine());
            tempOtherStat = Integer.parseInt(reader.readLine());
        } catch (Exception e){
            
        }
        
        P1 = createCharacter(tempName, tempHealth, tempOtherStat);              //creates P1
        
        //Same as above
        try {
            FileReader fileInput = new FileReader("src\\mob_gauntlet\\Character2Info.txt");
            BufferedReader reader = new BufferedReader(fileInput);
            tempName = reader.readLine();
            tempHealth = Integer.parseInt(reader.readLine());
            tempOtherStat = Integer.parseInt(reader.readLine());
        } catch (Exception e){
            System.out.println("whoops something died");
        }
        
        P2 = createCharacter(tempName, tempHealth, tempOtherStat);
        
        E1 = generateWeakMob(1);            //generates a temportary mob (for placeholder purposes)
        
        //Creates a new JFrame and sets its bounds and properites
        JFrame background = new JFrame("Battle!");
        background.setSize(1020, 830);
        background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setLayout(null);
        
        //The following 1500 lines of code generate buttons/labels/other stuff
        //and proceeds to  set its bounds and adjust its properties
        
        //Quit button
        JButton back = new JButton("Quit");
        back.setBounds(50, 100, 100, 40);
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
        
        //Narration box
        JTextArea narrationBox = new JTextArea("test");
        narrationBox.setBounds(70, 625, 550, 125);
        background.add(narrationBox);
        
        //Button for first attack
        JButton attack1 = new JButton();
        attack1.setBounds(700, 625, 125, 50);
        background.add(attack1);
                
        //Button for the second attack
        JButton attack2 = new JButton();
        attack2.setBounds(850, 625, 125, 50);
        background.add(attack2);
        
        //button for the third attack
        JButton attack3 = new JButton();
        attack3.setBounds(700, 700, 125, 50);
        background.add(attack3);
        
        //button for the fourth attack
        JButton attack4 = new JButton();
        attack4.setBounds(850, 700, 125, 50);
        background.add(attack4);
        
        //Description for all attacks (will change
        JLabel attackFlavorText = new JLabel("<html>" + ("This unit is currently shielded \nand will take 50% reduced dmg."
                + "\nTurns remaining: " + P1.getDefenseBuffTick()).replaceAll("\\n", "<br>") + "</html>");
        attackFlavorText.setBounds(650, 500, 400, 90);
        background.add(attackFlavorText);
        
        //Player 1 target button and image placeholder
        JButton player1 = new JButton();
        player1.setBounds(125, 350, 175, 225);
        background.add(player1);
        
        //Player 2 target button and image placeholder
        JButton player2 = new JButton();
        player2.setBounds(400, 350, 175, 225);
        background.add(player2);
        
        //Display of player 1 and player 2
        try{
            //Checks what class P1 is, and gets the respective file, repeat for player 2
            if (P1 instanceof Cleric){
                Image img = ImageIO.read(getClass().getResource("cleric.jpg"));
                player1.setIcon(new ImageIcon (img));
            } else if (P1 instanceof Sorcerer){
                Image img = ImageIO.read(getClass().getResource("sorcerer.png"));
                player1.setIcon(new ImageIcon (img));
            } else if (P1 instanceof Stormcaller){
                Image img = ImageIO.read(getClass().getResource("stormcaller.jpg"));
                player1.setIcon(new ImageIcon (img));
            } else if (P1 instanceof Assassin){
                Image img = ImageIO.read(getClass().getResource("assassin.jpg"));
                player1.setIcon(new ImageIcon (img));
            } else if (P1 instanceof Paladin){
                Image img = ImageIO.read(getClass().getResource("paladin.jpg"));
                player1.setIcon(new ImageIcon (img));
            } else if (P1 instanceof Samurai){
                Image img = ImageIO.read(getClass().getResource("samurai.jpg"));
                player1.setIcon(new ImageIcon (img));
            }
            
            if (P2 instanceof Cleric){
                Image img = ImageIO.read(getClass().getResource("cleric.jpg"));
                player2.setIcon(new ImageIcon (img));
            } else if (P2 instanceof Sorcerer){
                Image img = ImageIO.read(getClass().getResource("sorcerer.png"));
                player2.setIcon(new ImageIcon (img));
            } else if (P2 instanceof Stormcaller){
                Image img = ImageIO.read(getClass().getResource("stormcaller.jpg"));
                player2.setIcon(new ImageIcon (img));
            } else if (P2 instanceof Assassin){
                Image img = ImageIO.read(getClass().getResource("assassin.jpg"));
                player2.setIcon(new ImageIcon (img));
            } else if (P2 instanceof Paladin){
                Image img = ImageIO.read(getClass().getResource("paladin.jpg"));
                player2.setIcon(new ImageIcon (img));
            } else if (P2 instanceof Samurai){
                Image img = ImageIO.read(getClass().getResource("samurai.jpg"));
                player2.setIcon(new ImageIcon (img));
            }
        } catch (Exception e){
        }
        
        //Enemy placeholder
        JButton enemy = new JButton();
        enemy.setBounds(700, 200, 200, 250);
        background.add(enemy);
        
        //the 3 progressbars are visual representations of the players' health and the enemy's
        JProgressBar player1Health = new JProgressBar(0, P1.getMaxHealth());
        player1Health.setValue(P1.getMaxHealth());
        player1Health.setForeground(Color.green);
        player1Health.setBackground(Color.red);
        player1Health.setBounds(125, 300, 175, 20);
        background.add(player1Health);
        
        JProgressBar player2Health = new JProgressBar(0, P2.getMaxHealth());
        player2Health.setValue(P2.getMaxHealth());
        player2Health.setForeground(Color.green);
        player2Health.setBackground(Color.red);
        player2Health.setBounds(400, 300, 175, 20);
        background.add(player2Health);
        
        JProgressBar enemyHealth = new JProgressBar(0, E1.getMaxHealth());
        enemyHealth.setValue(E1.getMaxHealth());
        enemyHealth.setForeground(Color.green);
        enemyHealth.setBackground(Color.red);
        enemyHealth.setBounds(700, 130, 200, 40);
        background.add(enemyHealth);
        
        //The following 3 labels are text representations of the players' nd the enemy's health
        JLabel player1HealthValue = new JLabel(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
        player1HealthValue.setBounds(125, 325, 175, 20);
        background.add(player1HealthValue);
        
        JLabel player2HealthValue = new JLabel(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
        player2HealthValue.setBounds(400, 325, 175, 20);
        background.add(player2HealthValue);
        
        JLabel enemyHealthValue = new JLabel(E1.getCurrentHealth() + "/" + E1.getMaxHealth());
        enemyHealthValue.setBounds(700, 170, 175, 20);
        background.add(enemyHealthValue);
        
        //Player 1 and 2 efense buff icon creation, as well as the description (couldve been more efficient if done the same way as 
        //attack description if i changed the text of a single label instead of making multiple (but i ran out of time))
        JButton player1DefenseBuff = new JButton();
        player1DefenseBuff.setBounds(125, 250, 40, 40);
        background.add(player1DefenseBuff);    
                   
        JButton player2DefenseBuff = new JButton();
        player2DefenseBuff.setBounds(400, 250, 40, 40);
        background.add(player2DefenseBuff);
        
        try {
            Image img = ImageIO.read(getClass().getResource("defenseBuff.png"));
            player1DefenseBuff.setIcon(new ImageIcon (img));
            player2DefenseBuff.setIcon(new ImageIcon (img));
        } catch (Exception e){
        }
        
        JLabel player1DefenseBuffText = new JLabel("<html>" + ("This unit is currently shielded \nand will take 50% reduced dmg."
                + "\nTurns remaining: " + P1.getDefenseBuffTick()).replaceAll("\\n", "<br>") + "</html>");
        player1DefenseBuffText.setBounds(125, 195, 200, 50);
        background.add(player1DefenseBuffText);
        
        JLabel player2DefenseBuffText = new JLabel("<html>" + ("This unit is currently shielded \nand will take 50% reduced dmg."
                + "\nTurns remaining: " + P2.getDefenseBuffTick()).replaceAll("\\n", "<br>") + "</html>"); 
        player2DefenseBuffText.setBounds(400, 195, 200, 50);
        background.add(player2DefenseBuffText);
                
         //Enemy defense, damage and DoT debuff icon creation, as well as the description
        JButton enemyDefenseDebuff = new JButton();
        enemyDefenseDebuff.setBounds(700, 80, 40, 40);
        background.add(enemyDefenseDebuff);
        
        JLabel enemyDefenseDebuffText = new JLabel("<html>" + ("This unit is currently crippled \nand will take 50% increased dmg."
                + "\nTurns remaining: " + E1.getDefenseDebuffTick()).replaceAll("\\n", "<br>") + "</html>");
        enemyDefenseDebuffText.setBounds(700, 25, 200, 50);
        background.add(enemyDefenseDebuffText);
        
        JButton enemyDamageDebuff = new JButton();
        enemyDamageDebuff.setBounds(750, 80, 40, 40);
        background.add(enemyDamageDebuff);
        
        JLabel enemyDamageDebuffText = new JLabel("<html>" + ("This unit is currently exhausted \nand will deal 50% reduced dmg."
                + "\nTurns remaining: " + E1.getDamageDebuffTick()).replaceAll("\\n", "<br>") + "</html>");
        enemyDamageDebuffText.setBounds(700, 25, 200, 50);
        background.add(enemyDamageDebuffText);
        
        JButton enemyDoT = new JButton();
        enemyDoT.setBounds(800, 80, 40, 40);
        background.add(enemyDoT);
        
        JLabel enemyDoTText = new JLabel("<html>" + ("This unit is currently buffetted by \nwind and will take dmg each turn."
                + "\nTurns remaining: " + E1.getDoTTick()).replaceAll("\\n", "<br>") + "</html>");
        enemyDoTText.setBounds(700, 25, 200, 50);
        background.add(enemyDoTText);
        
        try {
            Image img = ImageIO.read(getClass().getResource("defenseDebuff.png"));
            enemyDefenseDebuff.setIcon(new ImageIcon (img));
            img = ImageIO.read(getClass().getResource("damageDebuff.png"));
            enemyDamageDebuff.setIcon(new ImageIcon (img));
            img = ImageIO.read(getClass().getResource("DoT.png"));
            enemyDoT.setIcon(new ImageIcon (img));
        } catch (Exception e){
        }
        
        //Current round display
        JLabel roundText = new JLabel("Round: " + level);
        roundText.setBounds(50, 50, 100, 20);
        background.add(roundText);
        
        //Displays info about the enemy
        JLabel enemyInfoText = new JLabel("<html>" + E1.toString().replaceAll("\\n", "<br>") + "</html>");
        enemyInfoText.setBounds(200, 10, 400, 200);
        background.add(enemyInfoText); 
        
        //Player 1 and 2 stealth buff and stealth buff description        
        JButton player1Stealth = new JButton();
        player1Stealth.setBounds(175, 250, 40, 40);
        background.add(player1Stealth);
        
        JLabel player1StealthText = new JLabel("<html>" + ("This unit is currently stealthed "
                + "\nand will deal 10x dmg on the next attack.").replaceAll("\\n", "<br>") + "</html>");
        player1StealthText.setBounds(125, 195, 200, 50);
        background.add(player1StealthText);
        
        JButton player2Stealth = new JButton();
        player2Stealth.setBounds(450, 250, 40, 40);
        background.add(player2Stealth);
        
        JLabel player2StealthText = new JLabel("<html>" + ("This unit is currently stealthed "
                + "\nand will deal 10x dmg on the next attack.").replaceAll("\\n", "<br>") + "</html>");
        player2StealthText.setBounds(400, 195, 200, 50);
        background.add(player2StealthText);
        
        //Player 1 and 2 channel buff and channel buff description
        JButton player1Channel = new JButton();
        player1Channel.setBounds(225, 250, 40, 40);
        background.add(player1Channel);
        
        JLabel player1ChannelText = new JLabel("<html>" + ("This unit is currently channelling "
                + "\nand will deal 3x dmg on the next attack.").replaceAll("\\n", "<br>") + "</html>");
        player1ChannelText.setBounds(125, 195, 200, 50);
        background.add(player1ChannelText);
        
        JButton player2Channel = new JButton();
        player2Channel.setBounds(500, 250, 40, 40);
        background.add(player2Channel);
        
        JLabel player2ChannelText = new JLabel("<html>" + ("This unit is currently channelling "
                + "\nand will deal 3x dmg on the next attack.").replaceAll("\\n", "<br>") + "</html>");
        player2ChannelText.setBounds(400, 195, 200, 50);
        background.add(player2ChannelText);
        
        try {
            Image img = ImageIO.read(getClass().getResource("stealth.png"));
            player1Stealth.setIcon(new ImageIcon (img));
            player2Stealth.setIcon(new ImageIcon (img));
            img = ImageIO.read(getClass().getResource("channelling.png"));
            player1Channel.setIcon(new ImageIcon (img));
            player2Channel.setIcon(new ImageIcon (img));
        } catch (Exception e){
            System.out.println("failed");
        }
        
        //Attack stats
        JLabel P1Attack = new JLabel("Attack: " + 4);
        P1Attack.setBounds(175, 570, 70, 50);
        background.add(P1Attack);
        
        JLabel P2Attack = new JLabel("Attack: " + 4);
        P2Attack.setBounds(450, 570, 70, 50);
        background.add(P2Attack);
        
        //Makes the background visible       
        background.setVisible(true);
        
        //What happens when the mouse interacts with the Attack1, 2, 3 and 4 buttons
        attack1.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //This is what happens when you mouse over this button
                    //Checks whose turn it is, if it is the first player's, 
                    //checks what class he is, changes the ability discription text,
                    //and makes it visible.
                    //If second turn, same thing but for player 2
                    //Otherwise, do nothing
                    if (turn == 1){
                        if (P1 instanceof Assassin){
                            attackFlavorText.setText("<html>" + ("Stabs the enemy from the shadows." 
                                    + "\nDeals medium physical damage."
                                    + "\nApplies poison to cripple foes for 2 turns."
                                    + "\nDoes 10x dmg cast while stealthed."
                                    + "\nIgnores armor.").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Cleric){
                            attackFlavorText.setText("<html>" + ("Smites the enemy with light." 
                                    + "\nDeals light magical damage."
                                    + "\nCripples enemy with holy light for 2 turns."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Paladin){
                            attackFlavorText.setText("<html>" + ("Summons a divine being to smite the enemy" 
                                    + "\nDeals medium physical damage."
                                    + "\nHeals unit for damage dealt."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Samurai){
                            attackFlavorText.setText("<html>" + ("Slashes at the enemy." 
                                    + "\nDeals medium physical damage."
                                    + "\n1 in 3 chance to crit for 5x dmg."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Sorcerer){
                            attackFlavorText.setText("<html>" + ("Shoots a bolt of dark magic at the enemy." 
                                    + "\nDeals medium magical damage."
                                    + "\nIgnores magic resistance."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Stormcaller){
                            attackFlavorText.setText("<html>" + ("Strikes the enemy with a bolt of thunder." 
                                    + "\nDeals medium magical damage."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else {
                            System.out.println("something died");
                        }
                    } else if (turn == 2){
                        if (P2 instanceof Assassin){
                            attackFlavorText.setText("<html>" + ("Stabs the enemy from the shadows." 
                                    + "\nDeals medium physical damage."
                                    + "\nApplies poison to cripple foes for 2 turns."
                                    + "\nDoes 10x dmg cast while stealthed."
                                    + "\nIgnores armor.").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Cleric){
                            attackFlavorText.setText("<html>" + ("Smites the enemy with light." 
                                    + "\nDeals light magical damage."
                                    + "\nCripples enemy with holy light for 2 turns."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Paladin){
                            attackFlavorText.setText("<html>" + ("Summons a divine being to smite the enemy" 
                                    + "\nDeals medium physical damage."
                                    + "\nHeals unit for damage dealt."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Samurai){
                            attackFlavorText.setText("<html>" + ("Slashes at the enemy." 
                                    + "\nDeals medium physical damage."
                                    + "\n1 in 3 chance to crit for 5x dmg."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Sorcerer){
                            attackFlavorText.setText("<html>" + ("Shoots a bolt of dark magic at the enemy." 
                                    + "\nDeals medium magical damage."
                                    + "\nIgnores magic resistance."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Stormcaller){
                            attackFlavorText.setText("<html>" + ("Strikes the enemy with a bolt of thunder." 
                                    + "\nDeals medium magical damage."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else {
                            System.out.println("something died");
                        }
                    }
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    attackFlavorText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) {
                    //This is what happens when you left click this button
                    //Takes the mob's initial hp, checks whose turn it is
                    //if it is the first player's checks what class it is
                    //execute the first ability of the player
                    //if the ability is targeted, skips the next steps 
                    //turns on targetedeffect and sets turn to 10/11 (10 being P1, 11 being P2)
                    //takes the final hp of the mob and calculates dmg
                    //sets the narrationbox accordingly and sets the turn to done
                    //does the same for P2 if other turn
                    //Otherwise does nothing
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        
                        int mobInitialHP = E1.getCurrentHealth();
                        if (turn == 1){
                            turnMove(P1, narrationBox, mobInitialHP);
                            
                        } else if (turn == 2){
                            turnMove(P2, narrationBox, mobInitialHP);
                        }
                        
                        turnDone = true;
                    }
                }
        });
        attack2.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //This is what happens when you mouse over this button
                    //Checks whose turn it is, if it is the first player's, 
                    //checks what class he is, changes the ability discription text,
                    //and makes it visible.
                    //If second turn, same thing but for player 2
                    //Otherwise, do nothing
                    if (turn == 1){
                        if (P1 instanceof Assassin){
                            attackFlavorText.setText("<html>" + ("Hides in the shadows, preparing for a sneak attack." 
                                    + "\nStealths the assassin."
                                    + "\nTakes 50% reduced dmg while active"
                                    + "\nEnables next attack to deal 10x dmg"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Cleric){
                            attackFlavorText.setText("<html>" + ("Creates a circle of healing around the party." 
                                    + "\nMedium heal for all party members."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Paladin){
                            attackFlavorText.setText("<html>" + ("Summons an angel to heal an ally." 
                                    + "\nMedium heal for 1 party member."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Samurai){
                            attackFlavorText.setText("<html>" + ("Quickly pierces the enemy, shattering their armor." 
                                    + "\nDeals medium physical damage."
                                    + "\nReduces target's defenses, crippling them for 3 turns."
                                    + "\nIgnores armor."
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Sorcerer){
                            attackFlavorText.setText("<html>" + ("Crushes the mind of the enemy." 
                                    + "\nDeals heavy magical damage."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Stormcaller){
                            attackFlavorText.setText("<html>" + ("Summons a tornado to deal DoT to the enemy." 
                                    + "\nDeals medium true damage."
                                    + "\nAffected for 5 turns."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else {
                            System.out.println("something died");
                        }
                    } else if (turn == 2){
                        if (P2 instanceof Assassin){
                            attackFlavorText.setText("<html>" + ("Hides in the shadows, preparing for a sneak attack." 
                                    + "\nStealths the assassin."
                                    + "\nTakes 50% reduced dmg while active"
                                    + "\nEnables next attack to deal 10x dmg"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Cleric){
                            attackFlavorText.setText("<html>" + ("Creates a circle of healing around the party." 
                                    + "\nMedium heal for all party members."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Paladin){
                            attackFlavorText.setText("<html>" + ("Summons an angel to heal an ally." 
                                    + "\nMedium heal for 1 party member."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Samurai){
                            attackFlavorText.setText("<html>" + ("Quickly pierces the enemy, shattering their armor." 
                                    + "\nDeals medium physical damage."
                                    + "\nReduces target's defenses, crippling them for 3 turns."
                                    + "\nIgnores armor."
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Sorcerer){
                            attackFlavorText.setText("<html>" + ("Crushes the mind of the enemy." 
                                    + "\nDeals heavy magical damage."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Stormcaller){
                            attackFlavorText.setText("<html>" + ("Summons a tornado to deal DoT to the enemy." 
                                    + "\nDeals medium true damage."
                                    + "\nAffected for 5 turns."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else {
                            System.out.println("something died");
                        }
                    }
                }
                @Override public void mouseExited (MouseEvent e) {
                    //If mouse leaves, make the description invisible
                    attackFlavorText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) {
                    //This is what happens when you left click this button
                    //Takes the mob's initial hp, checks whose turn it is
                    //if it is the first player's checks what class it is
                    //execute the second ability of the player
                    //if the ability is targeted, skips the next steps 
                    //turns on targetedeffect and sets turn to 10/11 (10 being P1, 11 being P2)
                    //takes the final hp of the mob and calculates dmg
                    //sets the narrationbox accordingly and sets the turn to done
                    //does the same for P2 if other turn
                    //Otherwise does nothing
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        int mobInitialHP = E1.getCurrentHealth();
                        
                        if (turn == 1){
                            if (P1 instanceof Assassin){
                                ((Assassin) (P1)).stealth();
                                narrationBox.setText("The " + P1.getName() + " went invisible!");
                                turnDone = true;
                            } else if (P1 instanceof Cleric){
                                int P1InitialHP = P1.getCurrentHealth();
                                int P2InitialHP = P2.getCurrentHealth();
                                ((Cleric) P1).healingCircle(P1, P2);
                                int P1FinalHP = P1.getCurrentHealth();
                                int P2FinalHP = P2.getCurrentHealth();
                                int P1Heal = P1FinalHP - P1InitialHP;
                                int P2Heal = P2FinalHP - P2InitialHP;
                                narrationBox.setText("The " + P1.getName() + " healed the party!"
                                        + "\nThe " + P1.getName() + " healed for " + P1Heal + " health!"
                                        + "\nThe " + P2.getName() + " healed for " + P2Heal + " health!");
                                turnDone = true;
                            } else if (P1 instanceof Paladin){
                                targetedEffect = 32;
                                narrationBox.setText("Who will the " + P1.getName() + " target?");
                                turn = 10;
                            } else if (P1 instanceof Samurai){
                                ((Samurai) P1).pierce(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                narrationBox.setText("The " + P1.getName() + " peirced the " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                                turnDone = true;
                            } else if (P1 instanceof Sorcerer){
                                ((Sorcerer) P1).mindCrush(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                narrationBox.setText("The " + P1.getName() + " crushed the mind of " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                                turnDone = true;
                            } else if (P1 instanceof Stormcaller){
                                ((Stormcaller) P1).tornado(E1);
                                narrationBox.setText("The " + P1.getName() + " summoned a tornado to engulf the " + E1.getName() + "!");
                                turnDone = true;
                            } else {
                                System.out.println("something died");
                            }
                        } else if (turn == 2){
                            if (P2 instanceof Assassin){
                                ((Assassin) (P2)).stealth();
                                narrationBox.setText("The " + P2.getName() + " went invisible!");
                                turnDone = true;
                            } else if (P2 instanceof Cleric){
                                int P1InitialHP = P1.getCurrentHealth();
                                int P2InitialHP = P2.getCurrentHealth();
                                ((Cleric) P2).healingCircle(P1, P2);
                                int P1FinalHP = P1.getCurrentHealth();
                                int P2FinalHP = P2.getCurrentHealth();
                                int P1Heal = P1FinalHP - P1InitialHP;
                                int P2Heal = P2FinalHP - P2InitialHP;
                                narrationBox.setText("The " + P2.getName() + " healed the party!"
                                        + "\nThe " + P1.getName() + " healed for " + P1Heal + " health!"
                                        + "\nThe " + P2.getName() + " healed for " + P2Heal + " health!");
                                turnDone = true;
                            } else if (P2 instanceof Paladin){
                                targetedEffect = 32;
                                narrationBox.setText("Who will the " + P2.getName() + " target?");
                                turn = 11;
                            } else if (P2 instanceof Samurai){
                                ((Samurai) P2).pierce(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                narrationBox.setText("The " + P2.getName() + " peirced the " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                                turnDone = true;
                            } else if (P2 instanceof Sorcerer){
                                ((Sorcerer) P2).mindCrush(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                narrationBox.setText("The " + P2.getName() + " crushed the mind of " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                                turnDone = true;
                            } else if (P2 instanceof Stormcaller){
                                ((Stormcaller) P2).tornado(E1);
                                narrationBox.setText("The " + P2.getName() + " summoned a tornado to engulf the " + E1.getName() + "!");
                                turnDone = true;
                            } else {
                                System.out.println("something died");
                            }
                        }
                    }    
                }
        });
        attack3.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //This is what happens when you mouse over this button
                    //Checks whose turn it is, if it is the first player's, 
                    //checks what class he is, changes the ability discription text,
                    //and makes it visible.
                    //If second turn, same thing but for player 2
                    //Otherwise, do nothing
                    if (turn == 1){
                        if (P1 instanceof Assassin){
                            attackFlavorText.setText("<html>" + ("Vanishes into the shadows before striking the enemy." 
                                    + "\nDeals heavy physical damage."
                                    + "\nDoes 10x dmg if cast while stealthed."
                                    + "\nIf cast while stealthed, has 1 in 3 chance of instant kill."
                                    + "\nIgnores armor.").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Cleric){
                            attackFlavorText.setText("<html>" + ("Summons the holy light to revive a fallen ally." 
                                    + "\nRevives and fully heals 1 fallen party member."
                                    + "\nCan only be cast on dead party members."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Paladin){
                            attackFlavorText.setText("<html>" + ("Summons divine light to shield one ally." 
                                    + "\nShields 1 party member."
                                    + "\nReduces dmg taken for 5 turns"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Samurai){
                            attackFlavorText.setText("<html>" + ("Strikes the enemy in rapid succession." 
                                    + "\nDeals light physical damage per hit."
                                    + "\nHits 5-11 times."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Sorcerer){
                            attackFlavorText.setText("<html>" + ("Channels dark enegry, preparing for an attack." 
                                    + "\nStarts channeling."
                                    + "\nNext attack does 3x dmg."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Stormcaller){
                            attackFlavorText.setText("<html>" + ("Summons the force of the wind to heal an ally." 
                                    + "\nMedium heal for 1 party member."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else {
                            System.out.println("something died");
                        }
                    } else if (turn == 2){
                        if (P2 instanceof Assassin){
                            attackFlavorText.setText("<html>" + ("Vanishes into the shadows before striking the enemy." 
                                    + "\nDeals heavy physical damage."
                                    + "\nDoes 10x dmg if cast while stealthed."
                                    + "\nIf cast while stealthed, has 1 in 3 chance of instant kill."
                                    + "\nIgnores armor.").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Cleric){
                            attackFlavorText.setText("<html>" + ("Summons the holy light to revive a fallen ally." 
                                    + "\nRevives and fully heals 1 fallen party member."
                                    + "\nCan only be cast on dead party members."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Paladin){
                            attackFlavorText.setText("<html>" + ("Summons divine light to shield one ally." 
                                    + "\nShields 1 party member."
                                    + "\nReduces dmg taken for 5 turns"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Samurai){
                            attackFlavorText.setText("<html>" + ("Strikes the enemy in rapid succession." 
                                    + "\nDeals light physical damage per hit."
                                    + "\nHits 5-11 times."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Sorcerer){
                            attackFlavorText.setText("<html>" + ("Channels dark enegry, preparing for an attack." 
                                    + "\nStarts channeling."
                                    + "\nNext attack does 3x dmg."
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Stormcaller){
                            attackFlavorText.setText("<html>" + ("Summons the force of the wind to heal an ally." 
                                    + "\nMedium heal for 1 party member."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else {
                            System.out.println("something died");
                        }
                    }
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    attackFlavorText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) {
                    //This is what happens when you left click this button
                    //Takes the mob's initial hp, checks whose turn it is
                    //if it is the first player's checks what class it is
                    //execute the third ability of the player
                    //if the ability is targeted, skips the next steps 
                    //turns on targetedeffect and sets turn to 10/11 (10 being P1, 11 being P2)
                    //takes the final hp of the mob and calculates dmg
                    //sets the narrationbox accordingly and sets the turn to done
                    //does the same for P2 if other turn
                    //Otherwise does nothing
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        int mobInitialHP = E1.getCurrentHealth();
                        
                        if (turn == 1){
                            if (P1 instanceof Assassin){
                                ((Assassin) (P1)).assassinate(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                if (((Assassin) P1).getSucess()){
                                    narrationBox.setText("The " + P1.getName() + " emerges from the shadow and strikes!"
                                            + "\nThe " + E1.getName() + " was assassinated!"
                                            + "\nIt did " + dmg + " damage!");
                                } else {
                                    narrationBox.setText("The " + P1.getName() + " struck the " + E1.getName() + "!" 
                                            + "\nIt did " + dmg + " damage!");
                                }
                                turnDone = true;
                            } else if (P1 instanceof Cleric){
                                targetedEffect = 23;
                                narrationBox.setText("Who will the " + P1.getName() + " target?");
                                turn = 10;
                            } else if (P1 instanceof Paladin){
                                targetedEffect = 33;
                                narrationBox.setText("Who will the " + P1.getName() + " target?");
                                turn = 10;
                            } else if (P1 instanceof Samurai){
                                ((Samurai) P1).comboStrike(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                int hits = ((Samurai) P1).getHits();
                                narrationBox.setText("The " + P1.getName() + " struck the " + E1.getName() + " " + hits + " times!"
                                        + "\nIt did " + dmg/hits + " per hit for a total of " + dmg + " damage!");
                                turnDone = true;
                            } else if (P1 instanceof Sorcerer){
                                ((Sorcerer) P1).netherTap();
                                narrationBox.setText("The " + P1.getName() + " started channeling");
                                turnDone = true;
                            } else if (P1 instanceof Stormcaller){
                                targetedEffect = 63;
                                narrationBox.setText("Who will the " + P1.getName() + " target?");
                                turn = 10;
                            } else {
                                System.out.println("something died");
                            }
                        } else if (turn == 2){
                            if (P2 instanceof Assassin){
                                ((Assassin) (P2)).assassinate(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                if (((Assassin) P2).getSucess()){
                                    narrationBox.setText("The " + P2.getName() + " emerges from the shadow and strikes!"
                                            + "\nThe " + E1.getName() + " was assassinated!"
                                            + "\nIt did " + dmg + " damage!");
                                } else {
                                    narrationBox.setText("The " + P2.getName() + " struck the " + E1.getName() + "!" 
                                            + "\nIt did " + dmg + " damage!");
                                }
                                turnDone = true;
                            } else if (P2 instanceof Cleric){
                                targetedEffect = 23;
                                narrationBox.setText("Who will the " + P2.getName() + " target?");
                                turn = 11;
                            } else if (P2 instanceof Paladin){
                                targetedEffect = 33;
                                narrationBox.setText("Who will the " + P2.getName() + " target?");
                                turn = 11;
                            } else if (P2 instanceof Samurai){
                                ((Samurai) P2).comboStrike(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                int hits = ((Samurai) P2).getHits();
                                narrationBox.setText("The " + P2.getName() + " struck the " + E1.getName() + " " + hits + " times!"
                                        + "\nIt did " + dmg/hits + " per hit for a total of " + dmg + " damage!");
                                turnDone = true;
                            } else if (P2 instanceof Sorcerer){
                                ((Sorcerer) P2).netherTap();
                                narrationBox.setText("The " + P2.getName() + " started channeling!");
                                turnDone = true;
                            } else if (P2 instanceof Stormcaller){
                                targetedEffect = 63;
                                narrationBox.setText("Who will the " + P2.getName() + " target?");
                                turn = 11;
                            } else {
                                System.out.println("something died");
                            }
                        }
                    }
                        
                }
        });
        attack4.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //This is what happens when you mouse over this button
                    //Checks whose turn it is, if it is the first player's, 
                    //checks what class he is, changes the ability discription text,
                    //and makes it visible.
                    //If second turn, same thing but for player 2
                    //Otherwise, do nothing
                    if (turn == 1){
                        if (P1 instanceof Assassin){
                            attackFlavorText.setText("<html>" + ("Throws down a smokescreen to surround the enemy." 
                                    + "\nBlinds the enemy, exhausting them for 3 turns."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Cleric){
                            attackFlavorText.setText("<html>" + ("Imbues an ally with life energy." 
                                    + "\nHeavy heal for 1 party member."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Paladin){
                            attackFlavorText.setText("<html>" + ("Summons holy light to blind an enemy." 
                                    + "\nBlinds the enemy, exhausting them for 3 turns."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Samurai){
                            attackFlavorText.setText("<html>" + ("Prepares to sidestep an oncoming attack." 
                                    + "\nReduces damage taken for 1 turn."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Sorcerer){
                            attackFlavorText.setText("<html>" + ("Summons dark energy to plague the enemy." 
                                    + "\nCripples and exhausts for 4 turns."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P1 instanceof Stormcaller){
                            attackFlavorText.setText("<html>" + ("Summon the wind to shield an ally." 
                                    + "\nShields one ally for 3 turns."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else {
                            System.out.println("something died");
                        }
                    } else if (turn == 2){
                        if (P2 instanceof Assassin){
                            attackFlavorText.setText("<html>" + ("Throws down a smokescreen to surround the enemy." 
                                    + "\nBlinds the enemy, exhausting them for 3 turns."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Cleric){
                            attackFlavorText.setText("<html>" + ("Imbues an ally with life energy." 
                                    + "\nHeavy heal for 1 party member."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Paladin){
                            attackFlavorText.setText("<html>" + ("Summons holy light to blind an enemy." 
                                    + "\nBlinds the enemy, exhausting them for 3 turns."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Samurai){
                            attackFlavorText.setText("<html>" + ("Prepares to sidestep an oncoming attack." 
                                    + "\nReduces damage taken for 1 turn."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Sorcerer){
                            attackFlavorText.setText("<html>" + ("Summons dark energy to plague the enemy." 
                                    + "\nCripples and exhausts for 4 turns."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else if (P2 instanceof Stormcaller){
                            attackFlavorText.setText("<html>" + ("Summon the wind to shield an ally." 
                                    + "\nShields one ally for 3 turns."
                                    + "\n"
                                    + "\n"
                                    + "\n").replaceAll("\\n", "<br>") + "</html>");
                            attackFlavorText.setVisible(true);
                        } else {
                            System.out.println("something died");
                        }
                    }
                }
                @Override public void mouseExited (MouseEvent e) {
                    //If mouse leaves, make the description invisible
                    attackFlavorText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) {
                    //This is what happens when you left click this button
                    //Takes the mob's initial hp, checks whose turn it is
                    //if it is the first player's checks what class it is
                    //execute the fourth ability of the player
                    //if the ability is targeted, skips the next steps 
                    //turns on targetedeffect and sets turn to 10/11 (10 being P1, 11 being P2)
                    //takes the final hp of the mob and calculates dmg
                    //sets the narrationbox accordingly and sets the turn to done
                    //does the same for P2 if other turn
                    //Otherwise does nothing
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        if (turn == 1){
                            if (P1 instanceof Assassin){
                                ((Assassin) (P1)).smokescreen(E1);
                                narrationBox.setText("The " + P1.getName() + " put down a smoke screen!");
                                turnDone = true;
                            } else if (P1 instanceof Cleric){
                                targetedEffect = 24;
                                narrationBox.setText("Who will the " + P1.getName() + " target?");
                                turn = 10;
                            } else if (P1 instanceof Paladin){
                                ((Paladin) P1).illuminate(E1);
                                narrationBox.setText("The " + P1.getName() + " illuminated the " + E1.getName() + "!");
                                turnDone = true;
                            } else if (P1 instanceof Samurai){
                                ((Samurai) P1).sidestep();
                                narrationBox.setText("The " + P1.getName() + " prepared to dodge an oncoming attack!");
                                turnDone = true;
                            } else if (P1 instanceof Sorcerer){
                                ((Sorcerer) P1).debilitate(E1);
                                narrationBox.setText("The " + P1.getName() + " debilitated the " + E1.getName() + "!");
                                turnDone = true;
                            } else if (P1 instanceof Stormcaller){
                                targetedEffect = 64;
                                narrationBox.setText("Who will the " + P1.getName() + " target?");
                                turn = 10;
                            } else {
                                System.out.println("something died");
                            }
                        } else if (turn == 2){
                            if (P2 instanceof Assassin){
                                ((Assassin) (P2)).smokescreen(E1);
                                narrationBox.setText("The " + P2.getName() + " put down a smoke screen!");
                                turnDone = true;
                            } else if (P2 instanceof Cleric){
                                targetedEffect = 24;
                                narrationBox.setText("Who will the " + P2.getName() + " target?");
                                turn = 11;
                            } else if (P2 instanceof Paladin){
                                ((Paladin) P2).illuminate(E1);
                                narrationBox.setText("The " + P2.getName() + " illuminated the " + E1.getName() + "!");
                                turnDone = true;
                            } else if (P2 instanceof Samurai){
                                ((Samurai) P2).sidestep();
                                narrationBox.setText("The " + P2.getName() + " prepared to dodge an oncoming attack!");
                                turnDone = true;
                            } else if (P2 instanceof Sorcerer){
                                ((Sorcerer) P2).debilitate(E1);
                                narrationBox.setText("The " + P2.getName() + " debilitated the " + E1.getName() + "!");
                                turnDone = true;
                            } else if (P2 instanceof Stormcaller){
                                targetedEffect = 64;
                                narrationBox.setText("Who will the " + P2.getName() + " target?");
                                turn = 11;
                            } else {
                                System.out.println("something died");
                            }
                        }
                    }
                        
                }
        });
        
        //What happens when the mouse interacts with the player buttons
        player1.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { }
                @Override public void mouseExited (MouseEvent e) { }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) {
                    //This is what happens when you left click this button
                    //Takes the player 1's hp, checks whose turn it is
                    //if it is the first player's checks what class it is
                    //execute the repective ability of the player on player 1
                    //takes the final hp of player 1 and calculates healing
                    //sets the narrationbox accordingly and sets the turn to done
                    //does the same for P2 if other turn
                    //Otherwise does nothing
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        if (turn == 10){
                            int InitialHP = P1.getCurrentHealth();
                            int FinalHP;
                            int Heal;
                            switch(targetedEffect){
                                case 23: 
                                    narrationBox.setText("But it failed!");
                                    turnDone = true;
                                    break;
                                case 24: 
                                   
                                    if (P1.getStatus()){
                                        ((Cleric) P1).imbue(P1);
                                        FinalHP = P1.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P1.getName() + " healed the " + P1.getName() + "!"
                                                + "\nThe " + P1.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 32: 
                                    if (P1.getStatus()){
                                        ((Paladin) P1).godsFavor(P1);
                                        FinalHP = P1.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P1.getName() + " healed the " + P1.getName() + "!"
                                                + "\nThe " + P1.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 33: 
                                    if (P1.getStatus()){
                                        ((Paladin) P1).divineShield(P1);
                                        narrationBox.setText("The " + P1.getName() + " shielded the " + P1.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 63: 
                                    if (P1.getStatus()){
                                        ((Stormcaller) P1).heal(P1);
                                        FinalHP = P1.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P1.getName() + " healed the " + P1.getName() + "!"
                                                + "\nThe " + P1.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    turnDone = true;
                                    break;
                                case 64: 
                                    if (P1.getStatus()){
                                        ((Stormcaller) P1).windwall(P1);
                                        narrationBox.setText("The " + P1.getName() + " summoned the winds to shield the " + P1.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    turnDone = true;
                                    break;
                                default: 
                            }
                        } else if (turn == 11){
                            int InitialHP = P1.getCurrentHealth();
                            int FinalHP;
                            int Heal;
                            switch(targetedEffect){
                                case 23: 
                                    if (P1.getStatus()){
                                        
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    if (P1.getStatus() == false){
                                        ((Cleric) P2).revive(P1);
                                        narrationBox.setText("The " + P2.getName() + " revived the " + P1.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    turnDone = true;
                                    break;
                                case 24: 
                                    if (P1.getStatus()){
                                        ((Cleric) P2).imbue(P1);
                                        FinalHP = P1.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P2.getName() + " healed the " + P1.getName() + "!"
                                                + "\nThe " + P1.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 32: 
                                    if (P1.getStatus()){
                                        ((Paladin) P2).godsFavor(P1);
                                        FinalHP = P1.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P2.getName() + " healed the " + P1.getName() + "!"
                                                + "\nThe " + P1.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 33: 
                                    if (P1.getStatus()){
                                        ((Paladin) P2).divineShield(P1);
                                        narrationBox.setText("The " + P2.getName() + " shielded the " + P1.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 63: 
                                    if (P1.getStatus()){
                                        ((Stormcaller) P2).heal(P1);
                                        FinalHP = P1.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P2.getName() + " healed the " + P1.getName() + "!"
                                                + "\nThe " + P1.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 64: 
                                    if (P1.getStatus()){
                                        ((Stormcaller) P2).windwall(P1);
                                        narrationBox.setText("The " + P2.getName() + " summoned the winds to shield the " + P1.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    turnDone = true;
                                    break;
                                default: 
                            }
                        }
                    } 
                }
        });
        player2.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { }
                @Override public void mouseExited (MouseEvent e) { }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) {
                    //This is what happens when you left click this button
                    //Takes the second player's hp, checks whose turn it is
                    //if it is the first player's checks what class it is
                    //execute the repective ability of the player on player 1
                    //takes the final hp of player 2 and calculates healing
                    //sets the narrationbox accordingly and sets the turn to done
                    //does the same for P2 if other turn
                    //Otherwise does nothing
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        if (turn == 10){
                            int InitialHP = P2.getCurrentHealth();
                            int FinalHP;
                            int Heal;
                            switch(targetedEffect){
                                case 23: 
                                    if (P2.getStatus() == false){
                                        ((Cleric) P1).revive(P2);
                                        narrationBox.setText("The " + P1.getName() + " revived the " + P2.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    turnDone = true;
                                    break;
                                case 24: 
                                   
                                    if (P2.getStatus()){
                                        ((Cleric) P1).imbue(P2);
                                        FinalHP = P2.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P1.getName() + " healed the " + P2.getName() + "!"
                                                + "\nThe " + P2.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 32: 
                                    if (P2.getStatus()){
                                        ((Paladin) P1).godsFavor(P2);
                                        FinalHP = P2.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P1.getName() + " healed the " + P2.getName() + "!"
                                                + "\nThe " + P2.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 33: 
                                    if (P2.getStatus()){
                                        ((Paladin) P1).divineShield(P2);
                                        narrationBox.setText("The " + P1.getName() + " shielded the " + P2.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 63: 
                                    if (P2.getStatus()){
                                        ((Stormcaller) P1).heal(P2);
                                        FinalHP = P2.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P1.getName() + " healed the " + P2.getName() + "!"
                                                + "\nThe " + P2.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    turnDone = true;
                                    break;
                                case 64: 
                                    if (P2.getStatus()){
                                        ((Stormcaller) P1).windwall(P2);
                                        narrationBox.setText("The " + P1.getName() + " summoned the winds to shield the " + P2.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    turnDone = true;
                                    break;
                                default: 
                            }
                        } else if (turn == 11){
                            int InitialHP = P2.getCurrentHealth();
                            int FinalHP;
                            int Heal;
                            switch(targetedEffect){
                                case 23: 
                                    narrationBox.setText("But it failed!");
                                    turnDone = true;
                                    break;
                                case 24: 
                                    if (P2.getStatus()){
                                        ((Cleric) P2).imbue(P2);
                                        FinalHP = P2.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P2.getName() + " healed the " + P2.getName() + "!"
                                                + "\nThe " + P2.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 32: 
                                    if (P2.getStatus()){
                                        ((Paladin) P2).godsFavor(P2);
                                        FinalHP = P2.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P2.getName() + " healed the " + P2.getName() + "!"
                                                + "\nThe " + P2.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 33: 
                                    if (P2.getStatus()){
                                        ((Paladin) P2).divineShield(P2);
                                        narrationBox.setText("The " + P2.getName() + " shielded the " + P2.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 63: 
                                    if (P2.getStatus()){
                                        ((Stormcaller) P2).heal(P2);
                                        FinalHP = P2.getCurrentHealth();
                                        Heal = FinalHP - InitialHP;
                                        narrationBox.setText("The " + P2.getName() + " healed the " + P2.getName() + "!"
                                                + "\nThe " + P2.getName() + " healed for " + Heal + " health!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    
                                    turnDone = true;
                                    break;
                                case 64: 
                                    if (P2.getStatus()){
                                        ((Stormcaller) P2).windwall(P2);
                                        narrationBox.setText("The " + P2.getName() + " summoned the winds to shield the " + P2.getName() + "!");
                                    } else {
                                        narrationBox.setText("But it failed!");
                                    }
                                    turnDone = true;
                                    break;
                                default: 
                            }
                        }
                    } 
                }
        });

        //What happens when the mouse interacts with any buff button
        player1DefenseBuff.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    player1DefenseBuffText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    player1DefenseBuffText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        player2DefenseBuff.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    player2DefenseBuffText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    player2DefenseBuffText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        enemyDefenseDebuff.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    enemyDefenseDebuffText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    enemyDefenseDebuffText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        enemyDamageDebuff.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    enemyDamageDebuffText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    enemyDamageDebuffText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        enemyDoT.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    enemyDoTText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    enemyDoTText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        player1Stealth.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    player1StealthText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    player1StealthText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        player2Stealth.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    player2StealthText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    player2StealthText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        player1Channel.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    player1ChannelText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    player1ChannelText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        player2Channel.addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { 
                    //If mouse enters/hovers over the button, make the description visible
                    player2ChannelText.setVisible(true);
                }
                @Override public void mouseExited (MouseEvent e) { 
                    //If mouse leaves, make the description invisible
                    player2ChannelText.setVisible(false);
                }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) { }
        });
        
        //Default wait time
        int time = 1000;
        level = 0;
        
        //Breaks out to here if everything is dead
        OUTER:
        do{
            //Makes everything invisible
            player1DefenseBuff.setVisible(false);
            player1DefenseBuffText.setVisible(false);
            player2DefenseBuff.setVisible(false);
            player2DefenseBuffText.setVisible(false);
            enemyDefenseDebuff.setVisible(false);
            enemyDefenseDebuffText.setVisible(false);
            enemyDamageDebuff.setVisible(false);
            enemyDamageDebuffText.setVisible(false);
            enemyDoT.setVisible(false);
            enemyDoTText.setVisible(false);
            player1Stealth.setVisible(false);
            player1StealthText.setVisible(false);
            player1Channel.setVisible(false);
            player1ChannelText.setVisible(false);
            player2Stealth.setVisible(false);
            player2StealthText.setVisible(false);
            player2Channel.setVisible(false);
            player2ChannelText.setVisible(false);
            attackFlavorText.setVisible(false);
            
            //Increments the level and updates the round counter
            level++;
            roundText.setText("Round: " + level);

            //If the level is greater than 25
            if (level > 25){
                if (level % 10 == 0){
                    //on any round ending in 0, fully heal and update GUI
                    narrationBox.setText("You come upon a campfire in the dungeon." 
                            + "\nParty's health was restored!");
                    P1.setCurrentHealth(P1.getMaxHealth());
                    P2.setCurrentHealth(P2.getMaxHealth());
                    player1Health.setValue(P1.getCurrentHealth());
                    player2Health.setValue(P2.getCurrentHealth());
                    player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                    player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                    sleep(time*2);
                    narrationBox.setText("Your party leveled up!"
                            + "\n" + (P1.getName()) + "'s health increased by 100!"
                            + "\n" + (P2.getName()) + "'s health increased by 100!");
                    P1.setMaxHealth(P1.getMaxHealth() + 100);
                    P2.setMaxHealth(P2.getMaxHealth() + 100);
                    P1.setCurrentHealth(P1.getMaxHealth());
                    P2.setCurrentHealth(P2.getMaxHealth());
                    player1Health.setMaximum(P1.getMaxHealth());
                    player2Health.setMaximum(P2.getMaxHealth());
                    player1Health.setValue(P1.getCurrentHealth());
                    player2Health.setValue(P2.getCurrentHealth());
                    player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                    player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                    sleep(time*2);
                    
                } else if (level % 5 == 0){
                    //on any round ending in 5, halve hp and update GUI
                    narrationBox.setText("A dark wind blows through you!" 
                        + "\nParty's health was halved!");
                    P1.setCurrentHealth((int)Math.ceil(P1.getCurrentHealth() / 2));
                    P2.setCurrentHealth((int)Math.ceil(P2.getCurrentHealth() / 2));
                    player1Health.setValue(P1.getCurrentHealth());
                    player2Health.setValue(P2.getCurrentHealth());
                    player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                    player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                    sleep(time*2);
                }
            } else if (level % 5 == 0 && level != 25){
                //on any round ending in 5, fully heal and update GUI
                narrationBox.setText("You come upon a campfire in the dungeon." 
                        + "\nParty's health was restored!");
                P1.setCurrentHealth(P1.getMaxHealth());
                P2.setCurrentHealth(P2.getMaxHealth());
                player1Health.setValue(P1.getCurrentHealth());
                player2Health.setValue(P2.getCurrentHealth());
                player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                sleep(time*2);
                narrationBox.setText("Your party leveled up!"
                        + "\n" + (P1.getName()) + "'s health increased by 30!"
                        + "\n" + (P2.getName()) + "'s health increased by 30!");
                P1.setMaxHealth(P1.getMaxHealth() + 30);
                P2.setMaxHealth(P2.getMaxHealth() + 30);
                P1.setCurrentHealth(P1.getMaxHealth());
                P2.setCurrentHealth(P2.getMaxHealth());
                player1Health.setMaximum(P1.getMaxHealth());
                player2Health.setMaximum(P2.getMaxHealth());
                player1Health.setValue(P1.getCurrentHealth());
                player2Health.setValue(P2.getCurrentHealth());
                player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                sleep(time*2);
            }
  
            if (level == 25){
                //heal
                narrationBox.setText("You come upon a campfire in the dungeon." 
                        + "\nParty's health was restored!");
                P1.setCurrentHealth(P1.getMaxHealth());
                P2.setCurrentHealth(P2.getMaxHealth());
                player1Health.setValue(P1.getCurrentHealth());
                player2Health.setValue(P2.getCurrentHealth());
                player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                sleep(time*2);
                
                //message showing that the difficulty has increased
                narrationBox.setText("You feel a chill down your spine!" 
                        + "\nCampfires now appear every 10 rounds!"
                        + "\nSpooky wind appears every 10 rounds!"
                        + "\nStronger mobs now appear!");
                sleep(time * 2);
                narrationBox.setText("Your party prepares for the challenge!"
                        + "\n" + (P1.getName()) + "'s health increased by 150!"
                        + "\n" + (P2.getName()) + "'s health increased by 150!");
                P1.setMaxHealth(P1.getMaxHealth() + 150);
                P2.setMaxHealth(P2.getMaxHealth() + 150);
                P1.setCurrentHealth(P1.getMaxHealth());
                P2.setCurrentHealth(P2.getMaxHealth());
                player1Health.setMaximum(P1.getMaxHealth());
                player2Health.setMaximum(P2.getMaxHealth());
                player1Health.setValue(P1.getCurrentHealth());
                player2Health.setValue(P2.getCurrentHealth());
                player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                sleep(time*2);
                
                //lvls up and triples attack
                String output = "";
                if (P1 instanceof Cleric){
                    ((Cleric) P1).setMagic(((Cleric) P1).getMagic() * 3);
                    output += (P1.getName()) + "'s magic increased by a factor of 3!"   
                        + "\n" + (P1.getName()) + "'s magic is now " + ((Cleric) P1).getMagic() + "!\n";
                } else if (P1 instanceof Sorcerer){
                    ((Sorcerer) P1).setMagic(((Sorcerer) P1).getMagic() * 3);
                    output += (P1.getName()) + "'s magic increased by a factor of 3!"   
                        + "\n" + (P1.getName()) + "'s magic is now " + ((Sorcerer) P1).getMagic() + "!\n";
                } else if (P1 instanceof Stormcaller){
                    ((Stormcaller) P1).setMagic(((Stormcaller) P1).getMagic() * 3);
                    output += (P1.getName()) + "'s magic increased by a factor of 3!"   
                        + "\n" + (P1.getName()) + "'s magic is now " + ((Stormcaller) P1).getMagic() + "!\n";
                } else if (P1 instanceof Assassin){
                    ((Assassin) P1).setStrength(((Assassin) P1).getStrength() * 3);
                    output += (P1.getName()) + "'s strength increased by a factor of 3!"   
                        + "\n" + (P1.getName()) + "'s strength is now " + ((Assassin) P1).getStrength() + "!\n";
                } else if (P1 instanceof Paladin){
                    ((Paladin) P1).setStrength(((Paladin) P1).getStrength() * 3);
                    output += (P1.getName()) + "'s strength increased by a factor of 3!"   
                        + "\n" + (P1.getName()) + "'s strength is now " + ((Paladin) P1).getStrength() + "!\n";
                } else if (P1 instanceof Samurai){
                    ((Samurai) P1).setStrength(((Samurai) P1).getStrength() * 3);
                    output += (P1.getName()) + "'s strength increased by a factor of 3!"   
                        + "\n" + (P1.getName()) + "'s strength is now " + ((Samurai) P1).getStrength() + "!\n";
                }
                
                if (P2 instanceof Cleric){
                    ((Cleric) P2).setMagic(((Cleric) P2).getMagic() * 3);
                    output += (P2.getName()) + "'s magic increased by a factor of 3!"   
                        + "\n" + (P2.getName()) + "'s magic is now " + ((Cleric) P2).getMagic() + "!";
                } else if (P2 instanceof Sorcerer){
                    ((Sorcerer) P2).setMagic(((Sorcerer) P2).getMagic() * 3);
                    output += (P2.getName()) + "'s magic increased by a factor of 3!"   
                        + "\n" + (P2.getName()) + "'s magic is now " + ((Sorcerer) P2).getMagic() + "!";
                } else if (P2 instanceof Stormcaller){
                    ((Stormcaller) P2).setMagic(((Stormcaller) P2).getMagic() * 3);
                    output += (P2.getName()) + "'s magic increased by a factor of 3!"   
                        + "\n" + (P2.getName()) + "'s magic is now " + ((Stormcaller) P2).getMagic() + "!";
                } else if (P2 instanceof Assassin){
                    ((Assassin) P2).setStrength(((Assassin) P2).getStrength() * 3);
                    output += (P2.getName()) + "'s strength increased by a factor of 3!"   
                        + "\n" + (P2.getName()) + "'s strength is now " + ((Assassin) P2).getStrength() + "!";
                } else if (P2 instanceof Paladin){
                    ((Paladin) P2).setStrength(((Paladin) P2).getStrength() * 3);
                    output += (P2.getName()) + "'s strength increased by a factor of 3!"   
                        + "\n" + (P2.getName()) + "'s strength is now " + ((Paladin) P2).getStrength() + "!";
                } else if (P2 instanceof Samurai){
                    ((Samurai) P2).setStrength(((Samurai) P2).getStrength() * 3);
                    output += (P2.getName()) + "'s strength increased by a factor of 3!"   
                        + "\n" + (P2.getName()) + "'s strength is now " + ((Samurai) P2).getStrength() + "!";
                }
                
                //updates attack
                if (P1 instanceof Cleric){
                    P1Attack.setText("Attack: " + ((Cleric) P1).getMagic());
                } else if (P1 instanceof Sorcerer){
                    P1Attack.setText("Attack: " + ((Sorcerer) P1).getMagic());
                } else if (P1 instanceof Stormcaller){
                    P1Attack.setText("Attack: "  + ((Stormcaller) P1).getMagic());
                } else if (P1 instanceof Assassin){
                    P1Attack.setText("Attack: "  + ((Assassin) P1).getStrength());
                } else if (P1 instanceof Paladin){
                    P1Attack.setText("Attack: "  + ((Paladin) P1).getStrength());
                } else if (P1 instanceof Samurai){
                    P1Attack.setText("Attack: "  + ((Samurai) P1).getStrength());
                }

                if (P2 instanceof Cleric){
                    P2Attack.setText("Attack: "  + ((Cleric) P2).getMagic());
                } else if (P2 instanceof Sorcerer){
                    P2Attack.setText("Attack: "  + ((Sorcerer) P2).getMagic());
                } else if (P2 instanceof Stormcaller){
                    P2Attack.setText("Attack: "  + ((Stormcaller) P2).getMagic());
                } else if (P2 instanceof Assassin){
                    P2Attack.setText("Attack: "  + ((Assassin) P2).getStrength());
                } else if (P2 instanceof Paladin){
                    P2Attack.setText("Attack: " + ((Paladin) P2).getStrength());
                } else if (P2 instanceof Samurai){
                    P2Attack.setText("Attack: "  + ((Samurai) P2).getStrength());
                }
                narrationBox.setText(output);
                sleep(time*3);
                
                 
            }
            
            //updates attack
            if (P1 instanceof Cleric){
                P1Attack.setText("Attack: " + ((Cleric) P1).getMagic());
            } else if (P1 instanceof Sorcerer){
                P1Attack.setText("Attack: " + ((Sorcerer) P1).getMagic());
            } else if (P1 instanceof Stormcaller){
                P1Attack.setText("Attack: "  + ((Stormcaller) P1).getMagic());
            } else if (P1 instanceof Assassin){
                P1Attack.setText("Attack: "  + ((Assassin) P1).getStrength());
            } else if (P1 instanceof Paladin){
                P1Attack.setText("Attack: "  + ((Paladin) P1).getStrength());
            } else if (P1 instanceof Samurai){
                P1Attack.setText("Attack: "  + ((Samurai) P1).getStrength());
            }
            
            if (P2 instanceof Cleric){
                P2Attack.setText("Attack: "  + ((Cleric) P2).getMagic());
            } else if (P2 instanceof Sorcerer){
                P2Attack.setText("Attack: "  + ((Sorcerer) P2).getMagic());
            } else if (P2 instanceof Stormcaller){
                P2Attack.setText("Attack: "  + ((Stormcaller) P2).getMagic());
            } else if (P2 instanceof Assassin){
                P2Attack.setText("Attack: "  + ((Assassin) P2).getStrength());
            } else if (P2 instanceof Paladin){
                P2Attack.setText("Attack: " + ((Paladin) P2).getStrength());
            } else if (P2 instanceof Samurai){
                P2Attack.setText("Attack: "  + ((Samurai) P2).getStrength());
            }
            
            
            //generates the mob depending on level
            if (level >= 25){
                E1 = generateStrongMob(level);
            } else {
                E1 = generateWeakMob(level);
            }
            
            //updates the enemy info and makes everything visible
            enemyHealth.setMaximum(E1.getMaxHealth());  
            enemyHealth.setValue(E1.getCurrentHealth()); 
            enemyHealthValue.setText(E1.getCurrentHealth() + "/" + E1.getMaxHealth());
            enemyInfoText.setText("<html>" + E1.toString().replaceAll("\\n", "<br>") + "</html>");
            enemyHealth.setVisible(true);
            enemyHealthValue.setVisible(true);
            enemyInfoText.setVisible(true);
            enemy.setVisible(true);
            
            try{
                if (E1 instanceof Dragon){
                    Image img = ImageIO.read(getClass().getResource("dragon.jpg"));
                    enemy.setIcon(new ImageIcon (img));
                } else if (E1 instanceof ElderDragon){
                    Image img = ImageIO.read(getClass().getResource("elderDragon.jpg"));
                    enemy.setIcon(new ImageIcon (img));
                } else if (E1 instanceof Elemental){
                    Image img = ImageIO.read(getClass().getResource("elemental.png"));
                    enemy.setIcon(new ImageIcon (img));
                } else if (E1 instanceof Ghost){
                    Image img = ImageIO.read(getClass().getResource("ghost.jpg"));
                    enemy.setIcon(new ImageIcon (img));
                } else if (E1 instanceof Orc){
                    Image img = ImageIO.read(getClass().getResource("orc.png"));
                    enemy.setIcon(new ImageIcon (img));
                } else if (E1 instanceof Skeleton){
                    Image img = ImageIO.read(getClass().getResource("skeleton.png"));
                    enemy.setIcon(new ImageIcon (img));
                } else if (E1 instanceof Spectre){
                    Image img = ImageIO.read(getClass().getResource("spectre.jpg"));
                    enemy.setIcon(new ImageIcon (img));
                } else if (E1 instanceof Witch){
                    Image img = ImageIO.read(getClass().getResource("witch.jpg"));
                    enemy.setIcon(new ImageIcon (img));
                } else if (E1 instanceof Zombie){
                    Image img = ImageIO.read(getClass().getResource("zombie.jpg"));
                    enemy.setIcon(new ImageIcon (img));
                }
            } catch (Exception e){
            }
            
            //updates GUI
            narrationBox.setText("A " + E1.getName() + " appeared!");
            sleep(time);
           
            //While the mob isn't dead
            while (E1.getStatus()){
                //If player 1 isnt dead, does it's turn, otherwise skips it
                if(P1.getStatus()){
                    //Changes turn to player 1's
                    turn = 1; 
                    
                    //Updates the attack button text
                    if (P1 instanceof Cleric){
                        attack1.setText("Smite");
                        attack2.setText("Circle Heal");
                        attack3.setText("Revive");
                        attack4.setText("Imbue");
                    } else if (P1 instanceof Sorcerer){
                        attack1.setText("Shadow Bolt");
                        attack2.setText("Mind Crush");
                        attack3.setText("Nether Tap");
                        attack4.setText("Debilitate");
                    } else if (P1 instanceof Stormcaller){
                        attack1.setText("Thunderstrike");
                        attack2.setText("Tornado");
                        attack3.setText("Heal");
                        attack4.setText("Wind Wall");
                    } else if (P1 instanceof Assassin){
                        attack1.setText("Stab");
                        attack2.setText("Stealth");
                        attack3.setText("Assassinate");
                        attack4.setText("Smokescreen");
                    } else if (P1 instanceof Paladin){
                        attack1.setText("God's Wrath");
                        attack2.setText("God's Favor");
                        attack3.setText("Divine Shield");
                        attack4.setText("Illuminate");
                    } else if (P1 instanceof Samurai){
                        attack1.setText("Slash");
                        attack2.setText("Pierce");
                        attack3.setText("Combo");
                        attack4.setText("Sidestep");
                    }

                    //resets the turn variable and updates the GUI
                    turnDone = false;
                    narrationBox.setText("What will the " + P1.getName() + " do?");
                    
                    //something for the program to do while waiting for the user to make a choice
                    while(turnDone == false){
                        sleep(1);
                    }
                    
                    //sets the turn to not player1 or player2
                    turn = 3;

                    //Updates GUI
                    player1Health.setValue(P1.getCurrentHealth());
                    player2Health.setValue(P2.getCurrentHealth());
                    enemyHealth.setValue(E1.getCurrentHealth()); 
                    player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                    player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                    enemyHealthValue.setText(E1.getCurrentHealth() + "/" + E1.getMaxHealth());
                    checkStatus(player1DefenseBuff, player2DefenseBuff, enemyDefenseDebuff, enemyDamageDebuff, enemyDoT, 
                            player1Stealth, player2Stealth, player1Channel, player2Channel,
                            player1DefenseBuffText, player2DefenseBuffText, enemyDefenseDebuffText, enemyDamageDebuffText, 
                            enemyDoTText, player1StealthText, player2StealthText, player1ChannelText, player2ChannelText, 
                            P1, P2, E1);
                    
                    //if enemy dies, 
                    if (E1.getStatus() == false){
                        //break out of the while loop and generates another mob
                        break;
                    }
                    
                    sleep(time);
                }
                
                //If P2 is alive, does the same thing as above
                if (P2.getStatus()){
                    
                    turn = 2;

                    if (P2 instanceof Cleric){
                        attack1.setText("Smite");
                        attack2.setText("Circle Heal");
                        attack3.setText("Revive");
                        attack4.setText("Imbue");
                    } else if (P2 instanceof Sorcerer){
                        attack1.setText("Shadow Bolt");
                        attack2.setText("Mind Crush");
                        attack3.setText("Nether Tap");
                        attack4.setText("Debilitate");
                    } else if (P2 instanceof Stormcaller){
                        attack1.setText("Thunderstrike");
                        attack2.setText("Tornado");
                        attack3.setText("Heal");
                        attack4.setText("Wind Wall");
                    } else if (P2 instanceof Assassin){
                        attack1.setText("Stab");
                        attack2.setText("Stealth");
                        attack3.setText("Assassinate");
                        attack4.setText("Smokescreen");
                    } else if (P2 instanceof Paladin){
                        attack1.setText("God's Wrath");
                        attack2.setText("God's Favor");
                        attack3.setText("Divine Shield");
                        attack4.setText("Illuminate");
                    } else if (P2 instanceof Samurai){
                        attack1.setText("Slash");
                        attack2.setText("Pierce");
                        attack3.setText("Combo");
                        attack4.setText("Sidestep");
                    }

                    turnDone = false;
                    narrationBox.setText("What will the " + P2.getName() + " do?");
                    while(turnDone == false){
                        sleep(1);
                    }
                    turn = 3;

                    player1Health.setValue(P1.getCurrentHealth());
                    player2Health.setValue(P2.getCurrentHealth());
                    enemyHealth.setValue(E1.getCurrentHealth()); 
                    player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                    player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                    enemyHealthValue.setText(E1.getCurrentHealth() + "/" + E1.getMaxHealth());
                    checkStatus(player1DefenseBuff, player2DefenseBuff, enemyDefenseDebuff, enemyDamageDebuff, enemyDoT, 
                            player1Stealth, player2Stealth, player1Channel, player2Channel,
                            player1DefenseBuffText, player2DefenseBuffText, enemyDefenseDebuffText, enemyDamageDebuffText, 
                            enemyDoTText, player1StealthText, player2StealthText, player1ChannelText, player2ChannelText, 
                            P1, P2, E1);
                    
                    if (E1.getStatus() == false){
                        break;
                    }
                    sleep(time);
                }
                
                //Sets turn to mobTurn
                turn = 3;  
                
                //If P1 is dead, then mob attacks P2
                //If P2 is dead, then mob attacks P1
                //Otherwise, randomly attacks someone                
                if (P1.getStatus() == false){
                    mobAttack(E1, P2, P2, narrationBox);
                } else if (P2.getStatus() == false){
                    mobAttack(E1, P1, P1, narrationBox);
                } else {
                    int target = (int)(Math.random() * ((2 - 1) + 1) + 1);
                    if (target == 1){
                        mobAttack(E1, P1, P2, narrationBox);
                    } else {
                        mobAttack(E1, P2, P1, narrationBox);
                    }
                }
                
                //Updates GUI
                player1Health.setValue(P1.getCurrentHealth());
                player2Health.setValue(P2.getCurrentHealth());
                enemyHealth.setValue(E1.getCurrentHealth()); 
                player1HealthValue.setText(P1.getCurrentHealth() + "/" + P1.getMaxHealth());
                player2HealthValue.setText(P2.getCurrentHealth() + "/" + P2.getMaxHealth());
                enemyHealthValue.setText(E1.getCurrentHealth() + "/" + E1.getMaxHealth());
                
                sleep(time);
                
                //If both parties are dead, breaks out of the enire loop
                if (P1.getStatus() == false && P2.getStatus() == false){
                    break OUTER;
                }
                
                //Checks if DoT applies
                if (E1.getDoT()){
                    E1.setCurrentHealth(E1.getCurrentHealth() - 50);
                    narrationBox.setText(E1.getName() + " was buffetted by wind!"
                            + "\n" + E1.getName() + " took 50 damage!");
                    enemyHealth.setValue(E1.getCurrentHealth()); 
                    enemyHealthValue.setText(E1.getCurrentHealth() + "/" + E1.getMaxHealth());
                    
                    if (E1.getStatus() == false){
                        break;
                    }
                }
                
                //Ticks down buff
                if (P1.getDefenseBuff()){
                    P1.setDefenseBuffTick(P1.getDefenseBuffTick() - 1);
                }
                if (P2.getDefenseBuff()){
                    P2.setDefenseBuffTick(P2.getDefenseBuffTick() - 1);
                }
                if (E1.getDefenseDebuff()){
                    E1.setDefenseDebuffTick(E1.getDefenseDebuffTick() - 1);
                }
                if (E1.getDamageDebuff()){
                    E1.setDamageDebuffTick(E1.getDamageDebuffTick() - 1);
                }
                if (E1.getDefenseDebuff()){
                    E1.setDoTTick(E1.getDoTTick() - 1);
                }
                
                //Updates GUI
                checkStatus(player1DefenseBuff, player2DefenseBuff, enemyDefenseDebuff, enemyDamageDebuff, enemyDoT, 
                    player1Stealth, player2Stealth, player1Channel, player2Channel,
                    player1DefenseBuffText, player2DefenseBuffText, enemyDefenseDebuffText, enemyDamageDebuffText, 
                    enemyDoTText, player1StealthText, player2StealthText, player1ChannelText, player2ChannelText, 
                    P1, P2, E1);
                
                sleep(time);
            }
            sleep(time);
            
            //Makes enemy values invisible
            enemyHealth.setVisible(false);
            enemyHealthValue.setVisible(false);
            enemyInfoText.setVisible(false);
            enemy.setVisible(false);
            
            //Removes all buffs
            E1.setDefenseDebuff(false);
            E1.setDamageDebuff(false);
            E1.setDoT(false);
            P1.setDefenseBuff(false);
            P2.setDefenseBuff(false);
            if (P1 instanceof Assassin){
                ((Assassin) P1).setStealthed(false);
            } 
            if (P2 instanceof Assassin){
                ((Assassin) P2).setStealthed(false);
            }
            if (P1 instanceof Sorcerer){
                ((Sorcerer) P1).setChanneled(false);
            }
            if (P2 instanceof Sorcerer){
                ((Sorcerer) P2).setChanneled(false);
            }
            
            //Updates GUI
            checkStatus(player1DefenseBuff, player2DefenseBuff, enemyDefenseDebuff, enemyDamageDebuff, enemyDoT, 
                    player1Stealth, player2Stealth, player1Channel, player2Channel,
                    player1DefenseBuffText, player2DefenseBuffText, enemyDefenseDebuffText, enemyDamageDebuffText, 
                    enemyDoTText, player1StealthText, player2StealthText, player1ChannelText, player2ChannelText, 
                    P1, P2, E1);
            
            //Updates narrationbox
            narrationBox.setText("The " + E1.getName() + " was defeated!");
            sleep(time);
        } while (P1.getStatus() || P2.getStatus());
        
        //Updates narrationbox        
        narrationBox.setText("You died!");
        sleep(time*2);
        Main.next = true;
        background.setVisible(false);
        
    }

    //Mob attack method
    //Takes the current hp of both party members
    //Executes the attack, calculates dmg and updates GUI 
    public static void mobAttack (Mob E1, Character target1, Character target2, JTextArea narrationBox){
        int target1InitialHP = target1.getCurrentHealth();
        int target2InitialHP = target2.getCurrentHealth();
    
        if (E1 instanceof Dragon){
            ((Dragon) (E1)).attack(target1);
            int target1FinalHP = target1.getCurrentHealth();
            int target1Dmg = target1InitialHP - target1FinalHP;
            narrationBox.setText("The " + E1.getName() + " swiped at the " + target1.getName() + "!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
        } else if (E1 instanceof ElderDragon){
            ((ElderDragon) (E1)).attack(target1);
            int target1FinalHP = target1.getCurrentHealth();
            int target1Dmg = target1InitialHP - target1FinalHP;
            narrationBox.setText("The " + E1.getName() + " breathed a fireball at the " + target1.getName() + "!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
        } else if (E1 instanceof Elemental){
            ((Elemental) (E1)).attack(target1);
            int target1FinalHP = target1.getCurrentHealth();
            int target1Dmg = target1InitialHP - target1FinalHP;
            narrationBox.setText("The " + E1.getName() + " struck the " + target1.getName() + " with a crackle of magic!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
        } else if (E1 instanceof Ghost){
            ((Ghost) (E1)).attack(target1);
            int target1FinalHP = target1.getCurrentHealth();
            int target1Dmg = target1InitialHP - target1FinalHP;
            narrationBox.setText("The " + E1.getName() + " cursed the " + target1.getName() + "!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
        } else if (E1 instanceof Orc){
            ((Orc) (E1)).attack(target1, target2);
            int target1FinalHP = target1.getCurrentHealth();
            int target1Dmg = target1InitialHP - target1FinalHP;
            if (target1.equals(target2)){
                narrationBox.setText("The " + E1.getName() + " smelt blood and clubbed the " + target1.getName() + " twice!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
            } else {
                int target2FinalHP = target2.getCurrentHealth();
                int target2Dmg = target2InitialHP - target2FinalHP;
                narrationBox.setText("The " + E1.getName() + " swung at the party!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!"
                + "\n" + target2.getName() + " took " + target2Dmg + " damage!");
            }
        } else if (E1 instanceof Skeleton){
            ((Skeleton) (E1)).attack(target1);
            int target1FinalHP = target1.getCurrentHealth();
            int target1Dmg = target1InitialHP - target1FinalHP;
            narrationBox.setText("The " + E1.getName() + " slashed the " + target1.getName() + "!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
        } else if (E1 instanceof Spectre){
            ((Spectre) (E1)).attack(target1);
            int target1FinalHP = target1.getCurrentHealth();
            int target1Dmg = target1InitialHP - target1FinalHP;
            narrationBox.setText("The " + E1.getName() + " showed the " + target1.getName() + " a nightmare!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
        } else if (E1 instanceof Witch){
            
            if (target1.equals(target2)){
                ((Witch) (E1)).attack1(target1);
                int target1FinalHP = target1.getCurrentHealth();
                int target1Dmg = target1InitialHP - target1FinalHP;
                narrationBox.setText("The " + E1.getName() + " threw a potion at the " + target1.getName() + "!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
            } else {
                ((Witch) (E1)).attack(target1, target2);
                int target1FinalHP = target1.getCurrentHealth();
                int target1Dmg = target1InitialHP - target1FinalHP;
                int target2FinalHP = target2.getCurrentHealth();
                int target2Dmg = target2InitialHP - target2FinalHP;
                narrationBox.setText("The " + E1.getName() + " threw a potion at the party!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!"
                + "\n" + target2.getName() + " took " + target2Dmg + " damage!");
            }
        } else if (E1 instanceof Zombie){
            ((Zombie) (E1)).attack(target1, target2);
            int target1FinalHP = target1.getCurrentHealth();
            int target1Dmg = target1InitialHP - target1FinalHP;
            
            if (target1.equals(target2)){
                narrationBox.setText("The " + E1.getName() + " smelt blood and bit the " + target1.getName() + " twice!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!");
            } else {
                int target2FinalHP = target2.getCurrentHealth();
                int target2Dmg = target2InitialHP - target2FinalHP;
                narrationBox.setText("The " + E1.getName() + " lunged at the party!" 
                + "\n" + target1.getName() + " took " + target1Dmg + " damage!"
                + "\n" + target2.getName() + " took " + target2Dmg + " damage!");
            }
        }
    }

    //Buff check method
    //Literally checks which buffs are active
    //If it is, makes the buff button visible
    public static void checkStatus(JButton P1Defense, JButton P2Defense, JButton EDefense, JButton EDamage, JButton EDoT,
            JButton P1Stealth, JButton P2Stealth, JButton P1Channel, JButton P2Channel,
            JLabel P1DefenseText, JLabel P2Text, JLabel EDefenseText, JLabel EDamageText, JLabel EDoTText, JLabel P1StealthText, 
            JLabel P2StealthText,JLabel P1ChannelText, JLabel P2ChannelText,
            Character P1, Character P2, Mob E1){
        if (P1.getDefenseBuff()){
            P1Defense.setVisible(true);
            P1DefenseText.setText("<html>" + ("This unit is currently shielded \nand will take 50% reduced dmg."
                + "\nTurns remaining: " + P1.getDefenseBuffTick()).replaceAll("\\n", "<br>") + "</html>");
        } else {
            P1Defense.setVisible(false);
            P1DefenseText.setVisible(false);
        }
        
        if (P2.getDefenseBuff()){
            P2Defense.setVisible(true);
            P2Text.setText("<html>" + ("This unit is currently shielded \nand will take 50% reduced dmg."
                + "\nTurns remaining: " + P2.getDefenseBuffTick()).replaceAll("\\n", "<br>") + "</html>");
        } else {
            P2Defense.setVisible(false);
            P2Text.setVisible(false);
        }
        
        if (E1.getDefenseDebuff()){
            EDefense.setVisible(true);
            EDefenseText.setText("<html>" + ("This unit is currently crippled \nand will take 50% increased dmg."
                + "\nTurns remaining: " + E1.getDefenseDebuffTick()).replaceAll("\\n", "<br>") + "</html>");
        } else {
            EDefense.setVisible(false);
            EDefenseText.setVisible(false);
        }
        
        if (E1.getDamageDebuff()){
            EDamage.setVisible(true);
            EDamageText.setText("<html>" + ("This unit is currently exhausted \nand will deal 50% reduced dmg."
                + "\nTurns remaining: " + E1.getDamageDebuffTick()).replaceAll("\\n", "<br>") + "</html>");
        } else {
            EDamage.setVisible(false);
            EDamageText.setVisible(false);
        }
        
        if (E1.getDoT()){
            EDoT.setVisible(true);
            EDoTText.setText("<html>" + ("This unit is currently buffeted by \nwind and will take dmg each turn."
                + "\nTurns remaining: " + E1.getDoTTick()).replaceAll("\\n", "<br>") + "</html>");
        } else {
            EDoT.setVisible(false);
            EDoTText.setVisible(false);
        }       
        
        if (P1 instanceof Assassin){
            if (((Assassin) P1).getStealthed()){
                P1Stealth.setVisible(true);
            } else {
                P1Stealth.setVisible(false);
                P1StealthText.setVisible(false);
            }       
        }
        
        if (P2 instanceof Assassin){
            if (((Assassin) P2).getStealthed()){
                P2Stealth.setVisible(true);
            } else {
                P2Stealth.setVisible(false);
                P2StealthText.setVisible(false);
            }
        }
            
        if (P1 instanceof Sorcerer){
            if (((Sorcerer) P1).getChanneled()){
                P1Channel.setVisible(true);
            } else {
                P1Channel.setVisible(false);
                P1ChannelText.setVisible(false);
            }
        }
        if (P2 instanceof Sorcerer){
            if (((Sorcerer) P2).getChanneled()){
                P2Channel.setVisible(true);
            } else {
                P2Channel.setVisible(false);
                P2ChannelText.setVisible(false);
            }
        }   
    }
          
    //Creates a character
    public static Character createCharacter(String name, int health, int stat){
        Character temp;
        if(name.equals("Cleric")){
            temp = new Cleric(name, health, stat);
        } else if(name.equals("Sorcerer")){
            temp = new Sorcerer(name, health, stat);
        } else if(name.equals("Stormcaller")){
            temp = new Stormcaller(name, health, stat);
        } else if(name.equals("Assassin")){
            temp = new Assassin(name, health, stat);
        } else if(name.equals("Paladin")){
            temp = new Paladin(name, health, stat);
        } else if(name.equals("Samurai")){
            temp = new Samurai(name, health, stat);
        } else {
            temp = new Stormcaller(name, health, stat);
        }
        
        return temp;
    }

    //Mobs scale with the round
    //Generates a strong mob
    public static Mob generateStrongMob(int lvl){
        int style = ((int)(Math.random() * ((4 - 1) + 1) + 1));
        int health = (int)(Math.random() * ((200 - 100) + 1) + 100) + lvl * 15;
        int attackStat = (int)(Math.random() * ((30 - 10) + 1) + 10) + lvl * 3;
        int armor = (int)(Math.random() * ((30 - 20) + 1) + 20) + lvl * 2;
        int magicRes = (int)(Math.random() * ((30 - 20) + 1) + 20) + lvl * 2;
        
        Mob temp;
        switch (style){
            case 1:
                temp = new Orc("Orc", health, attackStat, armor, magicRes);
                break;
            case 2:
                temp = new Spectre("Spectre", health, attackStat, armor, magicRes);
                break;
            case 3:
                temp = new ElderDragon("ElderDragon", health, attackStat, armor, magicRes);
                break;
            case 4:
                temp = new Elemental("Elemental", health, attackStat, armor, magicRes);
                break;
            default:
                temp = new Elemental("Elemental", health, attackStat, armor, magicRes);
                break; 
        }
        return temp;
    }
    
    //Generates a weak mob
    public static Mob generateWeakMob(int lvl){
        int style = ((int)(Math.random() * ((5 - 1) + 1) + 1));
        int health = (int)(Math.random() * ((200 - 100) + 1) + 100) + lvl * 15;
        int attackStat = (int)(Math.random() * ((30 - 10) + 1) + 10) + lvl * 3;
        int armor = (int)(Math.random() * ((30 - 20) + 1) + 20) + lvl * 2;
        int magicRes = (int)(Math.random() * ((30 - 20) + 1) + 20) + lvl * 2;
        
        Mob temp;
        switch (style){
            case 1:
                temp = new Skeleton("Skeleton", health, attackStat, armor, magicRes);
                break;
            case 2:
                temp = new Zombie("Zombie", health, attackStat, armor, magicRes);
                break;
            case 3:
                temp = new Ghost("Ghost", health, attackStat, armor, magicRes);
                break;
            case 4:
                temp = new Dragon("Dragon", health, attackStat, armor, magicRes);
                break;
            case 5:
                temp = new Witch("Witch", health, attackStat, armor, magicRes);
                break;
            default:
                temp = new Skeleton("Skeleton", health, attackStat, armor, magicRes);
                break; 
        }
        return temp;
    }
    
    //To pause the program
    public static void sleep(int x){
        try {
            Thread.sleep(x);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void turnMove(Character x, JTextArea narrationBox, int mobInitialHP){
        if (x instanceof Assassin){
                                ((Assassin) (x)).stab(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                narrationBox.setText("The " + x.getName() + " stabbed the " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                            } else if (x instanceof Cleric){
                                ((Cleric) x).smite(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                narrationBox.setText("The " + x.getName() + " smited the " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                            } else if (x instanceof Paladin){
                                ((Paladin) x).godsWrath(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                narrationBox.setText("The " + x.getName() + " summoned the wrath of God to strike the " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                            } else if (x instanceof Samurai){
                                ((Samurai) x).slash(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                if (((Samurai) x).getSucess()){
                                    narrationBox.setText("The " + x.getName() + " slashed the " + E1.getName() + "!" 
                                        + "\nIt crit for 3x the normal dmg!"
                                        + "\nIt did " + dmg + " damage!");
                                } else {
                                    narrationBox.setText("The " + x.getName() + " slashed the " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                                }
                            } else if (x instanceof Sorcerer){
                                ((Sorcerer) x).shadowBolt(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;

                                narrationBox.setText("The " + x.getName() + " shot a bolt of dark magic at the " + E1.getName() + "!" 
                                        + "\nIt did " + dmg + " damage!");
                            } else if (x instanceof Stormcaller){
                                ((Stormcaller) x).thunderstrike(E1);
                                int mobFinalHP = E1.getCurrentHealth();
                                int dmg = mobInitialHP - mobFinalHP;
                                narrationBox.setText("The " + x.getName() + " struck the " + E1.getName() + " with lightning!" 
                                        + "\nIt did " + dmg + " damage!");
                            } else {
                                System.out.println("something died");
                            }
    }
}
