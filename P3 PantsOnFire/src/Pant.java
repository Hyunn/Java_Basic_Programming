///////////////////////////////////////////////////////////////////////////////
//
// Title:            Pants on Fire
// Files:            Fire.java, Fireball.java, Hero.java, Level.java,
//						Pant.java, Water.java
// Semester:         Fall 2016
//
// Author:           Hyunho Choi
// Email:            hchoi225@wisc.edu
// CS Login:         hyunho
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is about pants. Pants turn into fire when collided with fireball.
 * Hero should protect pants from turning into fire because the game ends when
 * there are no more pants. Heros can protect pants by shooting water to
 * fireballs to destroy them.
 * 
 * @author Hyunho Choi
 *
 */
public class Pant 
{
	//Declare objects
	private Graphic graphic;
	private Random randGen;
	private boolean isAlive;

	/**
	 * This constructor creates a new pant at an allocated position. The random
	 * number generator helps create a new fire when pant collides a fireball.
	 * 
	 * @param x
	 *            horizontal position of Pant
	 * @param y
	 *            vertical position of Pant
	 * @param randGen
	 *            a random number generator to pass fire
	 */
	public Pant(float x, float y, Random randGen) 
	{
		//Draw new PANT
		graphic = new Graphic("PANT");
		//Set initial position
		graphic.setPosition( x , y );
		//Boolean to check if pant is alive or not
		isAlive = true;
		//Initialize randGen
		this.randGen = randGen;
	}

	/**
	 * This method is deals with collisions between pants and fireballs. When 
	 * collided, pants turn into fire and the graphic of pant as well as the 
	 * graphic of the collided fireball does not draw anymore.
	 * 
	 * @param fireballs
	 *            the arrayList of fireballs that are checked if they collided
	 *            with pants or not
	 * 
	 * @return fire if collision occurred
	 */
	public Fire handleFireballCollisions(ArrayList<Fireball> fireballs)
	{
		//Check whether or not pants collided with fireball
		for (int i = 0; i < fireballs.size(); i++)
		{
			//If collision occurred, destroy fireball and return fire
			if (graphic.isCollidingWith(fireballs.get(i).getGraphic()))
			{
				//Pant is dead
				isAlive = false;
				//Destroy fireball
				fireballs.get(i).destroy();
				//Create new fire
				Fire fire = new Fire(graphic.getX(), graphic.getY(), randGen);
				//Return new fire
				return fire;
			}
		}
		return null;
	}

	/**
	 * This method is used in the Level class and deals with deciding whether 
	 * or not to remove pant from the arrayList.
	 * 
	 * @return true collision occurs, false if no collision
	 */
	public boolean shouldRemove() 
	{
		//Return true if pant is dead, otherwise return false
		if(!isAlive)
			return true;
		return false;
	}

	/**
	 * This method draws the graphic of Pant while time is updated every 
	 * millisecond.
	 * 
	 * @param time
	 *           elapsed time since last update
	 */
	public void update(int time) 
	{
		if (isAlive)
			graphic.draw();
	}

	/**
	 * This method gives access to Graphic from other classes.
	 * 
	 * @return graphic of hero
	 */
	public Graphic getGraphic()
	{
		return graphic;
	}
}
