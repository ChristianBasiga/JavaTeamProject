/**
 * This class contains and plays the different animations of the characters
 * 
 * @author (Melirose Liwag) 
 */
import greenfoot.*;
import java.util.*;

public class Animation extends Actor
{
    private int timer = 10;
    private int num = 0;
    private int speed = 1;
    private GreenfootImage[] AnimFrames = new GreenfootImage[11];
    private AnimationManager frames;
    private Actor animating;

    public Animation(Actor animating){
        
        this.animating = animating;
    }
    public void setAnimating(Actor animating){
               
        this.animating = animating;
    }

    public void addedToWorld(World myWorld){
        
        frames = getWorld().getObjects(AnimationManager.class).get(1);
        
        animating.setImage(AnimFrames[0]);
    }
    
    public void animate(){
        
        if(timer == 10){
            timer = 0;
        }    
        if(timer == 0){
           animating.setImage(AnimFrames[num]);
           num++;
            if(num >= AnimFrames.length){
                num = 0;
            }  
            
        }
        timer = timer + 2;
           
    } 
    
    public void act(){
        animate();
    }
    
    public void setFrames(String[] frames){
        for(int i = 0; i < AnimFrames.length; i++){
            AnimFrames[i] = new GreenfootImage(frames[i]);  
        }
    }
    
}
