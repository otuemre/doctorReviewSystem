module DoctorReview {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.gp.doctorreview to javafx.fxml;
    exports com.gp.doctorreview;
    exports com.gp.doctorreview.Controllers;
    exports com.gp.doctorreview.Controllers.Admin;
    exports com.gp.doctorreview.Controllers.Patient;
    exports com.gp.doctorreview.Controllers.General;
    exports com.gp.doctorreview.Models;
    exports com.gp.doctorreview.Views;
}