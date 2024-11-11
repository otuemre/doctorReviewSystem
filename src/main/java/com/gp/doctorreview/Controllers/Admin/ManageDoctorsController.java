package com.gp.doctorreview.Controllers.Admin;

import com.gp.doctorreview.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageDoctorsController implements Initializable {
    public TextField id_fld;
    public TextField name_fld;
    public TextField spec_fld;
    public Button add_doctor_btn;
    public Button delete_doctor_btn;
    public Label err_msg;
    public TextField review_fld;
    public TextField total_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_doctor_btn.setOnAction(actionEvent -> onAddDoctor());
        delete_doctor_btn.setOnAction(actionEvent -> onDeleteDoctor());
    }

    private void onDeleteDoctor() {
        err_msg.setStyle("-fx-text-fill: #CC0000");
        if (id_fld.getText().isEmpty()) {
            err_msg.setText("ID field cannot be empty!");
        } else {
            Model.getInstance().getDatabaseDriver().deleteDoctor(Integer.parseInt(id_fld.getText()));
            err_msg.setStyle("-fx-text-fill: #2B7A78");
            err_msg.setText("You have successfully delete the doctor!");
        }
    }

    private void onAddDoctor() {
        err_msg.setStyle("-fx-text-fill: #CC0000");
        if (name_fld.getText().isEmpty()) {
            err_msg.setText("Name cannot be empty!");
        } else if (spec_fld.getText().isEmpty()) {
            err_msg.setText("Specialization cannot be empty1");
        } else if (review_fld.getText().isEmpty()) {
            err_msg.setText("Review Point cannot be empty!");
        } else if (total_fld.getText().isEmpty()) {
            err_msg.setText("Total View cannot be empty!");
        } else {
            Model.getInstance().getDatabaseDriver().addDoctor(name_fld.getText(), spec_fld.getText(), Integer.parseInt(review_fld.getText()), Integer.parseInt(total_fld.getText()));
            err_msg.setStyle("-fx-text-fill: #2B7A78");
            err_msg.setText("You have successfully created a new doctor!");
        }
    }
}
