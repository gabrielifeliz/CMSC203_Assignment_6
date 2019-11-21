//package com.company.assignment6;

/**
 * This class is the specific representation of MoviePass tickets
 * @author Gabriel I Feliz
 */
public class MoviePass extends Ticket {

    /**
     * Constant MoviePass before-6-pm fee
     */
    private final double BEFORE_SIX_PM_FEE = 10.50;

    /**
     * Constant MoviePass 6-pm-and-after fee
     */
    private final double SIX_PM_AND_AFTER_FEE = 13.50;

    /**
     * Constant MoviePass IMAX fee
     */
    private final double IMAX_FEE = 3.00;

    /**
     * Constant MoviePass 3D fee
     */
    private final double THREE_D_FEE = 2.50;

    /**
     * Constant tax percentage (9.6%)
     */
    private final double TAX = 0.096;

    /**
     * No-arg constructor that calls the superclass's constructor
     */
    public MoviePass() {
        super();
    }

    /**
     * Constructor to set values to the fields about MoviePass ticket,
     * send them to the superclass's constructor, and calculate ticket price
     * @param movieName movie name
     * @param rating movie rating
     * @param day day the movie is to be watched
     * @param time time the movie is to be watched
     * @param feature movie feature
     * @param type ticket type
     * @param id consumer ID
     */
    public MoviePass(String movieName, String rating, int day, int time,
                        String feature, String type, int id) {
        super(movieName, rating, day, time, feature, type, id);
        setTicketPrice(calculateTicketPrice());
    }

    /**
     * Constructor to set values to the fields about MoviePass ticket,
     * create a reference copy for movie ticket manager,
     * send them to the superclass's constructor, and calculate ticket price
     * @param movieName movie name
     * @param rating movie rating
     * @param day day the movie is to be watched
     * @param time time the movie is to be watched
     * @param feature movie feature
     * @param type ticket type
     * @param id consumer ID
     * @param m movie ticket manager
     */
    public MoviePass(String movieName, String rating, int day, int time,
                     String feature, String type, int id, MovieTicketManager m) {
        super(movieName, rating, day, time, feature, type, id, m);
        setTicketPrice(calculateTicketPrice());
    }

    /**
     * Gets id for MoviePass consumer
     * @return MoviePass consumer id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Calculates MoviePass ticket price based on the following criteria:
     * The first movie is $9.99 (includes tax) and all additional movies are free if:
     * 1.	Attend only one movie per day, 2.	Movie cannot have been watched before,
     * 3.	Movie cannot be IMAX or 3D. If any of the 3 rules have not been met,
     * they will be charged at the Adult price.
     * @return price of MoviePass ticket
     */
    @Override
    public double calculateTicketPrice() {
        double price = 9.99;

        boolean hasWatchedThisMovie =
                ticketManager.numThisMovie(id, getMovieName()) != 0;

        boolean hasWatchedAMovieToday =
                ticketManager.numMoviesToday(id, getDay()) != 0;

        boolean isImaxOr3D = this.getFeature().equals(Format.IMAX) ||
                this.getFeature().equals(Format.THREE_D);


        if (ticketManager.numVisits(id) > 0) {

            price = 0;

            if (hasWatchedThisMovie || hasWatchedAMovieToday || isImaxOr3D) {
                if (getTime() >= 8 && getTime() < 18) {
                    price += BEFORE_SIX_PM_FEE;
                } else if (getTime() >= 18 && getTime() < 24) {
                    price += SIX_PM_AND_AFTER_FEE;
                }

                if (getFeature().equals(Format.IMAX)) {
                    price = price + IMAX_FEE;
                }

                if (getFeature().equals(Format.THREE_D)) {
                    price = price + THREE_D_FEE;
                }

                price += price * TAX;
            }
        }

        return price;
    }

    /**
     * Gets the String representation of MoviePass ticket information,
     * calling the superclass's toString method
     * @return MoviePass ticket information
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
