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
public class Zombie extends Mob{
    public Zombie(String inputName, int inputMaxHealth, int inputAttack, int inputArmor, int inputMagicRes){
        super (inputName, inputMaxHealth, inputAttack, inputArmor, inputMagicRes);
        this.setMaxHealth(this.getMaxHealth() + 100);
        this.setCurrentHealth(this.getMaxHealth());
        this.setAttack(this.getAttack() + 10);
        this.setArmor(this.getArmor() - 100);
        if (this.getArmor() <= -100){
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
        
        if(x.equals(y) == false){
            dmg = this.getAttack();
            if (this.getDamageDebuff()){
                dmg = (int)Math.ceil(dmg / 2);
            }
            if (y.getDefenseBuff()){
                dmg = (int)Math.ceil(dmg / 2);
            }
            y.setCurrentHealth(y.getCurrentHealth() - dmg);
        } else {
            x.setCurrentHealth(x.getCurrentHealth() - dmg);
        }
    }
    
    public String toString(){                   //toString method
        String output = "MOB: " + this.getName()
                + "\nMax Health: " + this.getMaxHealth()
                + "\nAttack: " + this.getAttack()
                + "\nArmor: " + this.getArmor()
                + "\nMagic Resistance: " + this.getMagicRes()
                + "\nZombies have high attack and high health."
                + "\nThey have extremely low armor and magic resistance."
                + "\nThey have AoE attacks and will strike twice if a member is dead.";
        return output;
    }
}
