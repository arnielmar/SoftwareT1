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
import java.util.ArrayList;

/**
 * Controller klasi fyrir flug gluggann.
 * Birtir leitarglugga og leyfir notanda að leita.
 * Birtir niðurstöður þegar notandi er búinn að leita og
 * leyfir notanda að fara til baka í leit.
 *
 */
public class FlightTabController {
    // viðmótshlutir fyrir niðurstöður
    @FXML
    private Label flightOutLabel;
    @FXML
    private Label flightBackLabel;
    @FXML
    private Button pantaFlugUtButton;
    @FXML
    private Button pantaFlugHeimButton;
    @FXML
    private Button flightBackButton;
    @FXML
    private Label resultLabel;
    @FXML
    private ListView flightListViewOne;
    @FXML
    private ListView flightListViewTwo;

    // viðmótshlutir fyrir leit
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
    private ToggleGroup radio;
    @FXML
    private Button flightSearchButton;
    @FXML
    private Spinner flightPersons;

    private LeitController parentController;    // tenging við LeitController
    private FlightList flightList;              // tenging við gögn með lista af flugum
    private ListOfFlights listOfFlights;        // tenging við mock gagnagrunn af flugum
    private ArrayList<Flight> fromResults;      // heldur utan um niðurstöður leitar fyrir flug út
    private ArrayList<Flight> toResults;        // heldur utan um niðurstöður leitar fyrir flug til baka
    private ArrayList<Flight> orderedFlights;   // heldur utan um pöntuð flug
    private int virkurIndexOne = -1;            // heldur utan um hvaða stak í lista 1 er valið
    private int virkurIndexTwo = -1;            // heldur utan um hvaða stak í lista 2 er valið

    private String depart;                      // brottfararstaður
    private String destination;                 // áfangastaður
    private boolean oneWay;                     // oneway eða round trip
    private LocalDate dateFrom;                 // dagsetning frá
    private LocalDate dateTo;                   // dagsetning til
    private int noOfPeople;                     // fjöldi manns

    /**
     * Upphafsstillir gluggann fyrir flugleit.
     * Setur áfangastaði í comboboxin, upphafsstillir spinnerinn,
     * upphafsstillir dagatölin og listana með niðurstöðum.
     *
     */
    public void initialize() {
        flightList = new FlightList();
        listOfFlights = new ListOfFlights();
        orderedFlights = new ArrayList<>();
        setjaStadi();
        setjaSpinner();
        setjaDagsetningar();
        setjaLista();
    }

    /**
     * Tenging í LeitController sem er aðal controller-inn.
     *
     * @param parentController - LeitController
     */
    public void setParentController(LeitController parentController) {
        this.parentController = parentController;
    }

    /**
     * Upphafsstillir comboboxin með áfangastöðum.
     *
     */
    private void setjaStadi() {
        ArrayList<String> places = listOfFlights.getPlaces();   // ná í áfangastaði úr gagnagrunn
        ObservableList<String> stadir = FXCollections.observableArrayList(places);    // búa til lista
        flightFromCombo.setItems(stadir);   // setja lista í combobox
        flightFromCombo.getSelectionModel().select(0);  // setja fyrsta stak sem upphafsgildi
        depart = flightFromCombo.getSelectionModel().getSelectedItem();
        flightToCombo.setItems(stadir);
        flightToCombo.getSelectionModel().select(1);
        destination = flightToCombo.getSelectionModel().getSelectedItem();
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
     * Upphafsstillir dagsetningar á Date Pickers á daginn í dag.
     *
     */
    private void setjaDagsetningar() {
        flightDepartureDate.setValue(LocalDate.now());
        dateFrom = flightDepartureDate.getValue();
        flightReturningDate.setValue(LocalDate.now());
        dateTo = flightReturningDate.getValue();
    }

    /**
     * Upphafsstillir listana fyrir niðurstöður og setur
     * change-listener á þá til að halda utan um hvaða stak
     * í listunum er valið.
     *
     */
    private void setjaLista() {
        ObservableList<Flight> listi = flightList.getAllFlights();
        flightListViewOne.setItems(listi);
        flightListViewTwo.setItems(listi);
        MultipleSelectionModel<Flight> lsr = flightListViewOne.getSelectionModel();
        lsr.selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
                virkurIndexOne = lsr.getSelectedIndex();
                System.out.println("virkurIndexOne = " + virkurIndexOne);
            }
        });
        MultipleSelectionModel<Flight> lsm = flightListViewTwo.getSelectionModel();
        lsm.selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
                virkurIndexTwo = lsm.getSelectedIndex();
                System.out.println("virkurIndexTwo = " + virkurIndexTwo);
            }
        });
    }

    /**
     * Bregst við því þegar leitað er að flugi.
     * Nær í fjölda manns úr spinnernum, athugar hvort leitað
     * sé að one way eða round trip og kallar á leitaraðferð
     * í FlightList sem leitar að flugum.
     * Afvirkjar leitarglugga og virkjar glugga fyrir niðurstöður og
     * setur svo niðurstöður í ListViewOne og ListViewTwo.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á leitartakka
     */
    @FXML
    private void leitaHandler(ActionEvent actionEvent) {
        virkjaLeit(false);
        noOfPeople = (int) flightPersons.getValue();
        System.out.println(noOfPeople);
        if (oneWay) {   // leit að einu flugi
            fromResults = flightList.searchFlights(depart, destination, dateFrom, noOfPeople);
            ObservableList<Flight> listResults = FXCollections.observableArrayList(fromResults);
            flightListViewOne.setItems(listResults);
            virkurIndexOne = 0;
            virkjaNidurstodur(true, false);
            System.out.println("Leitaði að oneway flugi");
        } else {    // leit að tveimur flugum
            fromResults = flightList.searchFlights(depart, destination, dateFrom, noOfPeople);
            ObservableList<Flight> fromListResults = FXCollections.observableArrayList(fromResults);
            toResults = flightList.searchFlights(destination, depart, dateTo, noOfPeople);
            ObservableList<Flight> toListResults = FXCollections.observableArrayList(toResults);
            flightListViewOne.setItems(fromListResults);
            flightListViewTwo.setItems(toListResults);
            virkurIndexOne = 0;
            virkurIndexTwo = 0;
            virkjaNidurstodur(true, true);
            System.out.println("Leitaði að round way flugi");
        }
    }

    /**
     * Felur niðurstöður og sýnir leitarglugga aftur.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á til baka takka
     */
    @FXML
    private void tilBakaHandler(ActionEvent actionEvent) {
        virkjaNidurstodur(false, false);
        virkjaLeit(true);
    }

    /**
     * Pantar flug út.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á panta takka
     */
    @FXML
    private void pantaFlugUtHandler(ActionEvent actionEvent) {
        if (virkurIndexOne != -1) {
            Flight orderedFlight = fromResults.get(virkurIndexOne);
            orderedFlights.add(orderedFlight);
            parentController.setjaFlights(FXCollections.observableArrayList(orderedFlights));
        }
    }

    /**
     * Pantar flug heim.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á panta takka
     */
    @FXML
    private void pantaFlugHeimHandler(ActionEvent actionEvent) {
        if (virkurIndexTwo != -1) {
            Flight orderedFlight = toResults.get(virkurIndexTwo);
            orderedFlights.add(orderedFlight);
            parentController.setjaFlights(FXCollections.observableArrayList(orderedFlights));
        }
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
     * Sýnir einnig lista fyrir heimkomu flug ef roundWay er true.
     *
     * @param gildi - true ef á að virkja, annars false
     * @param roundWay - true ef á að virkja lista tvö
     */
    private void virkjaNidurstodur(boolean gildi, boolean roundWay) {
        flightOutLabel.setVisible(gildi);
        flightListViewOne.setVisible(gildi);
        flightBackLabel.setVisible(roundWay);
        flightListViewTwo.setVisible(roundWay);
        resultLabel.setVisible(gildi);
        flightBackButton.setVisible(gildi);
        pantaFlugUtButton.setVisible(gildi);
        pantaFlugHeimButton.setVisible(roundWay);
    }

    /**
     * Eyðir öllum flugum úr lista með pöntuðum flugum.
     *
     * @return - tómur listi með pöntuðum flugum
     */
    public ArrayList<Flight> removeAllOrderedFlights() {
        orderedFlights.clear();
        return orderedFlights;
    }

    public void removeOrderedFlight(Flight flight) {
        orderedFlights.remove(flight);
    }
}
