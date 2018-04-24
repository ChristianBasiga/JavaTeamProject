import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 *
 * GameManager observes the player to determine the state of the game such as GameOver and Pausing
 * and makes changes to the game and pops up UI accordingly.
 * 
 * @author Prince Christian Basiga 
 */
public class GameManager extends Observer
{   

    
    //UI
    HealthBar playerHealth;
    GameOver gameOver;
    PauseMenu pauseMenu;
    
    
    
    public GameManager() {
        
        playerHealth = new HealthBar();
        priority = 2;    
        StraightShot enemyShot = new StraightShot();
        gameOver = new GameOver();
        pauseMenu = new PauseMenu();
    }
    
    public void addedToWorld(World myWorld){
        
       Player player = (Player)subject;
       playerHealth.setHealth(player.getHealth());
       myWorld.addObject(playerHealth, 50, 20);
       
        
    }
    
    //At the start of the game, makes all the colliders in the world invisible.
    //honestly could prob just do based on image, since theirs prob better, maybe.
    public void startGame(){
        
       hideColliders();
    }
    
    public void act(){
              
    }
    
    private void hideColliders(){
        
         //Makes all colliders invisible.
        List<Collider> colliders = getWorld().getObjects(Collider.class);
     
        for (Collider coll : colliders){
            
            //Make their image into something transparent with same dimensions
            //so collision still works fine.
          //  coll.getImage().setTransparency(1);
        }
    }
    
    @Override
    public void react() {
        
        //I could just set up UI here instead of UIManager
        //cause at this point GameManager wouldn't nothing else.
        if (subject != null) {
            
           Player player = (Player)(subject);
                
           if (player.getCurrentState() == State.DEAD){
               gameOver();
           }
           
           else if (player.getCurrentState() == PlayerState.PAUSED){
               pauseGame();
           }
           else if (player.getCurrentState() == State.DAMAGED){
               //Should only really ever set it again when state changes, I know if have this in act, it updates correctly.
               playerHealth.setHealth(player.getHealth());
               
               
           }
           else if (player.getCurrentState() == PlayerState.TRANSFORMING){
               Level level = (Level)getWorld();
               
               //Cause then player up there is the past state player that was transforming
               //what I'm getting from pc. is new player the past state one transformed into.
               Player newPlayer = level.pc.getCurrentPlayer();             
               //Not using observe method but setting directly, because I know that all players have sure been initialized to already have us as observers.
               this.subject = newPlayer;
           }
           else if (player.getCurrentState() == State.DEFAULT){
               getWorld().removeObject(pauseMenu);
            }
           
        }
        
    }
   
    private void gameOver(){

        getWorld().addObject(gameOver, getWorld().getWidth() / 2, getWorld().getHeight() / 2);

    }
    
    private void pauseGame(){
        getWorld().addObject(pauseMenu, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }
   
}
