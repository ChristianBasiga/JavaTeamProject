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
    //Private so that derived classes do not change directly and have to use changeState
    private State currentState;
    
    public void addObserver(Observer observer){
        
        observers.add(observer);
        
    }
    
    public State GetCurrentState(){
        
        return currentState;
    }
    
    //To be called anytime a state of subject has been changed
    public void changeState(State state){
        
        this.currentState = state;
        //Notifies all the observers of the change.
        for (Observer observer : observers){
            
            observer.react();
        }
    }
    
    
}
