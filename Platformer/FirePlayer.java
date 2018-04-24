import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FirePlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirePlayer extends Player
{
    /**
     * Act - do whatever the FirePlayer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    public FirePlayer(){

             //Set ti before observers are there to react.
             changeState(State.DEFAULT,false);
    }
    
    public void start(){

    }
    
    @Override
    public void changeState(State state, boolean blend){
        
        
      //  System.out.printf("Current state of %s is %s. Changin into state %s\n", toString(),getCurrentState(),state.toString());
        
        super.changeState(state,blend);
    }
    
    @Override
    protected void addedToWorld(World world){
    
       collider.getImage().setTransparency(1);
       

       //Cause i want to make sure they're all observing him still
       //I could do how I did before and transfer observers, just to get it done.
    }
    
    
    
    public void act() 
    {
        // Add your action code here.
        super.act();
        //Could add here checking for woddlers I'm strong against so if they collide with me, they die, though I also get hurt?
      //  System.out.println("fire");
        
        checkIfTouchingWeakness();
             changeState(State.DEFAULT,false);

    }   
    
    //Oh man.. Even this doesn't require it lmao, since those also rn doing composition. If that changes then this will make sense and must be changed.
    private void checkIfTouchingWeakness(){
        
        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        
        
        if (enemy != null && this.toString().contains(enemy.getType())){
            getWorld().removeObject(enemy);
        }
    }
    

    public void attack(){
      
    }
    
    
    
    @Override
    public String toString(){
        
        return "firePlayer";
    }
}
