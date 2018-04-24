import greenfoot.*;
/**
 * Just visual representation of health.
 * 
 * @author Prince Christian Basiga
 */


public class HealthBar extends Actor
{
    
    private int textSize;

    /**
     * Constructor for objects of class HealthBar
     */
    public HealthBar()
    {
      textSize = 20;
        
    }
    public void setTextSize(int size){
        
        textSize = size;
    }
    
    
    public void setHealth(int health){
        
        GreenfootImage image = new GreenfootImage(String.format("Health: %d",health),textSize, greenfoot.Color.RED, greenfoot.Color.BLACK);
        setImage(image);
        act();
    }
    
    
   
    
}
