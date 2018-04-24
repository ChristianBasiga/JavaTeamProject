import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pause Menu...
 * @author Prince Christian Basiga
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
