import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager extends Observer
{  	
	public GameManager() {
		threadName = "gm";
	}
	
	public void run() {
		
		if (subject != null) {
			
			System.out.println("GameManager checking state of player");;
			System.out.println("GameManager done");
		}
		
	}
	
}
