/**
 * Those that extend observer class are meant to observe a certain subject. And react accordingly.
 * 
 * @author Prince Christian Basiga
 */
import greenfoot.Actor;
import java.util.*;
public abstract class Observer extends Actor implements  Comparable<Observer>
{
    
    //Separate thread for doing the reaction in.
    Thread t;

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
    /*final public void react() {
  
          try{
              //No longer threading, updating the frame gets messed up.
          //  t = new Thread(this,threadName);
            //t.start();
            run();
        }
        catch(Exception e){
            
        }
        
   }*/
   
   public abstract void react();
   
 
    
    public void observe(Subject subject) {
        
        //Comparing memory address to see if same instance
        
        if (subject == this.subject){
            return;
        }
        
        this.subject = subject;
        
        //To make it easier is I could separate this into two methods.
        //this adding itself to be observer could happen outside
        //but then not guaranteed that subject has reference to this observer.
        //This guarantees this. But this is also public, and if they do decide to addObserver, then the observers won't have reference to the subject, this would be where friend class good.
        //but in this case since both public, they can fuck up by choosing one 
        //this.subject.addObserver(this);
    }
    
    //To make sure certain observers react first.
    public int compareTo(Observer other){
        return other.priority - this.getPriority();
    }
   
}
