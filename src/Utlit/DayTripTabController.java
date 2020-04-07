package Utlit;

import Vinnsla.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DayTripTabController {

    //viðmótshlutir fyrir niðurstöður
    @FXML
    private ListView dayTripListView;
    @FXML
    private Button dayTripBook;

    // Viðmótshlutir fyrir leit
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
    public void initialize()
    {
        daytripList = new DayTripList();
        listOfDayTrips = new ListOfDayTrips();
        bookedDayTrips = new ArrayList<DayTrip>();
        setjaStadi();
        setjaDagsetningu();
        setjaMannfjolda();
        setjaLista();
    }

    private void setjaStadi()
    {
        ArrayList<String> places = listOfDayTrips.getPlaces();                              // ná í stadsetningar úr gagnagrunn
        ObservableList<String> stadir = FXCollections.observableArrayList(places);          // búa til lista
        dayTripCombo.setItems(stadir);                                                      // setja lista í combobox
        dayTripCombo.getSelectionModel().select(0);                                  // setja fyrsta stak sem upphafsgildi
        stadsetn = (String)dayTripCombo.getSelectionModel().getSelectedItem();
    }

    private void setjaDagsetningu()
    {
        dayTripDate.setValue(LocalDate.now());
        dagsetn = dayTripDate.getValue();
    }

    private void setjaMannfjolda()
    {
        SpinnerValueFactory.IntegerSpinnerValueFactory intSpin;
        intSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1, 1);
        dayTripPersons.setValueFactory(intSpin);
    }

    private void setjaLista()
    {
        //ListOfDayTrips listOfDayTrips = new ListOfDayTrips();
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

    public void leitHandler(ActionEvent actionevent)
    {
        if (dayTripSearchButton.getText().equals("Search"))
        {
            dayTripSearchButton.setText("Back");
            dayTripHeader.setText("Choose Day Trip");

            leitarVidmot(false);
            nidurstoduVidmot(true);

            fjoldiManns = (int) dayTripPersons.getValue();
            ArrayList<DayTrip> results = daytripList.searchDayTrips(stadsetn, dagsetn, fjoldiManns);
            ObservableList<DayTrip> listResults = FXCollections.observableArrayList(results);
            dayTripListView.setItems(listResults);
            virkurIndex = 0;

        } else
        {
            dayTripSearchButton.setText("Search");
            dayTripHeader.setText("Search for Day Trips");

            leitarVidmot(true);
            nidurstoduVidmot(false);

        }
    }

    public void dayTripComboHandler(ActionEvent actionEvent)
    {
        ComboBox cb = (ComboBox)actionEvent.getSource();
        stadsetn = (String)cb.getSelectionModel().getSelectedItem();
    }

    public void dayTripDateHandler(ActionEvent actionEvent)
    {
        dagsetn = dayTripDate.getValue();
    }

    public void bokunhandler(ActionEvent actionEvent) {
       DayTrip orderedDayTrip = (DayTrip)dayTripListView.getSelectionModel().getSelectedItem();
       System.out.println(orderedDayTrip.toString());
       bookedDayTrips.add(orderedDayTrip);
       parentController.setjaDayTrips(FXCollections.observableArrayList(bookedDayTrips));

    }

    private void leitarVidmot(boolean b)
    {
        dayTripPlaceLabel.setVisible(b);
        dayTripPerLabel.setVisible(b);
        dayTripCombo.setVisible(b);
        dayTripDate.setVisible(b);
        dayTripPersons.setVisible(b);
    }

    private void nidurstoduVidmot(boolean b)
    {
        dayTripListView.setVisible(b);
        dayTripBook.setVisible(b);
    }


    public void setParentController(LeitController parentController) {
        this.parentController = parentController;
    }

    public ArrayList<DayTrip> removeAllOrderedDayTrips() {
        bookedDayTrips.clear();
        return bookedDayTrips;
    }
}
