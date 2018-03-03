/**
 * Write a description of class Observer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.Actor;

public abstract class Observer extends Actor 
{
    //Subject Observing
    Subject subject;
    
    public abstract void update();
    
    public void observe(Subject subject){
        this.subject = subject;
    }
}
