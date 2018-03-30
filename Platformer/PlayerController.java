import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerController extends Observer
{
    Player player;
    boolean takingInput = true;
    
    
    public PlayerController(Player player){
        
        threadName = "pc";
        this.player = player;
        this.observe(player);
        priority = 1;

    }

    public void run() 
    {

        //Reaction code to changes in state to player go here
            if (player.getCurrentState() == State.DAMAGED || 
                player.getCurrentState() == PlayerState.TRANSFORMING){
                
                //Stop taking input for movement, attacking, etc.
                takingInput = false;
                
                if (player.getCurrentState() == PlayerState.TRANSFORMING){
                    
                    
                    //Changes player to Player
                    //I may need to reset what others are observing
                    Player prevPlayer = player;
                    player = PlayerFactory.produce(player.getCurrentTransformation());
                    player.setHealth(prevPlayer.getHealth());
                    getWorld().addObject(player,prevPlayer.getX(), prevPlayer.getY());
                    getWorld().removeObject(prevPlayer);
                    //then may need to reobserve but I'll test that.
                    System.out.println(player);
                    //Problem here is that this is called from separate thread, and different thread i
                    takingInput = true;
                }
            }
            else{
                System.out.println("Taking input again");
                takingInput = true;
            }
            
            
           
    }    
    
    public void act(){

 
        
            if (takingInput){

                if (Greenfoot.isKeyDown("a")){
                    System.out.println("Current ealth: " + player.getHealth()); 
                }
                else if (Greenfoot.isKeyDown("w")){
                    
                }
                else if (Greenfoot.isKeyDown("s")){
                    
                }
                else if (Greenfoot.isKeyDown("d")){
                    
                }
                
                //Attack inputs
                else if (Greenfoot.isKeyDown("e")){
            
                    //Why is this twice, wtf.

                    if (player.getCurrentState() != PlayerState.ABSORBING){
             
                    //Cause already reacting to it, and for animation reaction
                    //It will continue looping through currently selected animation
                    //So no resaon toset it again
                    player.changeState(PlayerState.ABSORBING,false);//This makes sense to be in separate thread, but problem still lies that spawning the absorption attack twice
                    //but this did fix the input probl
                    System.out.println("State is ABSORBING");
                    
                    getWorld().addObject(new AbsorptionAttack(player),player.getX(),player.getY());
                
                    }
                }   
                else if (Greenfoot.isKeyDown("f")){
                     player.attack();
                }
                else if (player.getCurrentState() != PlayerState.DEFAULT){
                    player.changeState(PlayerState.DEFAULT,false);
                }
            }
    
    
    }
    
    
   
}
