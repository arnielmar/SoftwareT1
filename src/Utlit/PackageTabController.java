package Utlit;

import Vinnsla.Flight;
import Vinnsla.FlightList;
import Vinnsla.ListOfFlights;
import Vinnsla.Package;
import Vinnsla.PackageList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

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

    @FXML
    //public void initialize(){
     //   ObservableList<String> listDest = FXCollections.observableArrayList("Reykjavík","Danmörk","Akureyri");
     //   packageFromCombo.setItems(listDest);
     //   packageFromCombo.getSelectionModel().selectFirst();
     //   packageToCombo.setItems(listDest);
     //   packageToCombo.getSelectionModel().selectFirst();

//        SpinnerValueFactory.IntegerSpinnerValueFactory intSpin;
//        intSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1, 1);
//        packagePersons.setValueFactory(intSpin);
//        packageDepartureDate.setValue(LocalDate.now());
//        packageReturningDate.setValue(LocalDate.now());
//    }
    public void initialize() {
        packagelist = new PackageList();
        listOfFlights = new ListOfFlights();
        setjaStadi();
        setjaSpinner();
        setjaDagsetningar();
        setjaLista();
        virkjaNidurstodur(false);
    }

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
                    theTypes.add(""+ p.getFlightTo());
                    theTypes.add(""+ p.getFlightFrom());
                    theTypes.add(""+ p.getHotel());
                    theTypes.add(""+ p.getDayTrip());
                    ObservableList<String> typeList = FXCollections.observableArrayList(theTypes);
                    packageListView2.setItems(typeList);
                }
            }
        });
    }

    @FXML
    private void leitHandler(javafx.event.ActionEvent actionEvent){
        virkjaLeit(false);
        noOfPeople = (int) packagePersons.getValue();
        packagelist.searchPackages(depart,destination,dateFrom,dateTo,noOfPeople);
        packageListView.setItems(packagelist.getAllPackages());
        setjaLista();
        virkurIndex = 0;
        virkjaNidurstodur(true);
    }

    @FXML
    private void tilBakaHandler(javafx.event.ActionEvent actionEvent) {
        packageListView2.getItems().clear();
        virkjaNidurstodur(false);
        virkjaLeit(true);
    }

    @FXML
    private void packageFromComboHandler(javafx.event.ActionEvent actionEvent) {
        ComboBox cb = (ComboBox) actionEvent.getSource();
        depart = (String)cb.getSelectionModel().getSelectedItem();
        System.out.println(depart);
    }

    @FXML
    private void packageToComboHandler(javafx.event.ActionEvent actionEvent) {
        ComboBox cb = (ComboBox)actionEvent.getSource();
        destination = (String)cb.getSelectionModel().getSelectedItem();
        System.out.println(destination);
    }

    @FXML
    private void packageDepartureDateHandler(javafx.event.ActionEvent actionEvent) {
        dateFrom = packageDepartureDate.getValue();
        System.out.println(dateFrom);
    }

    @FXML
    private void packageReturningDateHandler(javafx.event.ActionEvent actionEvent) {
        dateTo = packageReturningDate.getValue();
        System.out.println(dateTo);
    }
    @FXML
    private void packageOrderHandler(javafx.event.ActionEvent actionEvent){
        Package orderedPackage = packagelist.getPackage(virkurIndex);
        packagelist.addOrderedPackage(orderedPackage);
        parentController.setjaPackage(packagelist.getOrderedPackages());
    }

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

    private void virkjaNidurstodur(boolean gildi) {
        packageListView.setVisible(gildi);
        packageListView2.setVisible(gildi);
        resultsLabel.setVisible(gildi);
        nPakka.setVisible(gildi);
        InnihaldPakka.setVisible(gildi);
        packageBackButton.setVisible(gildi);
        packageOrderButton.setVisible(gildi);
    }


    public ObservableList<Package> removeAllOrderedPackages() {
        packagelist.removeAllOrderedPackages();
        return packagelist.getOrderedPackages();
    }
}


