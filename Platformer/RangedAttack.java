import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RangedAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class RangedAttack extends Reusable
{

    protected Class target;
    protected int topSpeed;
    protected int velocity;
    protected int acceleration;
    protected int damage;
    protected boolean isDone;
    protected int direction;
 
    
   
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
            doneUsing();
        }
    }   

    //Will probably get rid of melee attack and just do ranged to make it easier
    protected  void continueAttack(){
        
        //This will be called by derived classes after they do their own pattern, not done by their criteria, might be done by hitting target.
        ITakeDamage obj = (ITakeDamage)getOneIntersectingObject(target);
        
        if (obj != null){
            
            obj.damage(damage);
            isDone = true;
           
           
        }
        
      
        
        if (isDone){
            doneUsing();
        }
    }
    
    
    
    public abstract Object clone() throws CloneNotSupportedException;
}
