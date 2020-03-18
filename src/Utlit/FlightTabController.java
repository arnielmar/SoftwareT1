package Utlit;

import Vinnsla.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FlightTabController implements Initializable {
    @FXML
    private ComboBox<String> flightFromCombo;
    @FXML
    private ComboBox<String> flightToCombo;
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

    private String depart;              // brottfararstaður
    private String destination;         // áfangastaður
    private boolean oneWay;             // oneway eða round trip
    private LocalDate from;             // dagsetning frá
    private LocalDate to;               // dagsetning til
    private int noOfPeople;             // fjöldi manns

    /**
     * Upphafsstillir síðu. Upphafsstillir comboboxin.
     *
     * @param location - ónotað
     * @param resources - ónotað
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setjaStadi();
    }

    /**
     * Upphafsstillir comboboxin með áfangastöðum.
     *
     */
    public void setjaStadi() {
        ObservableList<String> stadir = FXCollections.observableArrayList();    // búa til lista
        stadir.add("Reykjavík");    // bæta í lista
        stadir.add("París");
        flightFromCombo.setItems(stadir);   // setja lista í combobox
        flightFromCombo.getSelectionModel().select(0);  // setja fyrsta stak sem upphafsgildi
        flightToCombo.setItems(stadir);
        flightToCombo.getSelectionModel().select(0);
    }

    /**
     * Bregst við því þegar leitað er að flugi.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á takka
     */
    public void leitaHandler(ActionEvent actionEvent) {
        Orders orders = new Orders();
        orders.makeDummyFlights();
    }

    /**
     * Atburðahandler fyrir flight from combobox.
     * Finnur hvaða staður er valinn í comboboxi.
     * @param actionEvent - atburðurinn sem varð til við að velja stak í comboboxi
     */
    public void flightFromComboHandler(ActionEvent actionEvent) {
        ComboBox cb = (ComboBox)actionEvent.getSource();
        depart = (String)cb.getSelectionModel().getSelectedItem();
    }

    /**
     * Atburðahandler fyrir flight to combobox.
     * Finnur hvaða staður er valinn í comboboxi.
     * @param actionEvent - atburðurinn sem varð til við að velja stak í comboboxi
     */
    public void flightToComboHandler(ActionEvent actionEvent) {
        ComboBox cb = (ComboBox)actionEvent.getSource();
        destination = (String)cb.getSelectionModel().getSelectedItem();
    }
}
