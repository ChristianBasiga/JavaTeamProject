import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 *
 * This class holds all the data about the player and handles changes to its states.
 * 
 * @author Prince Christian Basiga
 * 
 */
public class Player extends Subject implements ITakeDamage
{
    String currentTransformation = "default";
    int health;
   
    //This is mainly to be set for damage so at half way point still invincibile but can move.
    float initialInvincTime;
    float invincibilityTime;
    boolean indefinitelyInvincible;
    
    //-1 for left and 1 for right.
    int directionFacing = 1;
    
    int attackDistance = 5;
    int absorptionDistance = 20;
  
    
    //Basically fall speed.
    int verticalVelocity;
    int acceleration = 1;
    int jumpHeight;
   
    
    Collider collider;
    
    float attackCD = 5.0f;
    float timeTillAttack = 0;
    
    int maxSpeed = 4;
    int speed = 0;
    
    int momentum;
    
    
   
  
   public void damage(int dmg){
       health -= dmg;
   }
   
   public void setHealth(int health){
        this.health = health;
    }
    
    public int getHealth(){
        return health;
    }

    
    public Player(){   
        
        
        health = 15;
   
        currentTransformation = "default";
        
      

        jumpHeight = 15;
        verticalVelocity = jumpHeight;
        changeState(State.DEFAULT,false);
        
        initialInvincTime = 10.0f;
        collider = new Collider(this);
       
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
        
        if (!indefinitelyInvincible && health > 0){
      
            manageInvincibility(); 
            managePlayerYPosition();
            checkWalls();
            findItem();
        
        }
               
    }    
    
    private void manageInvincibility(){
        
         if (collider.isTouchingObject(Enemy.class) && getCurrentState() != State.ATTACKING && getCurrentState() != PlayerState.TRANSFORMING
        && getCurrentState() != State.DAMAGED && getCurrentState() != PlayerState.PAUSED){
            
            
            if (!isInvincible()){
                
               
                Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class); 
                if (enemy != null){
                     
                    System.out.println("here");
                health -= enemy.getDamage();
                if (health <= 0){
                    health = 0;
                }
                changeState(State.DAMAGED,false);
                invincibilityTime = initialInvincTime;
             }
            }
        }
        
        //So they can move while still invincible, otherwise fucked, if enemy stays on player.
        if (invincibilityTime <= initialInvincTime - 0.5f && getCurrentState() == State.DAMAGED){
            
            if (health == 0){
                changeState(State.DEAD,false);
            }
            else{
                
                if (!indefinitelyInvincible){
                    changeState(State.DEFAULT,false);
                }
            }
        }
     
        
        
        if (invincibilityTime > 0){
            invincibilityTime -= 0.1f;
        }
        else if (invincibilityTime <= 0){
            
            //Only if became invincible or damaged, cause if paused then stays invincible
            if (getCurrentState().equals(PlayerState.INVINCIBLE) || getCurrentState() == State.DAMAGED){
                changeState(State.DEFAULT,false);
            }
        }
        
            
        if (timeTillAttack > 0){
                timeTillAttack -= 0.1f;
        } 
        
       
     
    }
    
    private void managePlayerYPosition(){
        
         
        if (getCurrentState().equals(State.JUMPING) || getCurrentState().equals(State.FALLING)){

            setLocation(getX() + momentum , getY() + verticalVelocity);

            
            if (verticalVelocity <= jumpHeight){
             
           
                verticalVelocity += acceleration;
                if (verticalVelocity == 0){   
                    changeState(State.FALLING,false);
           
                }    
            }
            else if (!(collider.isTouchingObject(Ground.class))){//HERE IT WASSSS. Makes sense fall at slow from high jump height, makes perfect sense
            
                verticalVelocity -= 1;
            }
        }
            

        
       checkFloorAndCieling();
                        
        
    }
   
   public boolean isInvincible(){
       return invincibilityTime > 0;
    }
        
   public void becomeInvincible(float timePeriod){
       
        if (timePeriod < 0){
          indefinitelyInvincible = true;
          return;
       }
       else if (timePeriod == 0){
           indefinitelyInvincible = false;
           return;
        }
       
       invincibilityTime = timePeriod;
       initialInvincTime = timePeriod;
      
   }
    
   public void move(int direction){  
            
        switch (direction){
               case -1:
                   changeState(State.MOVINGLEFT,false);
                   break;
               case 1:
                   changeState(State.MOVINGRIGHT,false);
                   break;
        }
            
        
        
        
       changeDirection(direction);
       
       //If was already moving before, accelerate, otherwise set speed
       if (speed > 0 && speed < maxSpeed){
            speed += acceleration;
        }
       else if (speed == 0){
           speed = 1;
       }
                 
       setLocation(getX() + speed * directionFacing,getY());
    }
    
    public void checkFloorAndCieling(){
        

         if (collider.isTouchingObject(Ground.class) ){

                List<Ground> grounds = collider.getCollidingObjects(Ground.class);  
                
                momentum = 0;
                
                
                if (grounds.size() == 0){
                    
                    
                    changeState(State.DEFAULT,false);
                   
                    return;
                }
                           
                for (Ground ground : grounds){      
   
                    //Making sure within bounds of the ground touching
                    if (collider.getX() >= ground.getX() || collider.getX() + (collider.getWidth() + (collider.getWidth() / 2)) <= ground.getX() + ground.getImage().getWidth()){

                        if (getCurrentState().equals(State.FALLING)){

                            //This should happen when jump against wall, but not standing atop it.
                            //This could be made better, it's dipping past floor a little bit prob due to velocity
                            if (collider.getY() + collider.getHeight() + collider.getHeight() / 2 >=  ground.getY()){
                          
                                verticalVelocity = 0;
                              
                                momentum = 0;
                                changeState(State.DEFAULT,false);
                                break;

                            }   
                        }    
                        //For if jumps into bottom edge of ground or cieling.
                        else if (getCurrentState().equals(State.JUMPING)){
                                  
                                if (collider.getY() - collider.getHeight() / 2  - verticalVelocity <= ground.getY() + ground.getImage().getHeight() ){
                                
                                   
                                    verticalVelocity = 10;
                                    momentum = 0;
                                    changeState(State.DEFAULT,false);
                                    break;
                                }                    
                        }
                        
                    }
   
                }
            }
            else{
                changeState(State.FALLING,false);
            }
           
    }
    
    public void checkWalls(){
    
       
      
        int pullBack = 0;
        //Jittery v.s going through walls while jumping choose former.
        if (getCurrentState().equals(State.MOVINGLEFT) && getOneObjectAtOffset(-(collider.getWidth()/ 2  + speed),-collider.getHeight() / 2,Ground.class) != null){
                pullBack = speed * 2; 
        }
                    //Right side of platform
        else if (getCurrentState().equals(State.MOVINGRIGHT) && getOneObjectAtOffset((collider.getWidth() / 2 + speed),-collider.getHeight() / 2,Ground.class) != null){
               pullBack = -speed * 2;
        }
       
        //It does work sometimes, main problem is the differing sizes.
        /*if ((getCurrentState().equals(State.JUMPING) || getCurrentState().equals(State.FALLING))){
            
            System.out.println("pull back " + pullBack);
            pullBack *= 2;
                        System.out.println("pull back after" + pullBack);
        }*/
        
        setLocation(getX() + pullBack, getY());
        
 
    }
    
    public void jump(){
        
        if (collider.isTouchingObject(Ground.class) ){
            
            boolean blend = getCurrentState() == State.MOVINGRIGHT || getCurrentState() == State.MOVINGLEFT;
        
            //okay, does blend now but now falls when moves??
            changeState(State.JUMPING,blend);
           
            if (blend){
                momentum = directionFacing * speed;
            }

            verticalVelocity = -jumpHeight;
                      
             
        }
    }
    
    public int currentDirection(){
        return directionFacing;
    }
    
    public void changeDirection(int direction){
        directionFacing = direction;
        
    }
    
    
    
    public void transform(String transformation){
        
        if (getCurrentState() == PlayerState.TRANSFORMING || currentTransformation == transformation){
            return;
        } 
    
        currentTransformation = transformation;
        
        //Okay so transforming is where messed up.
        
        //Can no longer take in input.
        changeState(PlayerState.TRANSFORMING, false);

        
    }
    
    public void revert(){
      transform("default");
    }
    
    public void attack(RangedAttack attack){
        
        //What will be overriden part is where it spawns from player and what player does as attacks
        
        prepareAttack(attack);
          //Where it spawns will also differ.
        getWorld().addObject(attack,getX() + collider.getWidth() + (attackDistance * directionFacing),getY() - getImage().getHeight());
        
    }
    
    //This is part that will be overridden
    protected void prepareAttack(RangedAttack attack){
          
        
        attack.setDirection(directionFacing);
        
     
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
    
    public void findItem(){
      
       
       //For deciing if looking left or right.
       int whereToLook = collider.getWidth() / 2 * directionFacing;
        
       Item item = (Item)getOneObjectAtOffset(whereToLook,0,Item.class);
       
       //Item item = (Item)getOneIntersectingObject(Item.class);
       
       if (item != null){
           

           //Picks up items, which will trigger events for item to take effect.
           item.pickUp(this);
       }
    }
}
