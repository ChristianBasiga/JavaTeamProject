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
        //Fucked up here.
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
                    
                    //So on true
                    
                    //then may need to reobserve but I'll test that.
                    System.out.println(player);
                    
                    //So after fire transformation, I take in input again.
                }
            }
            else{
                takingInput = true;
            }
            
            
           
    }    
    
    public void act(){

 
        
            if (takingInput){


                if (Greenfoot.isKeyDown("a")){
                    //This so animator reacts and flips sprite accordingly, I could instead just leave it at MOVING, and then just check direction too, and pro better but fuck it.
                    playerMove(-1);
                    if (subject.getCurrentState() != State.MOVINGLEFT){
                        subject.changeState(State.MOVINGLEFT, false);
                    }

                }
                else if (Greenfoot.isKeyDown("d")){
                    
                    playerMove(1);
                    if (subject.getCurrentState() != State.MOVINGRIGHT){
                        subject.changeState(State.MOVINGRIGHT, false);
                    }
                }
                if (Greenfoot.isKeyDown("space")){
                    subject.changeState(State.JUMPING,true);
                }
                //Transformations.
                if (Greenfoot.isKeyDown("e")){            
                    
                    //Oh I see problem. Absorb cooldown needs to start ticking only after let go.
                    if (subject.getCurrentState() != PlayerState.ABSORBING && subject.getCurrentState() != PlayerState.TRANSFORMING && timeTillAbsorb <= 0){
             
                           absorb();    
                    }
                   
                }                   
                else if (Greenfoot.isKeyDown("r")){
                    player.revert();
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
            
        

        if (timeTillAbsorb > 0){
                timeTillAbsorb -= 0.1f;
        }
    }
    
    public void absorb(){
        
          player.changeState(PlayerState.ABSORBING,false);          
          getWorld().addObject(new AbsorptionAttack(player), player.getX() + (80 * player.currentDirection()),player.getY());
    }
    void playerMove(int direction){
        player.changeDirection(direction);
        player.move(direction * player.getSpeed());
        
    }

    
}
