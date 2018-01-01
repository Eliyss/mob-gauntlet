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
public abstract class Character implements entityInterface{
    private final String NAME;
    private int maxHealth;
    private int currentHealth;
    private boolean alive;
    private boolean defenseBuff;
    private int defenseBuffTick;

    Character(String inputName, int inputMaxHealth){
        NAME = inputName;
        currentHealth = inputMaxHealth;
        maxHealth = inputMaxHealth;
        alive = true;
    }
            
    public String getName(){                    //Accessor for the name
        return NAME;
    }

    public int getCurrentHealth(){                     //Accessor for the health
        return currentHealth;
    }
    
    public void setCurrentHealth(int newHealth){       //Mutator for the health
        currentHealth = newHealth;
        if(currentHealth <= 0){
            currentHealth = 0;
            this.alive = false;
        }
        
        if (currentHealth > 0){
            this.alive = true;
        }
    }
    
    public int getMaxHealth(){                  //Accessor for the max health
        return maxHealth;
    }
    
    public void setMaxHealth(int newMaxHealth){
        maxHealth = newMaxHealth;
    }
    
    public boolean getDefenseBuff(){
        return defenseBuff;
    }
    public void setDefenseBuff(boolean newDefenseBuff){
        defenseBuff = newDefenseBuff;
    }
    
    public boolean getStatus(){
        return alive;
    }
    
    public int getDefenseBuffTick(){
        return defenseBuffTick;
    }
    public void setDefenseBuffTick(int newDefenseBuffTick){
        defenseBuffTick = newDefenseBuffTick;
        if (defenseBuffTick == 0){
            this.setDefenseBuff(false);
            
        }
    }

    public String toString(){                   //toString method
        String output = "MOB: " + NAME
                
                
                + "\nHealth: " + currentHealth + "/" + maxHealth
                + "\n";
        return output;
    }
}
