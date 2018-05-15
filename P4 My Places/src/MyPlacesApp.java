///////////////////////////////////////////////////////////////////////////////
//
// Title:            Program4
// Files:            Place.java, PlaceList.java, MyPlacesApp.java
// Semester:         (cs302) Fall 2016
//
// Author:           Hyunho Choi
// Email:            hchoi225@wisc.edu
// CS Login:         hyunho
// Lecturer's Name:  Gary Dahl
// Lab Section:      333
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.util.Scanner;

/**
 *This class is the main method of the program and deals with user input and 
 *output. It catches errors and stores and reads files.
 *
 * @author Hyunho Choi
 */

public class MyPlacesApp 
{
	/**
	 * This method reads files with ".mp" extension and prints a list of the
	 * names of the files, in the project directory in eclipse.
	 * @param N/A
	 * @return N/A
	 */
	public static void fileNames()
	{
		//Declares new variables
		String DATA_PATH = ".";
		String FILE_EXTENSION = ".mp";
		//Creates a new file for data
		File folder = new File(DATA_PATH);
		//Loop to read the files in folder
		for( File file : folder.listFiles() )
		{
			//Read and print files only with ".mp"
			if (file.getName().endsWith(FILE_EXTENSION))
			{
				System.out.println("\t" + file.getName());
			}
		} 
	}

	/**
	 * This is the main method that contains menu choices of the program. The
	 * different menu choices enable users to choose an option to read or 
	 * write files, and also displays a message for invalid menu inputs. It
	 * calls Place.java and PlaceList.java to create or delete places.
	 *
	 * @param N/A
	 * @return N/A
	 */
	public static void main(String[] args) 
	{
		//New scanner to read user input
		Scanner scnr = new Scanner(System.in);
		//String to store user input
		String userEnter = "";
		//String to store the first letter of user input for menu choice
		String userInput = "";
		//String to store name of place
		String name = "";
		//String to store address of place
		String address = "";
		//String to store file
		String fileName = "";
		//Create new PlaceList store places
		PlaceList placeLists = new PlaceList();
		System.out.println("");

		//Loop to run program until user types 'Q'
		do
		{
			System.out.println("My Places 2016");
			System.out.println("--------------------------");

			//Used to catch exceptions for unrecognized choice
			try
			{
				//When place exists in placeLists, other menus are shown
				if (placeLists.hasPlaces())
				{
					//Loop to print all places in placeLists
					for(int i=0; i<placeLists.size(); i++)
					{
						System.out.println(i+1 + ") " 
								+ placeLists.get(i).getName());
					}
					System.out.println("--------------------------");   
					System.out.print("A)dd S)how D)elete R)ead W)rite Q)uit: ");
					//Scanner to read user input
					userEnter = scnr.nextLine();
					//Remove space from user input
					userInput = userEnter.trim();
					//Make user input case insensitive
					userInput = userInput.toUpperCase();
					//Display error message if there is more than one user
					//input or a number
					if (userEnter.trim().length() > 1 
							|| userInput.matches(".*\\d+.*"))
						System.out.println("Unrecognized choice: " + userEnter);
					//User chooses 'S'
					else if(userInput.equals("S"))
					{
						//Display error message if an appropriate number is not
						//entered
						try
						{
							System.out.print("Enter number of place to Show: ");
							//Scanner to read user choice of place
							int numPlace = scnr.nextInt();
							scnr.nextLine();
							System.out.println("" 
									+ placeLists.get(numPlace -1).getName());
							System.out.println("" 
									+ placeLists.get(numPlace -1).getAddress());
						}catch (Exception e)
						{
							System.out.println("Expected a number between 1 and " 
									+ placeLists.size() + ".");
						}
					}
					//User chooses 'D'
					else if(userInput.equals("D"))
					{
						//Display error message if an appropriate number is not
						//entered
						try
						{
							System.out.print("Enter number of place to Delete: ");
							//Scanner to delete user choice of place in placeLists
							int numDelete = scnr.nextInt();
							scnr.nextLine();
							System.out.println("Deleting: " 
									+ placeLists.get(numDelete - 1).getName());
							//Delete place
							placeLists.remove(numDelete - 1);
						}catch (Exception e)
						{
							System.out.println("Expected a number between 1 "
									+ "and " + placeLists.size() + ".");
						}
					}
					//User chooses 'W'
					else if(userInput.equals("W"))
					{
						System.out.println("My Places Files: ");
						//Calls and displays file names from folder
						fileNames();
						System.out.print("\nEnter filename: ");
						//Scanner to write new file names
						fileName = scnr.nextLine();
						System.out.println("Writing file: " + fileName);
						//Save new file name to folder
						placeLists.writeFile(fileName);

					}
				}
				//Display message if placeLists is empty
				else
				{
					System.out.println("No places in memory.");
					System.out.println("--------------------------");
					System.out.print("A)dd R)ead Q)uit: ");
					//Scanner to read user input
					userEnter = scnr.nextLine();
					//Remove space from user input
					userInput = userEnter.trim();
					//Make user input case insensitive
					userInput = userInput.toUpperCase();
					//Display error message if there is more than one user
					//input or a number
					if (userEnter.trim().length() > 1 || userInput.matches
							(".*\\d+.*"))
						System.out.println("Unrecognized choice: " + userEnter);
				}
				//User chooses 'A'
				if(userInput.equals("A"))
				{
					System.out.print("Enter the name: ");
					//Scanner to store name of place
					name = scnr.nextLine();
					System.out.print("Enter the address: ");
					//Scanner to store address of place
					address = scnr.nextLine();
					//Create new place using user input of name and address
					Place place = new Place(name, address);
					//Display message if placeLists already contains place
					if (placeLists.contains(place))
					{
						System.out.println(name + " already in list.");
					}
					//Add new place to placeLists if place doesn't exist
					else
					{
						//Add place to placeLists
						placeLists.add(place);
						System.out.println("Adding: " + name);
					}
				}
				//User chooses 'R'
				else if(userInput.equals("R"))
				{
					System.out.print("My Places Files: \n");
					//Calls and displays file names from folder
					fileNames();
					System.out.print("\nEnter filename: ");
					//Scanner to write new file names
					fileName = scnr.nextLine();
					System.out.println("Reading file: " + fileName);
					//Save new file name to folder
					placeLists.readFile(fileName);


				}
				//User chooses 'Q'
				else if(userInput.equals("Q"))
					break;

				System.out.println("Press Enter to continue.");
				scnr.nextLine();

			}
			catch (Exception e) 
			{
				System.out.println("Unrecognized choice: " + userEnter);
			}
		}while (!userInput.equals("Q"));


		System.out.println("Thank you for using My Places 2016!");
		//Close scanner
		scnr.close();
	}


}