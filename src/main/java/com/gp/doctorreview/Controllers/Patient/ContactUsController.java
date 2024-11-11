package com.gp.doctorreview.Controllers.Patient;

import com.gp.doctorreview.Models.Model;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ContactUsController implements Initializable {
    public TextField name_fld;
    public TextField email_fld;
    public ChoiceBox<String> msg_type_box;
    public TextArea msg_area;
    public Button send_btn;
    public Label err_msg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msg_type_box.setItems(FXCollections.observableArrayList("General Information", "Report a Bug", "Suggest Improvements"));
        msg_type_box.setValue("General Information");
        msg_type_box.valueProperty().addListener((observableValue, oldVal, newVal) -> msg_type_box.setValue(newVal));

        name_fld.textProperty().bind(Model.getInstance().getUser().nameProperty());
        email_fld.textProperty().bind(Model.getInstance().getUser().emailProperty());

        send_btn.setOnAction(actionEvent -> onSendMessage());
    }

    private void onSendMessage() {
        if (msg_area.getText().isEmpty()) {
            err_msg.setStyle("-fx-text-fill: #CC0000");
            err_msg.setText("Message Area Cannot Be Empty");
        } else {

            Model.getInstance().getDatabaseDriver().sendMessage(
                    Model.getInstance().getUser().IDProperty().get(),
                    name_fld.getText(),
                    email_fld.getText(),
                    msg_type_box.getValue(),
                    msg_area.getText(),
                    LocalDate.now()
            );

            showSuccessMessage();
            emptyFields();
        }
    }

    private void emptyFields() {
        msg_type_box.setValue("General Information");
        msg_area.setText("");
    }

    private void showSuccessMessage() {
        err_msg.setStyle("-fx-text-fill: #2B7A78");
        err_msg.setText("You have successfully send a query!");
    }
}
