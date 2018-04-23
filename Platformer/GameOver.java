import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game Over Screen.
 * 
 * @author Prince Christian Basiga
 */
public class GameOver extends Actor
{
    /**
     * Act - do whatever the GameOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
     
    public GameOver(){
        
       
        
         GreenfootImage image = new GreenfootImage("Game Over", 200, greenfoot.Color.RED, greenfoot.Color.GRAY);
         setImage(image);
    }
    
    public void addedToWorld(World myWorld){
        
        getImage().scale(myWorld.getWidth() / 2, myWorld.getHeight() / 3);
        
    }
    
   
}
