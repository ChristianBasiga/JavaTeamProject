import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
/**
 * Player controller maniplates the data in the player and handles input to the player.
 * I could change this so that player is really just a nested class to PlayerController
 * and access only allowed to PlayerController to change Player data
 * that will enforce this design.
 * 
 * @author Prince Christian Basiga 
 */
public class PlayerController extends Observer
{

    boolean takingInput = false;
    float absorbCD = 10.0f;
    float timeTillAbsorb = 0;
   
    
    Map<String,Player> playerMap;
    Map<String,RangedAttack> playerAttacks;
    
    //Player attacks
    StraightShot defaultAtt;
    WaveShot fireAtt;
    LightningShot lightningAtt;
    
    //Pool for player attacks could be here.
    
    PoolManager playerAttackPools;

    //So that pause menu doesn't flicker.
    float slightPauseDelay = 1.0f;
    float timeLeftForUnpause = 0;
    
    public PlayerController(Player subject){
   
        playerAttackPools = new PoolManager();

        
        this.observe(subject);
        priority = 100;

       
        initPlayers();
        initPlayerAttacks();
    }
    
    public void initPlayers(){
        
        playerMap = new HashMap<String,Player>();
        playerMap.put("Player",(Player)subject);;
        playerMap.put("firePlayer",new FirePlayer());
        
        playerMap.put("lightningPlayer",new LightningPlayer());
        
    }
    
    //This will have highest priority to make sure the rest of them will be observing the correct player.
    public Player getCurrentPlayer(){
        
        return (Player)subject;
        
    }
    
    private void initPlayerAttacks(){
        
        
        playerAttacks = new HashMap<String,RangedAttack>();
        
        StraightShot defaultAtt = new StraightShot();
        WaveShot fireAtt = new WaveShot();
        LightningShot lightningAtt = new LightningShot();
        
        
        playerAttacks.put("Player",defaultAtt);
        playerAttacks.put("firePlayer",fireAtt);
        playerAttacks.put("lightningPlayer",lightningAtt);
        
        playerAttackPools.addPool("Player",defaultAtt,20);
        playerAttackPools.addPool("firePlayer",fireAtt,10);
        playerAttackPools.addPool("lightningPlayer",lightningAtt,10);

        
    }
  

    @Override
    public void react() 
    {

        //Reaction code to changes in state to subject go here
        
            //local player meaning current player;
            Player player = (Player)subject;
            System.out.println("Player controller reacting: " +  player.getCurrentState());

            if (player.getCurrentState() == State.DAMAGED || player.getCurrentState() == State.DEAD ||
                player.getCurrentState() == PlayerState.TRANSFORMING || player.getCurrentState() == PlayerState.PAUSED){
                
                //Stop taking input for movement, attacking, while transformation is happening, etc.
                takingInput = false;
                
                //Indefinitely invincible if paused
                if (player.getCurrentState() == PlayerState.PAUSED){
                    player.becomeInvincible(-1);
                }

                    System.out.println("Player transforming into " + player.transformingInto());
                
                if (player.getCurrentState() == PlayerState.TRANSFORMING){
                                        
                    //Changes transforms player to different instance          
                    
                    //I was too stubborn to make inheritence work, no real need for
                    //this anyway, gotta learn to walk away.

                   // Player newPlayer = playerMap.get(player.transformingInto());
                   // player.setImage(newPlayer.getImage());
                    



                }
            }
           
            
            else{
                takingInput = true;

            }
          
    }    
    
    public void act(){
        

          //local player meaning current player;
            Player player = (Player)subject;

          //has to be before as this may change takingInput's value.
          if (Greenfoot.isKeyDown("escape")){
                
                if (timeLeftForUnpause <= 0){
                
                    if (player.getCurrentState() == PlayerState.PAUSED){
     
                    
                        player.becomeInvincible(0);
                    
                        timeLeftForUnpause = slightPauseDelay;
                        player.changeState(State.DEFAULT,false);
                        return;
                   }
                    else{
                    
                        player.changeState(PlayerState.PAUSED,false);
                    
                        timeLeftForUnpause = slightPauseDelay;
                    
                        return;
                   }
                }
                else{
                        
                    timeLeftForUnpause -= 0.1f;
                }
                
               
               
          }
          
            
          if (takingInput){  
              checkActions(player);            
          }

        if (timeTillAbsorb > 0){
                timeTillAbsorb -= 0.1f;
        }
    }
    
    
   
    private void checkActions(Player player){

        if (player.getCurrentState() != null && !player.getCurrentState().equals(State.JUMPING) && !player.getCurrentState().equals(State.FALLING)){
                    
              if (Greenfoot.isKeyDown("a")){
                    
                 
                   player.move(-1);


                }
                else if (Greenfoot.isKeyDown("d")){
                    
                    player.move(1);

                }
                else if (!player.isInvincible()){
                    
                    player.changeState(State.DEFAULT,false);
                }
                //Picking up items 
                if (Greenfoot.isKeyDown("q")){
                       
                    player.findItem();
                    
                }
                else if (player.getCurrentState() != State.DEFAULT){
                    
                        //If it was absorbing, then start ticking the cooldown
                        if (player.getCurrentState() == PlayerState.ABSORBING){
                            timeTillAbsorb = absorbCD;      
                        }
                  }
                  
                 if (Greenfoot.isKeyDown("w")){
                        player.jump();  
                        
                 }     
                   else if (Greenfoot.isKeyDown("f")){

                     RangedAttack attack = (RangedAttack)playerAttackPools.getReusable(player.toString());
                     
                     //If for some reason pool empty rn, then use original, prob should clone original instead.
                     if (attack == null){              
                         attack = playerAttacks.get(player.toString());
                     }
                                         
                     player.attack(attack);
                 }
                 //Transformations.
                else if (Greenfoot.isKeyDown("e")){            
                   
                        if (player.getCurrentState() != PlayerState.ABSORBING && player.getCurrentState() != PlayerState.TRANSFORMING && timeTillAbsorb <= 0){
             
                            System.out.println("Absorbing");
                            player.absorb();    
                        }
                   
                 } 
              
                else if (Greenfoot.isKeyDown("r")){
                        player.revert();

                }        
            }
            
            if (player.getCurrentState() == PlayerState.ABSORBING && !Greenfoot.isKeyDown("e")){
        
        
                     player.stopAbsorbing();

            }

        
   
    
  
   }
   
  


    
}
