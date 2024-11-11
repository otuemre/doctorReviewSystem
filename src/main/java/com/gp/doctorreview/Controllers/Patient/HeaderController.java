package com.gp.doctorreview.Controllers.Patient;

import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.PatientHeaderOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController  implements Initializable {
    public Label home_btn;
    public Label contact_us_btn;
    public Label doctors_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        home_btn.setOnMouseClicked(mouseEvent -> onHomeBtn());
        contact_us_btn.setOnMouseClicked(mouseEvent -> onContactUsBtn());
        doctors_btn.setOnMouseClicked(mouseEvent -> onDoctorsBtn());

        logout_btn.setOnAction(actionEvent -> onLogout());
    }

    private void onHomeBtn() {
        Model.getInstance().getViewFactory().getPatientSelectedHeaderItem().set(PatientHeaderOptions.HOME_PAGE);
    }

    private void onContactUsBtn() {
        Model.getInstance().getViewFactory().getPatientSelectedHeaderItem().set(PatientHeaderOptions.CONTACT_US_PAGE);
    }

    private void onDoctorsBtn() {
        Model.getInstance().getViewFactory().getPatientSelectedHeaderItem().set(PatientHeaderOptions.DOCTORS_PAGE);
    }

    private void onLogout() {
        Stage stage = (Stage) home_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginPage();
    }
}
