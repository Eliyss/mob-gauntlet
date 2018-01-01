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
public class asdfghjkl {
    public static void Main(String args){
        double r = Math.pow(1.004, 12);
        double a = 15000;
        double x = -1000;
        int year = 0;
        
        while (a>=0){
            a = a * r + x;
            year++;
            System.out.println(a + "\t"+year);
        }
                
    }
}
