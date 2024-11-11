package com.gp.doctorreview.Controllers;

import com.gp.doctorreview.Models.Model;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label create_account_lbl;
    public TextField email_field;
    public PasswordField password_field;
    public Button signin_btn;
    public Button logout_btn;
    public Label error_msg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signin_btn.setOnAction(actionEvent -> onLogin());
        logout_btn.setOnAction(actionEvent -> Platform.exit());
        create_account_lbl.setOnMouseClicked(mouseEvent -> onCreateAccount());
    }

    private void onLogin() {

        if (email_field.getText().isEmpty()) {
            error_msg.setText("Email Field Cannot Be Empty!");
        } else if (password_field.getText().isEmpty()) {
            error_msg.setText("Password Field Cannot Be Empty!");
        } else {
            checkDetails();
        }

    }

    private void onCreateAccount() {
        Stage stage = (Stage) error_msg.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignUpPage();
    }

    private void checkDetails() {
        Stage stage = (Stage) error_msg.getScene().getWindow();
        Model.getInstance().evaluateUserCredentials(email_field.getText(), password_field.getText());

        if (Model.getInstance().isUserLoginSuccessFlag()) {

            emptyFields();
            showSuccessMessage();

            if (Objects.equals(Model.getInstance().getUser().roleProperty().get(), "PATIENT")) {
                Model.getInstance().getViewFactory().showClientHomePage();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                Model.getInstance().getViewFactory().showAdminHomePage();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        } else {
            error_msg.setText("User is NOT Found! Please, Check Your Login Credentials.");
        }
    }

    private void emptyFields() {
        email_field.setText("");
        password_field.setText("");
        error_msg.setText("");
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Success");
        alert.setHeaderText(null);
        alert.setContentText("You have logged in successfully!");

        alert.showAndWait();
    }
}
