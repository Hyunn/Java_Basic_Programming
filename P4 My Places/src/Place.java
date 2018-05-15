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
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class manages data for a single place. It deals with names and
 * addresses for each place and contains constructors and accessors.
 * This class is the inheritance of the Object class.
 * 
 * @author Hyunho Choi
 */

public class Place extends Object
{
   //Declares objects
   private String name;
   private String address;

   /**
    * This constructor creates a new place using given names and addresses.
    * @param name (name of place)
    * @param address (address of place)
    * @return N/A
    */
   public Place(String name, String address)
   {
      this.name = name;
      this.address = address;
   }
   
   /**
    * This getter method returns name of the place.
    * @param N/A
    * @return name of place
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * This getter method returns address of the place.
    * @param N/A
    * @return address of place
    */
   public String getAddress()
   {
      return address;
   }
   /**
    * This setter method sets name of place.
    * @param   name (name of place)
    * @return   N/A
    */
   public void setName(String name)
   {
      this.name = name;
   }
   /**
    * This setter method sets address name of place.
    * @param   address (address of place)
    * @return   N/A
    */
   public void setAddress(String address)
   {
      this.address = address;
   }
   /**
    * Override the Object class's equal method and compare places using data
    * in each places.
    * @param   obj (variable to compare place to)
    * @return   True if obj is place object and getName is same as name of
    *    place, False if not
    */
   @Override
   public boolean equals(Object obj) 
   {
      //Check if obj is place object and name is equal case insensitively
      if( (obj instanceof Place) && 
            this.name.equalsIgnoreCase( ((Place) obj).getName() ))
         return true;
      return false;   
   }

}