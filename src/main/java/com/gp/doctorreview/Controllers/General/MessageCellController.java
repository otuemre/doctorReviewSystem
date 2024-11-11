package com.gp.doctorreview.Controllers.General;

import com.gp.doctorreview.Models.Message;
import com.gp.doctorreview.Models.Model;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageCellController implements Initializable {
    public Label name_lbl;
    public Label title_lbl;
    public Label date_lbl;
    public Button see_detail_btn;

    private final Message message;

    public MessageCellController(Message message) {
        this.message = message;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_lbl.textProperty().bind(Bindings.concat(message.senderNameProperty()).concat(": "));
        title_lbl.textProperty().bind(message.messageTypeProperty());
        date_lbl.textProperty().bind(message.dateSentProperty().asString());

        see_detail_btn.setOnAction(actionEvent -> {
            Model.getInstance().getSelectedMessage().IDProperty().bind(message.IDProperty());
            Model.getInstance().getSelectedMessage().senderIDProperty().bind(message.senderIDProperty());
            Model.getInstance().getSelectedMessage().senderNameProperty().bind(message.senderNameProperty());
            Model.getInstance().getSelectedMessage().senderEmailProperty().bind(message.senderEmailProperty());
            Model.getInstance().getSelectedMessage().messageTypeProperty().bind(message.messageTypeProperty());
            Model.getInstance().getSelectedMessage().messageProperty().bind(message.messageProperty());
            Model.getInstance().getSelectedMessage().dateSentProperty().bind(message.dateSentProperty());
        });
    }
}
