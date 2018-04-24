import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * ItemManager class handles the pooling the items and producing new items.
 * 
 * @author Prince Christian Basiga
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
        
        //Also add different player attacks here.
        
        itemPools.addPool("Star",star,5);
        itemPools.addPool("Heal",healing,10);
          
    }
    
    //This will be called by Player for attacks and Enemies for item drops
    public Item getItem(String itemName) throws Exception{
        
        Item item = (Item)itemPools.getReusable(itemName);
        
        //Meaning pool is competely used up.
        if (item == null){
           
            item = itemFactory(itemName);
            
            //if still null then item does not exist and throw exception
            if (item == null){
                throw new Exception(String.format("The Item %s does not exist",itemName));
            }
        }
        
        return item;
    }
    
    //Incase pools used up or for non-pooled items.
    private Item itemFactory(String itemName){
        
        if (itemName.equals("Star")){
            return new Star();
        }
        else if (itemName.equals("Heal")){
            return new Heal();
        }
        
        return null;
        
    } 
}
