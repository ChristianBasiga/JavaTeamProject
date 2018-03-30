import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level extends World
{

    public void started(){
       
        //Todo, create all instances that start out in the world
        //DOn't really need to store instance variables, but can change as required
        //And add observers to subjects.
        
        //They all need to be re-observed since new instance of player, atleast I believe so
        GameManager gm = new GameManager();
      
        Player player = new Player();
        
        gm.observe(player);
        
        //Constructur of these handles observing the player.
        PlayerController pc = new PlayerController(player);
        
        Animator playerAnimator = new Animator(player);
        
        //Then each enemey will have it's on animator
        //There will be loop fir each enemy, constructing an Animator along with the enemy.
        Enemy enemy = new Enemy("fire");
        Animator enemyAnimator = new Animator(enemy);

        
        
        
        addObject(player, getWidth() /2, getHeight() / 2);
        addObject(playerAnimator,0,0);
        
        addObject(enemy,getWidth() /2, (getHeight() / 2) + 20 );
        addObject(enemyAnimator,0,0);
        
        addObject(gm,0,0);
        addObject(pc,0,0);
       
        
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
}
