/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package star.wars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Jenna Wilkinson
 */
public class Space extends JPanel{

    final int marginX;
    final int marginY;
    private Hero hero;
    private Enemy enemy;
    private Timer timer;
    
    public Space() {
        super();
        marginX = 10;
        marginY = 10;
        hero = new Hero(600, 480, Color.YELLOW, 20, "hero");
        enemy = new Enemy(300, 480, Color.BLUE, 20, "enemy");
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 100);
    }
    
    @Override
    public void paintComponent(Graphics g) {    
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        
        drawStars(g);
        
        hero.draw(g);
        enemy.draw(g);
        //g.dispose();
    }
    
     private class ScheduleTask extends TimerTask {
    
        @Override
        public void run() {
            hero.update();
            enemy.update();
            repaint();
        }
    }
    
    public void run() {
        hero.move(-1,0);
        repaint();
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.setDX(1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.setDX(-1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.setDY(-1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.setDY(1);
        }
        run();
    }
    
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            hero.setDX(0);
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            hero.setDX(0);
        if (e.getKeyCode() == KeyEvent.VK_UP)
            hero.setDY(0);
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            hero.setDY(0);
    }

    private void drawStars(Graphics g) {
        for (int i = 0; i < 60; i++) {
        int x = (int) (Math.random() * 1100.0) + 1;
        int y = (int) (Math.random() * 800.0) + 1;
            int guesses = 0;
            x += marginX;
            y += marginY;
            g.fillOval(x, y, 10, 10);
            
            Color curr = g.getColor();
          if (x % 2 == 0) 
              g.setColor(curr.brighter());
          else
              g.setColor(curr.darker());
          g.fillOval(x, y, 3, 3);
            
            System.out.println(x+ " " +y);
            if (x > 1100 || y > 860) {
                break;
            }
        }
    }
    private void wallCollisions() {
        
    }
}
