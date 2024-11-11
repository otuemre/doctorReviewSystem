package com.gp.doctorreview.Controllers.Admin;

import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.AdminHeaderOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {
    public Label home_btn;
    public Label messages_btn;
    public Label doctors_btn;
    public Label profile_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        home_btn.setOnMouseClicked(mouseEvent -> onHomePage());
        messages_btn.setOnMouseClicked(mouseEvent -> onMessagesPage());
        doctors_btn.setOnMouseClicked(mouseEvent -> onDoctorsPage());
        profile_btn.setOnMouseClicked(mouseEvent -> onProfilePage());

        logout_btn.setOnAction(actionEvent -> onLogout());
    }

    private void onHomePage() {
        Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.HOME_PAGE);
    }

    private void onMessagesPage() {
        Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.MESSAGES_PAGE);
    }

    private void onDoctorsPage() {
        Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.DOCTORS_PAGE);
    }

    private void onProfilePage() {
        Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.PROFILE_PAGE);
    }

    private void onLogout() {
        Stage stage = (Stage) home_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginPage();

        Model.getInstance().setUserLoginSuccessFlag(false);
    }
}
