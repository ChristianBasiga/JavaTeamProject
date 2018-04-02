import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager extends Observer
{   
    static final double GRAVITY = 9.81;
    
    
    public GameManager() {
        //threadName = "gm";
        priority = 2;
        
        //Set image to be of player attack or enemy, etc.
        StraightShot playerShot = new StraightShot();
        
        StraightShot enemyShot = new StraightShot();
        
        PoolManager.addPool("PlayerStraightShot",playerShot,20);
        PoolManager.addPool("EnemyStraightShot",enemyShot,20);
    }
    
    public void run() {
        
        if (subject != null) {
                
           
        }
        
    }
    
    
    public void act(){
  
    }
    
}
