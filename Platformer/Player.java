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
    int weight;
    int jumpHeight;
    int groundY;
    
    Collider collider;
    
    float attackCD = 10.0f;
    float timeTillAttack = 0;
    int speed = 1;
    
    
    int xMovement = 0;
    int yMovement = 0;
    
    boolean onGround;
    
    //Blending states between movement and Jumping not working
    
    Player(){   
        health = 15;
    //    changeState(State.DEFAULT,false);
        currentTransformation = "default";
        
        weight = 1;
        jumpHeight = 100;
        changeState(State.DEFAULT,false);
        
        collider = new Collider(this);
        onGround = false;
    }
    
    protected void addedToWorld(World world){
        
        if (collider.getWorld() == null)
        {
            getWorld().addObject(collider,getX(),getY());
        
        //These 2 lines are for testing, as box will be invisible later.
        getWorld().removeObject(this);
        collider.getWorld().addObject(this,collider.getX(),collider.getY());
    }
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
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public int getSpeed(){
        return speed;
    }
   
    //What I need to do is have one set location happening, and just have movement so it's more synchronous
    public void act() 
    {
        System.out.println("current state is " + getCurrentState());
        //Then being damaged
        //Bit more overhead, but enforces use of not using currentState directly.
        if (collider.isTouchingObject(Enemy.class) && getCurrentState() != State.ATTACKING && getCurrentState() != PlayerState.TRANSFORMING
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
        
        if (!getCurrentState().equals(State.MOVINGRIGHT) && !getCurrentState().equals(State.MOVINGLEFT)){
            //if no longer moving, then no x movement
            xMovement = 0;
        }
                  
        managePlayerYPosition();
                
    }    
    
    private void managePlayerYPosition(){
      

        if (getCurrentState().equals(State.JUMPING)){


            yMovement = -weight;


            //So the state is jumping, but not falling down.

            if ((collider.getY() <= groundY - jumpHeight) || collider.getY() == 0){

                changeState(State.FALLING,false);
            }
        }
        //This works perfectly
       
        else if (!collider.isTouchingObject(Ground.class) && collider.getY() + 1 != getWorld().getHeight()){

            if (!getCurrentState().equals(State.FALLING)){

                changeState(State.FALLING, false);
            }
        }
 
       //If touching ground and falling
         if (collider.isTouchingObject(Ground.class) || collider.getY() + 1 == getWorld().getHeight() ){

                            
                List<Ground> grounds = collider.getCollidingObjects(Ground.class);
                if (grounds.size() == 0){
                    changeState(State.DEFAULT,false);
                }
                
                for (Ground ground : grounds){
                    
                  

                    if (getCurrentState().equals(State.MOVINGLEFT) && (collider.getX() - collider.getWidth() / 2) == ground.getX() + ground.getImage().getWidth() / 2){
                        setLocation(getX() + speed , getY());
                    }
                    //So this one works now, but above does not.
                    else if (getCurrentState().equals(State.MOVINGRIGHT) && (collider.getX() + collider.getWidth() + collider.getWidth() / 2)  == ground.getX()){
                        
                        setLocation(getX() - speed , getY());
                    }
                    //Making sure within bounds of a ground.
                    if (collider.getX() >= ground.getX() || collider.getX() + (collider.getWidth() + (collider.getWidth() / 2)) <= ground.getX() + ground.getImage().getWidth()){
                        
                        //For if falls onto ground
                        if (getCurrentState().equals(State.FALLING)){
                            
                            if (collider.getY() + collider.getHeight() + collider.getHeight() / 2 >= ground.getY()){
                                
                                //What I need to do, is move changing these values when state is updated in PlayerController, and then set location through PlayerController
                                yMovement = 0;
                                changeState(State.DEFAULT,false);
                            }
                            
                           
                        }
                        
                        //For if jumps into bottom edge of ground
                        else if (getCurrentState().equals(State.JUMPING)){
                                
                                if (collider.getY() == ground.getY() + ground.getImage().getHeight() / 2){
                                    setLocation(getX(),getY() + 1);
                                    changeState(State.FALLING,false);
                                }
                                
                        }
                        
                    }
                   
                  
               // }
                 
                }
            }
              
            if (getCurrentState().equals(State.FALLING)){
                  yMovement = weight;
            }
            
            setLocation(getX() + xMovement, getY() + yMovement);
    
    }
    
   public void move(int direction){
        
        boolean blendMovement = false;
        //Cause otherwise don't want animation to play for moving left or right if falling.
        blendMovement = (getCurrentState().equals(State.FALLING) || getCurrentState().equals(State.JUMPING));
          
            
        switch (direction){
               case -1:
                   changeState(State.MOVINGLEFT,blendMovement);
                   break;
               case 1:
                   changeState(State.MOVINGRIGHT,blendMovement);
                   break;
        }
            
           
        
        
       changeDirection(direction);
       xMovement = direction;
     //  super.move(direction * speed);
    }
    
    public void jump(){
        
        if (collider.isTouchingObject(Ground.class) || collider.getY() + 1 == getWorld().getHeight()){
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
