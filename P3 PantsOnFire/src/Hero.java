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

/**
 * The Hero Class gives three options known as ControlTypes to play the game.
 * The class returns boolean results for fire collision, 
 * as well as update water arrays.
 *
 * @author Hyunho Choi
 */
public class Hero {

	//Declare objects
	private Graphic graphic;
	private float   speed;
	private int     controlType;

	/**
	 * The Hero constructor has parameters of x, y, and controlType.
	 * The constructor initializes the parameters as private fields,
	 * setting the initial values of x and y as hero's position, speed,
	 * and the controlType.
	 * 
	 * @param x
	 *            represents hero's horizontal position
	 * @param y
	 *            represents hero's vertical position
	 * @param controlType
	 *            represents the ControlType user chose to play
	 */
	public Hero(float x, float y, int controlType)
	{
		//Draw HERO graphic
		graphic = new Graphic("HERO");
		//Initialize hero speed
		speed = 0.12f;
		//Initialize controlType
		this.controlType = controlType;
		//Set hero position
		graphic.setPosition(x, y);
	}

	/**
	 * This method is called to update hero's movements and water. It also
	 * reads ControlType from user input and splashes water with mouse or
	 * space bar input.
	 * 
	 * @param time
	 *            flow of time in milliseconds
	 * @param water
	 *            enables water to splash out of hero
	 */
	public void update(int time, Water[] water)
	{
		//Draw graphic
		graphic.draw();

		//Read whether ControlType is 1, 2, or 3
		switch(controlType) 
		{
		//ControlType is 1
		case 1:
			//Move right when D is pressed
			if (GameEngine.isKeyHeld("D"))
			{
				graphic.setDirection(0);
				graphic.setX(graphic.getX() + speed * time);
				graphic.draw();
			}
			//Move up when W is pressed
			else if (GameEngine.isKeyHeld("W"))
			{
				graphic.setDirection((float)Math.PI*3/2);
				graphic.setY(graphic.getY() - speed * time);
				graphic.draw();
			}
			//Move left when A is pressed
			else if (GameEngine.isKeyHeld("A"))
			{
				graphic.setDirection((float)Math.PI);
				graphic.setX(graphic.getX() - speed * time);
				graphic.draw();
			}
			//Move down when S is pressed
			else if (GameEngine.isKeyHeld("S"))
			{
				graphic.setDirection((float)Math.PI/2);
				graphic.setY(graphic.getY() + speed * time);
				graphic.draw();
			}
			//Create water
			for (int i = 0; i < water.length; i++)
			{
				//Splash water if mouse or space is pressed
				if (GameEngine.isKeyPressed("SPACE") 
						|| (GameEngine.isKeyPressed("MOUSE")))
					if(water[i] == null)
					{
						water[i] = new Water(graphic.getX(), 
								graphic.getY(), graphic.getDirection());
						break;
					}
			}
			break;

			//ControlType is 2
		case 2:
			//Move right when D is pressed while facing where mouse points
			if (GameEngine.isKeyHeld("D"))
			{
				graphic.setDirection(GameEngine.getMouseX(), 
						GameEngine.getMouseY());
				graphic.setX(graphic.getX() + speed * time);
				graphic.draw();
			}
			//Move up when W is pressed while facing where mouse points
			else if (GameEngine.isKeyHeld("W"))
			{
				graphic.setDirection(GameEngine.getMouseX(), 
						GameEngine.getMouseY());
				graphic.setY(graphic.getY() - speed * time);
				graphic.draw();
			}
			//Move left when A is pressed while facing where mouse points
			else if (GameEngine.isKeyHeld("A"))
			{
				graphic.setDirection(GameEngine.getMouseX(), 
						GameEngine.getMouseY());
				graphic.setX(graphic.getX() - speed * time);
				graphic.draw();
			}
			//Move down when S is pressed while facing where mouse points
			else if (GameEngine.isKeyHeld("S"))
			{
				graphic.setDirection(GameEngine.getMouseX(), 
						GameEngine.getMouseY());
				graphic.setY(graphic.getY() + speed * time);
				graphic.draw();
			}			
			//Create water
			for (int i = 0; i < water.length; i++)
			{
				//Splash water if mouse or space is pressed
				if (GameEngine.isKeyPressed("SPACE") 
						|| (GameEngine.isKeyPressed("MOUSE")))
					if(water[i] == null)
					{
						water[i] = new Water(graphic.getX(), 
								graphic.getY(), graphic.getDirection());
						break;
					}
			}
			break;

			//ControlType is 3
		case 3:
			//Initialize the distance between cursor and hero
			double distanceFromCursor = 0;
			//Horizontal position of hero
			double mouseX = GameEngine.getMouseX();
			//Vertical position of hero
			double mouseY = GameEngine.getMouseY();
			//Calculate distance between cursor and hero
			distanceFromCursor = Math.sqrt(Math.pow(mouseX, 2) 
					+ Math.pow(mouseY, 2));

			//Move if distance between cursor and hero is greater than 20
			if (distanceFromCursor >= 20)
			{
				//Face where cursor is pointing
				graphic.setDirection(GameEngine.getMouseX(), 
						GameEngine.getMouseY());
				//Move horizontally towards cursor
				graphic.setX(graphic.getX() 
						+ graphic.getDirectionX()*speed * time);
				//Move vertically towards cursor
				graphic.setY(graphic.getY() 
						+ graphic.getDirectionY()*speed * time);
				//Draw graphic
				graphic.draw();
			}
			//Create water
			for (int i = 0; i < water.length; i++)
			{
				//Splash water if mouse or space is pressed
				if (GameEngine.isKeyPressed("SPACE") 
						|| (GameEngine.isKeyPressed("MOUSE")))
					if(water[i] == null)
					{
						water[i] = new Water(graphic.getX(), 
								graphic.getY(), graphic.getDirection());
						break;
					}
			}
			break;
		}
		graphic.draw();
	}

	/**
	 * This method is to recognize the collisions between fireballs and the
	 * Hero, returning true if they collided, and false if they did not.
	 * 
	 * @param fireballs
	 * 				needs to check if there is a collision with Hero
	 * 
	 * @return boolean true if fireball and Hero collides
	 */
	public boolean handleFireballCollisions(ArrayList<Fireball> fireballs)
	{
		//Loop to check every fireball if they collided with Hero
		for (int i = 0; i < fireballs.size(); i++)
		{
			//Return true if collided
			if (graphic.isCollidingWith(fireballs.get(i).getGraphic()))
				return true;
		}
		return false;
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
