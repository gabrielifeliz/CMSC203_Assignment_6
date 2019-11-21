//package com.company.assignment6;

import java.text.DecimalFormat;

/**
 * This class is the general representation of tickets
 * @author Gabriel I Feliz
 */
public abstract class Ticket {

    /**
     * Movie feature
     */
    private Format feature;

    /**
     * Movie name
     */
    private String movieName;

    /**
     * Movie rating
     */
    private String rating;

    /**
     * Movie type
     */
    private String type;

    /**
     * Ticket price
     */
    private double ticketPrice;

    /**
     * Day the movie is watched
     */
    private int day;

    /**
     * Time the movie is watched
     */
    private int time;

    /**
     * Consumer ID
     */
    protected int id;

    /**
     * Movie ticket manager
     */
    protected MovieTicketManager ticketManager;

    /**
     * Format converter
     */
    private DecimalFormat currencyFormat = new DecimalFormat("$#,###,##0.00");

    /**
     * No-arg constructor
     */
    public Ticket() {

    }

    /**
     * Constructor to set values to the fields about ticket
     * @param movieName movie name
     * @param rating movie rating
     * @param day day the movie is to be watched
     * @param time time the movie is to be watched
     * @param feature movie feature
     * @param type ticket type
     * @param id consumer ID
     */
    public Ticket(String movieName, String rating, int day, int time,
                  String feature, String type, int id) {
        setMovieName(movieName);
        setRating(rating);
        setDay(day);
        setTime(time);
        if (feature.equalsIgnoreCase("IMAX")) {
            setFeature(Format.IMAX);
        } else if (feature.equalsIgnoreCase("3D")) {
            setFeature(Format.THREE_D);
        } else if (feature.equalsIgnoreCase("NONE")) {
            setFeature(Format.NONE);
        }
        setType(type);
        setId(id);
    }

    /**
     * Constructor to set values to the fields about ticket
     * and create a reference copy for movie ticket manager
     * @param movieName movie name
     * @param rating movie rating
     * @param day day the movie is to be watched
     * @param time time the movie is to be watched
     * @param feature movie feature
     * @param type ticket type
     * @param id consumer ID
     * @param m movie ticket manager
     */
    public Ticket(String movieName, String rating, int day, int time,
                  String feature, String type, int id, MovieTicketManager m){
        this(movieName,rating,day,time,feature,type,id);
        ticketManager = m;
    }

    /**
     * Gets movie feature
     * @return movie feature
     */
    public Format getFeature() {
        return feature;
    }

    /**
     * Sets movie feature
     * @param feature movie feature
     */
    public void setFeature(Format feature) {
        this.feature = feature;
    }

    /**
     * Gets movie name
     * @return movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Sets movie name
     * @param movieName movie name
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Gets movie rating
     * @return movie rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets movie rating
     * @param rating movie rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Gets movie type
     * @return movie type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets movie type
     * @param type movie type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets ticket price
     * @return ticket price
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets ticket price
     * @param ticketPrice ticket price
     */
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Gets day the movie is to be watched
     * @return day the movie is to be watched
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets day the movie is to be watched
     * @param day day the movie is to be watched
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Gets time the movie is to be watched
     * @return time the movie is to be watched
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets time the movie is to be watched
     * @param time time the movie is to be watched
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Sets consumer ID
     * @param id consumer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets consumer ID when implemented
     * @return consumer ID
     */
    public abstract int getId();

    /**
     * Calculates ticket price when implemented
     * @return ticket price
     */
    public abstract double calculateTicketPrice();

    /**
     * Gets a String representation of ticket information
     * @return ticket information
     */
    @Override
    public String toString() {
        return type.toUpperCase() + ((id > 0) ? "-" + id + " " : " ") +
                (feature.equals(Format.NONE) ? "" :
                        (feature.equals(Format.THREE_D) ? "3D" : feature))
                + " Movie: " + movieName + " Rating: " + rating + " Day: " + day
                + " Time: " + time + " Price: " + currencyFormat.format(ticketPrice);
    }
}
