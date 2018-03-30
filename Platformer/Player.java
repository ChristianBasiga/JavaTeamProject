import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Subject
{
    String currentTransformation;
    int health;
    float invincibilityFrames = 500.0f;
    float timeLeft = 0;
    
    
    Player(){
        
        health = 15;
        changeState(State.DEFAULT,false);
    }
    
    public String getCurrentTransformation(){
        return currentTransformation;
    }
   
   
    
    public void act() 
    {
        // Add your action code here.
        
        //Then being damaged
        if (isTouching(Enemy.class) && currentState != State.ATTACKING && currentState != PlayerState.TRANSFORMING
        && currentState != PlayerState.ABSORBING && currentState != State.DAMAGED && timeLeft <= 0){
            
            changeState(State.DAMAGED,false);
            Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
            health -= enemy.getDamage();
            
            timeLeft = invincibilityFrames;

            changeState(State.DEFAULT,false);
          //  System.out.println("New Health: " + health);
        }
        
        
        
        if (timeLeft > 0){            
            timeLeft -= 0.1f;
        }
        
        
    }    
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void transform(String transformation){
        
        currentTransformation = transformation;
        
        //False cause I want all contents of player to be finished mutating and animating before overwriting.
        changeState(PlayerState.TRANSFORMING,true);
     
        
    }
    
    //By default just rgular attack all transformations will override this attack method
    public void attack(){
        
        System.out.println("default attack");
    }
}
