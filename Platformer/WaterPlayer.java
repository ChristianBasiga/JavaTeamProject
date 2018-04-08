import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaterPlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterPlayer extends Player
{
    /**
     * Act - do whatever the WaterPlayer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    
    @Override
    public void attack(RangedAttack attack){
        
        prepareAttack(attack);
        
        getWorld().addObject(attack,getX() + collider.getWidth() + (attackDistance * directionFacing),getY() - getImage().getHeight() / 2);
        
        
        
        
        
    }
}
