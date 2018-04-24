import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player's health goes back up if touches this item.
 * 
 * @author Prince Christian Basiga
 */
public class Heal extends Item
{
   
    int healAmount;
    
    public Heal(){
        timeLeft = 20.0f;
        healAmount = 5;
    }
    public void act() 
    {
        // Add your action code here.
        super.act();
    }    
    
      public void pickUp(Player player){
        
        player.setHealth(player.getHealth() + healAmount);

        //Other stuff if needed.
        
        super.pickUp(player);
    }
    
    
    
   public Object clone() throws CloneNotSupportedException{
       
        Heal copy = new Heal();
        
        return copy;
        
   }
}
