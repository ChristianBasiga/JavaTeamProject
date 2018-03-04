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
    public static final State DAMAGED = new State(1,"Damaged");
    public static final State DEAD = new State(2,"Dead");
    public static final State MOVING = new State(4,"Moving");
    public static final State ATTACKING = new State(8,"Attacking");
    
    //Cannot be final since then cannot do bitwise.
    private int id;
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
        this.id = id;
    }
    
    public void assignState(State other){
        
        id |= other.id;
        
        if (name.equals("")){
            name = other.name;
        }
        else{
          name += ", " + other.name;
        }
    }
    
    public boolean equals(State other){
        
        //If matches flag then this state is also in that state.
        return (id & other.id) > 0? true : false;
    }
    
}
