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
public class Orc extends Mob{
    public Orc(String inputName, int inputMaxHealth, int inputAttack, int inputArmor, int inputMagicRes){
        super (inputName, inputMaxHealth, inputAttack, inputArmor, inputMagicRes);
        this.setMaxHealth(this.getMaxHealth() + 500);
        this.setCurrentHealth(this.getMaxHealth());
        this.setAttack(this.getAttack() + 40);
        this.setArmor(this.getArmor() - 40);
        if(this.getArmor() <= -100){
            this.setArmor(-99);
        }
        this.setMagicRes(this.getMagicRes() - 100);
        if (this.getMagicRes() <= -100){
            this.setMagicRes(-99);
        }
    }
    
    public void attack(Character x, Character y){
        int dmg = this.getAttack();
        if (this.getDamageDebuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        if (x.getDefenseBuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        x.setCurrentHealth(x.getCurrentHealth() - dmg);
        
        dmg = this.getAttack();
        if (this.getDamageDebuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        if (y.getDefenseBuff()){
            dmg = (int)Math.ceil(dmg / 2);
        }
        y.setCurrentHealth(y.getCurrentHealth() - dmg);
        
        this.setCurrentHealth(this.getCurrentHealth() + dmg);
        if (this.getCurrentHealth() > this.getMaxHealth()){
            this.setCurrentHealth(this.getMaxHealth());
        }
    }
    
    public String toString(){                   //toString method
        String output = "MOB: " + this.getName()
                + "\nMax Health: " + this.getMaxHealth()
                + "\nAttack: " + this.getAttack()
                + "\nArmor: " + this.getArmor()
                + "\nMagic Resistance: " + this.getMagicRes()
                + "\nOrcs have high attack and extremely high health."
                + "\nThey have extremely low armor and magic resistance."
                + "\nThey have AoE attacks and will strike twice if a member is dead.";
        return output;
    }
}
