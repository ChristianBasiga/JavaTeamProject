import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StraightShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StraightShot extends RangedAttack
{
    //Could have direction of shot, but that's decided by player shooting, not itself
    //so it should rotate it? or should I have attribute here.
   
    //
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
