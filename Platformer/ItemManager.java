import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class ItemManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemManager extends Actor
{
    
    Star star;
    Heal healing;
    PoolManager itemPools;
    Player player;
    
    public ItemManager(){
         
        itemPools = new PoolManager();
    }
    
    protected void addedToWorld(World world){
        
        
        createItems();
    }
    
     //This method will be creating pool for every time in game.
    private void createItems(){
        
        star = new Star();
        healing = new Heal();
        
        itemPools.addPool("Star",star,5);
        itemPools.addPool("Heal",healing,10);
          
    }
    
    //This will be called by Player for attacks and Enemies for item drops
    public Item getItem(String itemName){
        
        Item item = (Item)itemPools.getReusable(itemName);
        
        //Meaning pool is competely used up.
        if (item == null){
           
            item = itemFactory(itemName);
            
            //if still null then item does not exist and throw exception
            if (item == null){
                //Throw item not exist exception
            }
        }
        
        return item;
    }
    
    //Incase pools used up or for non-pooled items.
    private Item itemFactory(String itemName){
        
        if (itemName == "Star"){
            return new Star();
        }
        else if (itemName == "Heal"){
            return new Heal();
        }
        
        return null;
        
    } 
    
    //This method is for placing items into the world or assigning to enemies as drops.
   /* private void allocateItems(){
    
        //Also subscribing events to the items
        
        //Always finding player incase the player transformed
        Player playerInWorld = getWorld().getObjects(Player.class).get(0);
        
        //If not same player.
        if (player != playerInWorld){
            player = playerInWorld;
            
            //Add more events to these items as neccessarry
        
            //Now here's the fuck up, THE CLONED OBJECTS DO NOT GET THE UODATED EVENTS UNLESS overwrite it each time which is stupid, yeah no fuck using event system for it
            //tried something new but it has flaws will just do tried and true way.
            /*Deprecated, will delete once sure it won't be used but 99% won't be
        
            //Become invincible on picing up star.
            star.pickUpNotification.addEvent(() -> {player.becomeInvincible(10.0f);});
            
            //Heals player
            healing.pickUpNotification.addEvent(() -> {player.setHealth(player.getHealth() + 5);});
            
            PoolManager.addPool(star.getName(),star,5);
        
            PoolManager.addPool(healing.getName(),healing,10);
        
        } 
        
        
        
    }
    */
}
