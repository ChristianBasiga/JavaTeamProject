import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StraightShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StraightShot extends RangedAttack
{
    /**
     * Act - do whatever the StraightShot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    } 
    
    public Object clone() throws CloneNotSupportedException{
        
        StraightShot copy = new StraightShot();
        
        copy.velocity = velocity;
        copy.damage = damage;
        copy.setImage(copy.getImage());
        
        return copy;
    }
}
