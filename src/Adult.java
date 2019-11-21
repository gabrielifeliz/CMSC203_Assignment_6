//package com.company.assignment6;

/**
 * This class is the specific representation of adult tickets
 * @author Gabriel I Feliz
 */
public class Adult extends Ticket {

    /**
     * Constant adult before-6-pm fee
     */
    private final double BEFORE_SIX_PM_FEE = 10.50;

    /**
     * Constant adult 6-pm-and-after fee
     */
    private final double SIX_PM_AND_AFTER_FEE = 13.50;

    /**
     * Constant adult IMAX fee
     */
    private final double IMAX_FEE = 3.00;

    /**
     * Constant adult 3D fee
     */
    private final double THREE_D_FEE = 2.50;

    /**
     * Constant tax percentage (9.6%)
     */
    private final double TAX = 0.096;

    /**
     * No-arg constructor that calls the superclass's constructor
     */
    public Adult() {
        super();
    }

    /**
     * Constructor to set values to the fields about adult ticket,
     * send them to the superclass's constructor, and calculate ticket price
     * @param movieName movie name
     * @param rating movie rating
     * @param day day the movie is to be watched
     * @param time time the movie is to be watched
     * @param feature movie feature
     * @param type ticket type
     * @param id consumer ID
     */
    public Adult(String movieName, String rating, int day, int time,
                     String feature, String type, int id) {
        super(movieName, rating, day, time, feature, type, id);
        setTicketPrice(calculateTicketPrice());
    }

    /**
     * Constructor to set values to the fields about adult ticket,
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
    public Adult(String movieName, String rating, int day, int time,
                 String feature, String type, int id, MovieTicketManager m) {
        super(movieName, rating, day, time, feature, type, id,m);
        setTicketPrice(calculateTicketPrice());
    }

    /**
     * Gets id for adults, which is -1 because of its irrelevancy
     * @return adult id
     */
    @Override
    public int getId() {
        return -1;
    }

    /**
     * Calculates adult ticket price based on the following criteria:
     * Before 6pm: $10.50, 6pm and after: $13.50, IMAX: +$3.00, 3D: +$2.50,	Tax: +9.6%
     * @return price of adult ticket
     */
    @Override
    public double calculateTicketPrice() {
        double price = 0;

        if (getTime() >= 8 && getTime() < 18) {
            price += BEFORE_SIX_PM_FEE;
        } else if (getTime() >= 18 && getTime() < 24) {
            price += SIX_PM_AND_AFTER_FEE;
        }

        if (getFeature().equals(Format.IMAX)) {
            price += IMAX_FEE;
        }

        if (getFeature().equals(Format.THREE_D)) {
            price += THREE_D_FEE;
        }

        price += price * TAX;

        return price;
    }

    /**
     * Gets the String representation of adult ticket information,
     * calling the superclass's toString method
     * @return adult ticket information
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
