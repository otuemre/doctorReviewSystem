package com.gp.doctorreview.Controllers.General;

import com.gp.doctorreview.Models.Doctor;
import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.AdminHeaderOptions;
import com.gp.doctorreview.Views.PatientHeaderOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorCellController implements Initializable {
    public Label name_lbl;
    public Label spec_lbl;
    public Label review_point_lbl;
    public Label total_view_lbl;
    public Button see_detail_btn;

    private final Doctor doctor;

    public DoctorCellController(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_lbl.textProperty().bind(doctor.nameProperty());
        spec_lbl.textProperty().bind(doctor.specializationProperty());
        review_point_lbl.textProperty().bind(doctor.reviewPointProperty().asString());
        total_view_lbl.textProperty().bind(doctor.totalViewerProperty().asString());

        see_detail_btn.setOnAction(actionEvent -> {
            Model.getInstance().getSelectedDoctor().IDProperty().set(doctor.IDProperty().get());
            Model.getInstance().getSelectedDoctor().nameProperty().set(doctor.nameProperty().get());
            Model.getInstance().getSelectedDoctor().specializationProperty().set(doctor.specializationProperty().get());
            Model.getInstance().getSelectedDoctor().reviewPointProperty().set(doctor.reviewPointProperty().get());
            Model.getInstance().getSelectedDoctor().totalViewerProperty().set(doctor.totalViewerProperty().get());

            if (Model.getInstance().getUser().roleProperty().get().equals("PATIENT")){
                Model.getInstance().getViewFactory().getPatientSelectedHeaderItem().set(PatientHeaderOptions.DOCTOR_DETAILS_PAGE);
            } else {
                Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.DOCTORS_DETAILS_PAGE);
            }

        });

    }
}
