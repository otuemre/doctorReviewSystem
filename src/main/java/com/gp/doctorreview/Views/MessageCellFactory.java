package com.gp.doctorreview.Views;

import com.gp.doctorreview.Controllers.General.MessageCellController;
import com.gp.doctorreview.Models.Message;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MessageCellFactory extends ListCell<Message> {

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/General/MessageCell.fxml"));
            MessageCellController controller = new MessageCellController(message);
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
