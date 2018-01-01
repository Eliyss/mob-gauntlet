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
public class Stormcaller extends Character {
    private int magic;
    
    public Stormcaller(String inputName, int inputMaxHealth, int inputMagic){
        super(inputName, inputMaxHealth);
        this.setMaxHealth(this.getMaxHealth() + 100);
        this.setCurrentHealth(this.getMaxHealth());
        magic = inputMagic * 2;
    }
    
    public int getMagic(){                  //Accessor for the max health
        return magic;
    }
    
    public void setMagic(int newMagic){       //Mutator for the health
        magic = newMagic; 
    }
    
    public void tornado(Mob x){
        x.setDoT(true);
        if (x.getDefenseDebuff()){
            x.setDoTTick(5);
        } else {
            x.setDoTTick(3);
        }
    }
    
    public void thunderstrike(Mob x){
        double dmg = magic * 2;
        if (x.getDefenseDebuff()){
            dmg = dmg * 1.5;
        }
        int t = (int)(dmg * (100.0/((double)(x.getMagicRes()) + 100.0)));
        x.setCurrentHealth(x.getCurrentHealth() - t);
    }
    
    public void heal(Character x){
        x.setCurrentHealth(x.getCurrentHealth() + magic);
        if (x.getCurrentHealth() > x.getMaxHealth()){
            x.setCurrentHealth(x.getMaxHealth());
        }
    }
    
    public void windwall(Character x){
        x.setDefenseBuff(true);
        if (x.getDefenseBuffTick() < 3){
            x.setDefenseBuffTick(3);
        }
    }
    
    public String toString(){                   //toString method
        String output = this.getName()
                + "\n" + this.getMaxHealth()
                + "\n" + this.getMagic() / 2;
        return output;
    }
}
