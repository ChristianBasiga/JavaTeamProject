/**
 * This class observes the player's state and transformation and reacts accordingly.
 * Passes the right frames to be displayed
 *
 * @author(Melirose Liwag)
 */
import java.util.*;
public class AnimationManager extends Observer
{
    //Main Character Normal
    String[] idleFrames = {"idle001.png", "idle002.png", "idle003.png", "idle004.png" , "idle005.png"
    , "idle006.png", "idle007.png", "idle008.png", "idle009.png", "idle010.png", "idle011.png", "idle012.png"
    , "idle013.png", "idle014.png"};

    String[] sideFramesR = {"sideR001.png", "sideR002.png", "sideR003.png", "sideR003.png",
    "sideR004.png","sideR005.png","sideR006.png","sideR007.png","sideR008.png","sideR009.png","sideR010.png"};
      String[] sideFramesL = {"sideL001.png", "sideL002.png", "sideL003.png", "sideL003.png",
    "sideL004.png","sideL005.png","sideL006.png","sideL007.png","sideL008.png","sideL009.png","sideL010.png"};
    
    String[] jumpFrames = {"jump001.png", "jump001.png", "jump002.png", "jump003.png", "jump004.png", "jump005.png"
    , "jump006.png", "jump006.png"};
    
    String[] deathFrames = {"death001.png", "death002.png", "death003.png", "death004.png", "death005.png"};
    String[] absorbFrames = {"absorb001.png", "absorb002.png", "absorb003.png", "absorb004.png"};
    String[] hitFrames = {"hit001.png", "hit002.png", "hit003.png"};
    
    //Main Character Fire
    String[] idleFramesF = {"idleF001.png", "idleF002.png", "idleF003.png", "idleF004.png" , "idleF005.png"
    , "idleF006.png", "idleF007.png", "idleF008.png", "idleF009.png", "idleF010.png", "idleF011.png", "idleF012.png"
    , "idleF013.png", "idleF014.png"};

    String[] sideFramesRF = {"sideRF001.png", "sideRF002.png", "sideRF003.png", "sideRF003.png",
    "sideRF004.png","sideRF005.png","sideRF006.png","sideRF007.png","sideRF008.png","sideRF009.png","sideRF010.png"};
    String[] sideFramesLF = {"sideLF001.png", "sideLF002.png", "sideLF003.png", "sideLF003.png",
    "sideLF004.png","sideLF005.png","sideLF006.png","sideLF007.png","sideLF008.png","sideLF009.png","sideLF010.png"};
    
    String[] jumpFramesF = {"jumpF001.png", "jumpF002.png", "jumpF003.png", "jumpF004.png", "jumpF005.png"
    , "jumpF006.png"};
    
    String[] deathFramesF = {"deathF001.png", "deathF002.png", "deathF003.png", "deathF004.png", "deathF005.png"};
    String[] absorbFramesF = {"absorbF001.png", "absorbF002.png", "absorbF003.png", "absorbF004.png"};
    String[] hitFramesF = {"hitF001.png", "hitF002.png", "hitF003.png"};
    
    //Main Character Lighting
    String[] idleFramesL = {"idleL001.png", "idleL002.png", "idleL003.png", "idleL004.png" , "idleL005.png"
    , "idleL006.png", "idleL007.png", "idleL008.png", "idleL009.png", "idleL010.png", "idleL011.png", "idleL012.png"
    , "idleL013.png", "idleL014.png"};

    String[] sideFramesRL = {"sideRL001.png", "sideRL002.png", "sideRL003.png", "sideRL003.png",
    "sideRL004.png","sideRL005.png","sideRL006.png","sideRL007.png","sideRL008.png","sideRL009.png","sideRL010.png"};
    String[] sideFramesLL = {"sideLL001.png", "sideLL002.png", "sideLL003.png", "sideLL003.png",
    "sideLL004.png","sideLL005.png","sideLL006.png","sideLL007.png","sideLL008.png","sideLL009.png","sideLL010.png"};
    
    String[] jumpFramesL = {"jumpL001.png", "jumpL002.png", "jumpL003.png", "jumpL004.png", "jumpL005.png"
    , "jumpL006.png"};
    
    String[] deathFramesL = {"deathL001.png", "deathL002.png", "deathL003.png", "deathL004.png", "deathL005.png"};
    String[] absorbFramesL = {"absorbL001.png", "absorbL002.png", "absorbL003.png", "absorbL004.png"};
    String[] hitFramesL = {"hitL001.png", "hitL002.png", "hitL003.png"};
    
    //Main Character Earth
    String[] idleFramesE = {"idleE001.png", "idleE002.png", "idleE003.png", "idleE004.png" , "idleE005.png"
    , "idleE006.png", "idleE007.png", "idleE008.png", "idleE009.png", "idleE010.png", "idleE011.png", "idleE012.png"
    , "idleE013.png", "idleE014.png"};

    String[] sideFramesRE = {"sideRE001.png", "sideRE002.png", "sideRE003.png", "sideRE003.png",
    "sideRE004.png","sideRE005.png","sideRE006.png","sideRE007.png","sideRE008.png","sideRE009.png","sideRE010.png"};
    String[] sideFramesLE = {"sideLE001.png", "sideLE002.png", "sideLE003.png", "sideLE003.png",
    "sideLE004.png","sideLE005.png","sideLE006.png","sideLE007.png","sideLE008.png","sideLE009.png","sideLE010.png"};
    
    String[] jumpFramesE = {"jumpE001.png", "jumpE002.png", "jumpE003.png", "jumpE004.png", "jumpE005.png"
    , "jumpE006.png"};
    
    String[] deathFramesE = {"deathE001.png", "deathE002.png", "deathE003.png", "deathE004.png", "deathE005.png"};
    String[] absorbFramesE = {"absorbE001.png", "absorbE002.png", "absorbE003.png", "absorbE004.png"};
    String[] hitFramesE = {"hitE001.png", "hitE002.png", "hitE003.png"};
    
    //Enemy Frames
    String enemyF = "enemy001.png";
    String enemyL = "enemy002.png";
    String enemyE = "enemy003.png";
    String[] enemyProj = {"enemy004.png", "enemy005.png", "enemy006.png", "enemy007.png", "enemy008.png"};    

    //HashMaps For Transformations
    HashMap<String, String[]> idleHashTrans;
    HashMap<String, String[]> sideHashRTrans;
    HashMap<String, String[]> sideHashLTrans;
    HashMap<String, String[]> jumpHashTrans;
    HashMap<String, String[]> deathHashTrans;
    HashMap<String, String[]> absorbHashTrans;
    HashMap<String, String[]> hitHashTrans;
    

    Map<String, HashMap<String, String[]>> animMap;
    Animation playerAnim;
   public AnimationManager(){
       
        initIndividualStateMaps();
        initAnimMap();
        
        playerAnim = new Animation((Player)subject);
        playerAnim.getImage().setTransparency(1);
  
        
   }
   
   private void initIndividualStateMaps(){
   
       idleHashTrans = new HashMap<String, String[]>();
       idleHashTrans.put("Player", idleFrames);
       idleHashTrans.put("firePlayer", idleFramesF);
       idleHashTrans.put("lightningPlayer", idleFramesL);
       idleHashTrans.put("earthPlayer", idleFramesE);
        
       
       sideHashRTrans = new HashMap<String, String[]>();
       sideHashRTrans.put("Player", sideFramesR);
       sideHashRTrans.put("firePlayer", sideFramesRF);
       sideHashRTrans.put("lightningPlayer", sideFramesRL);
       sideHashRTrans.put("earthPlayer", sideFramesRE);
       
       sideHashLTrans = new HashMap<String, String[]>();
       sideHashLTrans.put("Player", sideFramesL);
       sideHashLTrans.put("firePlayer", sideFramesLF);
       sideHashLTrans.put("lightningPlayer", sideFramesLL);
       sideHashLTrans.put("earthPlayer", sideFramesLE);
       
       
        jumpHashTrans = new HashMap<String, String[]>();
        jumpHashTrans.put("Player", jumpFrames);
        jumpHashTrans.put("firePlayer", jumpFramesF);
        jumpHashTrans.put("lightningPlayer", jumpFramesL);
        jumpHashTrans.put("earthPlayer", jumpFramesE);
       
        deathHashTrans = new HashMap<String, String[]>();
        deathHashTrans.put("Player", deathFrames);
        deathHashTrans.put("firePlayer", deathFramesF);
        deathHashTrans.put("lightningPlayer", deathFramesL);
        deathHashTrans.put("earthPlayer", deathFramesE);
       
        absorbHashTrans = new HashMap<String, String[]>();
        absorbHashTrans.put("Player", absorbFrames);
        absorbHashTrans.put("firePlayer", absorbFramesF);
        absorbHashTrans.put("lightningPlayer", absorbFramesL);
        absorbHashTrans.put("earthPlayer", absorbFramesE);
       
       
        hitHashTrans = new HashMap<String, String[]>();
        hitHashTrans.put("Player", hitFrames);
        hitHashTrans.put("firePlayer", hitFramesF);
        hitHashTrans.put("lightningPlayer", hitFramesL);
        hitHashTrans.put("earthPlayer", hitFramesE);   
   }
   
   private void initAnimMap(){
       
        //HashMaps for States
        animMap = new HashMap<String, HashMap<String, String[]>>();
        animMap.put("IDLE", idleHashTrans);
        animMap.put("MOVINGRIGHT", sideHashRTrans);
        animMap.put("MOVINGLEFT", sideHashLTrans);
        animMap.put("JUMPING", jumpHashTrans);
        animMap.put("DEAD", deathHashTrans);
        animMap.put("ABSORBING", absorbHashTrans);
        animMap.put("DAMAGED", hitHashTrans);
   }
    
    @Override
    public void react(){
       
                             
        Player player = (Player) subject;
        State state = player.getCurrentState();
        
        if (state == PlayerState.TRANSFORMING){
           
            Level level = (Level)getWorld();
            subject = level.pc.getCurrentPlayer();
            playerAnim.setAnimating(subject);
        }
        else{
        
            //Because not all states have animation tied to them.
            HashMap<String, String[]> frameColl = animMap.get(state.toString());
            if (frameColl == null){ 
                return;
            }
            
            String[] frames = animMap.get(frameColl).get(player.toString());
            playerAnim.setFrames(frames);
        }
    }
}
