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
        
      
        
        StraightShot enemyShot = new StraightShot();
        

        PoolManager.addPool("EnemyStraightShot",enemyShot,20);
    }
    
    public void run() {
        
        if (subject != null) {
                
           
        }
        
    }
    
    
    public void act(){
  
    }
    
}
