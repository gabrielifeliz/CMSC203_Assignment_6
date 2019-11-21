//package com.company.assignment6;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is designed to test methods from MovieTicketManager
 * made by student
 * @author Gabriel I Feliz
 */
public class MovieTicketManagerTestSTUDENT {
	/**
	 * Declare ticket list that will be used for testing purposes
	 */
	private MovieTicketManager ticketList;


	/**
	 * Instantiate ticket list and add tickets for testing purposes
	 */
	@Before
	public void setUp() {
		ticketList = new MovieTicketManager();

		// Add adult tickets
		ticketList.addTicket("Venom", "PG13",
				21, 15, "NONE", "Adult", 0);
		ticketList.addTicket("Deadpool 2", "NR",
				21, 7, "NONE", "Adult", 0);

		// Add child tickets
		ticketList.addTicket("The Grinch", "PG",
				17, 23, "NONE", "Child", 0);
		ticketList.addTicket("Wretch-It Ralph", "PG",
				8, 19, "IMAX", "Child", 0);

		// Add employee tickets
		ticketList.addTicket("Logan", "R",
				5, 22, "NONE", "Employee", 12321);
		ticketList.addTicket("Fantastic Beasts: Crimes of Grindelwald", "PG13",
				14, 11, "3D", "Employee", 11223);

		// Add MoviePass tickets
		ticketList.addTicket("The Nun", "R",
				1, 10, "3D", "MoviePass", 14862);
		ticketList.addTicket("The Incredibles 2", "PG",
				16, 12, "IMAX", "MoviePass", 15973);
	}

	/**
	 * Remove memory information of ticket list
	 */
	@After
	public void tearDown() {
		//set tickets to null
		ticketList = null;
	}

	/**
	 * Test the number of visits to the theater within the month
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumVisits() {
		//Employees
		assertEquals(1, ticketList.numVisits(12321));
		ticketList.addTicket("Terrifier", "NR",
				4, 9, "NONE", "Employee", 12321);
		assertEquals(2, ticketList.numVisits(12321));
		ticketList.addTicket("Fantastic Beasts: Crimes of Grindelwald", "PG13",
				15, 8, "IMAX", "Employee", 12321);
		assertEquals(3, ticketList.numVisits(12321));

		//MoviePass members
		assertEquals(1, ticketList.numVisits(14862));
		ticketList.addTicket("The Incredibles 2", "PG",
				30, 20, "NONE", "MoviePass", 14862);
		assertEquals(2, ticketList.numVisits(14862));
	}

	/**
	 * Test the number of times this movie has been viewed
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumThisMovie() {
		//Employees
		assertEquals(1, ticketList.numThisMovie(12321, "Logan"));
		ticketList.addTicket("Terrifier", "NR",
				4, 9, "NONE", "Employee", 12321);
		assertEquals(1, ticketList.numThisMovie(12321, "Terrifier"));
		ticketList.addTicket("Fantastic Beasts: Crimes of Grindelwald", "PG13",
				15, 8, "IMAX", "Employee", 12321);
		assertEquals(1, ticketList.numThisMovie(12321, "Fantastic Beasts: Crimes of Grindelwald"));

		//MoviePass members
		assertEquals(1, ticketList.numThisMovie(14862, "The Nun"));
		ticketList.addTicket("The Incredibles 2", "PG",
				30, 20, "NONE", "MoviePass", 14862);
		assertEquals(1, ticketList.numThisMovie(14862, "The Incredibles 2"));
	}

	/**
	 * Test the number of movies attended on a day
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumMoviesToday() {
		//Employees
		assertEquals(1, ticketList.numMoviesToday(12321, 5));
		ticketList.addTicket("Terrifier", "NR",
				4, 9, "NONE", "Employee", 12321);
		assertEquals(1, ticketList.numMoviesToday(12321, 4));
		ticketList.addTicket("Fantastic Beasts: Crimes of Grindelwald", "PG13",
				15, 8, "IMAX", "Employee", 12321);
		assertEquals(1, ticketList.numMoviesToday(12321, 15));

		//MoviePass members
		assertEquals(1, ticketList.numMoviesToday(14862, 1));
		ticketList.addTicket("The Incredibles 2", "PG",
				30, 20, "NONE", "MoviePass", 14862);
		assertEquals(1, ticketList.numMoviesToday(14862, 30));
	}

	/**
	 * Test adding tickets of the 4 types of tickets
	 */
	@Test
	public void testAddTicket() {

		MovieTicketManager ticketManager = new MovieTicketManager();
		assertEquals(11.51, ticketManager.addTicket("Venom", "PG13",
				21, 15, "NONE", "Adult", 0), .01);
		assertEquals(11.78, ticketManager.addTicket("The Grinch", "PG",
				17, 23, "NONE", "Child", 0) ,.01);
		assertEquals(0.00, ticketManager.addTicket("Terrifier", "NR",
				4, 9, "NONE", "Employee", 12321), 0.01);
		assertEquals(9.99, ticketManager.addTicket("The Nun", "R",
				1, 10, "3D", "MoviePass", 14862), 0.1);
		assertEquals(0.00, ticketManager.addTicket("The Incredibles 2", "PG",
						16, 12, "NONE", "MoviePass", 14862), 0.01);
	}

	/**
	 * Test the total of tickets sales for the month
	 */
	@Test
	public void testTotalSalesMonth() {

		assertEquals(57.24,ticketList.totalSalesMonth(),.01);
		ticketList.addTicket("Terrifier", "NR", 4, 9, "NONE", "Employee", 12321);
		assertEquals(57.24,ticketList.totalSalesMonth(),.01);
		ticketList.addTicket("The Incredibles 2", "PG",
				12, 20, "NONE", "MoviePass", 14862);
		assertEquals(57.24,ticketList.totalSalesMonth(),.01);
		ticketList.addTicket("Wretch-It Ralph", "PG",
				20, 20, "IMAX", "MoviePass", 14862);
		assertEquals(75.33,ticketList.totalSalesMonth(),.01);
		//add a MoviePass member who has not seen a movie this month
		ticketList.addTicket("Iron Man", "PG13", 2,14,"NONE","MoviePass",77777);
		assertEquals(85.32,ticketList.totalSalesMonth(),.01);
	}

	/**
	 * The 3D tickets sold this month in chronological order by day
	 */
	@Test
	public void testGet3DTickets() {

		ArrayList<String> result = ticketList.get3DTickets();
		assertTrue("Day 1",result.get(0).contains("The Nun"));
		assertTrue("Day 14",result.get(1).contains("Fantastic Beasts: Crimes of Grindelwald"));
		ticketList.addTicket("Justice League", "PG13", 4, 9, "3D", "Employee", 12321);
		ticketList.addTicket("Iron Man", "PG13", 2,14,"3D","MoviePass",77777);
		result = ticketList.get3DTickets();
		assertTrue("Day 1",result.get(0).contains("The Nun"));
		assertTrue("Day 2",result.get(1).contains("Iron Man"));
		assertTrue("Day 4",result.get(2).contains("Justice League"));
		assertTrue("Day 14",result.get(3).contains("Fantastic Beasts: Crimes of Grindelwald"));
	}

	/**
	 * All tickets sold this month in chronological order by day
	 * You don't need to worry about ordering within the day
	 */
	@Test
	public void testGetAllTickets() {
		ArrayList<String> result = ticketList.getAllTickets();
		assertTrue("Day 1",result.get(0).contains("Day: 1"));
		assertTrue("Day 5",result.get(1).contains("Day: 5"));
		assertTrue("Day 8",result.get(2).contains("Day: 8"));
		assertTrue("Day 14",result.get(3).contains("Day: 14"));
		assertTrue("Day 16",result.get(4).contains("Day: 16"));
		assertTrue("Day 17",result.get(5).contains("Day: 17"));
		assertTrue("Day 21",result.get(6).contains("Day: 21"));
		assertTrue("Day 21",result.get(7).contains("Day: 21"));
	}

	/**
	 * The MoviePass tickets sold this month in order by MoviePass id
	 */
	@Test
	public void testGetMoviePassTickets() {

		ArrayList<String> result = ticketList.getMoviePassTickets();
		assertTrue("14862",result.get(0).contains("14862"));
		assertTrue("15973",result.get(1).contains("15973"));
		ticketList.addTicket("Justice League", "PG13", 4, 9, "IMAX", "MoviePass", 12321);
		ticketList.addTicket("Iron Man", "PG13", 2,14,"NONE","MoviePass",77777);
		result = ticketList.getMoviePassTickets();
		assertTrue("12321",result.get(0).contains("12321"));
		assertTrue("14862",result.get(1).contains("14862"));
		assertTrue("15973",result.get(2).contains("15973"));
		assertTrue("77777",result.get(3).contains("77777"));
	}

	/**
	 * The monthly sales report
	 */
	@Test
	public void testMonthlySalesReport(){
		Scanner scan = new Scanner(ticketList.monthlySalesReport());
		assertTrue(scan.nextLine().contains("Monthly Sales Report"));
		scan.nextLine();//blank line
		assertTrue(scan.nextLine().contains("Sales"));
		String line = scan.nextLine(); //Adult line
		assertTrue(line.contains("ADULT "));
		assertTrue(line.contains("11.51"));
		assertTrue(line.contains("2"));
		line = scan.nextLine(); //Child line
		assertTrue(line.contains("CHILD "));
		assertTrue(line.contains("25.76"));
		assertTrue(line.contains("2"));
		line = scan.nextLine(); //Employee line
		assertTrue(line.contains("EMPLOYEE "));
		assertTrue(line.contains("0.00"));
		assertTrue(line.contains("2"));
		line = scan.nextLine(); //Employee line
		assertTrue(line.contains("MOVIEPASS "));
		assertTrue(line.contains("19.98"));
		assertTrue(line.contains("2"));
	}
	
	/**
	 * Test readin from a file
	 * @throws FileNotFoundException when file is not found
	 */
	@Test
	public void testReadFile() throws FileNotFoundException {

		PrintWriter movieTest = new PrintWriter("testMovie.txt");
		movieTest.println("Venom:PG13:21:15:NONE:Adult:0");
		movieTest.println("The Grinch:PG:17:23:NONE:Child:0");
		movieTest.println("Logan:R:5:22:NONE:Employee:12321");
		movieTest.println("The Incredibles 2:PG:16:12:IMAX:MoviePass:15973");
		movieTest.close();

		MovieTicketManager ticketManager = new MovieTicketManager();
		ticketManager.readFile(new File("testMovie.txt"));
		ArrayList<String> result = ticketManager.getAllTickets();
		assertTrue("Day 5",result.get(0).contains("Logan"));
		assertTrue("Day 16",result.get(1).contains("The Incredibles 2"));
		assertTrue("Day 17",result.get(2).contains("The Grinch"));
		assertTrue("Day 21",result.get(3).contains("Venom"));
	}

}
