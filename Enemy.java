import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Baddies
{
    
    //How much damage this enemy does
    int damage;
    String type;
    
    private int spriteHeight = getImage().getHeight();
    private int spriteWidth = getImage().getWidth();
    private int lookForGroundDistance = (int)spriteHeight/2;
    private int lookForEdge = (int)spriteWidth/2;
    
    private int speed = 1;
    
    public void act() 
    {
        playerDetection();
        checkEdge();
        move();
        checkRightWall();
        checkLeftWall();
        
    }    
    
    
    /*public Enemy(String type){
        damage = 10;
        this.type = type;
    }*/
    
    public Enemy(){
        damage = 10;
    }
    
    public String getType(){
        return type;
    }
    
    public int getDamage(){
        return damage;
    }
    
    //Check if there is rightWall
    public boolean checkRightWall(){
        int spriteWidth = getImage().getWidth();
        int xDistance = (int)(spriteWidth/2);
        Actor rightWall = getOneObjectAtOffset(xDistance, 0, Platform.class);
        if(rightWall == null)
        {
            return false;
        }
        else
        {
            stopByRightWall(rightWall);
            return true;
        }
    }
    public void stopByRightWall(Actor rightWall){
      int width = rightWall.getImage().getWidth();
      int newX = rightWall.getX() - (width + getImage().getWidth())/2;
      setLocation(newX - 5, getY());
      speed *= -1;
    }
    
    public boolean checkLeftWall()
    {
        int spriteWidth = getImage().getWidth();
        int xDistance = (int)(spriteWidth/-2);
        Actor leftWall = getOneObjectAtOffset(xDistance, 0, Platform.class);
        if(leftWall == null)
        {
            return false;
        }
        else
        {
            stopByLeftWall(leftWall);
            return true;
        }
    }

    public void stopByLeftWall(Actor leftWall)
    {
        int wallWidth = leftWall.getImage().getWidth();
        int newX = leftWall.getX() + (wallWidth + getImage().getWidth())/2;
        setLocation(newX + 5, getY());
        speed *= -1;

    }
    
    // Check for the edge of the world and, if so, reverse direction
    public void checkEdge()
    {
    if(getX() < 10 || getX() > getWorld().getWidth() - 10)
        {
            speed *= -1;
        }
    } 
    
    public void playerDetection()
    {
        if (canSee(Player.class) )
        {
            get(Player.class);
            //Greenfoot.playSound("game-over.wav");
            Greenfoot.stop();
        }
    }
    
    public void move(){
        Actor ground = getOneObjectAtOffset(lookForEdge, lookForGroundDistance, Platform.class);
        //if(ground == null)
        //{
          //  speed *= -1; // Reverses direction
          //  lookForEdge *= -1; // Looks for a negative number
        //}
        //else
        //{
            move(speed);
        //}
    }
}