import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Reusable objects have method to return themselves to the pool. PoolManager can only pool Reusable types.
 * 
 * @author Prince Christian Basiga
 */

import java.util.*;
import java.lang.Exception.*;

public abstract class Reusable extends Actor implements Cloneable
{
    //It's okay if public, can only add to it so no risk of losing added events.
    public EventNotifier doneUsing;
   

    public Reusable(){
        doneUsing = new EventNotifier();
    }
    
    //This will be called by concrete classes when they're done being used
    protected void doneUsing(){  
        
        if (doneUsing != null){
                      
            System.out.printf("%s is gone", this.toString());
            doneUsing.notifyEvents();
            getWorld().removeObject(this);
        }
    }
   
    //Will be implemented by concrete classes cause they decide how they're cloned.
    public abstract Object clone() throws CloneNotSupportedException;
    
}
