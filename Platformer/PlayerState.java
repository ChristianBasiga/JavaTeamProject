/**
 * Write a description of class PlayerState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerState extends State 
{
   
   //Add more states as see fit.
   public static final PlayerState TRANSFORMING = new PlayerState(16,"Transforming");
   
   public PlayerState(int id, String name){
       super(id,name);
       
    }
}
