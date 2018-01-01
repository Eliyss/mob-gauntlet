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
public class Cleric extends Character{
    private int magic;
    
    public Cleric(String inputName, int inputMaxHealth, int inputMagic){
        super(inputName, inputMaxHealth);
        this.setMaxHealth(this.getMaxHealth() + 50);
        this.setCurrentHealth(this.getMaxHealth());
        magic = inputMagic + 10;
    }
    
    public int getMagic(){                  //Accessor for the max health
        return magic;
    }
    
    public void setMagic(int newMagic){       //Mutator for the health
        magic = newMagic; 
    }
    
    public void revive(Character x){
        x.setCurrentHealth(x.getMaxHealth());
    }
    
    public void healingCircle(Character x, Character y){
        x.setCurrentHealth(x.getCurrentHealth() + (magic + 5) * 2);
        if (x.getCurrentHealth() > x.getMaxHealth()){
            x.setCurrentHealth(x.getMaxHealth());
        }
        
        y.setCurrentHealth(y.getCurrentHealth() + (magic + 5) * 2);
        if (y.getCurrentHealth() > y.getMaxHealth()){
            y.setCurrentHealth(y.getMaxHealth());
        }
    }
    
    public void imbue(Character x){
        x.setCurrentHealth(x.getCurrentHealth() + (magic + 10) * 5);
        if (x.getCurrentHealth() > x.getMaxHealth()){
            x.setCurrentHealth(x.getMaxHealth());
        }
    }
    
    public void smite(Mob x){
        double dmg = magic;
        if (x.getDefenseDebuff()){
            dmg = dmg * 1.5;
        }
        int t = (int)(dmg * (100.0/((double)(x.getMagicRes()) + 100.0)));
        x.setCurrentHealth(x.getCurrentHealth() - t);
        x.setDefenseDebuff(true);
        if (x.getDamageDebuffTick() < 2){
            x.setDamageDebuffTick(2);
        }
    }
    
     public String toString(){                   //toString method
        String output = this.getName()
                + "\n" + this.getMaxHealth()
                + "\n" + (this.getMagic() - 10);
        return output;
    }
}
