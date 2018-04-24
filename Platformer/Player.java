import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 *
 * This class holds all the data about the player and handles changes to its states.
 * 
 * @author Prince Christian Basiga
 * 
 */
public class Player extends Subject
{

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
   
    
    public Collider collider;
    
    float attackCD = 5.0f;
    float timeTillAttack = 0;
    
    int maxSpeed = 4;
    int speed = 0;
    
    int momentum;
    
    String toTransformTo;
    
    
   
  
   public void damage(int dmg, String type){
       
       //Still might just do composition instead of inheritence. Because at this point the inheritence is useless.
       if (this.toString().contains(type)){
           //then no damage.
           return;
        }
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
        jumpHeight = 15;
        verticalVelocity = jumpHeight;
        changeState(State.DEFAULT,false);
        toTransformTo = null;
        
        initialInvincTime = 10.0f;
        collider = new Collider(this);
        
        collider.setHeight(getImage().getHeight() + 50);
        collider.setWidth(getImage().getWidth() - 10);
       
    }
    
    public void setVerticalVelocity(int velocity){
        this.verticalVelocity = velocity;
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
   
  
    
  
  
   
    //What I need to do is have one set location happening, and just have movement so it's more synchronous
    public void act() 
    {

        if (!indefinitelyInvincible && health > 0){
      
            manageInvincibility(); 
            managePlayerYPosition();
            checkWalls();
            findItem();
        
        
        }
        
        
        if (Greenfoot.isKeyDown("y")){
            
             //Cause intersecting with ground, but this has to be true, and lower than it's top still. Literally just needed to this to realize
             //Okay origin si top left, ut height is still full of it, no fucking shit, it's not like radius. Me making that assumption has wasted alot of time.
                                getWorld().addObject(new Ground(),getX(),collider.getY() + collider.getHeight());
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
        
                            //System.out.println(getCurrentState());
        if (!collider.isTouchingObject(Ground.class) ){
                changeState(State.FALLING,false);
        }
         
        if (getCurrentState().equals(State.JUMPING) || getCurrentState().equals(State.FALLING)){

            //Okay, so total Y increments by 1 everytime jump.
            setLocation(getX() + momentum , getY() + verticalVelocity);

       
            checkFloorAndCieling();
        
            //So for jumping up to jump height from current, it's good.
            //but from current to lower than jump height, then bad.
            if (verticalVelocity < jumpHeight){
             
           
                verticalVelocity += acceleration;
                if (verticalVelocity == 0){   
                    changeState(State.FALLING,false);
                    verticalVelocity += 1;
                }    
            }
            else if (!getCurrentState().equals(State.JUMPING)  && !(collider.isTouchingObject(Ground.class))){//HERE IT WASSSS. Makes sense fall at slow from high jump height, makes perfect sense
            
                //It's this part.
                //It's the drop
                //So it's if I go through it.

                verticalVelocity -= 1;
            }

        }
        
            

        

                        
        
    }
   
   public boolean isInvincible(){
       return invincibilityTime > 0 || indefinitelyInvincible;
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
                
                if (grounds.size() == 0){
                    
                    
                    changeState(State.DEFAULT,false);
                   
                    return;
                }
                           
                for (Ground ground : grounds){      
   
                    //Making sure within bounds of the ground touching
                    if (collider.getX() >= ground.getX() || collider.getX() + (collider.getWidth() + (collider.getWidth() / 2)) <= ground.getX() + ground.getImage().getWidth()){


                        if (getCurrentState().equals(State.FALLING)){


                            
                            //If touching ground on feet
                            if (collider.getY() + collider.getHeight() >= ground.getY()){
                          
                                //setLocation(getX(), getY() - (verticalVelocity - acceleration));
                                verticalVelocity = 0;
                              

                                System.out.println("I need sleep");
                                momentum = 0;
                                changeState(State.DEFAULT,false);
                                break;

                            }   
                        }    
                        //For if jumps into bottom edge of ground or cieling.
                        else if (getCurrentState().equals(State.JUMPING)){

                                                                      

                           
                             // setLocation(getX(),ground.getY() + ground.getImage().getHeight());
                              
                              if (collider.getY() >= ground.getY()){

                        
                                    
                                    
                                    
                                    setLocation(getX(),getY() + Math.abs(verticalVelocity * 2) + 2 );
                             
                                    momentum = 0;
                                    changeState(State.DEFAULT,false);
                                    break;
                                }                    
                        }
                        
                    }
   
                }
            }
            
           
    }
    
    public void checkWalls(){
    
    //    System.out.println("Y pos is " + getY());
       int pullBack = 0;
      
       if (getCurrentState().equals(State.MOVINGRIGHT) || getCurrentState().equals(State.MOVINGLEFT)){
       if (collider.isTouchingObject(Ground.class)){
           
           //Instead of way did hit floor and cieling.
           //I could try diff way where I just try to go through all possible Y offsets, but depending on scale that could be a huge N
           //that v.s just ast most 2 walls?
           
           List<Ground> walls =  getIntersectingObjects(Ground.class);
           
          

            
           for (Ground ground : walls){
               
               
               //In this check similiar to within bounds of x except just for y.
              
               if (collider.getY()  - collider.getHeight() <= ground.getY() -  ground.getImage().getHeight() && collider.getY() + (collider.getHeight()) >= ground.getY() + ground.getImage().getHeight()){
                   
                          if (ground.getX() <= getX()){
                              
                              pullBack = speed;                  

                          }
                          else if (getX() <= ground.getX()){
                              
                              pullBack = -speed;

                          }
                          
                          setLocation(getX() + pullBack, getY());

                           if (pullBack != 0){

                                    while (intersects(ground)){
                                        
                                           setLocation(getX() + pullBack, getY());
                                    }
                           
                            break;
                        }
               }
             
           }
           
       }
    }

      
        
 
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
        
        if (getCurrentState() == PlayerState.TRANSFORMING || this.toString() == transformation){
            return;
        } 
  
        
        toTransformTo = transformation;
        changeState(PlayerState.TRANSFORMING, false);

        
    }
    
    public String transformingInto(){
        
        return toTransformTo;
    }
    
    public void revert(){
      transform("default");
    }
    
    public void attack(RangedAttack attack){
        
        
        if (!canAttack()) return;
        //What will be overriden part is where it spawns from player and what player does as attacks
        attack.setDirection(directionFacing);
        prepareAttack(attack);
          //Where it spawns will also differ.
     
        
    }
    
    //This is part that will be overridden
    protected void prepareAttack(RangedAttack attack){
          
        


        
     
        //This will be duplicate code though otherwise, but worry about that later, or actually do move this back there
        timeTillAttack = attackCD;
        
        //Instead of changing state, it will blendstate so blending so that it's state can be both attacking and running, but for now just changeState is fine.
        changeState(State.ATTACKING,false);

        getWorld().addObject(attack,getX() + collider.getWidth() + (attackDistance * directionFacing),getY() - getImage().getHeight());
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
    
    @Override 
    public String toString(){
        return "Player";
    }
}
