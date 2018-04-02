import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Reusable
{
    //At this point could have just different instances of Item and let events subscribed to
    //that item change it's behavior
    public EventNotifier pickUpNotification;
    String name;
    
    public Item(String name){
        
        this.name = name;
    }
    //I could just switch this on player, or can just trigger event hm
    void pickUp(){
        //Effect the player with correct effect.
        pickUpNotification.notify();
        //This was changed in mainDevelop, when merge branch this error will be gone.
        doneUsing();
    }
    
    public Object clone() throws CloneNotSupportedException{
        
        Item item = new Item(name);
        
        item.pickUpNotification.attachEvents(this.pickUpNotification);
        
        return item;
        
    }

}
