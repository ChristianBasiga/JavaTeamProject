import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager extends Observer
{   
    public GameManager() {
        //threadName = "gm";
        priority = 2;
    }
    
    public void run() {
        
        if (subject != null) {
                
           
        }
        
    }
    
    
    public void act(){
        
        if (Greenfoot.isKeyDown("t")){
            //Yeah, figured. It's still pointing to same one
            System.out.println(subject);
        }
    }
    
}
