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
public interface entityInterface {
    //These are the basic mutators, accessors and methods of a Human
    public String getName();
    public int getCurrentHealth();
    public void setCurrentHealth(int newHealth);
    public int getMaxHealth();
    public String toString();
}
