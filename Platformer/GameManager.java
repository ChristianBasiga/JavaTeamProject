import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager extends Observer
{
    /**
     * Act - do whatever the GameManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //Will replace subject with Player class later, but that will be created in different
    //branch
    public GameManager(Subject player){
        
        this.subject = player;
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void update(){
        
       //Check what needs to be checked for update,
    }
}
