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
    int speed = 2;
    
    boolean onGround;
    
    Player(){   
        health = 15;
    //    changeState(State.DEFAULT,false);
        currentTransformation = "default";
        
        weight = 10;
        jumpHeight = 50;
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
        
                  
        
        if (getCurrentState().equals(State.JUMPING)){
          
            setLocation(getX(),getY() - 1);
            System.out.printf("Jumping: Current Y: %d, jumpheight: %d, groundY: %d\n", getY(), jumpHeight, groundY);

            if (getY() <= groundY - jumpHeight || isAtEdge()){

                changeState(State.FALLING,false);
            }
        }
        //This works perfectly
        else if (!isTouching(Ground.class)){
            
            //This is threaded too but greenfoot handle this one.
        //                System.out.printf(" Falling: Current Y: %d,, groundY: %d\n", getY(), groundY);
            setLocation(getX(),getY() + 1);
        }
        
        else if (isTouching(Ground.class)){
            
           //Cause this may change as touch other platforms.
           groundY = getY();
           
           //And no longer following so back to default state
           changeState(State.DEFAULT,false);

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
