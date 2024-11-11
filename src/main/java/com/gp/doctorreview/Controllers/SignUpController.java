package com.gp.doctorreview.Controllers;

import com.gp.doctorreview.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public Label signin_lbl;
    public TextField name_field;
    public TextField email_field;
    public PasswordField password_field;
    public DatePicker date_field;
    public Button create_an_account_btn;
    public Button logout_btn;
    public Label error_msg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        signin_lbl.setOnMouseClicked(mouseEvent -> onSignIn());
        create_an_account_btn.setOnAction(actionEvent -> onCreateAnAccount());

        logout_btn.setOnAction(actionEvent -> onLogout());
    }

    private void onSignIn() {
        Stage stage = (Stage) error_msg.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginPage();
    }

    private void onLogout() {
        Stage stage = (Stage) error_msg.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    private void onCreateAnAccount() {
        String validationError = validateFields();
        if (!validationError.isEmpty()) {
            error_msg.setText(validationError);
            return;
        }
        Model.getInstance().getDatabaseDriver().createNewUser(name_field.getText(), email_field.getText(), password_field.getText(), date_field.getValue());
        emptyFields();
        // Show Success Message
        showSuccessMessage();
    }

    private String validateFields() {
        if (name_field.getText().isEmpty()) {
            return "Name Field Cannot Be Empty!";
        }
        if (email_field.getText().isEmpty()) {
            return "Email Field Cannot Be Empty!";
        }
        if (password_field.getText().isEmpty()) {
            return "Password Field Cannot Be Empty!";
        }
        if (password_field.getText().length() < 8) {
            return "Password Cannot Be Less Than 8 Digits!";
        }
        if (date_field.getValue() == null) {
            return "Date of Birth Field Cannot Be Empty!";
        }

        LocalDate dob = date_field.getValue();
        LocalDate now = LocalDate.now();
        Period age = Period.between(dob, now);
        if (age.getYears() < 18 || (age.getYears() == 18 && (age.getMonths() > 0 || age.getDays() > 0))) {
            return "You must be at least 18 years old!";
        }

        if (isUserExist()) {
            return "User is Already Exist! Please, Try to Sign In.";
        }
        return ""; // No validation errors
    }

    private boolean isUserExist() {
        return Model.getInstance().isUserExist(email_field.getText());
    }

    private void emptyFields() {
        name_field.setText("");
        email_field.setText("");
        password_field.setText("");
        date_field.setValue(null);
        error_msg.setText("");
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account Created Successfully");
        alert.setHeaderText(null);
        alert.setContentText("Your account has been created successfully!");

        alert.showAndWait();
    }

}
