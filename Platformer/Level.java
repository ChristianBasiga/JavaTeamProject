import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends World
{
    //No need for player variable as that instance is constatnly being switched out.
    GameManager gm;
    PlayerController pc;
    Animator playerAnimator;
    ItemManager im;

    int[] playerSpawn;
    

    public void started(){
       
        im = new ItemManager();
        gm = new GameManager();
      
        Player player = new Player();
        
        gm.observe(player);
        
        //Constructur of these handles observing the player.
        pc = new PlayerController(player);
        
        playerAnimator = new Animator(player);     
       
        playerSpawn = new int[]{getWidth() / 2,getHeight() / 2};
        addObject(player,playerSpawn[0],playerSpawn[1]);
        addObject(playerAnimator,0,0);
        addObject(gm,0,0);
        addObject(pc,0,0);
       
        //Set up ground 
        
        addObject(new Ground(), getWidth() /2, (getHeight() / 2) + 300);
        
        Random random = new Random();
        //Testing star
    //    addObject(new Star(), random.nextInt(getWidth() - 50) + 50, getHeight() / 2 + 300);
        
      //  addObject(new Ground(), getWidth() / 2, (getHeight() / 2) - 70);
       /* for (int i = -5; i < 0; ++i){
            addObject(new Ground(), (getWidth() / 2) + (i * 50), (getHeight() / 2) - 100);
        }
       for (int i = 0; i < 5; ++i){
            addObject(new Ground(), (getWidth() / 2) + (i * 50), (getHeight() / 2) + 100);
        }*/
    }
    /**
     * Constructor for objects of class Level.
     * 
     */
    public Level()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
    }
    
    public void act(){
        
    }
    
    public void clearEnemies(){
       
    }
    
    private void setUpEnemies(){
        
        //Create pools of enemies
        //Spawn enemies in their correct spots
        //Again may end up doing this manually.
        
    }
    
    //Might just end up being set manually.
    private void setUpGround(){
    }
    

   
}
