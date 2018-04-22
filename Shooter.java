import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shooter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shooter extends Actor
{
    /**
     * Act - do whatever the Shooter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int direction = 1;
    int damage = 10;
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void shoot(){
        if(direction ==1)
        {
            System.out.println("Shot right\n");
            getWorld().addObject(new rightShot(), getX(), getY());
            //shootingCounter = 20;
            //return true;
        }
        if(direction ==-1)
        {
            System.out.println("Shot Left\n");
            getWorld().addObject(new leftShot(), getX(), getY());
            //shootingCounter = 20;
            //return true;
        }
        //if direction = 1
        //shoot right
        //wait 3 sec
        //direction = -1
        
        //if direction = -1
        //shoot left
        //wait 3 sec
        //direction = 1
    }
}
