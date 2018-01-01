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
public class Elemental extends Mob{
    public Elemental(String inputName, int inputMaxHealth, int inputAttack, int inputArmor, int inputMagicRes){
        super (inputName, inputMaxHealth, inputAttack, inputArmor, inputMagicRes);
        this.setMaxHealth(this.getMaxHealth() + 100);
        this.setCurrentHealth(this.getMaxHealth());
        this.setAttack(this.getAttack() + 100);
        this.setArmor(this.getArmor() - 150);
        if (this.getArmor() <= -100){
            this.setArmor(-99);
        }
        this.setMagicRes(this.getMagicRes() + 100);
    }
    
    public void attack(Character x){
        System.out.println(this.getAttack());
        
        int dmg = this.getAttack();
        System.out.println(dmg);
        x.setCurrentHealth(x.getCurrentHealth() - dmg);
    }
    
    
    public String toString(){                   //toString method
        String output = "MOB: " + this.getName()
                + "\nMax Health: " + this.getMaxHealth()
                + "\nAttack: " + this.getAttack()
                + "\nArmor: " + this.getArmor()
                + "\nMagic Resistance: " + this.getMagicRes()
                + "\nElementals have extremely high attack and low health."
                + "\nThey have extremely low armor and high magic resistance.";
        return output;
    }
}
