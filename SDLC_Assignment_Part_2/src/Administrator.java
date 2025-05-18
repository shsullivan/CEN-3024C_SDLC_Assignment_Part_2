/*
 * Shawn Sullivan
 * CEN 3024 - Software Development 1
 * May 17, 2025
 * Administrator.java
 * This class defines all the methods necessary perform administrative actions in the LMS App. These actions include
 * adding a new patron, removing a patron, list all patrons, and make bulk patron adds via a text file with "-"
 * delimiters. This class with also contain the hashmap that stores all Patron objects with their associated ID.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Administrator {

    // Attributes
    private final Map<String, Patron> patrons = new HashMap<>();

    // App will use generic constructor. No specific constructors need to be entered

    /*
     * Method: addPatron
     * Parameter(s): Patron patron
     * Returns: boolean
     * Purpose: Adds a patron to the LMS App, specifically the patrons HashMap and returns true or false for UI
     * response purposes in the main() method.
     */
    public boolean addPatron(Patron patron) {

        if (patrons.containsKey(patron.getID()))
            return false; // Check if  patron ID already exists
        else {
            patrons.put(patron.getID(), patron);
            return true;
        }// end else
    }// end of addPatron

    /*
     * Method: addFromFile
     * Parameters: String filePath
     * Returns: boolean
     * Purpose: Adds users from a text file with information delimited by a dash "-" character. If the text in file
     * is not properly formatted or doesn't contain all needed information a message is displayed to the user notifying
     * them of errors. Method also catches and handles exceptions thrown by the Patron constructor
     * for attributes that do not meet the validation criteria of the Patron constructor and exceptions if the file
     * cannot be found/read.
     */
    public void addFromFile(String filePath) {

        // Create buffer reader and load file containing data
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;       // Working variable to hold current line of text file
            int lineCount = 0; // Working variable to iterate through lines of file if multiple lines exist

            while ((line = br.readLine()) != null) {// While loop to iterate through lines of text file
                lineCount++;

                String[] attributes = line.split("-"); // Working list variable to hold separated attributes

                if (attributes.length != 4) {  // Verify that all 4 attributes needed for new Patron are present
                    System.out.println("Line " + lineCount + " is invalid will be skipped");
                    continue;
                }// end if

                try { // Try to create a new Patron object from current line
                    // Set working variables to hold attributes used in Patron constructor
                    String id = attributes[0].trim();
                    String name = attributes[1].trim();
                    String address = attributes[2].trim();
                    double fineAmount = Double.parseDouble(attributes[3].trim());

                    // Check if Patron on current line already exists and notify if added or already exists
                    if (addPatron(new Patron(id, name, address, fineAmount))) {
                        System.out.println("Patron " + id + " added");
                    }// end if
                    else {
                        System.out.println("Patron " + id + " already exists");
                    }// end else
                }// end of try adding Patron
                catch (NumberFormatException e) { // Catch invalid number formatting for fineAmount in entry
                    System.out.println("Invalid fine amount formatting for Patron at line: " + lineCount);
                }// end catch NumberFormatException
                catch (IllegalArgumentException e) { // Catch any validation errors thrown by Patron constructor
                    System.out.println("Invalid entry for Patron at line" + lineCount + ": " + e.getMessage());
                }// end catch IllegalArgumentException
            }// end while line != null

            // Notify user that Patrons were added successfully
            System.out.println("Patrons added from: " + filePath);
        }// end try BufferReader
        catch (IOException e) { // Catch file path or file read error
            System.out.println("Error reading file " + filePath + ": " + e.getMessage());
        }// end catch IOException
    }// end addFromFile

    /*
     * Method: removePatron
     * Parameters: String id
     * Returns: boolean
     * Purpose: Removes a patron from the LMS App, specifically the patrons HashMap and returns true if the Patron ID
     * exists in the HashMap and false if it does not. the return value is used for UI response purposes in the
     * main() method.
     */
    public boolean removePatron(String id) {
        return patrons.remove(id) != null;
    }// end of removePatron

    /*
     * Method: listAllPatrons
     * Parameters: none
     * Returns: boolean
     * Purpose: Lists all patrons stored in the LMS App, specifically in the patrons HashMap. If patrons HashMap is
     * empty listAllPatrons returns false and the main() method notifies the user in the UI
     */
    public boolean listAllPatrons() {
        if (patrons.isEmpty())
            return false;
        else {
            patrons.values().forEach(System.out::println);
            return true;
        }// end else
    }// end listAllPatrons
}// end of Administrator class
