
package mob_gauntlet;

public abstract class Mob implements entityInterface{

    private final String NAME;
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private int armor;
    private int magicRes;
    private boolean alive;
    private boolean DoT;
    private int DoTTick;
    private boolean damageDebuff;
    private int damageDebuffTick;
    private boolean defenseDebuff;
    private int defenseDebuffTick;
    

    public Mob(String inputName, int inputMaxHealth, int inputAttack, int inputArmor, int inputMagicRes){
        NAME = inputName;
        currentHealth = inputMaxHealth;
        maxHealth = inputMaxHealth;
        attack = inputAttack;
        armor = inputArmor;
        magicRes = inputMagicRes;
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
    }
    
    public int getMaxHealth(){                  //Accessor for the max health
        return maxHealth;
    }
    
    public void setMaxHealth(int newMaxHealth){
        maxHealth = newMaxHealth;
    }
    
    public boolean getStatus(){
        return alive;
    }
    

    public int getAttack(){                     //Accessor for the health
        return attack;
    }
    
    public void setAttack(int newAttack){                     //Accessor for the health
        attack = newAttack;
    }
    
    public int getArmor(){                     //Accessor for the health
        return armor;
    }
    
    public void setArmor(int newArmor){                     //Accessor for the health
        armor = newArmor;
    }
    
    public int getMagicRes(){
        return magicRes;
    }
    
    public void setMagicRes(int newMagicRes){                     //Accessor for the health
        magicRes = newMagicRes;
    }
    
    public boolean getDoT(){                     //Accessor for the health
        return DoT;
    }
    
    public void setDoT(boolean newDoT){       //Mutator for the health
        DoT = newDoT; 
    }
    
    public int getDoTTick(){
        return DoTTick;
    }
    
    public void setDoTTick(int newDoTTick){
        DoTTick = newDoTTick;
    }
    
    public boolean getDamageDebuff(){                     //Accessor for the health
        return damageDebuff;
    }
    
    public void setDamageDebuff(boolean newdamageDebuff){       //Mutator for the health
        damageDebuff = newdamageDebuff; 
    }
    
    public int getDamageDebuffTick(){
        return damageDebuffTick;
    } public void setDamageDebuffTick(int newDamageDebuffTick){
        damageDebuffTick = newDamageDebuffTick;
    }
    
    public boolean getDefenseDebuff(){                     //Accessor for the health
        return defenseDebuff;
    }
    
    public void setDefenseDebuff(boolean newDefenseDebuff){       //Mutator for the health
        defenseDebuff = newDefenseDebuff; 
    }
    public int getDefenseDebuffTick(){
        return defenseDebuffTick;
    }
    public void setDefenseDebuffTick(int newDefenseDebuffTick){
        defenseDebuffTick = newDefenseDebuffTick;
    }
    
    
    public String toString(){                   //toString method
        String output = "MOB: " + NAME
                + "\nMax Health: " + maxHealth
                + "\nAttack: " + attack
                + "\nArmor: " + armor
                + "\nMagic Resistance: " + magicRes;
        return output;
    }
}
