/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mob_gauntlet;

/**
 *
 * @author Kevin
 */
import javax.swing.*;
public class Samurai extends Character{
    private int strength;
    private int hits;
    private boolean sucess = false;

    
    public Samurai(String inputName, int inputMaxHealth, int inputStrength){
        super(inputName, inputMaxHealth);
        this.setMaxHealth(this.getMaxHealth() + 100);
        this.setCurrentHealth(this.getMaxHealth());
        strength = inputStrength*3;
    }
    
    public int getStrength(){                  //Accessor for the max health
        return strength;
    }
    
    public void setStrength(int newStrength){       //Mutator for the health
        strength = newStrength; 
    }
    
    public int getHits(){                  //Accessor for the max health
        return hits;
    }
    
    public void setHits(int newHits){       //Mutator for the health
        hits = newHits; 
    }
    
    public boolean getSucess(){
        return sucess;
    }
    
    public void setSucess(boolean newSucess){
        sucess = newSucess;
    }
    
    public void pierce(Mob x){
        double dmg = strength;
        if (x.getDefenseDebuff()){
            dmg = dmg * 1.5;
        }
        int t = (int)(dmg);
        x.setCurrentHealth(x.getCurrentHealth() - t);
        x.setDefenseDebuff(true);
        if (x.getDamageDebuffTick() < 3){
            x.setDamageDebuffTick(3);
        }
    }
    
    public void comboStrike(Mob x){
        double dmg = strength / 2;
        if (x.getDefenseDebuff()){
            dmg = dmg * 1.5;
        }
        int t = (int)(dmg * (100.0/((double)(x.getArmor()) + 100.0)));
        
        int strikes = (int)(Math.random() * ((11 - 5) + 1) + 5);
        for (int i = 1; i <= strikes; i++){
            x.setCurrentHealth(x.getCurrentHealth() - t);
        }
        hits = strikes;
    }
    
    
    
    public void slash(Mob x){
        int critChance = (int)(Math.random() * ((3 - 1) + 1) + 1);       
        double dmg = strength * 1.5;
        if (critChance == 1){
            dmg = dmg * 3;
            this.setSucess(true);
        } else {
            this.setSucess(false);
        }
        if (x.getDefenseDebuff()){
            dmg = dmg * 1.5;
        }
        int t = (int)(dmg * (100.0/((double)(x.getArmor()) + 100.0)));
        x.setCurrentHealth(x.getCurrentHealth() - t);
    }
    
    public void sidestep(){
        this.setDefenseBuff(true);
        if (this.getDefenseBuffTick() < 1){
            this.setDefenseBuffTick(1);
        }   
    }
    
    public String toString(){                   //toString method
        String output = this.getName()
                + "\n" + this.getMaxHealth()
                + "\n" + this.getStrength() / 3;
        return output;
    }
}
