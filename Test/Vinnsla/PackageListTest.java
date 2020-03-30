package Vinnsla;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PackageListTest {
    PackageList packageList;

    @BeforeEach
    void setUp() {
        packageList = new PackageList();
    }

    @AfterEach
    void tearDown() {
        packageList = null;
    }

    @Test
    void deletePackage() {
        int sizeBefore = packageList.getAllPackages().size();
        packageList.deletePackage(0);
        int sizeAfter = packageList.getAllPackages().size();
        assertEquals(sizeAfter, sizeBefore-1);
    }

    @Test
    void addPackage() {
        Package pakki = new Package(null, null, null, null);
        int sizeBefore = packageList.getAllPackages().size();
        packageList.addPackage(pakki);
        int sizeAfter = packageList.getAllPackages().size();
        assertEquals(sizeAfter, sizeBefore+1);
    }

    @Test
    void searchPackagesWorks() {
        packageList.searchPackages("Reykjavík", "Akureyri", LocalDate.now(), LocalDate.of(2020, 4, 15), 2);
        assertTrue(packageList.getAllPackages().size() <= 5);
    }

    @Test
    void searchPackagesLegalArguments() throws IllegalArgumentException {
        packageList.searchPackages("Reykjavík", "Akureyri", LocalDate.now(), LocalDate.of(2020, 4, 15), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    void searchPackagesIllegalArguments() {
        packageList.searchPackages(5, "Akureyri", LocalDate.now(), LocalDate.of(2020, 4, 15), 2);
    }
}