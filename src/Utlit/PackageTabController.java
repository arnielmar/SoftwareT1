package Utlit;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

public class PackageTabController {
    @FXML
    private ComboBox packageFromCombo;
    @FXML
    private ComboBox packageToCombo;
    @FXML
    private DatePicker packageDepartureDate;
    @FXML
    private DatePicker packageReturningDate;
    @FXML
    private Button packageSearchButton;
    @FXML
    private Spinner packagePersons;
}
