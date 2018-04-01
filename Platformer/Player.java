import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Subject
{
    String currentTransformation = "default";
    int health;
    float invincibilityFrames = 20.0f;
    float timeLeft = 0;
    
    //-1 for left and 1 for right.
    int directionFacing = 1;
    
    //Since melee not much.
    int attackDistance = 5;
    int absorptionDistance = 20;
    
    //Basically fall speed.
    double weight;
    int jumpHeight;
    int groundY;
    
    float attackCD = 10.0f;
    float timeTillAttack = 0;
    int speed = 2;
    
    boolean onGround;
    
    Player(){   
        health = 15;
    //    changeState(State.DEFAULT,false);
        currentTransformation = "default";
        
        weight = 10;
        jumpHeight = 100;
        changeState(State.DEFAULT,false);

        onGround = false;
    }
    
   
    
    
    public boolean isOnGround(){
        return onGround;
    }
    
    public void setOnGround(boolean value){
        onGround = value;
    }
    
    
    public String getCurrentTransformation(){
        return currentTransformation;
    }
    
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    public double getWeight(){
        return weight;
    }
    
    public int getSpeed(){
        return speed;
    }
   
    public void act() 
    {
        
        //Then being damaged
        //Bit more overhead, but enforces use of not using currentState directly.
        if (isTouching(Enemy.class) && getCurrentState() != State.ATTACKING && getCurrentState() != PlayerState.TRANSFORMING
        && getCurrentState() != PlayerState.ABSORBING && getCurrentState() != State.DAMAGED){
            
            changeState(State.DAMAGED,false);
            Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
            health -= enemy.getDamage();
            
            timeLeft = invincibilityFrames;
        }
        
        if (timeLeft > 0){            
            timeLeft -= 0.1f;
        }
        if (timeLeft < (invincibilityFrames / 2) && getCurrentState() == State.DAMAGED){
           //So when no longer stunned, can take in input again.
            System.out.println("hello");
            //K sthis isn't problem
            changeState(State.DEFAULT,false);
        }
            
        if (timeTillAttack > 0){
                timeTillAttack -= 0.1f;
        } 
        
                  
        managePlayerYPosition();
                
    }    
    
    private void managePlayerYPosition(){
        

        if (getCurrentState().equals(State.JUMPING)){

            setLocation(getX(),getY() - 1);


            if ((getY() <= groundY - jumpHeight) || isAtEdge()){

                changeState(State.FALLING,false);
            }
        }
        //This works perfectly
        else if (!isTouching(Ground.class)){
            
            if (!getCurrentState().equals(State.FALLING)){
             changeState(State.FALLING, false);
            }
        }
 
       //If touching ground and falling
         if (isTouching(Ground.class) ){

                            
                List<Ground> grounds = getIntersectingObjects(Ground.class);
           
                for (Ground ground : grounds){
                    
                    //Need to check if within X and width  of ground.
                    if (getX() >= ground.getX() || getX() + getImage().getWidth() <= ground.getX() + ground.getImage().getWidth()){
                    //For landing on ground
                        if (getCurrentState().equals(State.FALLING)){
                        

                      
                            //I was adding instead of subtracting, but thought adding would be right, as I want the feet of player so down, and top of ground so up.
                            //I feel like it's coincidence. Nope works in all cases, which doesn't make sense to me, well slightly off with differing images but that'll be fixed with our correct images.
                            //But this code should be exact regardless, ass
                        
                            if (getY() - getImage().getHeight()   == ground.getY() - ground.getImage().getHeight()){
                            
                                System.out.println("hit ground");
                                changeState(State.DEFAULT,false);
                                break;
                            }
                        }
                        //For being blocked by cieling / sides.
                        else if (getCurrentState().equals(State.JUMPING)){
                            //Start falling early if hit cieling//need to also make sure they are touching xs as well otherwise it will do on ground far away.
                        
                            if (getY() - 1 == ground.getY()){
                            
                                System.out.println("hit cieling");
                                setLocation(getX(),getY() + 1);
                                changeState(State.FALLING,false);
                                break;
                            }
                        }
                    }
                    else{
                        
                        //Makes sure doesn't go through sides of grounds.

                        //Need to specify rectangle if greater than
                        if (getX() + (getImage().getWidth()) + speed == ground.getX()){
                            setLocation(getX() - speed, getY());
                                                    System.out.println("checking if touching walls");
                                                    break;
                        }
                        else if (getX() - (getImage().getWidth() / 2) - speed == ground.getX() + (ground.getImage().getWidth() / 2)){
                            setLocation(getX() + speed, getY());                        System.out.println("checking if touching walls");
                                                    break;
                        }

                    }
                    
                }
                
        }
        
        if (getCurrentState().equals(State.FALLING)){
                        setLocation(getX(),getY() + 1);
                    }
    }
    
     
        

    
    public void move(int direction){
        
        
        //Cause otherwise don't want animation to play for moving left or right if falling.
        if (getCurrentState() == State.FALLING || getCurrentState() == State.JUMPING){
            
            switch (direction){
                case -1:
                    getCurrentState().blendState(State.MOVINGLEFT);
                    break;
                case 1:
                    getCurrentState().blendState(State.MOVINGRIGHT);
                    break;
            }
            
        }
        else{
            
            switch (direction){
                case -1:
                    changeState(State.MOVINGLEFT,false);
                    break;
                case 1:
                    changeState(State.MOVINGRIGHT,false);
                    break;
            }
        }
        
        changeDirection(direction);
        super.move(direction * speed);
    }
    
    public void jump(){
        
        if (isTouching(Ground.class)){
            groundY= getY();
            changeState(State.JUMPING,false);
        }
    }
    
    public int currentDirection(){
        return directionFacing;
    }
    
    public void changeDirection(int direction){
        directionFacing *= -1;
        
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void transform(String transformation){
        


        if (getCurrentState() == PlayerState.TRANSFORMING || currentTransformation == transformation){
            return;
        }
        

        
    
        currentTransformation = transformation;
        
        //Okay so transforming is where messed up.
        
        //Can no longer take in input.
        changeState(PlayerState.TRANSFORMING, false);
                     System.out.println("Current State is" + getCurrentState());    
        
    }
    
    public void revert(){
      transform("default");
    }
    
    
  
    //By default just rgular attack all transformations will override this attack method
    public void attack(){
        
        System.out.println("default attack");
        
        //Actually tbh, could just have PlayerAttackFactory instead of polymorphism, to avoid
        getWorld().addObject(new PlayerMeleeAttack(),getX() + (attackDistance * directionFacing),getY());
        
        //This will be duplicate code though otherwise, but worry about that later, or actually do move this back there
        timeTillAttack = attackCD;
        //Instead of changing state, it will blendstate so blending so that it's state can be both attacking and running, but for now just changeState is fine.
        changeState(State.ATTACKING,false);
    }
    
    public void absorb(){
          changeState(PlayerState.ABSORBING,false);          
          getWorld().addObject(new AbsorptionAttack(this), getX() + (80 * directionFacing),getY());
    }
    
    public boolean canAttack(){
      
        
      
        return timeTillAttack <= 0;
    }
}
