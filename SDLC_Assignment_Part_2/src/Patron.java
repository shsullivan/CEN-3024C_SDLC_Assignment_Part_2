/*
 * Shawn Sullivan
 * CEN 3024 - Software Development 1
 * May 17, 2025
 * Patron.java
 * This class defines the Parton class for the LMS App, verifies the arguments are in the correct format, and
 * overrides the toString method to properly format the console print for a Patron object.
 */

public class Patron {

    // Attributes
    private String ID;
    private String name;
    private String address;
    private double fineAmount;

    // Constructor containing input validation for each attribute of the Patron class

    public Patron(String ID, String name, String address, double fineAmount) {
        if (ID.length() != 7) {
            throw new IllegalArgumentException("ID must be 7 characters");
        }// end if
        else {
            this.ID = ID;
        }// end else

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }// end if
        else {
            this.name = name;
        }// end else

        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }// end  if
        else {
            this.address = address;
        }// end else

        if (fineAmount < 0 || fineAmount > 250) {
            throw new IllegalArgumentException("Overdue fine must be between $0 and $250");
        }// end if
        else {
            this.fineAmount= fineAmount;
        }// end else
    }// end Patron Constructor

    // Getters and setters

    public String getID() {
        return ID;
    }// end getID

    public String getName() {
        return name;
    }// end getName

    public String getAddress() {
        return address;
    }// end getAddress

    public double getFineAmount() {
        return fineAmount;
    }// end getFineAmount

    public void setID(String ID) {
        if (ID.length() != 7) {
            throw new IllegalArgumentException("ID must be 7 characters");
        }// end if
        else {
            this.ID = ID;
        }// end else
    }// end setID

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }// end if
        else {
            this.name = name;
        }// end else
    }// end setName

    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }// end if
        else {
            this.address = address;
        }// end else
    }// end setAddress

    public void setFineAmount(double fineAmount) {
        if (fineAmount < 0 || fineAmount > 250) {
            throw new IllegalArgumentException("Overdue fine must be between $0 and $250");
        }// end if
        else {
            this.fineAmount = fineAmount;
        }// end else
    }// end setFineAmount

    //Override the toString method to display Patron information in a readable format
    @Override
    public String toString() {
        return ID + " | " + name + " | " + address + " | $" + fineAmount;
    }// end toString override
}// end Patron class
