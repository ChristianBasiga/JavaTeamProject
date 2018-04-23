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
    ItemManager im;

    int[] playerSpawn;
    

    public void started(){
       
    }
    /**
     * Constructor for objects of class Level.
     * 
     */
    public Level()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
          im = new ItemManager();
        gm = new GameManager();
      
        Player player = new Player();
        
        gm.observe(player);
        
        //Constructur of these handles observing the player.
        pc = new PlayerController(player);
        
         
       
        playerSpawn = new int[]{getWidth() / 2,getHeight() / 2};
        addObject(player,playerSpawn[0],playerSpawn[1]);
  
        addObject(gm,0,0);
        addObject(pc,0,0);
       
        //Set up ground 
        setUpGround();
      
        //set up enemies
        setUpEnemies();
    }
    
    public void act(){
        
    }
    
    public void clearEnemies(){
       
    }
    
    private void setUpEnemies(){
        
        //Create pools of enemies
        for(int i = 0; i < 3; i++){
            Enemy e = new Enemy(null);
            int n = e.getImage().getWidth();
            addObject(e, 20 + n*i, 200);
        }
        //Spawn enemies in their correct spots
        //Again may end up doing this manually.
        
    }
    
    //Might just end up being set manually.
    private void setUpGround(){
        
        //spawns floor
        for(int i = 0; i < 7; i++){
            Ground g = new Ground();
            int n = g.getImage().getWidth();
            System.out.println("Ground width: "+n);
            addObject(g, n*i, 400); //floor
        }
        
        //spawns platform1
        for(int i = 0; i < 3; i++){
            Ground g = new Ground();
            int n = g.getImage().getWidth()/2;
            System.out.println("Ground width: "+n);
            addObject(g, n*i, 330); //floor
        }
        
        //spawns platform2
        for(int i = 0; i < 3; i++){
            Ground g = new Ground();
            int n = g.getImage().getWidth();
            System.out.println("Ground width: "+n);
            addObject(g, 500 + n*i, 330); //floor
        }
    }
    

   
}
