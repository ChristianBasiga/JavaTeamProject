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
    String threadName = "d";
    int priority;

    
    Subject subject;
    
    public int getPriority(){
        return priority;
    }

    //Not meant to be overwridden, do not change anything here, this is fine as is.
    //What will be overridden is the run method.
    //All logic where Observer is reacting to a subject will be in run override
    final public void react(boolean isConCurrentReaction) {

        if (isConCurrentReaction){
        
          
            t = new Thread(this,threadName);
            
        
            t.start();
        }else{
            //Otherwise I call run myself directly to run it in main thread.
            run();
        }
        
   }
    
    public void observe(Subject subject) {
        this.subject = subject;
        
        System.out.printf("The observer %s is now observing the subject %s\n", this.toString(),subject);
        this.subject.addObserver(this);
    }
    
    public int compareTo(Observer other){
        return other.priority - this.getPriority();
    }
   
}
