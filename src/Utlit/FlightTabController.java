import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class FlightTabController {
    public ComboBox flightFromCombo;
    public ComboBox flightToCombo;
    public DatePicker flightDepartureDate;
    public DatePicker flightReturningDate;
    public RadioButton flightRoundWay;
    public RadioButton flightOneWay;
    public ToggleGroup radio;               // tengir radioButtons saman
    public Button flightSearchButton;
    public Spinner flightPersons;

    /**
     * Bregst við því þegar leitað er að flugi.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á takka
     */
    public void leitaHandler(ActionEvent actionEvent) {
    }
}
