
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

public class FlightTabController {
    @FXML
    private ComboBox flightFromCombo;
    @FXML
    private ComboBox flightToCombo;
    @FXML
    private DatePicker flightDepartureDate;
    @FXML
    private DatePicker flightReturningDate;
    @FXML
    private RadioButton flightRoundWay;
    @FXML
    private RadioButton flightOneWay;
    @FXML
    private ToggleGroup radio;               // tengir radioButtons saman
    @FXML
    private Button flightSearchButton;
    @FXML
    private Spinner flightPersons;

    /**
     * Bregst við því þegar leitað er að flugi.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á takka
     */
    public void leitaHandler(ActionEvent actionEvent) {
    }
}
