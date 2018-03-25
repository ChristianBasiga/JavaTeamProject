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
    
    Player(){
        
        health = 15;
    }
    
  
    public void act() 
    {
        // Add your action code here.
        
        //Then being damaged
        if (isTouching(Enemy.class) && currentState != State.ATTACKING){
            
            changeState(State.DAMAGED,false);
            Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
            health -= enemy.getDamage();
            System.out.println("New Health: " + health);
        }
        
    }    
}
