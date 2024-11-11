package com.gp.doctorreview.Views;

import com.gp.doctorreview.Controllers.General.DoctorCellController;
import com.gp.doctorreview.Models.Doctor;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class DoctorCellFactory extends ListCell<Doctor> {
    @Override
    protected void updateItem(Doctor doctor, boolean empty) {
        super.updateItem(doctor, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/General/DoctorCell.fxml"));
            DoctorCellController controller = new DoctorCellController(doctor);
            loader.setController(controller);
            setText(null);

            try {
                setGraphic(loader.load());
            } catch (IOException e) {
                System.out.println("File could not found!");
            }
        }
    }
}
