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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with the arrayList of places called placeLists. It can add
 * and remove places from placeLists or check if a place is already in the list.
 * It also reads and writes files.
 *
 * @author Hyunho Choi
 */

public class PlaceList 
{
   private ArrayList<Place> places;
   /**
    * Constructor to create an arrayList of places
    * @param    N/A
    * @return   N/A
    */
   public PlaceList()
   {
      //Create new arrayList
      places = new ArrayList<Place>();
   }
   /**
    * Method to add place to arrayList.
    * @param   place (place to add to arrayList)
    * @return   N/A
    */
   public void add(Place place)
   {
      //Add place
      places.add(place);
   }
   /**
    * Method to remove place from arrayList.
    * @param   index (to remove from arrayList)
    * @return   N/A
    */
   public void remove(int index)
   {
      places.remove(index);
   }
   /**
    * Method to display size of arrayList
    * @param   N/A
    * @return   number of places in arrayList
    */
   public int size()
   {
      //Display size
      return places.size();
   }
   /**
    * Method to check if a place exists in the arrayList
    * @param   N/A
    * @return   True if place exists, False if not
    */
   public boolean hasPlaces()
   {
      //Checks if size is greater than 0
      return places.size() > 0;
   }
   /**
    * Method to get information of place from arrayList
    * @param   index (choice of number)
    * @return   place in placeLists
    */
   public Place get(int index)
   {
      //Return place
      return places.get(index);
   }
   /**
    * Method to check if place exists in places
    * @param   place
    * @return   True if exists, False if not
    */
   public boolean contains(Place place) 
   {
      //Loop to check if place exists in arrayList
      for (int i = 0; i < places.size(); i++)
         if(places.get(i).equals(place))
            return true;
      return false ;
   }

   /**
    *   This method reads files in the folder and creates a new place object to
    *   store.
    * @param fileName (the name of file to read, must be in the format ".mp," 
    * and must be separated by a semicolon between name and address)
    * @return N/A
    */
   public void readFile(String fileName)
   {
      //Used to catch errors if a certain file is not found
      try
      {
         //Create new file instance
         File folder = new File(fileName);
         //Used to read the files
         Scanner in = new Scanner(folder);
         //Keeps reading until there is no new line
         while(in.hasNextLine())
         {
            //Store the line input
            String lines = in.nextLine();
            //Split name and address
            String[] elements = lines.split(";");
            String name = elements[0].trim();
            String address = elements[1].trim();
            //Create new place using information gathered from scanner
            Place place = new Place(name, address);

            //If the new place already exists, then message appears.
            if(places.contains(place))
               System.out.println(name + " already in list.");
            else
               places.add(place);
         }
         in.close();
      }catch(FileNotFoundException e) 
      {
         System.out.println("Unable to read from file: " + fileName);
      }
   }

   /**
    * This method creates new user-named files and writes places in the files.
    *
    * @param fileName (name of file to be stored)
    * @return N/A
    */
   public void writeFile(String fileName)
   {
      //Used to catch errors if a certain file is not found
      try
      {
         //Create new file instance
         File folder = new File(fileName);
         //Create new PrintWriter to save user-named files
         PrintWriter fout = new PrintWriter(folder);

         //Loop to save contents of files
         for(int i=0; i<places.size(); i++)
         {
            fout.print(places.get(i).getName());
            fout.print(";");
            fout.println(places.get(i).getAddress());
         }
         //Close PrintWrite to store
         fout.close();
      }catch(FileNotFoundException e) 
      {
         System.out.println("Unable to write to file: " + fileName);
      }
   }
}