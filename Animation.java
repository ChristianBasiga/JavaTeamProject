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
    private AnimationManager frames = new AnimationManager();

    public Animation(String[] frames)
    {
        for(int i = 0; i < AnimFrames.length; i++){
            AnimFrames[i] = new GreenfootImage(frames[i]);
        }
    }

    public void addedToWorld(World myWorld){
        setImage(AnimFrames[0]);
    }
    
    public void animate(){
        if(timer == 10){
            timer = 0;
        }    
        if(timer == 0){
           setImage(AnimFrames[num]);
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
    
    public void react(){
        if(player.state.equals("right")){
            for(int i = 0; i < frames.sideFramesR.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.sideFramesR[i]);
            }
                                
        }
        else if(player.state.equals("left")){
            for(int i = 0; i < frames.sideFramesL.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.sideFramesL[i]);
            }  
                
        }    
        else if (player.state.equals("up") && player.state.equals("right")){
            for(int i = 0; i < frames.jumpFramesR.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.jumpFramesR[i]);
            }  
        }  
        else if (player.state.equals("up") && playerstate.equals("left")){
            for(int i = 0; i < frames.jumpFramesL.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.jumpFramesL[i]);
            }  
        }  
         else if (player.state.equals("absorb")){
            for(int i = 0; i < absorbFrames.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.absorbFrames[i]);
            }
                
        }
        else if (player.state.equals("hit")){
            if( playerLife != 0){
                for(int i = 0; i < hitFrames.length; i++){
                    AnimFrames[i] = new GreenfootImage(frames.hitFrames[i]);
                }
            }
            else{
                
                 for(int i = 0; i < deathFrames.length; i++){
                    AnimFrames[i] = new GreenfootImage(frames.deathFrames[i]);
                }
            }
                
        }
        else{
            for(int i = 0; i < AnimFrames.length; i++){
                AnimFrames[i] = new GreenfootImage(frames.idleFrames[i]);
            }
                
        }
    }    
    
}