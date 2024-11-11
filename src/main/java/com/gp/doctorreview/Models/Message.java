package com.gp.doctorreview.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Message {

    private final IntegerProperty ID;
    private final IntegerProperty senderID;
    private final StringProperty senderName;
    private final StringProperty senderEmail;
    private final StringProperty messageType;
    private final StringProperty message;
    private final ObjectProperty<LocalDate> dateSent;

    public Message (int ID, int senderID, String senderName, String senderEmail, String messageType, String message, LocalDate dateSent) {
        this.ID = new SimpleIntegerProperty(this, "ID", ID);
        this.senderID = new SimpleIntegerProperty(this, "SenderID", senderID);
        this.senderName = new SimpleStringProperty(this, "SenderName", senderName);
        this.senderEmail = new SimpleStringProperty(this, "SenderEmail", senderEmail);
        this.messageType = new SimpleStringProperty(this, "MessageType", messageType);
        this.message = new SimpleStringProperty(this, "Message", message);
        this.dateSent = new SimpleObjectProperty<>(this, "DateSent", dateSent);
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

    public StringProperty senderEmailProperty() {
        return senderEmail;
    }

    public StringProperty messageTypeProperty() {
        return messageType;
    }

    public StringProperty messageProperty() {
        return message;
    }

    public ObjectProperty<LocalDate> dateSentProperty() {
        return dateSent;
    }
}
