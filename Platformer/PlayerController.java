import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    
    Player player;
    FirePlayer fp;
    LightningPlayer lp;
    
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
        priority = 1;

       
        initPlayers();
        initPlayerAttacks();
    }
    
    public void initPlayers(){
        
        player = (Player)subject;
        fp = new FirePlayer();
        lp = new LightningPlayer();
        
    }
    
    //This will have highest priority to make sure the rest of them will be observing the correct player.
    public Player getCurrentPlayer(){
        
        return (Player)subject;
        
    }
    
    private void initPlayerAttacks(){
        
        
       
        defaultAtt = new StraightShot();
        fireAtt = new WaveShot();
        lightningAtt = new LightningShot();
        
        
        
        playerAttackPools.addPool("defaultattack",defaultAtt,20);
        playerAttackPools.addPool("fireattack",fireAtt,10);
        playerAttackPools.addPool("lightningattack",lightningAtt,10);
        //Set up other attacks.
        
    }
   
    public void observe(Subject subject){
        
        super.observe(subject);
        player = (Player)subject;
        takingInput = true;
    }

    @Override
    public void react() 
    {

        //Reaction code to changes in state to subject go here
        
            //local player meaning current player;
            Player player = (Player)subject;

            if (player.getCurrentState() == State.DAMAGED || player.getCurrentState() == State.DEAD ||
                player.getCurrentState() == PlayerState.TRANSFORMING || player.getCurrentState() == PlayerState.PAUSED){
                
                //Stop taking input for movement, attacking, while transformation is happening, etc.
                takingInput = false;
                
                //Indefinitely invincible if paused
                if (player.getCurrentState() == PlayerState.PAUSED){
                    player.becomeInvincible(-1);
                }


                
                if (subject.getCurrentState() == PlayerState.TRANSFORMING){
                                        
                    //Changes transforms player to different instance          
                    
                    Player newPlayer = playerFactory(player.transformingInto());
                    
                    //Copies all all data.
                    newPlayer.setHealth(player.getHealth());
                   
                    //Each observer should handle reacting to the subject themselves, they'll observer new player later.
                    //they will be called after this returns, instead of (2N) its (N) because I will visit rest of observers later.
                    //player.transferObservers(newPlayer);   
                    getWorld().addObject(newPlayer,player.getX(), player.getY());                 
                    getWorld().removeObject(player);                    
                    
                    subject = newPlayer;

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
        
        if (!player.getCurrentState().equals(State.JUMPING) && !player.getCurrentState().equals(State.FALLING)){
                    
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
                else if (player.getCurrentState() != PlayerState.DEFAULT){
                    
                        //If it was absorbing, then start ticking the cooldown
                        if (player.getCurrentState() == PlayerState.ABSORBING){
                            timeTillAbsorb = absorbCD;      
                        }
                  }
               
                   
            }
            
            else{
                 
                if (Greenfoot.isKeyDown("w")){
                   
                        player.jump();
                       
                 }              
                 //Transformations.
                 if (Greenfoot.isKeyDown("e")){            
                   
                        if (player.getCurrentState() != PlayerState.ABSORBING && player.getCurrentState() != PlayerState.TRANSFORMING && timeTillAbsorb <= 0){
             
                            player.absorb();    
                        }
                   
                 }                   
                 else if (Greenfoot.isKeyDown("r")){
                        player.revert();

                 }              
                 else if (Greenfoot.isKeyDown("f")){
                        
                        attack();
                 }
     
                }
                        
   
    }
     
    
    private RangedAttack playerAttackFactory(String attackName){
        
        RangedAttack product = null;
        
        try{
            if (attackName == "defaultattack"){
                product =  (RangedAttack)defaultAtt.clone();
            }
            else if (attackName == "waterattack"){
               product =  (RangedAttack)waterAtt.clone();
            }
        }
        catch (Exception e){
            //Log it.
        }
        
        return product;
    }
    public Player playerFactory(String type){
        
        //Todo: Check for type and return corresponding Player
        if (type == "fire"){
            return fp;
        }
        return new Player();
    }

    
}
