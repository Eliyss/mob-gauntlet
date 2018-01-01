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

public class Main {
    
    public static boolean next = false;
    public static void main(String[] args){
        new startScreen();
        while (next == false){
            sleep(1);
        }
        next = false;
        new draftScreen();
        while (next == false){
            sleep(1);
        }
        next = false;

        new battleScreen();
        while (next == false){
           sleep(1);
        }
        new endScreen();
    }
    
    public static void sleep(int x){
        try {
            Thread.sleep(x);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
