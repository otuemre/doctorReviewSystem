package com.gp.doctorreview.Controllers.Admin;

import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.AdminHeaderOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public Button home_page_btn;
    public Button messages_btn;
    public Button doctors_btn;
    public Button profile_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    public void addListeners() {
        home_page_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.HOME_PAGE));
        messages_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.MESSAGES_PAGE));
        doctors_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.DOCTORS_PAGE));
        profile_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.PROFILE_PAGE));
    }
}
