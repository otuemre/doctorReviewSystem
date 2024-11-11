package com.gp.doctorreview.Controllers.Admin;

import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.AdminHeaderOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public Label name_lbl;
    public Label email_lbl;
    public Label date_lbl;
    public Label address_line_lbl;
    public Label post_code_lbl;
    public Button manage_users_btn;
    public Button manage_doctors_btn;
    public TextField new_password_fld;
    public TextField confirm_password_fld;
    public Button change_password_btn;
    public Label error_msg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_lbl.textProperty().bind(Model.getInstance().getUser().nameProperty());
        email_lbl.textProperty().bind(Model.getInstance().getUser().emailProperty());
        date_lbl.textProperty().bind(Model.getInstance().getUser().dateOfBirthProperty().asString());
        address_line_lbl.textProperty().bind(Model.getInstance().getUser().addressLineProperty());
        post_code_lbl.textProperty().bind(Model.getInstance().getUser().postcodeProperty());

        manage_users_btn.setOnAction(actionEvent -> onManageUsers());
        manage_doctors_btn.setOnAction(actionEvent -> onManageDoctors());
        change_password_btn.setOnAction(actionEvent -> onChangePassword());
    }

    private void onManageUsers() {
        Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.MANAGE_USERS);
    }
    private void onManageDoctors() {
        Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.MANAGE_DOCTORS);
    }

    private void onChangePassword() {
        error_msg.setStyle("-fx-text-fill: #CC0000");
        if (new_password_fld.getText().isEmpty()) {
            error_msg.setText("New Password cannot be empty1");
        } else if (confirm_password_fld.getText().isEmpty()) {
            error_msg.setText("Confirmation Password cannot be empty!");
        } else if (!Objects.equals(new_password_fld.getText(), confirm_password_fld.getText())) {
            error_msg.setText("Passwords do not match!");
        } else {
            Model.getInstance().getDatabaseDriver().changeUserPassword(Model.getInstance().getUser().emailProperty().get(), confirm_password_fld.getText());
            error_msg.setText("You have successfully changed you password!");
            error_msg.setStyle("-fx-text-fill: green");
        }
    }
}
