package com.gp.doctorreview.Controllers.Admin;

import com.gp.doctorreview.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedHeaderItem()
                .addListener((observableValue, oldVal, newVal) -> {
                    switch (newVal) {
                        case MESSAGES_PAGE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminMessagesPage());
                        case DOCTORS_PAGE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminDoctorPage());
                        case DOCTORS_DETAILS_PAGE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminDoctorDetailsPage());
                        case PROFILE_PAGE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminProfilePage());
                        case MANAGE_USERS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getManageUsersPage());
                        case MANAGE_DOCTORS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getManageDoctorsPage());
                        default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminHomePage());
                    }
                });
    }
}
