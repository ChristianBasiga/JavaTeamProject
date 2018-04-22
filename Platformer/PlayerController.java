import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player controller maniplates the data in the player and handles input to the player.
 * I could change this so that player is really just a nested class to PlayerController
 * and access only allowed to PlayerController to change Player data
 * that will enforce this design.
 */
public class PlayerController extends Observer
{

    boolean takingInput = false;
    float absorbCD = 10.0f;
    float timeTillAbsorb = 0;
    Player player;
    
    //Player attacks
    StraightShot defaultAtt;
    WaveShot waterAtt;
    LightningShot lightningAtt;
    
    //Pool for player attacks could be here.
    
    PoolManager playerAttackPools;

    float slightPauseDelay = 1.0f;
    float timeLeftForUnpause = 0;
    
    public PlayerController(Player subject){
   
        playerAttackPools = new PoolManager();
        this.observe(subject);
        priority = 1;
        threadName = "pc";
       
        initPlayerAttacks();
    }
    
    private void initPlayerAttacks(){
        
        
        
        //It might better if just have AttackManager that pools all the attacks
        //and will basically be exact same as ItemManager...Then should I make amnager class? or just put it in ItemManager, that
        
        //Could be something else, whatever.
        defaultAtt = new StraightShot();
        waterAtt = new WaveShot();
        lightningAtt = new LightningShot();
        
        
        
        playerAttackPools.addPool("defaultattack",defaultAtt,20);
        playerAttackPools.addPool("waterattack",waterAtt,10);
        playerAttackPools.addPool("lightningattack",lightningAtt,10);
        //Set up other attacks.
        
    }
   
    public void observe(Subject subject){
        
        super.observe(subject);
        player = (Player)subject;
        takingInput = true;
    }

    public void run() 
    {

        //Reaction code to changes in state to subject go here

            if (player.getCurrentState() == State.DAMAGED || player.getCurrentState() == State.DEAD ||
                player.getCurrentState() == PlayerState.TRANSFORMING || player.getCurrentState() == PlayerState.PAUSED){
                
                //Stop taking input for movement, attacking, while transformation is happening, ie: animation still playing.
                takingInput = false;
               
              
      
                //Indefinitely invincible if paused
                if (player.getCurrentState() == PlayerState.PAUSED){
                    player.becomeInvincible(-1);
                }
                //also shouldn't be taking damage anymore as player so become invincible
                
                if (subject.getCurrentState() == PlayerState.TRANSFORMING){
                                        
                    System.out.println("Transforming");

                    //Changes transforms player to different instance

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
        
        System.out.println("current state: " + player.getCurrentState());

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


                checkMovement();
                checkActions();
                
          }

        if (timeTillAbsorb > 0){
                timeTillAbsorb -= 0.1f;
        }
    }
    
    private void checkMovement(){
        
                    
        
               
    }
   
    private void checkActions(){
        
        if (!player.getCurrentState().equals(State.JUMPING) && !player.getCurrentState().equals(State.FALLING)){
                    
              if (Greenfoot.isKeyDown("a")){
                    
                 
                   player.move(-1);


                }
                else if (Greenfoot.isKeyDown("d")){
                    
                    player.move(1);

                }
                else{
                    
                  
                  //  player.setSpeed(0);
                  if (player.getCurrentState() != PlayerState.PAUSED && !player.isInvincible()){
                    player.changeState(State.DEFAULT,false);
                }
                   
                }
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
                        
                        checkAttack();
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
                    
                       // player.changeState(State.DEFAULT,false);

                   
                }
               
                    
               
                
       }
        
    }
    
    private void checkAttack(){
        
         // RangedAttack playerAttack = (RangedAttack)playerAttackPools.getReusable(player.getCurrentTransformation() + "attack");
          RangedAttack playerAttack = (RangedAttack)playerAttackPools.getReusable("lightningattack");
          if (playerAttack == null){
              //Log the error, but user need not know about this issue so still attack like normal
              playerAttack = playerAttackFactory(player.getCurrentTransformation() + "attack");
          }
         
          playerAttack.setTarget(Enemy.class);
          player.attack(playerAttack);  
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

    
}
