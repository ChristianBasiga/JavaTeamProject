import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reusable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.Exception.*;

public class Reusable extends Actor implements Cloneable
{
    Runnable onDeath;
    //Pool will assign to onDeath lambda to go back into pool
   public void setOnDeath(Runnable toRun){
       this.onDeath = toRun;
   }
   
   public void die(){
       onDeath.run();
   }
    
    //Need to actually override every place that derves reusable.
    //So enemy needs to implement clone to return new Enemy(), etc.
    public Object clone() throws CloneNotSupportedException{
        
        return new Reusable();
    }
    
    
}
