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
    
    //Will just override observe to call base and also re-assign animating
    
    public void observe(Subject subject){
        
        super.observe(subject);
        animating = subject;
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
            

            //I think this needs to change. FUcking up, shit okay
            //yeah I need to rethink when the state is changed
            //or just add an extra here.
            //This temporary, if jumping or falling, then just repeating animation
            if (animating != null && animating.getCurrentState() != null){
            if (animating.getCurrentState().equals(State.FALLING)  || animating.getCurrentState().equals(State.JUMPING)){
                //Restart the current animation.
                return;
            }
            if (animating.getCurrentState() != State.DEFAULT ){
                animating.changeState(State.DEFAULT,false);
            }
        }
        }
    }
    
}
