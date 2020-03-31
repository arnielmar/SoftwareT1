package Utlit;

import Vinnsla.Flight;
import Vinnsla.FlightList;
import Vinnsla.ListOfFlights;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class FlightTabController {
    @FXML
    private Button flightBackButton;
    @FXML
    private Label resultLabel;
    @FXML
    private ListView flightListView;
    @FXML
    private Label searchLabel;
    @FXML
    private Label fromLabel;
    @FXML
    private Label toLabel;
    @FXML
    private Label personsLabel;
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
    private ToggleGroup radio;          // tengir radioButtons saman
    @FXML
    private Button flightSearchButton;
    @FXML
    private Spinner flightPersons;

    private FlightList flightList;      // tenging við gögn með lista af flugum
    private int virkurIndex = 0;        // heldur utan um hvaða stak í lista er valið

    private String depart;              // brottfararstaður
    private String destination;         // áfangastaður
    private boolean oneWay;             // oneway eða round trip
    private LocalDate dateFrom;         // dagsetning frá
    private LocalDate dateTo;           // dagsetning til
    private int noOfPeople;             // fjöldi manns

    /**
     * Upphafsstillir síðu. Upphafsstillir comboboxin.
     *
     */
    public void initialize() {
        setjaStadi();
        setjaSpinner();
        setjaDagsetningar();
        setjaLista();
    }

    /**
     * Upphafsstillir comboboxin með áfangastöðum.
     *
     */
    private void setjaStadi() {
        ObservableList<String> stadir = FXCollections.observableArrayList();    // búa til lista
        stadir.add("Reykjavík");    // bæta í lista
        stadir.add("París");
        flightFromCombo.setItems(stadir);   // setja lista í combobox
        flightFromCombo.getSelectionModel().select(0);  // setja fyrsta stak sem upphafsgildi
        flightToCombo.setItems(stadir);
        flightToCombo.getSelectionModel().select(0);
    }

    /**
     * Stillir spinner-inn. Upphafsgildi er 1,
     * min gildi er 1 og max gildi er 250.
     *
     */
    private void setjaSpinner() {
        SpinnerValueFactory.IntegerSpinnerValueFactory intSpin;
        intSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1, 1);
        flightPersons.setValueFactory(intSpin);
    }

    /**
     * Stillir dagsetningar á Date Pickers á daginn í dag.
     *
     */
    private void setjaDagsetningar() {
        flightDepartureDate.setValue(LocalDate.now());
        flightReturningDate.setValue(LocalDate.now());
    }

    private void setjaLista() {
        flightList = new FlightList();
        ListOfFlights listOfFlights = new ListOfFlights();
        ObservableList<Flight> listi = FXCollections.observableArrayList(listOfFlights.getListiAfFlugum());
        //flightList.addFlight(listi.get(0));
        //flightList.addFlight(listi.get(1));
        flightListView.setItems(flightList.getAllFlights());
        MultipleSelectionModel<Flight> lsr = flightListView.getSelectionModel();
        lsr.selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
                virkurIndex = lsr.getSelectedIndex();
            }
        });
    }

    /**
     * Bregst við því þegar leitað er að flugi.
     * Nær í fjölda manns úr spinnernum, athugar hvort leitað
     * sé að one way eða round trip og kallar á samsvarandi aðferð
     * í FlightList sem leitar að flugum.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á leitartakka
     */
    @FXML
    private void leitaHandler(ActionEvent actionEvent) {
        noOfPeople = (int) flightPersons.getValue();
        System.out.println(noOfPeople);
        // ef aðeins er leitað að einu flugi
        if (oneWay) {
            System.out.println("Leitaði að oneway flugi");
        } else {
            System.out.println("Leitaði að round way flugi");
        }
        /*Orders orders = new Orders();
        orders.makeDummyFlights();*/
        virkjaLeit(false);
        virkjaNidurstodur(true);
    }

    /**
     * Felur niðurstöður og sýnir leitarglugga aftur.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á til baka takka
     */
    @FXML
    private void tilBakaHandler(ActionEvent actionEvent) {
        virkjaNidurstodur(false);
        virkjaLeit(true);
    }

    /**
     * Atburðahandler fyrir flight from combobox.
     * Finnur hvaða staður er valinn í comboboxi.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja stak í comboboxi
     */
    @FXML
    private void flightFromComboHandler(ActionEvent actionEvent) {
        ComboBox cb = (ComboBox) actionEvent.getSource();
        depart = (String)cb.getSelectionModel().getSelectedItem();
        System.out.println(depart);
    }

    /**
     * Atburðahandler fyrir flight to combobox.
     * Finnur hvaða staður er valinn í comboboxi.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja stak í comboboxi
     */
    @FXML
    private void flightToComboHandler(ActionEvent actionEvent) {
        ComboBox cb = (ComboBox)actionEvent.getSource();
        destination = (String)cb.getSelectionModel().getSelectedItem();
        System.out.println(destination);
    }

    /**
     * Atburðahandler fyrir radio buttons.
     * Tilviksbreytan oneWay sett false ef round trip er valið,
     * true annars. Round trip hefur id=1, one way hefur id=2.
     * Disable-ar líka Date Picker fyrir heimkomu ef oneWay er true.
     *
     * @param actionEvent - atburðurinn sem varð til við að ýta á radio button
     */
    @FXML
    private void radioButtonsHandler(ActionEvent actionEvent) {
        int id = Integer.valueOf(((RadioButton) actionEvent.getSource()).getId());
        if (id == 1) {
            oneWay = false;
            flightReturningDate.setDisable(false);
            System.out.println(oneWay);
        } else {
            oneWay = true;
            flightReturningDate.setDisable(true);
            System.out.println(oneWay);
        }
    }

    /**
     * Atburðahandler fyrir dagsetningu brottfarar.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja dagsetningu
     */
    @FXML
    private void flightDepartureDateHandler(ActionEvent actionEvent) {
        dateFrom = flightDepartureDate.getValue();
        System.out.println(dateFrom);
    }

    /**
     * Atburðahandler fyrir dagsetningu heimkomu.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja dagsetningu
     */
    @FXML
    private void flightReturningDateHandler(ActionEvent actionEvent) {
        dateTo = flightReturningDate.getValue();
        System.out.println(dateTo);
    }

    /**
     * Gerir viðmótshluti fyrir leit sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     *
     * @param gildi - true ef á að virkja, annars false
     */
    private void virkjaLeit(boolean gildi) {
        searchLabel.setVisible(gildi);
        fromLabel.setVisible(gildi);
        toLabel.setVisible(gildi);
        personsLabel.setVisible(gildi);
        flightRoundWay.setVisible(gildi);
        flightOneWay.setVisible(gildi);
        flightFromCombo.setVisible(gildi);
        flightToCombo.setVisible(gildi);
        flightDepartureDate.setVisible(gildi);
        flightReturningDate.setVisible(gildi);
        flightPersons.setVisible(gildi);
        flightSearchButton.setVisible(gildi);
    }

    /**
     * Gerir viðmótshluti fyrir niðurstöður sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     *
     * @param gildi - true ef á að virkja, annars false
     */
    private void virkjaNidurstodur(boolean gildi) {
        flightListView.setVisible(gildi);
        resultLabel.setVisible(gildi);
        flightBackButton.setVisible(gildi);
    }
}
