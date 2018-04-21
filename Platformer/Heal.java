import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Heal here.
 * 
 * @author (your name) 
 * @version (a version numbetr or a date)
 */
public class Heal extends Item
{
    /**
     * Act - do whatever the Heal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
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
