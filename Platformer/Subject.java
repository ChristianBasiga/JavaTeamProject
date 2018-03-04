/**
 * Write a description of class Subject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.Actor;
import java.util.*;
public class Subject extends Actor  
{
    
    List<Observer> observers;
    State currentState;
    
    public void addObserver(Observer observer){
        
        observers.add(observer);
        
    }
    
    //To be called anytime a state of subject has been changed
    protected void changeState(State state){
        
        this.currentState = state;
        //Notifies all the observers of the change.
        for (Observer observer : observers){
            
            observer.react();
        }
    }
    
    
}
