package com.gp.doctorreview.Controllers.Patient;

import com.gp.doctorreview.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    public BorderPane patient_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getPatientSelectedHeaderItem()
                .addListener((observableValue, oldVal, newVal) -> {
                    switch (newVal) {
                        case CONTACT_US_PAGE -> patient_parent.setCenter(Model.getInstance().getViewFactory().getContactUsPage());
                        case DOCTORS_PAGE -> patient_parent.setCenter(Model.getInstance().getViewFactory().getDoctorsPage());
                        case DOCTOR_DETAILS_PAGE -> patient_parent.setCenter(Model.getInstance().getViewFactory().getDoctorDetailPage());
                        default -> patient_parent.setCenter(Model.getInstance().getViewFactory().getHomePage());
                    }
                });
    }
}
