import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AbsorptionAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AbsorptionAttack extends Actor
{
    /**
     * Act - do whatever the AbsorptionAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //To transform player
    Player emitter;
    
    public AbsorptionAttack(Player player){
        emitter = player;
        //This actually could be another observer, cause it changes once player is diff state, only problem is removing observer. But that feels unneccessary for something that gets destroyed constantly.
    }
    
    public void act() 
    {
      if (isTouching(Enemy.class)){
          
          //Then based on type of the Enemy, probably just attribute then change accordingly.
          Enemy enemyAbsorbing = (Enemy)getOneIntersectingObject(Enemy.class);
           getWorld().removeObject(enemyAbsorbing);
          getWorld().removeObject(this);
          //Change type name later on as needed
          if (enemyAbsorbing.getType() == "fire"){
              System.out.println("Absorbing fire enemy");
              //Okay so this happens.
              emitter.transform("fire");
          }
        
      }
      else if (emitter.getCurrentState() != PlayerState.ABSORBING){
          
          //Then garbage colection get rid of this cause only world has pointer to it right now.
          getWorld().removeObject(this);
          
      }
    }    
}
