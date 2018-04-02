import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reusable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.Exception.*;

public class Reusable extends Actor
{
    //It's okay if public, can only add to it so no risk of losing added events.
    public EventNotifier doneUsing;
   

    public Reusable(){
        doneUsing = new EventNotifier();
    }
   
}
