import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.*;
/**
 * This class is meant to act as Collider for all actors in the game.
 * Doing based offof greenfootImage not consistent enough.
 */
public class Collider extends Actor
{
    /**
     * Act - do whatever the Collider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    Actor detectingFor;

    
    //Collider is really another observer to Subjects and it's truly one-to-one.
    public Collider(Actor actor){
        
        detectingFor = actor;
    }
    
    public void act() 
    {
        setLocation(detectingFor.getX(),detectingFor.getY());
    }   
    
    //Need to create my own boxes, can't depend on images cause they're polygons.

    public <A> boolean isTouchingObject(java.lang.Class<A> cls){
        
        return isTouching(cls);
        
    }
    
    public <A> Object getObjectAtOffset(int xOffset, int yOffset, java.lang.Class<A> cls){
        
        return getOneObjectAtOffset(xOffset,yOffset, cls);
    }
    
   
    
    public void setWidth(int width){
        
        getImage().scale(width, getImage().getHeight());
    }
    
    public int getWidth(){
        
        return getImage().getWidth();
    }
    
    public void setHeight(int height){
        
        getImage().scale(getImage().getWidth(),height);
        
    }
    
    public int getHeight(){
        return getImage().getHeight();
    }
    
    public <T> List<T> getCollidingObjects(java.lang.Class<T> cls){
        return getIntersectingObjects(cls);
    }

}
