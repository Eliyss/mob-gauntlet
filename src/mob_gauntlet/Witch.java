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
public class Witch extends Mob{
    public Witch(String inputName, int inputMaxHealth, int inputAttack, int inputArmor, int inputMagicRes){
        super (inputName, inputMaxHealth, inputAttack, inputArmor, inputMagicRes);
        this.setMaxHealth(this.getMaxHealth() - 30);
        this.setCurrentHealth(this.getMaxHealth());
        this.setAttack(this.getAttack() + 15);
        this.setArmor(this.getArmor() - 40);
        if(this.getArmor() <= -100){
            this.setArmor(-99);
        }
        this.setMagicRes(this.getMagicRes() + 40);
    }
    
    public void attack(Character x, Character y){
        int dmg = (int)Math.ceil(this.getAttack() * 0.75);
        if (this.getDamageDebuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        if (x.getDefenseBuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        x.setCurrentHealth(x.getCurrentHealth() - dmg);
        
        dmg = (int)Math.ceil(this.getAttack() * 0.75);
        if (this.getDamageDebuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        if (y.getDefenseBuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        y.setCurrentHealth(y.getCurrentHealth() - dmg);
    }
    
    public void attack1(Character x){
        int dmg = (int)Math.ceil(this.getAttack() * 0.75);
        if (this.getDamageDebuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        if (x.getDefenseBuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        x.setCurrentHealth(x.getCurrentHealth() - dmg);
    }
    
    public String toString(){                   //toString method
        String output = "MOB: " + this.getName()
                + "\nMax Health: " + this.getMaxHealth()
                + "\nAttack: " + this.getAttack()
                + "\nArmor: " + this.getArmor()
                + "\nMagic Resistance: " + this.getMagicRes()
                + "\nWitches have high attack and low health."
                + "\nThey have low armor and high magic resistance."
                + "\nThey have AoE attacks.";
        return output;
    }
}
