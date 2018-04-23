import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Self Explanatory, it's a projectile that goes straight.
 * 
 * @author Prince Christian Basiga
 */
public class StraightShot extends RangedAttack
{
  
    float secondsTillAccel;
    float secondsForAccel = 1.0f;
    
    public StraightShot(){
        
        secondsTillAccel = 0;  
    
        velocity = 1;
        topSpeed = 10;
        acceleration = 1;
    }
    
    public void act() 
    {

        setLocation(getX() + velocity, getY());
        
        if (secondsTillAccel > 0){
            secondsTillAccel -= 0.1f;
        }
        else{
            continueAttack();
        }
        
    } 
    
    @Override
    protected void continueAttack(){
  
        velocity += acceleration;
        
        
        isDone = velocity >= topSpeed;
        
       
        
        secondsTillAccel = secondsForAccel;
        
        super.continueAttack();
    }
    
    public Object clone() throws CloneNotSupportedException{
        
        StraightShot copy = new StraightShot();
        
        copy.velocity = velocity;
        copy.topSpeed = topSpeed;
        copy.damage = damage;
        copy.setImage(copy.getImage());
        copy.secondsForAccel = secondsForAccel;
        copy.acceleration = acceleration;
        
        return copy;
    }
}
