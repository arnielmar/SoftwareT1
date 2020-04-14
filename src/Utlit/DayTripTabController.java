package Utlit;

import Vinnsla.*;
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
 * Controller fyrir DayTrip tab.
 *
 */
public class DayTripTabController {
    //viðmótshlutir fyrir niðurstöður
    @FXML
    private ListView dayTripListView;
    @FXML
    private Button dayTripBook;
    // Viðmótshlutir fyrir leit
    @FXML
    private Label dayTripDateLabel;
    @FXML
    private Label dayTripPlaceLabel;
    @FXML
    private Label dayTripPerLabel;
    @FXML
    private ComboBox dayTripCombo;
    @FXML
    private DatePicker dayTripDate;
    @FXML
    private Spinner dayTripPersons;
    // Viðmóthlutir fyrir bæði
    @FXML
    private Button dayTripSearchButton;
    @FXML
    private Label dayTripHeader;

    private LeitController parentController;

    private DayTripList daytripList;            // tenging við gögn með lista af day trip-um
    private ListOfDayTrips listOfDayTrips;      // tenging við mock gagnagrunn af day trip-um
    private ArrayList<DayTrip> bookedDayTrips;  // Heldur utan um hvaða daytrip hafa verið bokuð
    private int virkurIndex = 0;                // heldur utan um hvaða stak í listanum er valið

    private String stadsetn;
    private LocalDate dagsetn;
    private int fjoldiManns;


    /**
     * Upphafsstillir gluggann fyrir flugleit.
     * Setur áfangastaði í comboboxin, upphafsstillir spinnerinn,
     * upphafsstillir dagatölin og listana með niðurstöðum.
     *
     */
    public void initialize() {
        daytripList = new DayTripList();
        listOfDayTrips = new ListOfDayTrips();
        bookedDayTrips = new ArrayList<DayTrip>();
        setjaStadi();
        setjaDagsetningu();
        setjaMannfjolda();
        setjaLista();
    }

    /**
     * Upphafsstillir comboboxið með áfangastöðum.
     *
     */
    private void setjaStadi() {
        ArrayList<String> places = listOfDayTrips.getPlaces();                              // ná í stadsetningar úr gagnagrunn
        ObservableList<String> stadir = FXCollections.observableArrayList(places);          // búa til lista
        dayTripCombo.setItems(stadir);                                                      // setja lista í combobox
        dayTripCombo.getSelectionModel().select(0);                                  // setja fyrsta stak sem upphafsgildi
        stadsetn = (String)dayTripCombo.getSelectionModel().getSelectedItem();
    }

    /**
     * Upphafsstillir dagsetningu á Date Picker á daginn í dag.
     *
     */
    private void setjaDagsetningu() {
        dayTripDate.setValue(LocalDate.now());
        dagsetn = dayTripDate.getValue();
    }

    /**
     * Stillir spinner-inn. Upphafsgildi er 1,
     * min gildi er 1 og max gildi er 250.
     *
     */
    private void setjaMannfjolda() {
        SpinnerValueFactory.IntegerSpinnerValueFactory intSpin;
        intSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1, 1);
        dayTripPersons.setValueFactory(intSpin);
    }

    /**
     * Upphafsstillir listana fyrir niðurstöður og setur
     * change-listener á þá til að halda utan um hvaða stak
     * í listunum er valið.
     *
     */
    private void setjaLista() {
        ObservableList<DayTrip> listi = FXCollections.observableArrayList(listOfDayTrips.getListofDayTrips());
        dayTripListView.setItems(listi);
        MultipleSelectionModel<DayTrip> lsr = dayTripListView.getSelectionModel();
        lsr.selectedItemProperty().addListener(new ChangeListener<DayTrip>() {
            @Override
            public void changed(ObservableValue<? extends DayTrip> observable, DayTrip oldValue, DayTrip newValue) {
                virkurIndex = lsr.getSelectedIndex();
                System.out.println("virkurIndex = " + virkurIndex);
            }
        });
    }

    /**
     * Bregst við því þegar leitað er að day trip eða farið til baka.
     * Ef verið er að leita:
     * Nær í fjölda manns úr spinnernum, athugar hvort leitað
     * sé að one way eða round trip og kallar á leitaraðferð
     * í FlightList sem leitar að flugum.
     * Afvirkjar leitarglugga og virkjar glugga fyrir niðurstöður og
     * setur svo niðurstöður í ListView.
     * Ef verið er að fara til baka:
     * Farið aftur í leitarviðmót.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á leitartakka eða til baka takka
     */
    @FXML
    private void leitHandler(ActionEvent actionEvent) {
        if (dayTripSearchButton.getText().equals("Search")) {
            dayTripSearchButton.setText("Back");
            dayTripHeader.setText("Choose Day Trip");

            leitarVidmot(false);
            nidurstoduVidmot(true);

            fjoldiManns = (int) dayTripPersons.getValue();
            ArrayList<DayTrip> results = daytripList.searchDayTrips(stadsetn, dagsetn, fjoldiManns);
            ObservableList<DayTrip> listResults = FXCollections.observableArrayList(results);
            dayTripListView.setItems(listResults);
            virkurIndex = 0;
        } else {
            dayTripSearchButton.setText("Search");
            dayTripHeader.setText("Search for Day Trips");

            leitarVidmot(true);
            nidurstoduVidmot(false);

        }
    }

    /**
     * Atburðahandler fyrir combobox.
     * Finnur hvaða staður er valinn í comboboxi.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja stak í comboboxi
     */
    @FXML
    private void dayTripComboHandler(ActionEvent actionEvent) {
        ComboBox cb = (ComboBox)actionEvent.getSource();
        stadsetn = (String)cb.getSelectionModel().getSelectedItem();
    }

    /**
     * Atburðahandler fyrir dagsetningu.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja dagsetningu
     */
    @FXML
    private void dayTripDateHandler(ActionEvent actionEvent) {
        dagsetn = dayTripDate.getValue();
    }

    /**
     * Pantar day trip.
     *
     * @param actionEvent - atburðurinn sem varð til við að ýta á order takka
     */
    @FXML
    private void bokunhandler(ActionEvent actionEvent) {
       DayTrip orderedDayTrip = (DayTrip)dayTripListView.getSelectionModel().getSelectedItem();
       System.out.println(orderedDayTrip.toString());
       bookedDayTrips.add(orderedDayTrip);
       parentController.setjaDayTrips(FXCollections.observableArrayList(bookedDayTrips));
    }

    /**
     * Gerir viðmótshluti fyrir leit sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     *
     * @param b - true ef á að virkja, annars false
     */
    private void leitarVidmot(boolean b) {
        dayTripPlaceLabel.setVisible(b);
        dayTripPerLabel.setVisible(b);
        dayTripCombo.setVisible(b);
        dayTripDateLabel.setVisible(b);
        dayTripDate.setVisible(b);
        dayTripPersons.setVisible(b);
    }

    /**
     * Gerir viðmótshluti fyrir niðurstöður sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     * Sýnir einnig lista fyrir heimkomu flug ef roundWay er true.
     *
     * @param b - true ef á að virkja, annars false
     */
    private void nidurstoduVidmot(boolean b) {
        dayTripListView.setVisible(b);
        dayTripBook.setVisible(b);
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
     * Eyðir öllum day trips úr lista með pöntuðum day trips.
     *
     * @return - tómur listi með pöntuðum day trips
     */
    public ArrayList<DayTrip> removeAllOrderedDayTrips() {
        bookedDayTrips.clear();
        return bookedDayTrips;
    }
}
