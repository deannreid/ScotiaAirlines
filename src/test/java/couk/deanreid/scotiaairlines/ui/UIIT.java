/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package couk.deanreid.scotiaairlines.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 10004084
 */
public class UIIT {
    
    public UIIT () {
    }
    
    @BeforeClass
    public static void setUpClass () {
    }
    
    @AfterClass
    public static void tearDownClass () {
    }
    
    @Before
    public void setUp () {
    }
    
    @After
    public void tearDown () {
    }

    /**
     * Test of genericPopup method, of class UI.
     */
    @Test
    public void testGenericPopup () {
        System.out.println ("genericPopup");
        String inputMessage = "";
        UI instance = null;
        instance.genericPopup (inputMessage);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of mainMenu method, of class UI.
     */
    @Test
    public void testMainMenu () {
        System.out.println ("mainMenu");
        UI instance = null;
        instance.mainMenu ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of adminMenu method, of class UI.
     */
    @Test
    public void testAdminMenu () {
        System.out.println ("adminMenu");
        UI instance = null;
        instance.adminMenu ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of addNewFlight method, of class UI.
     */
    @Test
    public void testAddNewFlight () {
        System.out.println ("addNewFlight");
        UI instance = null;
        instance.addNewFlight ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of deleteFlight method, of class UI.
     */
    @Test
    public void testDeleteFlight () {
        System.out.println ("deleteFlight");
        String status = "";
        UI instance = null;
        instance.deleteFlight (status);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of selectFlight method, of class UI.
     */
    @Test
    public void testSelectFlight () {
        System.out.println ("selectFlight");
        String status = "";
        UI instance = null;
        instance.selectFlight (status);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of displayTestMenu method, of class UI.
     */
    @Test
    public void testDisplayTestMenu () {
        System.out.println ("displayTestMenu");
        UI instance = null;
        instance.displayTestMenu ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of displayChangeStatusMenu method, of class UI.
     */
    @Test
    public void testDisplayChangeStatusMenu () {
        System.out.println ("displayChangeStatusMenu");
        String FlightInfo = "";
        UI instance = null;
        instance.displayChangeStatusMenu (FlightInfo);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of displayBookingMenu method, of class UI.
     */
    @Test
    public void testDisplayBookingMenu () {
        System.out.println ("displayBookingMenu");
        String FlightInfo = "";
        UI instance = null;
        instance.displayBookingMenu (FlightInfo);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of getSeatno method, of class UI.
     */
    @Test
    public void testGetSeatno () {
        System.out.println ("getSeatno");
        String flightNo = "";
        int choice = 0;
        UI instance = null;
        instance.getSeatno (flightNo, choice);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of bookPassenger method, of class UI.
     */
    @Test
    public void testBookPassenger () {
        System.out.println ("bookPassenger");
        String flightNumber = "";
        int bookingChoice = 0;
        String seatNo = "";
        UI instance = null;
        instance.bookPassenger (flightNumber, bookingChoice, seatNo);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of displaySeatMap method, of class UI.
     */
    @Test
    public void testDisplaySeatMap () {
        System.out.println ("displaySeatMap");
        String flightNo = "";
        UI instance = null;
        instance.displaySeatMap (flightNo);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of webGet method, of class UI.
     */
    @Test
    public void testWebGet () throws Exception {
        System.out.println ("webGet");
        UI instance = null;
        instance.webGet ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of getPassengerDetails method, of class UI.
     */
    @Test
    public void testGetPassengerDetails () {
        System.out.println ("getPassengerDetails");
        String flightNumber = "";
        int bookingChoice = 0;
        String seatNo = "";
        UI instance = null;
        instance.getPassengerDetails (flightNumber, bookingChoice, seatNo);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of getResidence method, of class UI.
     */
    @Test
    public void testGetResidence () {
        System.out.println ("getResidence");
        String flightNumber = "";
        int bookingChoice = 0;
        String seatNo = "";
        String passengerName = "";
        UI instance = null;
        instance.getResidence (flightNumber, bookingChoice, seatNo, passengerName);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class UI.
     */
    @Test
    public void testGetCompany () {
        System.out.println ("getCompany");
        String flightNumber = "";
        int bookingChoice = 0;
        String seatNo = "";
        String passengerName = "";
        UI instance = null;
        instance.getCompany (flightNumber, bookingChoice, seatNo, passengerName);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of getPromo method, of class UI.
     */
    @Test
    public void testGetPromo () {
        System.out.println ("getPromo");
        String flightNumber = "";
        int bookingChoice = 0;
        String seatNo = "";
        String passengerName = "";
        UI instance = null;
        instance.getPromo (flightNumber, bookingChoice, seatNo, passengerName);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }
    
}
