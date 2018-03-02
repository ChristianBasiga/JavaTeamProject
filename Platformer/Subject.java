/**
 * Write a description of class Subject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.Actor;
public class Subject extends Actor  
{
    Observer[] observers;
    
    //To be called anytime a state of subject has been changed
    protected void didUpdateState(){
        
        //Notifies all the observers of the change.
        for (Observer observer : observers){
            
            observer.update();
        }
    }
}
