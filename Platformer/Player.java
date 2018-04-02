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
    int verticalVelocity;
    int acceleration = 1;
    int jumpHeight;
    int groundY;
    
    Collider collider;
    
    float attackCD = 10.0f;
    float timeTillAttack = 0;
    
    int maxSpeed = 5;
    int speed = 0;
    
    
    

    
    boolean onGround;
    
    //Blending states between movement and Jumping not working
    
    public Player(){   
        
        
        health = 15;
    //    changeState(State.DEFAULT,false);
        currentTransformation = "default";
        
      

        jumpHeight = 15;
        verticalVelocity = jumpHeight;
        changeState(State.DEFAULT,false);
        
        collider = new Collider(this);
        onGround = false;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    protected void addedToWorld(World world){
        
        if (collider.getWorld() == null)
        {
            getWorld().addObject(collider,getX(),getY());
        
        //These 2 lines are for testing, as box will be invisible later.
        getWorld().removeObject(this);
        collider.getWorld().addObject(this,collider.getX(),collider.getY());
        
                changeState(State.FALLING, false);
        
       }

    
    }
   
  
    
    
    public String getCurrentTransformation(){
        return currentTransformation;
    }
    
  
   
    //What I need to do is have one set location happening, and just have movement so it's more synchronous
    public void act() 
    {
       
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
        
       
     
        
        managePlayerYPosition();
                
    }    
    
    private void managePlayerYPosition(){
      
        

        setLocation(getX()/* + (speed * directionFacing)*/, getY() + verticalVelocity);           
        
        
        //Either when done jumping, aka when verticalVelocity is jumpHeight again.
        //Otherwise when done falling, aka hit ground, and then this will change
        if ((getCurrentState().equals(State.JUMPING) || getCurrentState().equals(State.FALLING)) && verticalVelocity <= jumpHeight){
            
           verticalVelocity += acceleration;
           
           //Then this is critical point and switches directions
           if (verticalVelocity == 0){
               changeState(State.FALLING,false);
           }
        }
        else if (!getCurrentState().equals(State.JUMPING) && !collider.isTouchingObject(Ground.class)){
            
            //then go bakc to falling.
            verticalVelocity = 0;
            changeState(State.FALLING,false);
        }
        
        checkWalls(); 
                        
        checkFloorAndCieling();
            
            
   
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
       speed = 1;
       setLocation(getX() + direction * speed, getY());
     //  super.move(direction * speed);
    }
    
    public void checkFloorAndCieling(){
        

         if (collider.isTouchingObject(Ground.class) || collider.getY() + 1 == getWorld().getHeight() ){

                        
             
                List<Ground> grounds = collider.getCollidingObjects(Ground.class);
                if (grounds.size() == 0){
                    
                    speed = 0;
                    
                    //no longer on ground, so need to keep falling
                    changeState(State.DEFAULT,false);
                }
                
                for (Ground ground : grounds){      

                    
                    
                    //Making sure within bounds of the ground touching
                    if (collider.getX() >= ground.getX() || collider.getX() + (collider.getWidth() + (collider.getWidth() / 2)) <= ground.getX() + ground.getImage().getWidth()){
                        
                        
                        //For if falls onto ground
                        if (getCurrentState().equals(State.FALLING)){
                            
                            if (collider.getY() + collider.getHeight() + collider.getHeight() / 2 >= ground.getY()){
                           // if (getOneObjectAtOffset(collider.getWidth() / 2,collider.getHeight(),Ground.class) != null){                
                                verticalVelocity = 0;
                                changeState(State.DEFAULT,false);
                                                                                                        System.out.println("I happen too");

                            }
                            
                           
                        }    
                        //For if jumps into bottom edge of ground or cieling.
                          if (getCurrentState().equals(State.JUMPING)){
                                
                              System.out.println(collider.getY() - collider.getHeight() / 2);
                              System.out.println(ground.getY() + ground.getImage().getHeight() / 2);
                              

                                if (collider.getY() - collider.getHeight() / 2 <= ground.getY() + ground.getImage().getHeight() ){
                                    setLocation(getX(),getY() + 2);
                                    verticalVelocity = 10;
                                    changeState(State.DEFAULT,false);
                                }
                                
                               
                        }
                        
                    }
   
                }
            }
    }
    
    public void checkWalls(){
        
        //Left side of platform.
                    //if (getCurrentState().equals(State.MOVINGLEFT) && (collider.getX() - collider.getWidth() / 2) <= ground.getX() + ground.getImage().getWidth() / 2){
        if (getOneObjectAtOffset(-collider.getWidth() * 2,0,Ground.class) != null){
            System.out.println("why am i no longer working yo");
            setLocation(getX() + 1 , getY());

        }
                    //Right side of platform
        else if (getOneObjectAtOffset(collider.getWidth() * 2,0,Ground.class) != null){
            System.out.println("why am i no longer working yo");
            setLocation(getX() - 1 , getY());

        }
 
    }
    
    public void jump(){
        
        if (collider.isTouchingObject(Ground.class) || collider.getY() + 1 == getWorld().getHeight()){
            
            groundY= getY();
            changeState(State.JUMPING,false);
            verticalVelocity = -jumpHeight;
            
        }
    }
    
    public int currentDirection(){
        return directionFacing;
    }
    
    public void changeDirection(int direction){
        directionFacing = direction;
        
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
    
    //If moving while dropping I don't get stopped
  
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
