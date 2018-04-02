import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reusable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        doneUsing.notify();
    }
   
    //Will be implemented by concrete classes cause they decide how they're cloned.
    public abstract Object clone() throws CloneNotSupportedException;
    
}
