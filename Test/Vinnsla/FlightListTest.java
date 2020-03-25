package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FlightListTest {
    FlightList fList;
    mockupFlight flightTest1;
    mockupFlight flightTest2;

    @BeforeEach
    void setUp() {
        fList = new FlightList();
        String[] s = new String[2];
        s[0] = "1";
        s[1] = "2";
        flightTest1 = new mockupFlight("1","1",null,1);
        flightTest1 = new mockupFlight("2","2",null,2);
        fList.addFlight(flightTest1);
        fList.addFlight(flightTest2);
    }

    @AfterEach
    void tearDown() {
        fList = null;
    }

    @Test
    void getFlight() {
        assertEquals(flightTest1, fList.getFlight(0));
    }

    @Test
    void getAllFlights() {
        ObservableList<mockupFlight> flightsTest = FXCollections.observableArrayList(flightTest1, flightTest2);
        assertEquals(flightsTest, fList.getAllFlights());
    }

    @Test
    void deleteFlight() {
        fList.deleteFlight(0);
        int size = fList.getAllFlights().size();
        assertEquals(size, 1);
    }

    @Test
    void addFlight() {
        mockupFlight flightTest3 = new mockupFlight("3","3",null,3);
        fList.addFlight(flightTest3);
        assertEquals(flightTest3, fList.getFlight(2));
    }

    @Test
    void searchFlightsRoundWay() {
        fList.searchFlightsRoundWay("Ak", "Rey", LocalDate.now(), LocalDate.of(2020,4,15), 2);
        mockupFlights mFlights = new mockupFlights();
        ArrayList<mockupFlight> mSearchList1 = mFlights.search("Rey", "Ak", LocalDate.now(), 2);
        ArrayList<mockupFlight> mSearchList2 = mFlights.search("Ak", "Rey", LocalDate.of(2020,4,15), 2);
        mSearchList1.addAll(mSearchList2);
        assertEquals(fList.getAllFlights(), FXCollections.observableArrayList(mSearchList1));
    }

    @Test
    void searchFlightsOneWay() {
        fList.searchFlightsOneWay("Ak", "Rey", LocalDate.now(), 2);
        mockupFlights mFlights = new mockupFlights();
        ArrayList<mockupFlight> mSearchList = mFlights.search("Rey", "Ak", LocalDate.now(), 2);
        assertEquals(fList.getAllFlights(), FXCollections.observableArrayList(mSearchList));
    }
}