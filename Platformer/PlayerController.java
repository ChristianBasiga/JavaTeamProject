import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerController extends Observer
{

    boolean takingInput = true;
    float absorbCD = 10.0f;
    float timeTillAbsorb = 0;
    Player player;
    
    public PlayerController(Player subject){
        
        threadName = "pc";
       
        this.observe(subject);
        priority = 1;

    }
    
    public void observe(Subject subject){
        
        super.observe(subject);
        player = (Player)subject;
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


                if (Greenfoot.isKeyDown("a")){
                    
                    player.move(-1);
                }
                else if (Greenfoot.isKeyDown("d")){
                    
                    player.move(1);
                }
                //This is off one, where i chnage state of player and the subject
                //reacts instead but fuck it for, otherwise this is just inside player jump
                //method, exact same code, putting in player will just add extra overhead
               
                if (player.getCurrentState() != State.JUMPING && player.getCurrentState() != State.FALLING){
                    
                    if (Greenfoot.isKeyDown("space")){
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
                        getWorld().addObject(new Enemy("sd"),player.getX(),player.getY());
                    }              
                    //Attacking
                    else if (Greenfoot.isKeyDown("f") && player.canAttack()){
                        player.attack();             
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
            
        

        if (timeTillAbsorb > 0){
                timeTillAbsorb -= 0.1f;
        }
    }
    

    
}
