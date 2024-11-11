package com.gp.doctorreview.Controllers.Admin;

import com.gp.doctorreview.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {
    public TextField name_fld;
    public TextField role_fld;
    public TextField email_fld;
    public TextField password_fld;
    public TextField dob_fld;
    public TextField address_line_fld;
    public TextField postcode_fld;
    public Button add_user_btn;
    public Button delete_user_btn;
    public Label err_msg;
    public TextField del_email_address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_user_btn.setOnAction(actionEvent -> onAddUser());
        delete_user_btn.setOnAction(actionEvent -> onDeleteUser());
    }

    private void onAddUser() {
        if (name_fld.getText().isEmpty()) {
            err_msg.setText("Please, type name!");
        } else if (role_fld.getText().isEmpty()) {
            err_msg.setText("Please, type the role. Eg. PATIENT or ADMIN");
        } else if (email_fld.getText().isEmpty()) {
            err_msg.setText("Please, type the email!");
        } else if (password_fld.getText().isEmpty()) {
            err_msg.setText("Please, type password!");
        } else if (dob_fld.getText().isEmpty()) {
            err_msg.setText("Please, type Date of Birth!");
        } else {
            String[] dateParts = dob_fld.getText().split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            LocalDate date = LocalDate.of(year, month, day);
            Model.getInstance().getDatabaseDriver().createNewUser(name_fld.getText(), role_fld.getText(), email_fld.getText(), password_fld.getText(), date, address_line_fld.getText(), postcode_fld.getText());
            err_msg.setStyle("-fx-text-fill: #2B7A78");
            err_msg.setText("You have successfully added a new user!");
        }
    }

    private void onDeleteUser() {
        if (del_email_address.getText().isEmpty()) {
            err_msg.setStyle("-fx-text-fill: #CC0000");
            err_msg.setText("Please, type email address!");
        } else {
            Model.getInstance().getDatabaseDriver().deleteUserByEmail(Integer.parseInt(del_email_address.getText()));
            err_msg.setText("You have successfully deleted the user!");
            err_msg.setStyle("-fx-text-fill: #2B7A78");
        }
    }
}
