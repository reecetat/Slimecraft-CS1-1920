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
public class Schlime extends Slime {
    private static final int SPEED = 5;
    private static final Color COLOR = Color.BLUE;
        
    public Schlime (int x, int y) {
        super(SPEED, x, y, COLOR);
    }  
    
    public void fight(Glob glob) {
        if (super.collide(glob)) {
            if (super.getStrength() >= glob.getStrength()) {
                this.didWin(glob);
                glob.die();
            } else {
                glob.didWin(this);
                this.die();
            }
        }
    }
    public Schlime reproduce(Schlime mate) {
        int newX = super.getX() + (int) (Math.random() * 10 - 5);
        int newY = super.getY() + (int) (Math.random() * 10 - 5);
        Schlime baby = new Schlime (newX, newY);
        return baby;
    }
}