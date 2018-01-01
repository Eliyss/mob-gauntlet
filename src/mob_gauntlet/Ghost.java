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
public class Ghost extends Mob{
    public Ghost(String inputName, int inputMaxHealth, int inputAttack, int inputArmor, int inputMagicRes){
        super (inputName, inputMaxHealth, inputAttack, inputArmor, inputMagicRes);
        this.setMaxHealth(this.getMaxHealth() - 20);
        this.setCurrentHealth(this.getMaxHealth());
        this.setAttack(this.getAttack() + 0);
        this.setArmor(this.getArmor() + 30);        
        this.setMagicRes(this.getMagicRes() -40);
        if (this.getMagicRes() <= -100){
            this.setMagicRes(-99);
        }
    }
    
    public void attack(Character x){
        int dmg = this.getAttack();
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
                + "\nGhosts have mediocre attack and low health."
                + "\nThey have high armor and low magic resistance."
                + "\nThey are not special in any way";
        return output;
    }
}
