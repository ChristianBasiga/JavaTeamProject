import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    int speed = 4;
    
    boolean onGround;
    
    Player(){   
        health = 15;
    //    changeState(State.DEFAULT,false);
        currentTransformation = "default";
        
        weight = 10;
        jumpHeight = 200;
        

        onGround = false;
    }
    
    protected void addedToWorld(World world){
         
        groundY = getY();
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
        
        
        if (getCurrentState() == State.JUMPING){
            
            
            setLocation(getX(),getY() - 1);
            if (getY() == groundY + jumpHeight){
                changeState(State.FALLING,true);
            }
        }
        else if (isTouching(Ground.class)){
            
           onGround = true;
           //Cause this may change as touch other platforms.
           groundY = getY();
           
           //And no longer following so back to default state
           changeState(State.DEFAULT,false);
           System.out.println("on ground");
        }
        else if (!isTouching(Ground.class)){
            
            onGround = false;
            
            //This is threaded too but greenfoot handle this one.
            setLocation(getX(),getY() + 1);
           
        }
        
        if (getCurrentState() == State.MOVINGLEFT){
            move(-1);
            changeDirection(-1);
        }
        else if (getCurrentState() == State.MOVINGRIGHT){
            
            move(1);
            changeDirection(1);
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
        changeState(PlayerState.TRANSFORMING, true);
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
    
    public boolean canAttack(){
      
        
      
        return timeTillAttack <= 0;
    }
}
