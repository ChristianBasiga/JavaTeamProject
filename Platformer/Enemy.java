import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Subject
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //How much damage this enemy does
    int damage;
    String type;
    
    public Enemy(String type){
        damage = 10;
        this.type = type;
        
        setImage(new GreenfootImage(type+"Enemy.png"));
    }
    
    
    public String getType(){
        return type;
    }
    
    public int getDamage(){
        return damage;
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
