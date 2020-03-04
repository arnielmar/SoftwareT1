
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

public class HotelTabController {
    @FXML
    private ComboBox hotelCombo;
    @FXML
    private DatePicker hotelFromDate;
    @FXML
    private DatePicker hotelToDate;
    @FXML
    private Button hotelSearchButton;
    @FXML
    private Spinner hotelPersons;

    /**
     * Bregst við leit af hóteli.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á leitartakka
     */
    public void leitaHandler(ActionEvent actionEvent) {
    }
}
