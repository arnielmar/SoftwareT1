package Utlit;

import Vinnsla.ListOfFlights;
import Vinnsla.Package;
import Vinnsla.PackageList;
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
 * Controller fyrir Package tab.
 *
 */
public class PackageTabController {
    //Viðmóts hlutirnir
    @FXML
    private Button packageBackButton;
    @FXML
    private Button packageOrderButton;
    @FXML
    private ListView packageListView;
    @FXML
    private ListView packageListView2;
    @FXML
    private Label resultsLabel;
    @FXML
    private Label nPakka;
    @FXML
    private Label InnihaldPakka;
    //Leitar hlutirnir
    @FXML
    private ComboBox<String> packageFromCombo;
    @FXML
    private ComboBox<String> packageToCombo;
    @FXML
    private DatePicker packageDepartureDate;
    @FXML
    private DatePicker packageReturningDate;
    @FXML
    private Button packageSearchButton;
    @FXML
    private Spinner packagePersons;
    @FXML
    private Label fromLabel;
    @FXML
    private Label toLabel;
    @FXML
    private Label searchLabel;
    @FXML
    private Label personsLabel;

    private PackageList packagelist;
    private ListOfFlights listOfFlights;
    private LeitController parentController;

    private String depart;                  // brottfararstaður
    private String destination;             // áfangastaður
    private LocalDate dateFrom;             // dagsetning frá
    private LocalDate dateTo;               // dagsetning til
    private int noOfPeople;                 // fjöldi manns
    private int virkurIndex = 0;

    /**
     * Upphafsstillir gluggann fyrir pakkaleit.
     * Setur áfangastaði í comboboxin, upphafsstillir spinnerinn,
     * upphafsstillir dagatölin og listana með niðurstöðum.
     *
     */
    public void initialize() {
        packagelist = new PackageList();
        listOfFlights = new ListOfFlights();
        setjaStadi();
        setjaSpinner();
        setjaDagsetningar();
        setjaLista();
        virkjaNidurstodur(false);
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
        packageFromCombo.setItems(stadir);   // setja lista í combobox
        packageFromCombo.getSelectionModel().select(0);  // setja fyrsta stak sem upphafsgildi
        depart = packageFromCombo.getSelectionModel().getSelectedItem();
        packageToCombo.setItems(stadir);
        packageToCombo.getSelectionModel().select(1);
        destination = packageToCombo.getSelectionModel().getSelectedItem();
    }

    /**
     * Stillir spinner-inn. Upphafsgildi er 1,
     * min gildi er 1 og max gildi er 250.
     *
     */
    private void setjaSpinner() {
        SpinnerValueFactory.IntegerSpinnerValueFactory intSpin;
        intSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1, 1);
        packagePersons.setValueFactory(intSpin);
    }

    /**
     * Upphafsstillir dagsetningar á Date Pickers á daginn í dag.
     *
     */
    private void setjaDagsetningar() {
        packageDepartureDate.setValue(LocalDate.now());
        dateFrom = packageDepartureDate.getValue();
        packageReturningDate.setValue(LocalDate.now());
        dateTo = packageReturningDate.getValue();
    }

    /**
     * Upphafsstillir listana fyrir niðurstöður og setur
     * change-listener á þá til að halda utan um hvaða stak
     * í listunum er valið.
     *
     */
    private void setjaLista() {
        ObservableList<Package> listi = packagelist.getAllPackages();
        packageListView.setItems(listi);
        MultipleSelectionModel<Package> lsr = packageListView.getSelectionModel();
        lsr.selectedItemProperty().addListener(new ChangeListener<Package>() {
            @Override
            public void changed(ObservableValue<? extends Package> observable, Package oldValue, Package newValue) {
                virkurIndex = lsr.getSelectedIndex();

                System.out.println("virkurIndexOne = " + virkurIndex);

                if (virkurIndex>=0){
                    ArrayList<String> theTypes = new ArrayList<String>();
                    Package p = (Package) packageListView.getItems().get(virkurIndex);
                    theTypes.add(""+ p.getFlightFrom());
                    theTypes.add(""+ p.getFlightTo());
                    theTypes.add(""+ p.getHotel());
                    theTypes.add(""+ p.getDayTrip());
                    ObservableList<String> typeList = FXCollections.observableArrayList(theTypes);
                    packageListView2.setItems(typeList);
                }
            }
        });
    }

    /**
     * Bregst við því þegar leitað er að flugi.
     * Nær í fjölda manns úr spinnernum og kallar á leitaraðferð
     * í PackageList sem leitar að flugum, hótelum og day trips.
     * Afvirkjar leitarglugga og virkjar glugga fyrir niðurstöður og
     * setur svo niðurstöður í ListView.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á leitartakka
     */
    @FXML
    private void leitHandler(ActionEvent actionEvent){
        virkjaLeit(false);
        noOfPeople = (int) packagePersons.getValue();
        packagelist.searchPackages(depart,destination,dateFrom,dateTo,noOfPeople);
        packageListView.setItems(packagelist.getAllPackages());
        setjaLista();
        virkurIndex = 0;
        virkjaNidurstodur(true);
    }

    /**
     * Felur niðurstöður og sýnir leitarglugga aftur.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á til baka takka
     */
    @FXML
    private void tilBakaHandler(ActionEvent actionEvent) {
        packageListView2.getItems().clear();
        virkjaNidurstodur(false);
        virkjaLeit(true);
    }

    /**
     * Atburðahandler fyrir package from combobox.
     * Finnur hvaða staður er valinn í comboboxi.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja stak í comboboxi
     */
    @FXML
    private void packageFromComboHandler(ActionEvent actionEvent) {
        ComboBox cb = (ComboBox) actionEvent.getSource();
        depart = (String)cb.getSelectionModel().getSelectedItem();
        System.out.println(depart);
    }

    /**
     * Atburðahandler fyrir package to combobox.
     * Finnur hvaða staður er valinn í comboboxi.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja stak í comboboxi
     */
    @FXML
    private void packageToComboHandler(ActionEvent actionEvent) {
        ComboBox cb = (ComboBox)actionEvent.getSource();
        destination = (String)cb.getSelectionModel().getSelectedItem();
        System.out.println(destination);
    }

    /**
     * Atburðahandler fyrir dagsetningu brottfarar.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja dagsetningu
     */
    @FXML
    private void packageDepartureDateHandler(ActionEvent actionEvent) {
        dateFrom = packageDepartureDate.getValue();
        System.out.println(dateFrom);
    }

    /**
     * Atburðahandler fyrir dagsetningu heimkomu.
     *
     * @param actionEvent - atburðurinn sem varð til við að velja dagsetningu
     */
    @FXML
    private void packageReturningDateHandler(ActionEvent actionEvent) {
        dateTo = packageReturningDate.getValue();
        System.out.println(dateTo);
    }

    /**
     * Pantar pakka.
     *
     * @param actionEvent - atburðurinn sem varð til við að ýta á order
     */
    @FXML
    private void packageOrderHandler(ActionEvent actionEvent){
        Package orderedPackage = packagelist.getPackage(virkurIndex);
        packagelist.addOrderedPackage(orderedPackage);
        parentController.setjaPackage(packagelist.getOrderedPackages());
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
        packageFromCombo.setVisible(gildi);
        packageToCombo.setVisible(gildi);
        packageDepartureDate.setVisible(gildi);
        packageReturningDate.setVisible(gildi);
        packagePersons.setVisible(gildi);
        packageSearchButton.setVisible(gildi);
    }

    /**
     * Gerir viðmótshluti fyrir niðurstöður sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     * Sýnir einnig lista fyrir heimkomu flug ef roundWay er true.
     *
     * @param gildi - true ef á að virkja, annars false
     */
    private void virkjaNidurstodur(boolean gildi) {
        packageListView.setVisible(gildi);
        packageListView2.setVisible(gildi);
        resultsLabel.setVisible(gildi);
        nPakka.setVisible(gildi);
        InnihaldPakka.setVisible(gildi);
        packageBackButton.setVisible(gildi);
        packageOrderButton.setVisible(gildi);
    }

    /**
     * Eyðir öllum pökkum úr lista með pöntuðum pökkum.
     *
     * @return - tómur listi með pöntuðum pökkum
     */
    public ObservableList<Package> removeAllOrderedPackages() {
        packagelist.removeAllOrderedPackages();
        return packagelist.getOrderedPackages();
    }

    /**
     * Eyðir pakkahlut úr lista sem geymir pantaða pakka.
     *
     * @param packages - Package hlutur sem er eytt
     */
    public void removeOrderedPackage(Package packages) {
        packagelist.removeOrderedPackage(packages);
    }
}


