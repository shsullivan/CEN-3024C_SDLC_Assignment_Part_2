/*
 * Shawn Sullivan
 * CEN 3024 - Software Development 1
 * May 18, 2025
 * LMSApp.java
 * This class contains the main() method for the LMS App. When initialized this class instantiates an instance of the
 * Administrator class and the main method prompts the user to select from a list of interactions with the LMS database
 * which is stored in the Administrator class in the form of a HashMap. The main method then calls on other methods
 * defined in the Administrator and Patron classes based on the user's input. The main method then gives the user
 * feedback based on whether the action was successful or not. Once the user has completed all their actions they can
 * exit the application via the last option in the actions list.
 */

import java.util.Scanner;

public class LMSApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Instantiation of Scanner to capture user inputs
        Administrator admin = new Administrator(); // Instantiate Administrator to access all defined methods
        boolean done = false; // Working variable to allow app to run or user to exit

        while (!done) {
            // Print menu graphic and request user input
            System.out.println("----Library Management System----");
            System.out.println("Main Menu:");
            System.out.println("1. Enter new Patron information");
            System.out.println("2. Load Patrons from text file");
            System.out.println("3. Remove a Patron");
            System.out.println("4. List all Patrons");
            System.out.println("5. Exit");
            System.out.println("Make a selection by entering the corresponding number above: ");

            String selection = sc.nextLine().trim(); // Capture user selection in a variable

            switch (selection) {
                case "1": // Add patron
                    try { //Try block to for any exceptions thrown by input validation
                        // Prompt user for all attribute information and store in variables to
                        // pass to the Patron constructor
                        System.out.println("Enter Patron's 7 digit ID: ");
                        String id = sc.nextLine().trim();
                        System.out.println("Enter Patron's name: ");
                        String name = sc.nextLine().trim();
                        System.out.println("Enter Patron's address: ");
                        String address = sc.nextLine().trim();
                        System.out.println("Enter Patron's current overdue fine balance (Min: $0 Max: $250): ");
                        double fineAmount = Double.parseDouble(sc.nextLine().trim());

                        Patron patron = new Patron(id, name, address, fineAmount); // Instantiate new Patron

                        // Attempt to add new patron to HashMap and provide feedback on success or failure
                        if (admin.addPatron(patron)) {
                            System.out.println("Patron added successfully!");
                        }// end if
                        else {
                            System.out.println("Patron ID exists! Patron not added!");
                        }// end else
                    }
                    catch (NumberFormatException e) { // Catch text entered for fine amount
                        System.out.println("Invalid entry! Fine amount must be a number. Please try again.");
                    }// end catch NumberFormatException
                    catch (IllegalArgumentException e) { // Catch input validation exception from Patron constructor
                        System.out.println("***Error: " + e.getMessage() + "***");
                    }// end IllegalArgumentException

                    break;

                case "2": // Add patron(s) from text file
                    System.out.println("Enter file path: ");
                    String path = sc.nextLine().trim(); // Capture file path entered in working variable
                    admin.addFromFile(path);
                    break;

                case "3": // Remove a patron
                    System.out.println("Enter ID of Patron to remove: ");
                    String remove = sc.nextLine().trim(); // Capture patron id in working variable
                    if (admin.removePatron(remove)) {
                        System.out.println("Patron removed successfully!");
                    }// end if
                    else {
                        System.out.println("Patron ID does not exist! No patrons were removed!");
                    }// end else
                    break;

                case "4": // List all Patrons
                    System.out.println("---Patron List---");
                    if(admin.listAllPatrons()) {
                        System.out.println("---End of List---");
                    }// end if
                    else {
                        System.out.println("Patron list is empty!");
                    }// end else
                    break;

                case "5":
                    done = true;
                    System.out.println("Thank you for using the Library Management System! Goodbye!");
                    break;

                default:
                    System.out.println("Invalid selection! Please try again!");
            }// end switch
        }// end while(!done)
        sc.close(); // close scanner on App exit
    }// end of main method
}// end of LMSApp
