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
    public GameManager gm;
    public PlayerController pc;
    public ItemManager im;

    public AnimationManager am;
    
    int[] playerSpawn;
    

  
    /**
     * Constructor for objects of class Level.
     * 
     */
    public Level()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        Player player = new Player();
        
        im = new ItemManager();
        gm = new GameManager();
        am = new AnimationManager();
        pc = new PlayerController(player);
      
        //I COULD make it so these guys react to player transforming but getting reference then doing that. Also gives more to react at that point.
        

        am.observe(player);
        player.addObserver(am);
        
        gm.observe(player);
        player.addObserver(gm);        


        
        pc.observe(player);
        player.addObserver(pc);
         
       
        playerSpawn = new int[]{getWidth() / 2,getHeight() / 2};
        addObject(player,playerSpawn[0],playerSpawn[1]);
  
        addObject(gm,0,0);
        addObject(pc,0,0);
       
        addObject(im,0,0);
        addObject(am,0,0);
        //Set up ground 
        setUpGround();
      
        //set up enemies
        setUpEnemies();
    }
    public void started(){
        
        gm.getImage().setTransparency(1);
        pc.getImage().setTransparency(1);
        im.getImage().setTransparency(1);
        am.getImage().setTransparency(1);
        gm.startGame();
    }
    
    public void act(){
        
    }
    
    public void clearEnemies(){
       
    }
    
    private void setUpEnemies(){
        
        //Create pools of enemies
        //This sin't creating a pool.
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
