import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PauseMenu extends Actor
{
    public PauseMenu(){
        
       
        
         GreenfootImage image = new GreenfootImage("Paused", 200, greenfoot.Color.GREEN, greenfoot.Color.GRAY);
         setImage(image);
    }
    
    public void addedToWorld(World myWorld){
        
        getImage().scale(myWorld.getWidth() / 2, myWorld.getHeight() / 3);
        
    }
    
   
}
