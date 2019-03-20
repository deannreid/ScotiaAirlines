/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package couk.deanreid.scotiaairlines.core;

import java.util.*;
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
public class AirlineIT {
    
    public AirlineIT () {
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
     * Test of getFlights method, of class Airline.
     */
    @Test
    public void testGetFlights_0args () {
        System.out.println ("getFlights");
        Airline instance = new Airline ();
        HashMap<String, Flight> expResult = null;
        HashMap<String, Flight> result = instance.getFlights ();
        assertEquals (expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of getFlights method, of class Airline.
     */
    @Test
    public void testGetFlights_String () {
        System.out.println ("getFlights");
        String flightNo = "";
        Airline instance = new Airline ();
        Flight expResult = null;
        Flight result = instance.getFlights (flightNo);
        assertEquals (expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of addFlight method, of class Airline.
     */
    @Test
    public void testAddFlight () {
        System.out.println ("addFlight");
        Flight aFlight = null;
        Airline instance = new Airline ();
        instance.addFlight (aFlight);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of loadFlightsFromDB method, of class Airline.
     */
    @Test
    public void testLoadFlightsFromDB () throws Exception {
        System.out.println ("loadFlightsFromDB");
        Airline instance = new Airline ();
        instance.loadFlightsFromDB ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of loadSeatsFromDB method, of class Airline.
     */
    @Test
    public void testLoadSeatsFromDB () throws Exception {
        System.out.println ("loadSeatsFromDB");
        Airline instance = new Airline ();
        instance.loadSeatsFromDB ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of loadPassengersFromDB method, of class Airline.
     */
    @Test
    public void testLoadPassengersFromDB () {
        System.out.println ("loadPassengersFromDB");
        String flightNo = "";
        Seat passengerSeat = null;
        int status = 0;
        float takings = 0.0F;
        Airline instance = new Airline ();
        instance.loadPassengersFromDB (flightNo, passengerSeat, status, takings);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of emptyDB method, of class Airline.
     */
    @Test
    public void testEmptyDB () {
        System.out.println ("emptyDB");
        Airline instance = new Airline ();
        instance.emptyDB ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of saveSeatsToDB method, of class Airline.
     */
    @Test
    public void testSaveSeatsToDB () {
        System.out.println ("saveSeatsToDB");
        Airline instance = new Airline ();
        instance.saveSeatsToDB ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of savePassengersToDB method, of class Airline.
     */
    @Test
    public void testSavePassengersToDB () {
        System.out.println ("savePassengersToDB");
        Airline instance = new Airline ();
        instance.savePassengersToDB ();
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }

    /**
     * Test of getSeat method, of class Airline.
     */
    @Test
    public void testGetSeat () {
        System.out.println ("getSeat");
        String FlightNo = "";
        String SeatNo = "";
        Airline instance = new Airline ();
        Seat expResult = null;
        Seat result = instance.getSeat (FlightNo, SeatNo);
        assertEquals (expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail ("The test case is a prototype.");
    }
    
}
