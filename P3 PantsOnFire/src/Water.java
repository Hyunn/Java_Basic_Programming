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
 * This class splashes water from Hero to destroy fire and fireballs.
 * 
 * @author Hyunho Choi
 *
 */
public class Water
{	
	//Delare objects
	private Graphic graphic;
	private float speed;
	private float distanceTraveled;

	/**
	 * The constructor initializes the position of Water and direction it
	 * will splash.
	 * 
	 * @param x
	 *            horizontal position of Water
	 * @param y
	 *            vertical position of Water
	 * @param direction
	 *            direction that the water will splash
	 */
	public Water(float x, float y, float direction) 
	{
		//Draw new WATER graphic
		graphic = new Graphic("WATER");
		//Set initial water speed
		speed = 0.7f;
		//Set initial position
		graphic.setPosition(x, y);
		//Set initial direction
		graphic.setDirection(direction);
	}

	/**
	 * This method is called to update water. If the distance water traveled
	 * is greater than 200, return null.
	 * 
	 * @param time
	 *         flow of time in milliseconds
	 * 
	 * @return water if water traveled less than 200, if not, return null
	 */
	public Water update(int time) 
	{
		//Calculate distance water traveled
		distanceTraveled += speed*time;
		//Set position of water as time passes
		graphic.setPosition(graphic.getX() + graphic.getDirectionX() * 
				(speed*time),
				graphic.getY() + graphic.getDirectionY() * (speed*time));
		//Return water if water traveled less than 200
		if(distanceTraveled <= 200)
		{
			graphic.draw();
			return this;	
		}
		//Or return null
		else
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
