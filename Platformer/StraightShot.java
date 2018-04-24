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
    
    public void addedToWorld(World world){
        
        secondsTillAccel = 0;  
    
        velocity = 1;
        topSpeed = 10;
        acceleration = 1;
        setTarget(Enemy.class);
    }
    
    public void act() 
    {

                super.act();
                
        
       
        setLocation(getX() + velocity , getY());
        
        if (secondsTillAccel > 0){
            secondsTillAccel -= 0.1f;
        }
        else{
            continueAttack();
        }
        

        
    } 
    
    @Override
    protected void continueAttack(){
  
        velocity += acceleration * direction;
        
        
                super.continueAttack();
        isDone = velocity >= topSpeed;
        
       
        
        secondsTillAccel = secondsForAccel;
        

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
