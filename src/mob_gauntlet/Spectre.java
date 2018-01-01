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
public class Spectre extends Mob{
    public Spectre(String inputName, int inputMaxHealth, int inputAttack, int inputArmor, int inputMagicRes){
        super (inputName, inputMaxHealth, inputAttack, inputArmor, inputMagicRes);
        this.setMaxHealth(this.getMaxHealth() + 80);
        this.setCurrentHealth(this.getMaxHealth());
        this.setAttack(this.getAttack() + 20);
        this.setArmor(100000);
        this.setMagicRes(this.getMagicRes() + 20);
    }
    
    public void attack(Character x){
        int dmg = this.getAttack();
        if (this.getDamageDebuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        if (x.getDefenseBuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        int strikes = (int)(Math.random() * ((3 - 1) + 1) + 1);
        for (int i = 1; i <= strikes; i++){
            x.setCurrentHealth(x.getCurrentHealth() - dmg);
        }
    }
    
    public String toString(){                   //toString method
        String output = "MOB: " + this.getName()
                + "\nMax Health: " + this.getMaxHealth()
                + "\nAttack: " + this.getAttack()
                + "\nArmor: " + this.getArmor()
                + "\nMagic Resistance: " + this.getMagicRes()
                + "\nSpectres have high attack and high health."
                + "\nThey are immune to physical damage and have high magic resistance."
                + "\nThey have nothing special.";
        return output;
    }
}
