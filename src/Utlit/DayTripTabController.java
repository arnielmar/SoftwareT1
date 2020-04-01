package Utlit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

public class DayTripTabController {
    @FXML
    private Label dayTripPlaceLabel;
    @FXML
    private Label dayTripHeader;
    @FXML
    private Label dayTripPerLabel;
    @FXML
    private ComboBox dayTripCombo;
    @FXML
    private DatePicker dayTripDate;
    @FXML
    private Button dayTripSearchButton;
    @FXML
    private Spinner dayTripPersons;


public void leitHandler(ActionEvent actionevent)
{
    if (dayTripSearchButton.getText().equals("Search"))
    {
        dayTripSearchButton.setText("Back");
        dayTripHeader.setText("Choose Day Trip");

        dayTripPlaceLabel.setVisible(false);
        dayTripPerLabel.setVisible(false);
        dayTripCombo.setVisible(false);
        dayTripDate.setVisible(false);
        dayTripPersons.setVisible(false);

    } else
    {
        dayTripSearchButton.setText("Search");
        dayTripHeader.setText("Search for Day Trips");

        dayTripPlaceLabel.setVisible(true);
        dayTripPerLabel.setVisible(true);
        dayTripCombo.setVisible(true);
        dayTripDate.setVisible(true);
        dayTripPersons.setVisible(true);
    }
}
}
