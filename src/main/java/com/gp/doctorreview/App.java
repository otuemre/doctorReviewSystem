package com.gp.doctorreview;

import com.gp.doctorreview.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginPage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
