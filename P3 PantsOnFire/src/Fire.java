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

import java.util.Random;

/**
 * The Fire class creates new positions of fire, and also makes fireballs. It 
 * also handles water collisions to destroy fire when heat is lower than 1.
 * 
 * @author Hyunho Choi
 */
public class Fire 
{
	//Declare objects
	private Graphic graphic;
	private Random randGen;
	private int fireballCountdown;
	private int heat;

	/**
	 * The Fire constructor is a new fire object, with parameters of x, y, and 
	 * randGen. The constructor initializes the location and heat of fire, and 
	 * also initializes the randGen to determine how often fireballs are 
	 * created.
	 * 
	 * @param x
	 *            horizontal position of Fire
	 * @param y
	 *            vertical position of Fire
	 * @param randGen
	 *            random number generator to shoot new fireballs in a random 
	 *            length of time
	 */
	public Fire(float x, float y, Random randGen) 
	{
		//Initialize randGen
		this.randGen = randGen;
		//Draw new FIRE
		graphic = new Graphic("FIRE");
		//Initialize position of fire
		graphic.setPosition( x , y );
		//Number of milliseconds before the next fireball is shot
		fireballCountdown = randGen.nextInt(3001) + 3000;
		//Initialize heat to 40
		heat = 40;
	}

	/**
	 * This method deals with collisions between fire and water. It keeps 
	 * looping through the array of wate to check if there are any 
	 * collisions, and subtracts 1 from heat every time there is a 
	 * collision. The water graphic is destroyed when it collides with fire. 
	 * When heat becomes 0, the fire graphic is destroyed.
	 * 
	 * @param water
	 *            an array of water that loops to check for collisions 
	 *            that occurred
	 * 
	 */
	public void handleWaterCollisions(Water[] water)
	{
		//Checks whether or not there was a collision with water
		for (int i = 0; i < water.length; i++)
		{
			if(water[i] != null)
			{
				if(graphic != null)
				{
					//When collided, heat is deducted by 1, and water is 
					//destroyed
					if(graphic.isCollidingWith(water[i].getGraphic()))
					{
						heat--;
						water[i] = null;
						//Fire is destroyed when heat is 0.
						if (heat == 0)
							graphic = null;
					}
				}
			}
		}
	}

	/**
	 * This method is in Level class to indicate whether or not fire should be 
	 * removed. Return true when heat is less than 1, otherwise return false.
	 * 
	 * @return true if heat is less than 1, otherwise return false
	 */
	public boolean shouldRemove() 
	{
		if(heat < 1)
			return true;
		return false;
	}

	/**
	 * This method draws fireballs from fire while it has heat. It shoots 
	 * fireballs from a fire in a randomly assigned interval of time.
	 * 
	 * @param time
	 *           elapsed time in milliseconds from last update
	 * 
	 * @return fireball if fire is alive, otherwise null
	 */
	public Fireball update(int time) 
	{
		//When fire exists
		if (graphic != null)
		{
			graphic.draw();
			//Countdown time
			fireballCountdown -= time;
			//If time runs out
			if (fireballCountdown <= 0)
			{
				Fireball fireball;
				//Shoot new fireball in a random direction
				fireball = new Fireball(graphic.getX(), graphic.getY(), 
						randGen.nextFloat()*(float)Math.PI*2);
				//Start new countdown with a random time
				fireballCountdown = randGen.nextInt(3001) + 3000;
				return fireball;
			}
		}
		return null;
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
