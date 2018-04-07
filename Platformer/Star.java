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
    
    public Star(){
       timeLeft = 100.0f;
        invincibilityTime = 10.0f;
    }
    
    public void act() 
    {
        // Add your action code here.
        super.act();
    }    
    
    
    public void pickUp(Player player){
        
        System.out.println("I happen");
        player.becomeInvincible(invincibilityTime);
        
        super.pickUp(player);
    }
    
    
     public Object clone() throws CloneNotSupportedException{
        
        Star star = new Star();
        
        return star;
        
    }
}
