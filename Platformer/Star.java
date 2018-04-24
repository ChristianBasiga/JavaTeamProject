import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Star makes the player invincible for certain amount of time when picked up.
 * 
 * @author Prince Christian Basiga
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
