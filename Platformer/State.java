 /**
 * Write a description of class State here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//Just to be used for shared type but otherwse simulates enum
//There will be PlayerState, and EnemyState but base will contain the shared states
public class State  
{
    public static final State DEFAULT = new State(512,"Default");
    public static final State DAMAGED = new State(1,"Damaged");
    public static final State DEAD = new State(2,"Dead");
    public static final State ATTACKING = new State(4,"Attacking");
    public static final State MOVINGLEFT = new State(8,"MovingLeft");
    public static final State MOVINGRIGHT = new State(16,"MovingRight");
    public static final State JUMPING = new State(32,"Jumping");
    public static final State FALLING = new State(64,"Falling");
  
    //Cannot be final since then cannot do bitwise.
    private int id;
    private int originalID;
    private String name;
    
    public State(){
        
        id = 0;
        name = "";
    }
    
    public State(int id, String name){
        
        //If there's chance that can be in two states at once
        //attacking and moving for example, then I want to make it bitwise instead
        //String and id then
        this.name = name;
        this.id = originalID = id;
    }
    
    public void turnOffState(State other){
        
      //Only if it's on can I turn off, otherwise will switch it on
      if ((id & other.id) > 0){
        id =  id & ~other.id;
        
        //The string not really neccesarry
        String[] activeStates = name.split("[, ]");
        String newName = "";
        
        for (String state : activeStates){
            //If equal then don't re-add it
            if (state != other.name){
                newName = newName +", " + state;
            }
        }
        this.name = newName;
      }
    }
    public void resetState(){
        id = originalID;
    }
    public void setState(State other){
    
        
        id = other.id;
    }
    //Better way is to have this return the blended state, this way won't effect the originals
    public State getBlendedState(State rhs){
        
        if (this.id == rhs.id) return null;
        
        int newID = 0;
        newID |= this.id;
        newID |= rhs.id;
        
        State blend = new State(newID, toString() + "," + rhs.toString());
        
        return blend;
       
    }
    
  
    
    public boolean equals(State other){
        
        //If matches flag then this state is also in that state.
        return (id & other.id) > 0? true : false;
    }
    
    public String toString(){
        return name;
    }
    
}
