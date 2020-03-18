package Utlit;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class HotelTabController {
    @FXML
    private ComboBox<String> hotelCombo;
    @FXML
    private DatePicker hotelFromDate;
    @FXML
    private DatePicker hotelToDate;
    @FXML
    private Button hotelSearchButton;
    @FXML
    private Spinner hotelPersons;



    /**
     * setur hluti í comboboxið og lætur spinnerinn hegða sér venjulega
     */
    @FXML
    public void initialize() {
        ObservableList<String> listDest = FXCollections.observableArrayList("Reykjavík", "Akureyri", "Danmörk");
        hotelCombo.setItems(listDest);
        hotelCombo.getSelectionModel().selectFirst();

        SpinnerValueFactory.IntegerSpinnerValueFactory intSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1, 1);
        hotelPersons.setValueFactory(intSpin);
    }

    /**
     * Bregst við leit af hóteli.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á leitartakka
     */
    public void leitaHandler(ActionEvent actionEvent) {
        Date from = java.sql.Date.valueOf(hotelFromDate.getValue());
        Date to = java.sql.Date.valueOf(hotelToDate.getValue());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedFrom = formatter.format(from);
        String formattedTo = formatter.format(to);

        String destination = hotelCombo.getValue();
        int peopleNumber = (int)hotelPersons.getValue();
        System.out.println(peopleNumber + " manns ætla að vera í " + destination + " frá " + formattedFrom + " til " + formattedTo);
        //HotelList.search(destination, from, to, peopleNumber);
    }
}
