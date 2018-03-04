import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerController extends Observer
{
    Player player;
    
    public PlayerController(Player player){
        
        this.player = player;
        priority = 100;
    }

    public void run() 
    {
            //Reaction code to changes in state to player go here
    }    
    
    public void act(){
        
       //All checks for input will go here
    
    }
    
    private void transform(){
        //Change player to transformation produced by pf that we passed in.
    }
   
}
