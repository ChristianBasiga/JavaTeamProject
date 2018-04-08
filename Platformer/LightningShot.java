import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LightningShot here.
 * This is ranged shot that changes y velocity direction on collision with platform
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LightningShot extends RangedAttack
{
    
    int yAcceleration = 1;
    int maxBounces = 3;
    int currentBounces;

   
    int yVelocity;
    
    //Max in either direction, if not bounce before hit max, then also runs out
    int maxYVelocity;
    
    public LightningShot(){
        
        topSpeed = 5;
        acceleration = 1;
        maxYVelocity = 10;
        
    }
    
    
    @Override
    public void act() 
    {
       if (velocity < topSpeed){
           velocity += acceleration;
       }
       
      
       yVelocity += yAcceleration;
       System.out.printf("Y velocity: %d\n", yVelocity);
       setLocation(getX() + velocity * direction, getY() + yVelocity);
       
       continueAttack();
    }    
    
    @Override
    protected void continueAttack(){
                
        //So it bounces but then goes back down?
        if (isTouching(Ground.class)){
    
            yVelocity = 0;
            yAcceleration *= -1;
            currentBounces += 1;
            velocity = 0;
            
           //Also need to reverse the x if hit side, that's overcomplicating this so fuck it.
            setLocation(getX(), getY() +  getImage().getHeight() * yAcceleration * 2);

        }
        
        
        isDone = currentBounces == maxBounces;// || yVelocity == maxYVelocity;
        super.continueAttack();
        
    }
    
     public Object clone() throws CloneNotSupportedException{
        
        LightningShot copy = new LightningShot();
        
        copy.velocity = velocity;
        copy.topSpeed = topSpeed;
        copy.damage = damage;
        copy.setImage(copy.getImage());
       
        
        return copy;
    }
    
}
