import java.util.*;
/**
 * Write a description of class EventNotifier here.
 * EventNotifier has list of events that will happen when specific event is occured
 * If want event to happen at specific paint then different instance  of EventNotifier should 
 * be made for that event.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EventNotifier  
{
  
    List<IEvent> events;
    
    public EventNotifier(){
        events = new LinkedList<IEvent>();
        
    }
    
    public void attachEvents(EventNotifier toAttach){
        
        for (IEvent event : events){
            toAttach.addEvent(event);
        }
    }
    
    public void addEvent(IEvent event){
        
        events.add(event);
    }
    
    
    public void notifyEvents(){
        
        
        for (IEvent event : events){
            event.triggerEvent();
        }
        
    }
}
