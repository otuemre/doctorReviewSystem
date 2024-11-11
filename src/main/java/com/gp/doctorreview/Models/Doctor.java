package com.gp.doctorreview.Models;

import javafx.beans.property.*;

public class Doctor {

    private final IntegerProperty ID;
    private final StringProperty name;
    private final StringProperty specialization;
    private final DoubleProperty reviewPoint;
    private final IntegerProperty totalViewer;

    public Doctor(int ID, String name, String specialization, double reviewPoint, int totalViewer) {
        this.ID = new SimpleIntegerProperty(this, "ID", ID);
        this.name = new SimpleStringProperty(this, "Name", name);
        this.specialization = new SimpleStringProperty(this, "Specialization", specialization);
        this.reviewPoint = new SimpleDoubleProperty(this, "ReviewPoint", reviewPoint);
        this.totalViewer = new SimpleIntegerProperty(this, "TotalViewer", totalViewer);
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty specializationProperty() {
        return specialization;
    }

    public DoubleProperty reviewPointProperty() {
        return reviewPoint;
    }

    public IntegerProperty totalViewerProperty() {
        return totalViewer;
    }
}
