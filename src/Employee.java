//package com.company.assignment6;

/**
 * This class is the specific representation of employee tickets
 * @author Gabriel I Feliz
 */
public class Employee extends Ticket {

    /**
     * Constant employee before-6-pm fee
     */
    private final double BEFORE_SIX_PM_FEE = 5.25;

    /**
     * Constant employee 6-pm-and-after fee
     */
    private final double SIX_PM_AND_AFTER_FEE = 6.75;

    /**
     * Constant employee IMAX fee
     */
    private final double IMAX_FEE = 1.50;

    /**
     * Constant employee 3D fee
     */
    private final double THREE_D_FEE = 1.75;

    /**
     * Constant tax percentage (9.6%)
     */
    private final double TAX = 0.096;

    /**
     * No-arg constructor that calls the superclass's constructor
     */
    public Employee() {
        super();
    }

    /**
     * Constructor to set values to the fields about employee ticket,
     * send them to the superclass's constructor, and calculate ticket price
     * @param movieName movie name
     * @param rating movie rating
     * @param day day the movie is to be watched
     * @param time time the movie is to be watched
     * @param feature movie feature
     * @param type ticket type
     * @param id consumer ID
     */
    public Employee(String movieName, String rating, int day, int time,
                     String feature, String type, int id) {
        super(movieName, rating, day, time, feature, type, id);
        setTicketPrice(calculateTicketPrice());
    }

    /**
     * Constructor to set values to the fields about employee ticket,
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
    public Employee(String movieName, String rating, int day, int time,
                    String feature, String type, int id,MovieTicketManager m) {
        super(movieName, rating, day, time, feature, type, id, m);
        setTicketPrice(calculateTicketPrice());
    }

    /**
     * Gets id for employees
     * @return employee id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Calculates employee ticket price based on the following criteria:
     * The first two movies are free and all additional movies
     * are half of the pre-tax Adult price + 9.6% tax
     * @return price of employee ticket
     */
    @Override
    public double calculateTicketPrice() {
        double price = 0;

        if (ticketManager.numVisits(id) > 2) {
            if (getTime() > 7 && getTime() < 18) {
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

            price += (price * TAX);
        }

        return price;
    }

    /**
     * Gets the String representation of employee ticket information,
     * calling the superclass's toString method
     * @return employee ticket information
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
