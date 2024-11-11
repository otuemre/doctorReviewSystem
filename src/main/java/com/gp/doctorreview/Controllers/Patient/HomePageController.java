package com.gp.doctorreview.Controllers.Patient;

import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.PatientHeaderOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public Button home_page_btn;
    public Button contact_us_btn;
    public Button doctors_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    public void addListeners() {
        home_page_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().getPatientSelectedHeaderItem().set(PatientHeaderOptions.HOME_PAGE));
        contact_us_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().getPatientSelectedHeaderItem().set(PatientHeaderOptions.CONTACT_US_PAGE));
        doctors_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().getPatientSelectedHeaderItem().set(PatientHeaderOptions.DOCTORS_PAGE));
    }
}
