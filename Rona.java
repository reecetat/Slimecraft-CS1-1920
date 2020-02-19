/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;

import java.awt.Color;

/**
 *
 * @author 641775
 */
public class Rona extends Slime {
    private static final int SPEED = 6;
    private static final Color COLOR = Color.GREEN; 
    public Rona (int x, int y) {
        super(SPEED, x, y, COLOR);
    }  
    
    public void fight(Glob glob, Schlime schlime) {
        if (super.collide(glob)) {
            
                this.didWin(glob);
                glob.die();
            } else {         
        if (super.collide(schlime)) {
            
                this.didWin(schlime);
                schlime.die();       
            }
        }
    }
public Rona reproduce(Rona mate) {
        int newX = super.getX() + (int) (Math.random() * 10 - 5);
        int newY = super.getY() + (int) (Math.random() * 10 - 5);
        Rona baby = new Rona (newX, newY);
        return baby;
    }
}


