import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * The Wave Shot moves forawrd and gradually rises up. When it reaches topYVelocity then that's end of wave.
 * 
 * eg:
 * 
 *                    ........
 *              .......
 *        ......
 * .......
 * 
 * @author: Prince Christian Basiga
 **/

public class WaveShot extends RangedAttack
{
    
    int topYVelocity = 5;
    int yVelocity;
    int yAcceleration;
    
    float accelerationTime = 5.0f;
    float timeUntilAccel;
    
    
    
    public WaveShot(){
        
        yAcceleration = 1;
        acceleration = 2;
        topSpeed = 5;
        
    }
    
    
    public void act() 
    {
       
        System.out.printf("Y movemnet :%d, X movement: %d\n", yVelocity, velocity* direction);
        
        setLocation(getX() + velocity *  direction, getY() - yVelocity / 2);
        
       
        
        if (timeUntilAccel > 0){
            timeUntilAccel -= 0.1f;
        }
        else{
            continueAttack();
           
        }
        
       

        super.act();
    }    
    
    
    
    
    @Override
    protected void continueAttack(){
        
        timeUntilAccel = accelerationTime;
      
        //Increase X velocity
        if (velocity < topSpeed){
            velocity += acceleration;
        }
                yVelocity += yAcceleration;
        isDone = yVelocity >= topYVelocity;

        
        super.continueAttack();
        
    }
    
     public Object clone() throws CloneNotSupportedException{
        
        WaveShot copy = new WaveShot();
        
        copy.velocity = velocity;
        copy.topSpeed = topSpeed;
        copy.damage = damage;
        copy.setImage(copy.getImage());
       
        
        return copy;
    }
}
