/**
 * This class contains and plays the different animations of the characters
 * 
 * @author (Melirose Liwag) 
 */
import greenfoot.*;
import java.util.*;

public class Animation extends Observer
{
    private int timer = 10;
    private int num = 0;
    private int speed = 1;
    private GreenfootImage[] AnimFrames = new GreenfootImage[11];
    private AnimationManager frames;

    public Animation(String[] frames)
    {
       
        for(int i = 0; i < AnimFrames.length; i++){
            System.out.println("setting up frames");
            AnimFrames[i] = new GreenfootImage(frames[i]);  
        }
        System.out.println("setting up frames");
    }

    public void addedToWorld(World myWorld){
        frames = getWorld().getObjects(AnimationManager.class).get(1);
        subject.setImage(AnimFrames[0]);
    }
    
    public void animate(){
        if(timer == 10){
            timer = 0;
        }    
        if(timer == 0){
           subject.setImage(AnimFrames[num]);
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
    
    public void run(){
        if(subject.getCurrentState().equals(State.MOVINGRIGHT)){
            for(int i = 0; i < frames.sideFramesR.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.sideFramesR[i]);
            }
                                
        }
        else if(subject.getCurrentState().equals(State.MOVINGLEFT)){
            for(int i = 0; i < frames.sideFramesL.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.sideFramesL[i]);
            }  
                
        }    
        else if (subject.getCurrentState().equals(State.JUMPING) && subject.getCurrentState().equals(State.MOVINGRIGHT)){
            for(int i = 0; i < frames.jumpFramesR.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.jumpFramesR[i]);
            }  
        }  
        else if (subject.getCurrentState().equals(State.JUMPING) && subject.getCurrentState().equals(State.MOVINGLEFT)){
            for(int i = 0; i < frames.jumpFramesL.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.jumpFramesL[i]);
            }  
        }  
         else if (subject.getCurrentState().equals(PlayerState.ABSORBING)){
            for(int i = 0; i < frames.absorbFrames.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.absorbFrames[i]);
            }
                
        }
        else if (subject.getCurrentState().equals(State.DAMAGED)){
            
            for(int i = 0; i < frames.hitFrames.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.hitFrames[i]);
            }
               
        }
        
        else if (subject.getCurrentState().equals(State.DEAD)){
               
            for(int i = 0; i < frames.deathFrames.length; i++){        
                AnimFrames[i] = new GreenfootImage(frames.deathFrames[i]);    
            }
        }
        else{
            
            for(int i = 0; i < AnimFrames.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.idleFrames[i]);
            }
                
        }
    }    
    
}