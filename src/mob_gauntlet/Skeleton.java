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
public class Skeleton extends Mob{
    public Skeleton(String inputName, int inputMaxHealth, int inputAttack, int inputArmor, int inputMagicRes){
        super (inputName, inputMaxHealth, inputAttack, inputArmor, inputMagicRes);
        this.setMaxHealth(this.getMaxHealth() + 0);
        this.setAttack(this.getAttack() + 0);
        this.setArmor(this.getArmor() + 0);
        this.setMagicRes(this.getMagicRes() + 0);
    }
    
    public void attack(Character x){
        int dmg = (int)Math.ceil(this.getAttack() * 1.5);
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
                + "\nSkeletons have mediocre attack and meiocre health."
                + "\nThey have mediocre armor and magic resistance."
                + "\nThey are the generic mob character.";
        return output;
    }
}
