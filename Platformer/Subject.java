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
    
    Queue<Observer> observers;
    //Private so that derived classes do not change directly and have to use changeState
    private State currentState;
    
    public Subject(){
        
        observers = new PriorityQueue<Observer>();
        
    }
    
    public void copyObservers(Subject subject){
        
        for (Observer observer : observers){
            
            observer.observe(subject);
        }
    }
    
    public void addObserver(Observer observer){
        
        observers.add(observer);
        
    }
    
    public State getCurrentState(){
        
        return currentState;
    }
    
    //To be called anytime a state of subject has been changed
    //2nd paramater is lame workaround to possible data race
    //during transformation for changing player instance as well as
    //changing player's image for animations. Transformation should go first,
    //then animations start happening again. I'll work on way to prioritize
    //characterController as main observer, will have to make priority attribute and then
    //create priority queue.
    public void changeState(State state, boolean reactionThread){
        
        this.currentState = state;
    //    System.out.printf("I have %d observers\n", observers.size());
        //Notifies all the observers of the change. Prioritizing PlayerController, GameManager first to make sure  player done changing and game state still valid.
        for (Observer observer : observers){
            
            observer.react(reactionThread);
        }
    }
    
    
}
