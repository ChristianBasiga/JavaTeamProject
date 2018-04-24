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
        System.out.println(animating.toString());
    }
    public void setAnimating(Actor animating){
               
        this.animating = animating;
    }

    
    
    public void animate(){
        

      //  System.out.println("transparency is " + animating.getImage());
        if(timer == 10){
            timer = 0;
        }    
        if(timer == 0){
            
          
          
           animating.setImage(AnimFrames[num]);
           num++;
            
           if( num >= AnimFrames.length ||  (AnimFrames[num] == null)){
            
               num = 0;
          
            }  
            
        }
        timer = timer + 2;
           
    } 
    
    public void act(){
        animate();
    }
    
    public void setFrames(String[] frames){
        
      
        for (String frame : frames){
            
            System.out.println(frame);
        }
        System.out.println("I am called");
        for(int i = 0; i < AnimFrames.length; i++){
            
            if (i >= frames.length){
                //Sets remaining null incase have left over frames.
                AnimFrames[i] = null;
            }
            else
                AnimFrames[i] = new GreenfootImage(frames[i]);  
                
            if (AnimFrames[i] == null){

            }
        }
        num = 0;
    }
    
}
