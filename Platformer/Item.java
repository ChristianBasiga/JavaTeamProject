import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Item: This is abstract tiem class, has behavior of item in that it only exists for certain amount of time, and then pickUp method that will mean it goes back into pool.
 * 
 * @author Prince Christian Basiga
 */
public abstract class Item extends Reusable
{
    //At this point could have just different instances of Item and let events subscribed to
    //that item change it's behavior, orr I could have both?
  //  public EventNotifier pickUpNotification;
   // String name;
   

   float timeLeft;
    
    public Item(){
        
    //    pickUpNotification = new EventNotifier();
    //    this.name = name;
    }
   
    public void act(){
        

        if (timeLeft > 0){

            timeLeft -= 0.1f;
        }
        else if (timeLeft <= 0){

            doneUsing();
        }
        
    }
   
   
    //I could just switch this on player, or can just trigger event hm
   public void pickUp(Player player){     
       doneUsing();
   }
    
   public abstract  Object clone() throws CloneNotSupportedException;/*{
        
        Item item = new Item(name);
        
        item.pickUpNotification.attachEvents(this.pickUpNotification);
        
        return item;
        
    }
*/
}
