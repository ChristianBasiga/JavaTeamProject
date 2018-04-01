import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerAttack here.
 * This is the default attack of player
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerAttack extends Actor
{
   
  
    public void act() 
    {
        // Add your action code here.
        if (isTouching(Enemy.class)){
            
            Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
            
            getWorld().removeObject(enemy);
            getWorld().removeObject(this);
        }
    }    
}
