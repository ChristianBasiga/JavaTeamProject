/**
 * Write a description of class PlayerFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PlayerFactory  
{
    
    public static Player produce(String type){
        
        //Todo: Check for type and return corresponding Player
        if (type == "fire"){
            return new FirePlayer();
        }
        return new Player();
    }
   
}
