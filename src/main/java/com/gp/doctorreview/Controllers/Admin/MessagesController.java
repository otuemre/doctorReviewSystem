package com.gp.doctorreview.Controllers.Admin;

import com.gp.doctorreview.Models.Message;
import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.MessageCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MessagesController implements Initializable {
    public TextField name_fld;
    public TextField email_fld;
    public TextField message_title_fld;
    public TextArea msg_area;
    public ListView<Message> messages_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        messages_listview.setItems(Model.getInstance().getMessages());
        messages_listview.setCellFactory(m -> new MessageCellFactory());

        name_fld.textProperty().bind(Model.getInstance().getSelectedMessage().senderNameProperty());
        email_fld.textProperty().bind(Model.getInstance().getSelectedMessage().senderEmailProperty());
        message_title_fld.textProperty().bind(Model.getInstance().getSelectedMessage().messageTypeProperty());
        msg_area.textProperty().bind(Model.getInstance().getSelectedMessage().messageProperty());
    }

    private void initData() {
        if (Model.getInstance().getMessages().isEmpty()) {
            Model.getInstance().setMessages();
            System.out.println(Model.getInstance().getMessages().size());
        }
    }
}
