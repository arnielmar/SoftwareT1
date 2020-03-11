package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
}
