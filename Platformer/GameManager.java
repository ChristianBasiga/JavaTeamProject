import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager extends Observer
{   
    static final double GRAVITY = 9.81;
    
    
    public GameManager() {
        threadName = "gm";
        priority = 2;    
        StraightShot enemyShot = new StraightShot();
    }
    
    //At the start of the game, makes all the colliders in the world invisible.
    //honestly could prob just do based on image, since theirs prob better, maybe.
    public void startGame(){
        
        //Makes all colliders invisible.
        List<Collider> colliders = getWorld().getObjects(Collider.class);
        GreenfootImage transparentImage = new GreenfootImage("transparent");
        for (Collider coll : colliders){
            
            //Make their image into something transparent with same dimensions
            //so collision still works fine.
            transparentImage.scale(coll.getWidth(),coll.getHeight());
            coll.setImage(transparentImage);
        }
    }
    
    public void react() {
        
        if (subject != null) {
                
           
        }
        
    }
    
    
    public void act(){
  
    }
    
}
