import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ItemManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemManager extends Actor
{
    
    public ItemManager(){
        
        createItems();
        
    }
    
    protected void addedToWorld(World world){
        
        allocateItems();
    }
    
    //This method will be creating pool for every time in game.
    private void createItems(){
        
        Item item = new Item("Star");
        
        //Only 5 as they aren't being constantly set up
        PoolManager.addPool("Star",item,5);
        //set image of star.
    }
    
    //This method is for placing items into the world or assigning to enemies as drops.
    private void allocateItems(){
    
    }
}
