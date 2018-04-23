/**
 * Those that extend observer class are meant to observe a certain subject. And react accordingly.
 * 
 * @author Prince Christian Basiga
 */
import greenfoot.Actor;
import java.util.*;
public abstract class Observer extends Actor implements  Comparable<Observer>, Runnable
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
    //Might not need it to be threaded anymore, stuff like damage and then health going down
    //can just happen after another, frames go by pretty fast anyway.
    final public void react() {
  
          try{
              //No longer threading, updating the frame gets messed up.
          //  t = new Thread(this,threadName);
            //t.start();
            run();
        }
        catch(Exception e){
            
        }
        
   }
   
 
    
    public void observe(Subject subject) {
        this.subject = subject;
        
        System.out.printf("The observer %s is now observing the subject %s\n", this.toString(),subject);
        this.subject.addObserver(this);
    }
    
    //To make sure certain observers react first.
    public int compareTo(Observer other){
        return other.priority - this.getPriority();
    }
   
}
