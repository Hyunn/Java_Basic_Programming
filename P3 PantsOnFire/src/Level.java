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
import java.util.Scanner;

/**
 * The Level class is responsible for managing all of the objects in your game.
 * The GameEngine creates a new Level object for each level, and then calls that
 * Level object's update() method repeatedly until it returns either "ADVANCE"
 * (to go to the next level), or "QUIT" (to end the entire game).
 * <br/><br/>
 * This class should contain and use at least the following private fields:
 * <tt><ul>
 * <li>private Random randGen;</li>
 * <li>private Hero hero;</li>
 * <li>private Water[] water;</li>
 * <li>private ArrayList&lt;Pant&gt; pants;</li>
 * <li>private ArrayList&lt;Fireball&gt; fireballs;</li>
 * <li>private ArrayList&lt;Fire&gt; fires;</li>
 * </ul></tt>
 */
public class Level
{
	//Declare objects
	Hero hero;
	Water[] water;
	Pant pant;
	ArrayList<Pant> pants = new ArrayList<Pant>();
	Fireball fireball;
	ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
	Fire fire;
	ArrayList<Fire> fires = new ArrayList<Fire>();
	Random randGen;

	/**
	 * This constructor initializes a new Level object, so that the GameEngine
	 * can begin calling its update() method to advance the game's play.  In
	 * the process of this initialization, all of the objects in the current
	 * level should be instantiated and initialized to their beginning states.
	 * @param randGen is the only Random number generator that should be used
	 * throughout this level, by the Level itself and all of the Objects within.
	 * @param level is a string that either contains the word "RANDOM", or the 
	 * contents of a level file that should be loaded and played. 
	 */
	public Level(Random randGen, String level)
	{ 
		//Declares 8 arrays of water
		water = new Water [8];
		this.randGen = randGen;

		//Checks if level has RANDOM or not
		if (level.contains("RANDOM"))
			//Creates a random game
			createRandomLevel();
		else
			//Runs custom level file
			loadLevel(level);

	}

	/**
	 * The GameEngine calls this method repeatedly to update all of the objects
	 * within your game, and to enforce all of the rules of your game.
	 * @param time is the time in milliseconds that have elapsed since the last
	 * time this method was called.  This can be used to control the speed that
	 * objects are moving within your game.
	 * @return When this method returns "QUIT" the game will end after a short
	 * 3 second pause and a message indicating that the player has lost.  When
	 * this method returns "ADVANCE", a short pause and win message will be 
	 * followed by the creation of a new level which replaces this one.  When
	 * this method returns anything else (including "CONTINUE"), the GameEngine
	 * will simply continue to call this update() method as usual. 
	 */
	public String update(int time) 
	{	
		//Updates hero
		hero.update(time, water);

		//Loop to update water if water is not null
		for (int i = 0; i <water.length; i++)
		{
			if(water[i] != null) water[i] = water[i].update(time);
		}

		//Loop to update pants according to the number of pants
		for (int i = 0; i <pants.size(); i++)
			pants.get(i).update(time);

		//Loop to hide pants from screen if it collides with fireball
		for (int i = 0; i <pants.size(); i++)
		{
			Fire firess = pants.get(i).handleFireballCollisions(fireballs);
			if (firess != null)
			{
				fires.add(firess);
				pants.remove(i);
			}
		}

		//Updates every fire element
		for (int i = 0; i <fires.size(); i++)
		{
			if (fires.get(i) != null)
				fires.get(i).handleWaterCollisions(this.water);
			Fireball fireballss = fires.get(i).update(time);
			//Adds fireball if fireball is not null
			if (fireballss != null)
				fireballs.add(fireballss);
		}

		//Loop to hide fireball from screen if it collides with water
		for (int i = 0; i <fireballs.size(); i++)
		{
			if (fireballs.get(i) != null)
				fireballs.get(i).handleWaterCollisions(this.water);
			fireballs.get(i).update(time);
		}

		//Loop to hide fire from screen if it collides with water
		for (int i = 0; i <fires.size(); i++)
		{
			if (fires.get(i) != null)
				fires.get(i).handleWaterCollisions(this.water);
			fires.get(i).update(time);
		}

		//Loop to remove hidden fireball from array
		for (int i = 0; i < fireballs.size(); i++)
		{
			if (fireballs.get(i).shouldRemove())
			{
				fireballs.remove(i);
				i--;
			}	
		}

		//Loop to remove hidden pants from array
		for (int i = 0; i < pants.size(); i++)
		{
			if (pants.get(i).shouldRemove())
			{
				pants.remove(i);
				i--;
			}	
		}

		//Loop to remove hidden fire from array
		for (int i = 0; i < fires.size(); i++)
		{
			if (fires.get(i).shouldRemove())
			{
				fires.remove(i);
				i--;
			}	
		}

		//Quit game if hero collides with fireball
		if (hero.handleFireballCollisions(fireballs))
			return "QUIT";

		//Loads next game if there is no more fire
		if (fires.size() <= 0)
			return "ADVANCE";

		//Quit game if there are no more pants
		if (pants.size() <=0)
			return "QUIT";

		return "CONTINUE"; 

	}	

	/**
	 * This method returns a string of text that will be displayed in the
	 * upper left hand corner of the game window.  Ultimately this text should 
	 * convey the number of unburned pants and fires remaining in the level.  
	 * However, this may also be useful for temporarily displaying messages that 
	 * help you to debug your game.
	 * @return a string of text to be displayed in the upper-left hand corner
	 * of the screen by the GameEngine.
	 */
	public String getHUDMessage() 
	{
		return "Pants Left: " + pants.size() + "\nFires Left: " + fires.size(); 
	}

	/**
	 * This method creates a random level consisting of a single Hero centered
	 * in the middle of the screen, along with 6 randomly positioned Fires,
	 * and 20 randomly positioned Pants.
	 */
	public void createRandomLevel() 
	{ 
		//Create a new hero in the middle of the screen
		hero = new Hero (GameEngine.getWidth()/2, GameEngine.getHeight()/2, 
				randGen.nextInt(3) + 1);

		//Create 20 initial pants in random spots
		for (int i= 0; i <20; i++)
		{
			pant = new Pant (randGen.nextFloat()*GameEngine.getWidth(), 
					randGen.nextFloat()*GameEngine.getHeight(), randGen);
			pants.add(pant);
		}

		//Create 6 initial fires in random spots
		for (int i=0; i<6; i++)
		{
			fire = new Fire (randGen.nextFloat()*GameEngine.getWidth(), 
					randGen.nextFloat()*GameEngine.getHeight(), randGen);
			fires.add(fire);
		}

	}

	/**
	 * This method initializes the current game according to the Object location
	 * descriptions within the level parameter.
	 * @param level is a string containing the contents of a custom level file 
	 * that is read in by the GameEngine.  The contents of this file are then 
	 * passed to Level through its Constructor, and then passed from there to 
	 * here when a custom level is loaded.  You can see the text within these 
	 * level files by dragging them onto the code editing view in Eclipse, or 
	 * by printing out the contents of this level parameter.  Try looking 
	 * through a few of the provided level files to see how they are formatted.
	 * The first line is always the "ControlType: #" where # is either 1, 2, or
	 * 3.  Subsequent lines describe an object TYPE, along with an X and Y 
	 * position, formatted as: "TYPE @ X, Y".  This method should instantiate 
	 * and initialize a new object of the correct type and at the correct 
	 * position for each such line in the level String.
	 */
	public void loadLevel(String level) 
	{ 
		//Scans level to in
		Scanner in = new Scanner(level);
		//Reads first line of scanner
		String controlTypeText = in.nextLine();
		//Obtain the number of ControlType
		int controlType = Integer.parseInt
				(controlTypeText.split(":")[1].trim());

		//Loop to read a new line until there are no more new lines
		do
		{
			//Read a new line
			String lines = in.nextLine();
			//Split lines from scanner
			String[] elements = lines.split(":|@|,");

			//Stores x position from scanner
			float x = Float.parseFloat(elements[1].trim());
			//Stores y position from scanner
			float y = Float.parseFloat(elements[2].trim());

			//Create new fire if element contains FIRE
			if (elements[0].contains("FIRE"))
			{
				fire = new Fire (x, y, randGen);
				fires.add(fire);
			}

			//Create new pants if element contains PANT
			if (elements[0].contains("PANT"))
			{
				pant = new Pant (x, y, randGen);
				pants.add(pant);
			}

			//Create new hero if element contains HERO
			if (elements[0].contains("HERO"))
			{
				hero = new Hero (x, y, controlType);
			}
		} while(in.hasNextLine());

		in.close();
	}

	/**
	 * This method creates and runs a new GameEngine with its first Level.  Any
	 * command line arguments passed into this program are treated as a list of
	 * custom level filenames that should be played in a particular order.
	 * @param args is the sequence of custom level files to play through.
	 */
	public static void main(String[] args)
	{
		//Start game
		GameEngine.start(null,args);
	}
}
