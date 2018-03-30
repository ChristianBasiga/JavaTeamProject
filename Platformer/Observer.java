/**
 * Write a description of class Observer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.Actor;
import java.util.*;
public abstract class Observer extends Actor implements Runnable, Comparable<Observer>
{
    
    //Separate thread for doing the reaction in.
    Thread t;
    String threadName ="d";
    int priority;
    //Subject observing
    Subject subject;
    
    public int getPriority(){
        return priority;
    }

    //Not meant to be overwridden, do not change anything here, this is fine as is.
    //What will be overridden is the run method.
    //All logic where Observer is reacting to a subject will be in run override
    final public void react(boolean isConCurrentReaction) {

        if (isConCurrentReaction){
        
            if (t == null) {
                t = new Thread(this,threadName);
            }
        
            t.start();
        }else{
            //Otherwise I call run myself directly to run it in main thread.
            run();
        }
        
   }
    
    public void observe(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }
    
    public int compareTo(Observer other){
        return other.priority - this.getPriority();
    }
   
}
