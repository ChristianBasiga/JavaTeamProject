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
    
    int currentAnimPlaying = 0;
    public Animator(Subject animating){
        
        this.animating = animating;
        this.observe(animating);
        this.threadName = animating.toString() + "animator";    
        priority = 3;
        animations = new Animation[5];
        animations[0] = new Animation();    
    }
    
    public void run(){
        

                                    
        //Check state, run animation corresponding to the state.
        if (animating.getCurrentState() == PlayerState.TRANSFORMING){
            
            //Only ever one player in world at time, and guaranteed to be atleast one.
            Player toAnime = getWorld().getObjects(Player.class).get(0);
            animating = toAnime;
            
            //Then this should replace animations array with set of animations specific to anim. That or just replace the image within them, that's up to you.
            
           
        }
        
    }
    
    public void act(){
        
        if (animations[currentAnimPlaying].isDone()){
            
            //Update the state to now be in default mode, this should make it so player is takign in input again now too. Yup
            animating.changeState(State.DEFAULT,false);
        }
    }
    
}
