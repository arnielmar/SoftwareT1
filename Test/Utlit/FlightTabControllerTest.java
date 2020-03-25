package Utlit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTabControllerTest {
    private FlightTabController controller1, controller2;

    @BeforeEach
    void setUp() {
        controller1 = new FlightTabController();
        controller2 = new FlightTabController();

    }

    @AfterEach
    void tearDown() {
        controller1 = null;
        controller2 = null;
    }

    @Test
    void testComboBox() {
        assertEquals("Reykjavík", controller1.getFromCombo());
        //assertEquals(1, controller1.getSpinner());
    }
}