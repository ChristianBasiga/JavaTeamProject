import greenfoot.*;

/**
 * Write a description of class Animation here.
 * This will be what animator will have array of. Initialized with array of strings
 * or greenfoot images.
 */
public class Animation  
{
    private GreenfootImage[] frames;
    int currentFrame = 0;
    //Decide how you determine this.
    boolean donePlaying;
    //Can add as you feel fit.
    /**
     * Constructor for objects of class Animation
     */
    public Animation()
    {
        donePlaying = true;
    }
    
    public boolean isDone(){

        return donePlaying;
    }

    //think about different methods animations will have, like next frame, time between, etc.
}
