/**
 * Write a description of class Observer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.Actor;
import java.util.*;
public abstract class Observer extends Actor implements Runnable
{
    
    //Separate thread for doing the reaction in.
    Thread t;
    String threadName;
    //Subject observing
    Subject subject;
    

    //Not meant to be overwridden, do not hcange anything here, this is fine as is.
    final void react() {

        
        if (t == null) {
            t = new Thread(this,threadName);
        }
        
        if (t.isAlive()){
           
           try {
			t.wait();
		   } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
        }
        
        t.start();
       
   }
    
    public void observe(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }
}
