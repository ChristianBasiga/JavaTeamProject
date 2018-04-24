import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * RangedAttack is abstract attack that has all properties of ranged attack, velocity, acceleration, damage the attack will do and then isDone is for when attack is finished.
 * This variable is to check when it should be sent back to the pool
 * 
 * @author Prince Christian Basiga
 */
public abstract class RangedAttack extends Reusable
{

    protected Class target;
    protected int topSpeed;
    protected int velocity;
    protected int acceleration;
    protected int damage; 
    protected int direction;
 
    
    //If the attack is finished
    protected boolean isDone;
   
    
   
    public <A> void setTarget(Class<A> cls ){
        this.target = cls;
    }
    
   
    public void setDirection(int direction){
        
        this.direction = direction;
    }
    
    //I could make this abstract so that they do different actions
    public void act() 
    {

          if (isTouching(Ground.class) || isAtEdge()){
            isDone = true;
        }
    }   

    //Will probably get rid of melee attack and just do ranged to make it easier
    protected  void continueAttack(){
        
        //This will be called by derived classes after they do their own pattern, not done by their criteria, might be done by hitting target.
        Actor obj = getOneIntersectingObject(target);
        
        if (obj != null){
            
            getWorld().removeObject(obj);
            isDone = true;
           
           
        }
        
      
        
        if (isDone){
            System.out.println("hello");
            doneUsing();
        }
    }
    
    
    
    public abstract Object clone() throws CloneNotSupportedException;
}
