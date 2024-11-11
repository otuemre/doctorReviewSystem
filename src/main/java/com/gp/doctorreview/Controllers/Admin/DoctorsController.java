package com.gp.doctorreview.Controllers.Admin;

import com.gp.doctorreview.Models.Doctor;
import com.gp.doctorreview.Models.Model;
import com.gp.doctorreview.Views.AdminHeaderOptions;
import com.gp.doctorreview.Views.DoctorCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorsController implements Initializable {
    public ListView<String> specializations_listview;
    public TextField search_bar_fld;
    public Button search_btn;
    public RadioButton name_asc;
    public ToggleGroup sorting_toggle;
    public RadioButton name_desc;
    public RadioButton review_asc;
    public RadioButton review_desc;
    public ListView<Doctor> doctor_cards_listview;
    public Button add_doctor_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
        initData();
        doctor_cards_listview.setItems(Model.getInstance().getDoctors());
        doctor_cards_listview.setCellFactory(d -> new DoctorCellFactory());

        specializations_listview.setItems(Model.getInstance().getSpecializations());
    }

    private void addListeners() {
        search_btn.setOnAction(actionEvent -> onSearch());

        add_doctor_btn.setOnAction(actionEvent -> onDoctorDetails());
        specializations_listview.setOnMouseClicked(mouseEvent -> Model.getInstance().filterDoctorsBySpec(specializations_listview.getSelectionModel().getSelectedItem()));

        sorting_toggle.selectedToggleProperty().addListener((observableValue, oldVal, newVal) -> {
            RadioButton selectedRadioButton = (RadioButton) newVal;
            String radioButtonID = selectedRadioButton.getId();

            switch (radioButtonID) {
                case "name_asc" -> Model.getInstance().sortDoctorsByNameAsc();
                case "name_desc" -> Model.getInstance().sortDoctorsByNameDesc();
                case "review_asc" -> Model.getInstance().sortDoctorsByReviewAsc();
                default -> Model.getInstance().sortDoctorsByReviewDesc();
            }
        });


    }

    private void onDoctorDetails() {
        Model.getInstance().getViewFactory().getAdminSelectedHeaderItem().set(AdminHeaderOptions.MANAGE_DOCTORS);
    }
    private void onSearch() {
        Model.getInstance().filterDoctorsByName(search_bar_fld.getText());
    }

    private void initData() {
        if (Model.getInstance().getDoctors().isEmpty()) {
            Model.getInstance().setDoctors();
            Model.getInstance().sortDoctorsByReviewDesc();
        }

        if (Model.getInstance().getSpecializations().isEmpty()) {
            Model.getInstance().setSpecializations();
        }
    }
}
