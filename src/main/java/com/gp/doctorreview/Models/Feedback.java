package com.gp.doctorreview.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Feedback {

    private final IntegerProperty ID;
    private final IntegerProperty senderID;
    private final StringProperty senderName;
    private final IntegerProperty doctorID;
    private final StringProperty doctorName;
    private final ObjectProperty<LocalDate> dateSent;
    private final StringProperty feedbackTitle;
    private final StringProperty feedbackMessage;
    private final DoubleProperty reviewPoint;

    public Feedback(int ID, int senderID, String senderName, int doctorID, String doctorName, LocalDate dateSent, String feedbackTitle, String feedbackMessage, double reviewPoint) {
        this.ID = new SimpleIntegerProperty(this, "ID", ID);
        this.senderID = new SimpleIntegerProperty(this, "senderID", senderID);
        this.senderName = new SimpleStringProperty(this, "SenderName", senderName);
        this.doctorID = new SimpleIntegerProperty(this, "DoctorID", doctorID);
        this.doctorName = new SimpleStringProperty(this, "DoctorName", doctorName);
        this.dateSent = new SimpleObjectProperty<>(this, "DateSent", dateSent);
        this.feedbackTitle = new SimpleStringProperty(this, "FeedbackTitle", feedbackTitle);
        this.feedbackMessage = new SimpleStringProperty(this, "FeedbackMessage", feedbackMessage);
        this.reviewPoint = new SimpleDoubleProperty(this, "ReviewPoint", reviewPoint);
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public IntegerProperty senderIDProperty() {
        return senderID;
    }

    public StringProperty senderNameProperty() {
        return senderName;
    }

    public IntegerProperty doctorIDProperty() {
        return doctorID;
    }

    public StringProperty doctorNameProperty() {
        return doctorName;
    }

    public ObjectProperty<LocalDate> dateSentProperty() {
        return dateSent;
    }

    public StringProperty feedbackTitleProperty() {
        return feedbackTitle;
    }

    public StringProperty feedbackMessageProperty() {
        return feedbackMessage;
    }

    public DoubleProperty reviewPointProperty() {
        return reviewPoint;
    }
}
