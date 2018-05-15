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

/**
 * The Fireball class creates new fireball positions. It also removes 
 * fireballs that move out more than 100 from the screen and handles 
 * collisions between water and fireball.
 * 
 * @author Hyunho Choi
 */
public class Fireball 
{
	//Declare objects
	private Graphic graphic;
	private float speed;
	private boolean isAlive;

	/**
	 * The fireball constructor is a new fireball object that has parameters of
	 * x, y, and directionAngle. It initializes the location of the new 
	 * fireball, and checks whether or not a fireballl is dead by using the 
	 * boolean isAlive.
	 * 
	 * @param x
	 *            horizontal position of fireball
	 * @param y
	 *            vertical position of fireball
	 * @param dirctionAngle
	 *            direction of fireball
	 */
	public Fireball(float x, float y, float directionAngle) 
	{
		//Draw new FIREBALL
		graphic = new Graphic("FIREBALL");
		//Initialize speed of fireball
		speed = 0.2f;
		//Initialize position of fireball
		graphic.setPosition(x, y);
		//Initialize direction of fireball
		graphic.setDirection(directionAngle);
		//Initialize to alive
		isAlive = true;
	}

	/**
	 * This method destroy fireballs that move more than 100 out of the screen,
	 * and isAlive changes to false.
	 */
	public void destroy()
	{
		isAlive = false;
	}

	/**
	 * This method is in Level class. Return true when isAlive is false to 
	 * remove null values in the arrayList of fireballs.
	 * 
	 * @return true if isAlive is false, otherwise return false
	 */
	public boolean shouldRemove() 
	{
		//Return true if isAlive is false, otherwise false
		if(!isAlive)
			return true;
		return false;
	}

	/**
	 * This method deals with collisions between water and fire ball. It loops 
	 * through the water array to find any collisions and destroys the graphic
	 * of fireball and water if a collision occurred.
	 * 
	 * @param water
	 *           an array of water that is checked for collisions
	 * 
	 */
	public void handleWaterCollisions(Water[] water)
	{
		//Checks whether or not fireball collided with water
		for(int i = 0; i < water.length; i++)
		{
			if (water[i] != null)
			{
				//When collided, change isAlive to false and water to null
				if (graphic.isCollidingWith(water[i].getGraphic()))
				{
					this.isAlive = false;
					water[i] = null;
				}
			}
		}
	}

	/**
	 * This method draws fireballs and checks for fireballs that move out of 
	 * the screen. Moving out more than 100 from the screen changes the
	 * isAlive for fireball to false, and the fireball is destoryed.
	 *  
	 * @param time
	 *           elapsed time since last update
	 * 
	 */
	public void update(int time) 
	{ 
		//Checks if fireball moved out of the screen more than 100 pixels,
		//if so, fireball is dead
		if (graphic.getX() >= GameEngine.getWidth() + 100 
				|| graphic.getX() <= (float) -100 
				|| graphic.getY() >= GameEngine.getHeight() + 100 
				|| graphic.getY() <= -100)
		{
			isAlive = false;
		}

		//If fireball is alive, fireball keeps moving the same direction
		if (isAlive)
		{
			graphic.setPosition(graphic.getX() 
					+ graphic.getDirectionX()*speed*time, graphic.getY() 
					+ graphic.getDirectionY()*speed*time);
			graphic.draw();
		}
		else 
		{
			graphic.setPosition(graphic.getX(), graphic.getY());
		}

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
