
package mob_gauntlet;


public class Sorcerer extends Character {
    private int magic;
    private boolean channeled = false;
    
    public Sorcerer(String inputName, int inputMaxHealth, int inputMagic){
        super(inputName, inputMaxHealth);
        this.setMaxHealth(this.getMaxHealth() + 50);
        this.setCurrentHealth(this.getMaxHealth());
        this.setMagic(inputMagic * 3);
    }
    
    public int getMagic(){
        return magic;
    } public void setMagic(int newMagic){
        magic = newMagic; 
    }
    
    public boolean getChanneled(){
        return channeled;
    } public void setChanneled(boolean newChanneled){ 
        channeled = newChanneled; 
    } 
    
    public void mindCrush(Mob x){                                      
        double dmg = magic * 3;
        if (this.getChanneled()){
            dmg = dmg * 3;
            this.setChanneled(false);
        }
        if (x.getDefenseDebuff()){
            dmg = dmg * 2;
        }
        
        int t = (int)(dmg * (100.0/((double)(x.getMagicRes()) + 100.0)));

        x.setCurrentHealth(x.getCurrentHealth() - t);
    }
    
    public void shadowBolt(Mob x){                                      
        double dmg = magic;
        if (this.getChanneled()){
            dmg = dmg * 3;
            this.setChanneled(false);
        }
        if (x.getDefenseDebuff()){
            dmg = dmg * 2;
        }
                int t = (int)(dmg);
        x.setCurrentHealth(x.getCurrentHealth() - t);
    }
    
    public void netherTap(){
        this.setChanneled(true);
    }
            
    public void debilitate(Mob x){
        x.setDamageDebuff(true);
        x.setDefenseDebuff(true);
        if (x.getDamageDebuffTick() < 4){
            x.setDamageDebuffTick(4);
        }
        if (x.getDefenseDebuffTick() < 4){
            x.setDefenseDebuffTick(4);
        }
    } 
    
    public String toString(){                   //toString method
        String output = this.getName()
                + "\n" + this.getMaxHealth()
                + "\n" + this.getMagic() / 3;
        return output;
    }
    //test if this works
}
