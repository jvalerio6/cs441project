package EisenhowerBox;

import javax.swing.JOptionPane;

/*
 * Software Engineering project Spring Semester 2016
 * Instructor Sukendeep Samra
 * Group Project EisenhowerBox Team members: Samir Asfirane, Erik Kalan,
 * Team members: Erik Kalan, Javier Valerio, Nelson Debate,  Ricky Lee,
 * Samir Asfirane, Yu (Will) Tian
 */

/**
 * Class DbManager.java defines the DbManager Class
 * that creates the connection with the database
 * @author Samir Asfirane
 */
public class Date {
    /** variable year an integer */
    private int year;
    /** variable month an integer */
    private int month;
    /** variable day an integer */
    private int day;
    /** Date constructor takes three  integers as arguments that represents the
     * year, month and day checks they're valid before creating an object date
     * @param year
     * @param month
     * @param day
     */
    public Date(int year, int month, int day){
        // checks if year is valid then intializes it
        if (year >= 0) {
        	this.year = year;
        }
        else {
        	System.out.println("A year cannot be negative");
        }

        // checks if month is valid then intializes it
        if (month >= 0 && month <= 12) {
        	this.month = month;
        }
        else {
        	System.out.println("A month cannot be negative or greater than 12");
        }

        // checks if day is valid then intializes it
        if (day > 0 && day <= 31) {
        	this.day = day;
        }
        else {
        	System.out.println("A day cannot be negative");
        }


    }

    public Date(int year, String month, int day) {
        if (year >= 0) {
        	this.year = year;
        }
        else {
        	System.out.println("A year cannot be negative");
        }

        // checks if month is valid then initializes it
        if(monthOK(month)) {
        	this.month = getMonthToInt(month);
        }
        else {
        	System.out.println("A month cannot be negative or greater than 12");
        }

        // checks if day is valid then initializes it
        if(day > 0  && day <= 31) {
        	this.day = day;
        }
        else {
        	System.out.println("A day cannot be negative");
        }
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /** This method returns an integer representing the month.
     *  @return An integer from 1-12 indicating the month of the year.
    */
    private int getMonthToInt(String monthString)
    {
        if (monthString.equals("January") || monthString.equals("Jan"))
            return 1;
        else if (monthString.equals("February") || monthString.equals("Feb"))
            return 2;
        else if (monthString.equalsIgnoreCase("March") || monthString.equals("Mar"))
            return 3;
        else if (monthString.equalsIgnoreCase("April") || monthString.equals("Apr"))
            return 4;
        else if (monthString.equalsIgnoreCase("May") || monthString.equals("May"))
            return 5;
        else if (monthString.equals("June") || monthString.equals("Jun"))
            return 6;
        else if (monthString.equalsIgnoreCase("July") || monthString.equals("Jul"))
            return 7;
        else if (monthString.equalsIgnoreCase("August") || monthString.equals("Aug"))
            return 8;
        else if (monthString.equalsIgnoreCase("September") || monthString.equals("Sep"))
            return 9;
        else if (monthString.equalsIgnoreCase("October") || monthString.equals("Oct"))
            return 10;
        else if (monthString.equals("November") || monthString.equals("Nov"))
            return 11;
        else if (monthString.equals("December") || monthString.equals("Dec"))
            return 12;
        else {
            return 0;
        }
    }

    private boolean monthOK(String month) {
        return ((month.equals("January") || month.equals("Jan")) ||
        		(month.equals("February") || month.equals("Feb")) ||
                (month.equals("March") || month.equals("Mar")) ||
                (month.equals("April") || month.equals("Apr")) ||
                (month.equals("May")) ||
                (month.equals("June") || month.equals("Jun")) ||
                (month.equals("July") || month.equals("Jul")) ||
                (month.equals("August") || month.equals("Aug")) ||
                (month.equals("September") || month.equals("Sep")) ||
                (month.equals("October") || month.equals("Oct")) ||
                (month.equals("November") || month.equals("Nov")) ||
                (month.equals("December")) || month.equals("Dec"));
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    public String toString( ) {
    	//return day + ", " + month + " - " + year;
    	return year + "/" + month + "/" + day;
    }
}