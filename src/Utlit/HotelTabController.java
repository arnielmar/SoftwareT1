package Utlit;

import Vinnsla.Hotel;
import Vinnsla.HotelList;
import Vinnsla.ListOfHotels;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Controller fyrir Hotel tab.
 *
 */
public class HotelTabController {
    // viðmótshlutir fyrir niðurstöður
    @FXML
    private Button hotelBackButton;
    @FXML
    private Button hotelBuyButton;
    @FXML
    private Label resultLabel;
    @FXML
    private Label resultHotelLabel;
    @FXML
    private Label resultRoomLabel;
    @FXML
    private ListView hotelListViewOne;
    @FXML
    private ListView hotelListViewTwo;
    // viðmótshlutir fyrir leit
    @FXML
    private Label searchLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label fromLabel;
    @FXML
    private Label toLabel;
    @FXML
    private Label personLabel;
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
    private LeitController parentController;


    private HotelList hotelList;            //hotelList er klasi sem listi af hótelum og er notaður fyrir aðgerðir
    private ListOfHotels listOfHotels;      //listOfHotels er gefinn listi af mock hótelum
    private int virkur1;                    //virkur1 gefur upp hvaða hotel er valið
    private int virkur2;                    //virkur2 gefur upp hvaða herbergi er valið
    private ArrayList<Hotel> results;       //results heldur utan um hótelin úr leitarniðurstöðunni
    private String[] types;                 //types heldur utan um laus herbergi í völdu hóteli

    private String location;                //location heldur utan um staðsetninguna sem leitað var að
    private LocalDate dateFrom;             //dateFrom heldur utan um "frá" dagsetninguna frá leit
    private LocalDate dateTo;               //dateTo heldur utan um "til" dagsetninguna frá leit


    /**
     * Upphafsstillir gluggann fyrir hótelleit.
     * Setur áfangastaði í comboboxin, upphafsstillir spinnerinn,
     * upphafsstillir dagatölin og listana með niðurstöðum.
     *
     */
    @FXML
    public void initialize() {
        hotelList = new HotelList();
        listOfHotels = new ListOfHotels();
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
     * Stillir spinner-inn. Upphafsgildi er 1,
     * min gildi er 1 og max gildi er 250.
     *
     */
    public void setjaSpinner() {
        SpinnerValueFactory.IntegerSpinnerValueFactory intSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1, 1);
        hotelPersons.setValueFactory(intSpin);
    }

    /**
     * Upphafsstillir comboboxin með áfangastöðum.
     *
     */
    public void setjaStadi(){
        ObservableList<String> listDest = FXCollections.observableArrayList("Reykjavík", "París", "London", "Copenhagen");
        hotelCombo.setItems(listDest);
        hotelCombo.getSelectionModel().selectFirst();
    }

    /**
     * Upphafsstillir dagsetningar á Date Pickers á daginn í dag og 28. apríl.
     *
     */
    private void setjaDagsetningar() {
        hotelFromDate.setValue(LocalDate.now());
        dateFrom = hotelToDate.getValue();
        hotelToDate.setValue(LocalDate.of(2020,4,28));
        dateTo = hotelFromDate.getValue();
    }

    /**
     * Bregst við leit af hóteli.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á leitartakka
     */ 
    public void leitaHandler(ActionEvent actionEvent) {
        setjaLista();
        dateFrom = hotelFromDate.getValue();
        dateTo = hotelToDate.getValue();
        location = hotelCombo.getValue();
        int peopleNumber = (int)hotelPersons.getValue();

        results = HotelList.searchHotels(location, dateFrom, dateTo, peopleNumber);
        ObservableList<Hotel> resultsList = FXCollections.observableList(results);

        hotelListViewOne.setItems(resultsList);

        virkur1 = 0;

        virkjaLeit(false);
        virkjaNidurstodur(true);
    }

    /**
     * Setur upp listview listana í niðurstöðuskjánum og
     * change listener á þá til þess að fylgjast með
     * því hvaða hlutur er valinn að hverju sinni.
     *
     */
    private void setjaLista() {
        MultipleSelectionModel<Hotel> lsr = hotelListViewOne.getSelectionModel();
        lsr.selectedItemProperty().addListener(new ChangeListener<Hotel>() {
            @Override
                public void changed(ObservableValue<? extends Hotel> observable, Hotel oldValue, Hotel newValue) {
                virkur1 = lsr.getSelectedIndex();
                ObservableList<Hotel> theHotels = hotelListViewOne.getItems();

                ArrayList<String> theTypes = new ArrayList<String>();
                    for (Hotel h : theHotels) {
                        if (virkur1 >= 0) {
                            if (h.equals(theHotels.get(virkur1))) {
                                types = h.getRoomType();
                                for (String s : h.getRoomType()) {
                                    String toString = new String(s + " fyrir " + h.getRoomTypeNumber(s) + " manns");
                                    theTypes.add(toString);
                                }
                            }
                        }
                    }
                    ObservableList<String> typeList = FXCollections.observableArrayList(theTypes);
                    hotelListViewTwo.setItems(typeList);
            }
        });

            MultipleSelectionModel<String> lsm = hotelListViewTwo.getSelectionModel();
            lsm.selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    virkur2 = lsm.getSelectedIndex();
                }
            });
    }

    /**
     * Gerir viðmótshluti fyrir leit sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     *
     * @param gildi - true ef á að virkja, annars false
     */
    private void virkjaLeit(boolean gildi) {
        searchLabel.setVisible(gildi);
        locationLabel.setVisible(gildi);
        personLabel.setVisible(gildi);
        fromLabel.setVisible(gildi);
        toLabel.setVisible(gildi);
        hotelCombo.setVisible(gildi);
        hotelFromDate.setVisible(gildi);
        hotelToDate.setVisible(gildi);
        hotelPersons.setVisible(gildi);
        hotelSearchButton.setVisible(gildi);
    }

    /**
     * Gerir viðmótshluti fyrir niðurstöður sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     * Sýnir einnig lista fyrir heimkomu flug ef roundWay er true.
     *
     * @param gildi - true ef á að virkja, annars false
     */
    private void virkjaNidurstodur(boolean gildi) {
        hotelListViewOne.setVisible(gildi);
        hotelListViewTwo.setVisible(gildi);
        resultLabel.setVisible(gildi);
        resultHotelLabel.setVisible(gildi);
        resultRoomLabel.setVisible(gildi);
        hotelBackButton.setVisible(gildi);
        hotelBuyButton.setVisible(gildi);
    }

    /**
     * Þegar smellt er á "Til baka" þá lokast niðurstöðuskjárinn,
     * og leitarskjárinn er birtur á ný.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á til baka takka
     */
    public void tilBakaHandler(ActionEvent actionEvent){
        virkjaLeit(true);
        virkjaNidurstodur(false);

        hotelListViewTwo.setItems(null);
        virkur1 = 0;
    }

    /**
     * Þegar smellt er á panta, þá er völdu hóteli og herbergi bætt í pöntuð hótel.
     * pöntuð hótel eru svo sett í listview í orders.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á order takka
     */
    public void pantaHandler(ActionEvent actionEvent) throws IOException {
        Hotel orderedHotel = results.get(virkur1);
        hotelList.addOrderedHotel(orderedHotel, types[virkur2]);
        parentController.setjaHotel(hotelList.getOrderedHotels());
    }

    /**
     * Eyðir öllum hótelum úr lista með pöntuðum hótelum.
     *
     * @return - tómur listi með pöntuðum flugum
     */
    public ObservableList<Hotel> removeAllOrderedHotels() {
        hotelList.removeAllOrderedHotels();
        return hotelList.getOrderedHotels();
    }

    /**
     * Eyðir hótelhlut úr lista með pöntuðum hótelum.
     *
     * @param hotel - Hotel hlutur sem á að eyða
     */
    public void removeOrderedHotel(Hotel hotel) {
        hotelList.removeOrderedHotel(hotel);
    }
}
