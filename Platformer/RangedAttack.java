import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RangedAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class RangedAttack extends Reusable
{
    //velocity of attack.
    protected int velocity;
    protected int damage;
    protected boolean isDone;
   
    //I could make this abstract so that they do different actions
    public void act() 
    {
        continueAttack();
        
    }   

    //Will probably get rid of melee attack and just do ranged to make it easier
    protected  void continueAttack(){
        
        //This will be called by derived classes after they do their own pattern
        //isDone will be set by them.
        if (isDone){
            doneUsing();
        }
    }
    
    
    
    public abstract Object clone() throws CloneNotSupportedException;
}
