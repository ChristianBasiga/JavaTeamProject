import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerController extends Observer
{

    boolean takingInput = false;
    float absorbCD = 10.0f;
    float timeTillAbsorb = 0;
    Player player;
    
    int xMovement = 0;
    int yMovement = 0;
    
    public PlayerController(Player subject){
        
        threadName = "pc";
       
        this.observe(subject);
        priority = 1;

    }
    
    public void observe(Subject subject){
        
        super.observe(subject);
        player = (Player)subject;
        takingInput = true;
    }

    public void run() 
    {

        //Reaction code to changes in state to subject go here

            if (subject.getCurrentState() == State.DAMAGED || 
                subject.getCurrentState() == PlayerState.TRANSFORMING){
                
                //Stop taking input for movement, attacking, while transformation is happening, ie: animation still playing.
                takingInput = false;
              
                if (subject.getCurrentState() == PlayerState.TRANSFORMING){
                    
                    
                    System.out.println("Transforming");
                    //Changes subject to Player
                    //I may need to reset what others are observing
                    Player prevPlayer = player;
                    Player newPlayer = PlayerFactory.produce(prevPlayer.getCurrentTransformation());
                    
                    newPlayer.setHealth(prevPlayer.getHealth());
                   
                    prevPlayer.copyObservers(newPlayer);   
                    
                    getWorld().addObject(newPlayer,prevPlayer.getX(), prevPlayer.getY());                 
                    getWorld().removeObject(prevPlayer);                    
                    
                    System.out.println(player);
                }
            }
            else{
                takingInput = true;
            }
          
    }    
    
    public void act(){

            if (takingInput){


                checkMovement();
                checkActions();
                
            }
          
        //determiningPlayerMovement();
        //player.setLocation(player.getX() + xMovement, player.getY() + yMovement);

        if (timeTillAbsorb > 0){
                timeTillAbsorb -= 0.1f;
        }
    }
    
    private void checkMovement(){
        
           if (Greenfoot.isKeyDown("a")){
                    
                    player.move(-1);
                 //s   System.out.println("Y movement is : " + yMovement);

                }
                if (Greenfoot.isKeyDown("d")){
                    
                    player.move(1);
                                     //   System.out.println("Y movement is : " + yMovement);

                }
                else{
                    //It seems it must wait for frame until these states are turned off, to register falling to floor.
                    player.getCurrentState().turnOffState(State.MOVINGLEFT);
                    player.getCurrentState().turnOffState(State.MOVINGRIGHT);
                    player.setSpeed(0);
                }
    }
   
    private void checkActions(){
        
        if (!player.getCurrentState().equals(State.JUMPING) && !player.getCurrentState().equals(State.FALLING)){
                    
                    if (Greenfoot.isKeyDown("w")){
                     
                        player.jump();
                    }               
                    //Transformations.
                    else if (Greenfoot.isKeyDown("e")){            
                   
                        if (player.getCurrentState() != PlayerState.ABSORBING && player.getCurrentState() != PlayerState.TRANSFORMING && timeTillAbsorb <= 0){
             
                            player.absorb();    
                        }
                   
                    }                   
                    else if (Greenfoot.isKeyDown("r")){
                        player.revert();

                    }              
                    //Attacking
                    else if (Greenfoot.isKeyDown("f") && player.canAttack()){
                        player.attack();             
                    }
                    
                    //Picking up items
                    else if (Greenfoot.isKeyDown("q")){
                        player.findItem();
                    }
                
                    else if (player.getCurrentState() != PlayerState.DEFAULT){
                    
                        //If it was absorbing, then start ticking the cooldown
                        if (player.getCurrentState() == PlayerState.ABSORBING){
                            timeTillAbsorb = absorbCD;      
                        }
                    
                    //Otherwise if it's not default, then set to default.
                    player.changeState(PlayerState.DEFAULT,false);

                   }
                }
        
    }

    
}
