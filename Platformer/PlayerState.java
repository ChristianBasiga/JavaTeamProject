/**
 * Write a description of class PlayerState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerState extends State 
{
   
   //Add more states as see fit.
   //Really only blended states need to be powers of 2.
   public static final PlayerState TRANSFORMING = new PlayerState(128,"Transforming");
   public static final PlayerState ABSORBING = new PlayerState(254,"Absorbing");
   public static final PlayerState PAUSED = new PlayerState(2048,"Paused");
   public static final PlayerState INVINCIBLE = new PlayerState(1024,"Invincible");


   
   public PlayerState(int id, String name){
       super(id,name);
       
    }
}
