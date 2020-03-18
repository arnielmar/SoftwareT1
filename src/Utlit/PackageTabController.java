package Utlit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;

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

    @FXML
    public void initialize(){
        ObservableList<String> listDest = FXCollections.observableArrayList("Reykjavík","Danmörk","Akureyri");
        packageFromCombo.setItems(listDest);
        packageFromCombo.getSelectionModel().selectFirst();
        packageToCombo.setItems(listDest);
        packageToCombo.getSelectionModel().selectFirst();

        SpinnerValueFactory.IntegerSpinnerValueFactory intSpin;
        intSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1, 1);
        packagePersons.setValueFactory(intSpin);
    }
}
