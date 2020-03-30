package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class PackageList {
    private ObservableList<Package> packages;

    /**
     * Smíðar nýjan Observable List af Package hlutum.
     */
    public PackageList() {
        this.packages = FXCollections.observableArrayList();
    }

    /**
     * Skilar pakka sem er númer index á lista.
     *
     * @param index - nr. pakka
     * @return Package - pakki nr. index í lista
     */
    public Package getPackage(int index) {
        return (Package)packages.get(index);
    }

    /**
     * Skilar öllum lista af pökkum.
     *
     * @return - lista af Package hlutum
     */
    public ObservableList<Package> getAllPackages() {
        return packages;
    }

    /**
     * Eyðir pakka nr. index úr lista.
     *
     * @param index - nr. pakka
     */
    public void deletePackage(int index) {
        packages.remove(index);
    }

    /**
     * Bætir pakka package við á lista.
     *
     * @param pack - Package hlutur sem bæta á við í lista
     */
    public void addPackage(Package pack) {
        packages.add(pack);
    }

    /**
     * Leitar að pökkum á staðsetningu dest frá staðsetningu depart
     * frá dagsetningu from til dagsetningu to fyrir noOfPeople manns.
     * Skilar ArrayList<Package>.
     *
     * @param depart - Brottfararstaður
     * @param dest - Áfangastaður
     * @param from - Dagsetning komu
     * @param to - Dagsetning brottfararar
     * @param noOfPeople - Fjöldi manns
     */
    public void searchPackages(String depart, String dest, LocalDate from, LocalDate to, int noOfPeople) {
        //TODO
    }
}
