package slimecraft;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author 641775
 */
public class World extends JPanel {
    private ArrayList<Schlime> schlimes = new ArrayList<>();    
    private ArrayList<Glob> globs = new ArrayList<>();    
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();   
    private ArrayList<Slime> slimes = new ArrayList<>();    
    private ArrayList<Rona> ronas = new ArrayList<>(); 
    Timer timer;
    
    public World() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/12);
        super.setSize(800, 600);
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800 / 2);
            int y = (int) (Math.random() * 600);
            Schlime schlime = new Schlime(x,y);
            sprites.add(schlime);
            slimes.add(schlime);
            schlimes.add(schlime);
        }
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800 / 2 + 800 / 2);
            int y = (int) (Math.random() * 600);
            Glob glob = new Glob(x,y);
            sprites.add(glob);    
            slimes.add(glob);    
            globs.add(glob);    
        }
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            Food food = new Food(x,y);
            foods.add(food);
            sprites.add(food);
        }  
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800 / 2 + 800 / 2);
            int y = (int) (Math.random() * 600);
            Rona rona = new Rona(x,y);
            sprites.add(rona);    
            slimes.add(rona);    
            ronas.add(rona);    
        }
    }
    
   
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Slime slime : slimes) {
            for (Food food : foods) {
                slime.eat(food);
            }            
        }
        
        ArrayList<Schlime> newSchlimes = new ArrayList<>();
        ArrayList<Glob> newGlobs = new ArrayList<>();
         ArrayList<Rona> newRonas = new ArrayList<>();
        
         for (Schlime schlime : schlimes) {
             for (Glob glob : globs) {
                 for (Rona rona : ronas) {
                     schlime.fight(glob);
                     rona.fight(glob, schlime);
                 
                 
                 for (Schlime otherSchlime : schlimes) {
                     if (schlime == otherSchlime) continue;
                     if (schlime.collide(otherSchlime) && Math.random() < 0.03)
                         newSchlimes.add(schlime.reproduce(otherSchlime));
                 }
         }
    }
                    for (Rona rona : ronas) {
                     for (Rona otherRona : ronas) {
                         if (rona == otherRona) continue;
                         if (rona.collide(otherRona) && Math.random() < 0.03)
                             newRonas.add(rona.reproduce(otherRona));
                     }
                 }
}
                 for (Glob glob : globs) {
                 for (Glob otherGlob : globs) {
                     if (glob == otherGlob) continue;
                     if (glob.collide(otherGlob) && Math.random() < 0.03)
                         newGlobs.add(glob.reproduce(otherGlob));
                 }         
                  }
    
            for (Sprite sprite : sprites) {
                sprite.draw(g);
                sprite.update();
                sprite.collideWorldBounds(800,600);          
        }
            takeOutTheTrash();
            addNewSlimes(newSchlimes, newGlobs, newRonas);
    }        
    private void addNewSlimes(ArrayList<Schlime> newSchlimes, ArrayList<Glob> newGlobs, ArrayList<Rona> newRonas) {
        schlimes.addAll(newSchlimes);
        globs.addAll(newGlobs);
        ronas.addAll(newRonas);
    }
    
    private void takeOutTheTrash() {
        ArrayList<Sprite> trash = new ArrayList<>();
        for (Sprite sprite : sprites) {
            if (!sprite.isAlive())
                trash.add(sprite);
        }
        sprites.removeAll(trash);
        trash.clear();
        for (Food food : foods) {
            if (!food.isAlive())
                trash.add(food);
        }
        foods.removeAll(trash);
        trash.clear();
        for (Schlime schlime : schlimes) {
            if (!schlime.isAlive())
                trash.add(schlime);
        }
        schlimes.removeAll(trash);
        trash.clear();
        for (Glob glob : globs) {
            if (!glob.isAlive())
                trash.add(glob);
        }
        globs.removeAll(trash);
        trash.clear();  
         for (Rona rona : ronas) {
            if (!rona.isAlive())
                trash.add(rona);
    }
    }
    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
}
