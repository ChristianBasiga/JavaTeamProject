import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the absorption attack of the player. If absorbable enemies touch this object, then the player will transform.
 * 
 * @author Prince Christian Basiga
 */
public class AbsorptionAttack extends Actor
{
    
    
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
