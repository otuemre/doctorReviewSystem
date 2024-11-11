package com.gp.doctorreview.Views;

import com.gp.doctorreview.Controllers.General.FeedbackCellController;
import com.gp.doctorreview.Models.Feedback;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class FeedbackCellFactory extends ListCell<Feedback> {
    @Override
    protected void updateItem(Feedback feedback, boolean empty) {
        super.updateItem(feedback, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/General/FeedbackCell.fxml"));
            FeedbackCellController controller = new FeedbackCellController(feedback);
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
