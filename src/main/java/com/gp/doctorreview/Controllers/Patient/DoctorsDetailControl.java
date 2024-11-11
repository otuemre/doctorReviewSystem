package com.gp.doctorreview.Controllers.Patient;

import com.gp.doctorreview.Models.Feedback;
import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.FeedbackCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DoctorsDetailControl implements Initializable {
    public Label doctor_name;
    public Label doctor_spec;
    public Label review_point;
    public Label total_viewers;
    public TextField name_fld;
    public TextField date_fld;
    public TextField feedback_title_fld;
    public TextArea feedback_text_area;
    public Slider feedback_slider;
    public Button submit_btn;
    public Label err_msg;
    public ListView<Feedback> feedbacks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        doctor_name.textProperty().bind(Model.getInstance().getSelectedDoctor().nameProperty());
        doctor_spec.textProperty().bind(Model.getInstance().getSelectedDoctor().specializationProperty());
        review_point.textProperty().bind(Bindings.concat(Model.getInstance().getSelectedDoctor().reviewPointProperty()).concat(" / 5.0"));
        total_viewers.textProperty().bind(Bindings.concat(Model.getInstance().getSelectedDoctor().totalViewerProperty()).concat(" Total View"));

      feedbacks.setItems(Model.getInstance().getDoctorFeedbacks());
      feedbacks.setCellFactory(f -> new FeedbackCellFactory());

      name_fld.textProperty().bind(Model.getInstance().getUser().nameProperty());
      date_fld.setText(LocalDate.now().toString());

      submit_btn.setOnAction(actionEvent -> onSubmit());
    }

     private void initData() {
         if (Model.getInstance().getDoctorFeedbacks().isEmpty()) {
              Model.getInstance().setDoctorFeedbacks(Model.getInstance().getSelectedDoctor().IDProperty().get());
          }
       }

     private void onSubmit(){
         if (feedback_title_fld.getText().isEmpty()) {
             err_msg.setStyle("-fx-text-fill: #CC0000");
             err_msg.setText("Please, type your feedback title!");
         } else if (feedback_text_area.getText().isEmpty()) {
             err_msg.setStyle("-fx-text-fill: #CC0000");
             err_msg.setText("Please, type your feedback message!");
         } else {
             int id = 0;
             int senderID = Model.getInstance().getUser().IDProperty().get();
             String userName = name_fld.getText();
             int doctorID = Model.getInstance().getSelectedDoctor().IDProperty().get();
             String doctorName = doctor_name.getText();
             LocalDate dateSent = LocalDate.now();
             String feedbackTitle = feedback_title_fld.getText();
             String message = feedback_text_area.getText();
             double revPoint = Math.round(feedback_slider.getValue());

             System.out.println(revPoint);

             Feedback feedback = new Feedback(id, senderID, userName, doctorID, doctorName, dateSent, feedbackTitle, message, revPoint);
             Model.getInstance().getDatabaseDriver().addReview(feedback);

             // Re-calculate review point
             int newTotalReview = Model.getInstance().getSelectedDoctor().totalViewerProperty().get() + 1;
             double newReview = ((Model.getInstance().getSelectedDoctor().reviewPointProperty().get() * Model.getInstance().getSelectedDoctor().totalViewerProperty().get()) + revPoint) / (newTotalReview);
             newReview = Math.round(newReview);
             Model.getInstance().getDatabaseDriver().updateDoctor(doctorID, newReview, newTotalReview);

             // Change Current Doctor
             Model.getInstance().getSelectedDoctor().reviewPointProperty().set(newReview);
             Model.getInstance().getSelectedDoctor().totalViewerProperty().set(newTotalReview);

             err_msg.setStyle("-fx-text-fill: #2B7A78");
             err_msg.setText("You have successfully send your feedback!");
             feedback_title_fld.setText("");
             feedback_text_area.setText("");
         }
     }
}
