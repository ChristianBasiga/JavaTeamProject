import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager extends Observer
{   
    static final double GRAVITY = 9.81;
    
    //UI
    HealthBar playerHealth;
    GameOver gameOver;
    PauseMenu pauseMenu;
    
    
    
    public GameManager() {
        
        playerHealth = new HealthBar();
        threadName = "gm";
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
        
        //Yeah, I mean of course it works here but that shouldn't be neccessarry.
         Player player = (Player)subject;
       //  playerHealth.setHealth(player.getHealth());
              
    }
    
    private void hideColliders(){
        
         //Makes all colliders invisible.
        List<Collider> colliders = getWorld().getObjects(Collider.class);
        GreenfootImage transparentImage = new GreenfootImage("transparent");
        for (Collider coll : colliders){
            
            //Make their image into something transparent with same dimensions
            //so collision still works fine.
            transparentImage.scale(coll.getWidth(),coll.getHeight());
            coll.setImage(transparentImage);
        }
    }
    
    public void run() {
        
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
           
        }
        
    }
   
    private void gameOver(){

        getWorld().addObject(gameOver, getWorld().getWidth() / 2, getWorld().getHeight() / 2);

    }
    
    private void pauseGame(){
        getWorld().addObject(pauseMenu, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }
   
}
