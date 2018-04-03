import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Star here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Star extends Item
{
    float invincibilityTime;
    
    public void Star(){
        timeLeft = 10.0f;
        invincibilityTime = 10.0f;
    }
    
    public void act() 
    {
        // Add your action code here.
        super.act();
    }    
    
    
    public void pickUp(Player player){
        
        player.becomeInvincible(invincibilityTime);

        //Other stuff if needed.
        
        super.pickUp(player);
    }
    
    
     public Object clone() throws CloneNotSupportedException{
        
        Star star = new Star();
        
        return star;
        
    }
}
