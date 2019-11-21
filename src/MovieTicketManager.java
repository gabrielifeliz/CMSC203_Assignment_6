//package com.company.assignment6;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains utility methods for the movie ticket manager
 * @author Gabriel I Feliz
 */
public class MovieTicketManager implements MovieTicketManagerInterface {

    /**
     * Format converter
     */
    private DecimalFormat currencyFormat = new DecimalFormat("$#,###,##0.00");

    /**
     * Ticket list
     */
    private ArrayList<Ticket> ticketList = new ArrayList<>();

    /**
     * Gets ticket list
     * @return list of bought tickets
     */
    public ArrayList<Ticket> getTicketList(){
        return ticketList;
    }

    /**
     * Gets the number of movie theater visits a person has
     * @param id ID of the patron
     * @return number of movie theater visits
     */
    @Override
    public int numVisits(int id) {

        int visits = 0;

        for (Ticket ticket : ticketList) {
            if (ticket.getId() == id) {
                visits++;
            }
        }

        return visits;
    }

    /**
     * Gets the number of times a person has watched a specified movie
     * @param id the ID of the movie
     * @param movie the name of the movie
     * @return number of times a movie has been watched
     */
    @Override
    public int numThisMovie(int id, String movie) {

        int visits = 0;

        for (Ticket ticket : ticketList) {
            boolean watchedMovie = ticket.getMovieName()
                    .equalsIgnoreCase(movie);
            if (ticket.getId() == id && watchedMovie) {
                visits++;
            }
        }

        return visits;
    }

    /**
     * Gets the number of times a person has watched movies in a specified day
     * @param id ID of patron
     * @param date current date
     * @return Number of times movies have been watched in a day
     */
    @Override
    public int numMoviesToday(int id, int date) {

        int visits = 0;

        for (Ticket ticket : ticketList) {
            if (ticket.getId() == id && ticket.getDay() == date) {
                visits++;
            }
        }

        return visits;
    }

    /**
     * Adds a ticket to the ticket list and returns its price
     * @param movieN movie to be watched
     * @param rating movie rating
     * @param d date
     * @param t time
     * @param f feature
     * @param type type of patron
     * @param id consumer ID
     * @return the price of the ticket. Returns a negative number if type is invalid
     */
    @Override
    public double addTicket(String movieN, String rating, int d, int t, String f, String type, int id) {

        Ticket ticket = null;

        if (type.equalsIgnoreCase("ADULT")) {
            ticket = new Adult(movieN, rating, d, t, f, type, id,this);
        } else if (type.equalsIgnoreCase("CHILD")) {
            if (rating.equalsIgnoreCase("PG") || rating.equalsIgnoreCase("G")) {
                ticket = new Child(movieN, rating, d, t, f, type, id, this);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unfortunately, a child ticket could not be saved.\n" +
                        "The rating for child tickets must be either G or PG.");
                alert.showAndWait();
            }
        } else if (type.equalsIgnoreCase("EMPLOYEE")) {
            ticket = new Employee(movieN, rating, d, t, f, type, id,this);
        } else if (type.equalsIgnoreCase("MOVIEPASS")) {
            ticket = new MoviePass(movieN, rating, d, t, f, type, id, this);
        }

        if (ticket != null) {
            ticketList.add(ticket);
            return ticket.getTicketPrice();
        }
        return -1;
    }

    /**
     * Calculates the total sales in the current month
     * @return total prices of ticket purchased in the month
     */
    @Override
    public double totalSalesMonth() {

        double total = 0;

        for (Ticket ticket : ticketList) {
            total += ticket.getTicketPrice();
        }

        return total;
    }

    /**
     * Generates sales report of movie tickets
     * @return a String representation about the sales and number of tickets
     * for each type as well as total monthly sales
     */
    @Override
    public String monthlySalesReport() {

        String table = "";
        table += String.format("%15s %30s %15s", "", "Monthly Sales Report", "") + "\n";
        table += " \n";
        table += String.format("%15s %30s %15s", "", "Sales", "Number") + "\n";

        int countAdult = 0, countChild = 0, countEmployee = 0, countMoviePass = 0;
        double totalAdult = 0, totalChild = 0, totalEmployee = 0, totalMoviePass = 0;

        for (Ticket ticket : ticketList) {
            if (ticket instanceof Adult) {
                totalAdult += ticket.getTicketPrice();
                countAdult++;
            } else if (ticket instanceof Child) {
                totalChild += ticket.getTicketPrice();
                countChild++;
            } else if (ticket instanceof Employee) {
                totalEmployee += ticket.getTicketPrice();
                countEmployee++;
            } else if (ticket instanceof MoviePass) {
                totalMoviePass += ticket.getTicketPrice();
                countMoviePass++;
            }
        }

        table += String.format("%15s %22s %20s", "ADULT",
                currencyFormat.format(totalAdult), countAdult) + "\n";
        table += String.format("%15s %23s %20s", "CHILD",
                currencyFormat.format(totalChild), countChild) + "\n";
        table += String.format("%15s %20s %20s", "EMPLOYEE",
                currencyFormat.format(totalEmployee), countEmployee) + "\n";
        table += String.format("%15s %18s %20s", "MOVIEPASS",
                currencyFormat.format(totalMoviePass), countMoviePass) + "\n";
        table += "\nTotal Monthly Sales: " + currencyFormat.format(totalSalesMonth());

        return table;
    }

    /**
     * Generates a list of 3D tickets by day
     * @return List of String representation of 3D tickets by day
     */
    @Override
    public ArrayList<String> get3DTickets() {

        sortByDay();

        ArrayList<String> threeDTickets = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            if (ticket.getFeature().equals(Format.THREE_D)) {
                threeDTickets.add(ticket.toString() + "\n");
            }
        }
        return threeDTickets;
    }

    /**
     * Generates a list of all tickets by day
     * @return List of String representation of all tickets by day
     */
    @Override
    public ArrayList<String> getAllTickets() {

        sortByDay();

        ArrayList<String> allTickets = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            allTickets.add(ticket.toString() + "\n");
        }
        return allTickets;
    }

    /**
     * Generates a list of MoviePass tickets by ID
     * @return List of String representation of MoviePass tickets by ID
     */
    @Override
    public ArrayList<String> getMoviePassTickets() {

        sortById();

        ArrayList<String> moviePassTickets = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            if (ticket instanceof MoviePass) {
                moviePassTickets.add(ticket.toString() + "\n");
            }
        }
        return moviePassTickets;
    }

    /**
     * Reads file to populate ticket list
     * @param file file to be read from
     * @throws FileNotFoundException specified file is not found
     */
    @Override
    public void readFile(File file) throws FileNotFoundException {

        Scanner scanFile = new Scanner(file);
        while(scanFile.hasNextLine()) {
            String[] movieInfo = scanFile.nextLine().split(":");
            String movieName = movieInfo[0];
            String rating = movieInfo[1];
            int day = Integer.parseInt(movieInfo[2]);
            int time = Integer.parseInt(movieInfo[3]);
            String feature = movieInfo[4];
            String type = movieInfo[5];
            int id = Integer.parseInt(movieInfo[6]);
            addTicket(movieName, rating, day, time, feature, type, id);
        }
    }

    /**
     * Sort ticket list by day, using insertion sort algorithm
     */
    private void sortByDay() {
        Ticket ticket;
        for (int i = 1; i < ticketList.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (ticketList.get(j).getDay() < ticketList.get(j - 1).getDay()) {
                    ticket = ticketList.get(j);
                    ticketList.set(j, ticketList.get(j - 1));
                    ticketList.set(j - 1, ticket);
                }
            }
        }

    }

    /**
     * Sort ticket list by ID, using insertion sort algorithm
     */
    private void sortById() {
        Ticket ticket;
        for (int i = 1; i < ticketList.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (ticketList.get(j).getId() < ticketList.get(j - 1).getId()) {
                    ticket = ticketList.get(j);
                    ticketList.set(j, ticketList.get(j - 1));
                    ticketList.set(j - 1, ticket);
                }
            }
        }
    }
}
