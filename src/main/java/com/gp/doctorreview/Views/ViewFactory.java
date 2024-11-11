package com.gp.doctorreview.Views;

import com.gp.doctorreview.Controllers.Admin.AdminController;
import com.gp.doctorreview.Controllers.Patient.PatientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewFactory {
    // Patient Settings
    private final ObjectProperty<PatientHeaderOptions> patientSelectedHeaderItem;
    private BorderPane homePage;
    private AnchorPane contactUsPage;
    private AnchorPane doctorsPage;
    private AnchorPane doctorDetailPage;

    // Admin Settings
    private final ObjectProperty<AdminHeaderOptions> adminSelectedHeaderItem;
    private BorderPane adminHomePage;
    private AnchorPane adminMessagesPage;
    private AnchorPane adminDoctorPage;
    private AnchorPane adminDoctorDetailsPage;
    private AnchorPane adminProfilePage;
    private AnchorPane manageUsersPage;
    private AnchorPane manageDoctorsPage;

    public ViewFactory(){
        this.patientSelectedHeaderItem = new SimpleObjectProperty<>();
        this.adminSelectedHeaderItem = new SimpleObjectProperty<>();
    }

    /*
     * User Pages
     */

    public ObjectProperty<PatientHeaderOptions> getPatientSelectedHeaderItem(){
        return patientSelectedHeaderItem;
    }
    public ObjectProperty<AdminHeaderOptions> getAdminSelectedHeaderItem() { return adminSelectedHeaderItem; }

    public BorderPane getHomePage() {
        if (homePage == null) {
            try {
                homePage = new FXMLLoader(getClass().getResource("/Fxml/Patient/HomePage.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return homePage;
    }

    public AnchorPane getContactUsPage() {
        if (contactUsPage == null) {
            try {
                contactUsPage = new FXMLLoader(getClass().getResource("/Fxml/Patient/ContactUs.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return contactUsPage;
    }

    public AnchorPane getDoctorsPage() {
        if (doctorsPage == null) {
            try {
                doctorsPage = new FXMLLoader(getClass().getResource("/Fxml/Patient/Doctors.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return doctorsPage;
    }

    public AnchorPane getDoctorDetailPage() {
        if (doctorDetailPage == null) {
            try {
                doctorDetailPage = new FXMLLoader(getClass().getResource("/Fxml/Patient/DoctorsDetail.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return doctorDetailPage;
    }

    /*
     * Admin Pages
     */
    public BorderPane getAdminHomePage() {
        if (adminHomePage == null) {
            try {
                adminHomePage = new FXMLLoader(getClass().getResource("/Fxml/Admin/HomePage.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return adminHomePage;
    }

    public AnchorPane getAdminDoctorPage() {
        if (adminDoctorPage == null) {
            try {
                adminDoctorPage = new FXMLLoader(getClass().getResource("/Fxml/Admin/Doctors.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return adminDoctorPage;
    }

    public AnchorPane getAdminMessagesPage() {
        if (adminMessagesPage == null) {
            try {
                adminMessagesPage = new FXMLLoader(getClass().getResource("/Fxml/Admin/Messages.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return adminMessagesPage;
    }

    public AnchorPane getAdminDoctorDetailsPage() {
        if (adminDoctorDetailsPage == null) {
            try {
                adminDoctorDetailsPage = new FXMLLoader(getClass().getResource("/Fxml/Admin/DoctorDetails.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return adminDoctorDetailsPage;
    }

    public AnchorPane getAdminProfilePage() {
        if (adminProfilePage == null) {
            try {
                adminProfilePage = new FXMLLoader(getClass().getResource("/Fxml/Admin/Profile.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return adminProfilePage;
    }

    public AnchorPane getManageUsersPage() {
        if (manageUsersPage == null) {
            try {
                manageUsersPage = new FXMLLoader(getClass().getResource("/Fxml/Admin/ManageUsers.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return manageUsersPage;
    }

    public AnchorPane getManageDoctorsPage() {
        if (manageDoctorsPage == null) {
            try {
                manageDoctorsPage = new FXMLLoader(getClass().getResource("/Fxml/Admin/ManageDoctors.fxml")).load();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return manageDoctorsPage;
    }


    public void showLoginPage () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public void showSignUpPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/SignUp.fxml"));
        createStage(loader);
    }

    public void showClientHomePage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Patient/Patient.fxml"));
        PatientController controller = new PatientController();
        loader.setController(controller);

        createStage(loader);
    }

    public void showAdminHomePage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);

        createStage(loader);
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("Doctor Review");
        stage.setScene(scene);
        stage.show();
    }

}
