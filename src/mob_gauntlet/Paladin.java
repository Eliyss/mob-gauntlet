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
public class Paladin extends Character{
    private int strength;
    
    public Paladin(String inputName, int inputMaxHealth, int inputStrength){
        super(inputName, inputMaxHealth);
        this.setMaxHealth(this.getMaxHealth() + 150);
        this.setCurrentHealth(this.getMaxHealth());
        strength = inputStrength + 15;
    }
    
    public int getStrength(){                  //Accessor for the max health
        return strength;
    }
    
    public void setStrength(int newStrength){       //Mutator for the health
        strength = newStrength; 
    }
    
    public void godsFavor(Character x){
        int favor = (int)(Math.random() * ((40 - 15) + 1) + 15);
        x.setCurrentHealth(x.getCurrentHealth() + favor);
        if (x.getCurrentHealth() > x.getMaxHealth()){
            x.setCurrentHealth(x.getMaxHealth());
        }
    }
    
    public void divineShield(Character x){
        x.setDefenseBuff(true);
        x.setDefenseBuffTick(5);
        if (x.getDefenseBuffTick() < 5){
            x.setDefenseBuffTick(5);
        }
    }
    
    public void illuminate(Mob x){
        x.setDamageDebuff(true);
        if (x.getDamageDebuffTick() < 3){
            x.setDamageDebuffTick(3);
        }
    }
    
    public void godsWrath(Mob x){
        double dmg = strength;
        
        if (x.getDefenseDebuff()){
            dmg = dmg * 1.5;
        }
        int t = (int)(dmg * (100.0/((double)(x.getArmor()) + 100.0)));
        x.setCurrentHealth(x.getCurrentHealth() - t);
        
        this.setCurrentHealth(this.getCurrentHealth() + t);
        if (this.getCurrentHealth() > this.getMaxHealth()){
            this.setCurrentHealth(this.getMaxHealth());
        }
    }
    
    public String toString(){                   //toString method
        String output = this.getName()
                + "\n" + this.getMaxHealth()
                + "\n" + (this.getStrength() - 15);
        return output;
    }
            
}
