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
public class Assassin extends Character{
    private int strength;
    private boolean stealthed;
    private boolean sucess = false;
    
    
    public Assassin(String inputName, int inputMaxHealth, int inputStrength){
        super(inputName, inputMaxHealth);
        this.setMaxHealth(this.getMaxHealth());
        this.setCurrentHealth(this.getMaxHealth());
        strength = inputStrength * 2;
    }
    
    public int getStrength(){                  //Accessor for the max health
        return strength;
    }
    
    public void setStrength(int newStrength){       //Mutator for the health
        strength = newStrength; 
    }
        
    public boolean getStealthed(){
        return stealthed;
    } public void setStealthed(boolean newStealthed){ 
        stealthed = newStealthed; 
    } 
    
    public void stealth(){
        this.setDefenseBuff(true);
        if (this.getDefenseBuffTick() < 1){
            this.setDefenseBuffTick(1);
        } 
        this.setStealthed(true);
    }
    
    public void stab(Mob x){
        double dmg = strength;
        if (this.getStealthed()){
            dmg = dmg * 10;
            this.setStealthed(false);
        }
        if (x.getDefenseDebuff()){
            dmg = dmg * 1.5;
        }
        int t = (int)(dmg);
        x.setCurrentHealth(x.getCurrentHealth() - t);
        x.setDefenseDebuff(true);
        if (x.getDefenseDebuffTick() < 2){
            x.setDefenseDebuffTick(2);
        }
    }
    
    public void assassinate(Mob x){
        int PP = 5;
        if (PP != 0){


            double dmg = strength;
            int deathChance = 0;
            if (this.getStealthed()){
                if (x.getDefenseDebuff()){
                    deathChance = (int)(Math.random() * ((10 - 1) + 1) + 1);
                    dmg = dmg * 1.5;
                } else {

                    deathChance = 1;
                }
                dmg = dmg * 10;
                this.setStealthed(false);
            } else {
                if (x.getDefenseDebuff()){
                    dmg = dmg * 1.5;
                }
            }
            int t = (int)(dmg);
            if (deathChance == 1){
                this.setSucess(true);
                x.setCurrentHealth(0);
            } else {
                this.setSucess(false);
                x.setCurrentHealth(x.getCurrentHealth() - t);
            }
        } else {
            
        }
        PP--;
        
    }
    
    
    public boolean getSucess(){
        return sucess;
    }
    
    public void setSucess(boolean newSucess){
        sucess = newSucess;
    }
            
    public void smokescreen(Mob x){
        x.setDamageDebuff(true);
        if (x.getDamageDebuffTick() < 3){
            x.setDamageDebuffTick(3);
        }
    }
    
    public String toString(){                   //toString method
        String output = this.getName()
                + "\n" + this.getMaxHealth()
                + "\n" + this.getStrength() / 2;
        return output;
    }
}
