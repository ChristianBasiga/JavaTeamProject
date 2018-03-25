import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animator here.
 * This will contain frames of subject it is observing, and update image as neccessary.
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Animator extends Observer
{
    /**
     * Act - do whatever the Animator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //The subject the animator will be animating
    Subject animating;
    
    //Different set of animations this animating subject will be animating
    //Would be better to use Hashmap here so can map string "Damaged" to damaged animation
    //etc. But that's up to you.
    Animation[] animations;
    
    
    public Animator(Subject animating){
        
        this.animating = animating;
        this.observe(animating);
    }
    
    public void run(){
        
        //Check state, run animation corresponding to the state.
        
    }
    
    public void act() 
    {
        
    }    
}
