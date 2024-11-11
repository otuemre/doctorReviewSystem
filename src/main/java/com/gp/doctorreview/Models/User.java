package com.gp.doctorreview.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class User {
    private final IntegerProperty ID;
    private final StringProperty name;
    private final StringProperty role;
    private final StringProperty email;
    private final ObjectProperty<LocalDate> dateOfBirth;
    private final StringProperty addressLine;
    private final StringProperty postcode;

    public User(int ID, String name, String role, String email, LocalDate dateOfBirth, String addressLine, String postCode) {
        this.ID = new SimpleIntegerProperty(this, "ID", ID);
        this.name = new SimpleStringProperty(this, "Name", name);
        this.role = new SimpleStringProperty(this, "Role", role);
        this.email = new SimpleStringProperty(this, "Email", email);
        this.dateOfBirth = new SimpleObjectProperty<>(this, "DateOfBirth", dateOfBirth);
        this.addressLine = new SimpleStringProperty(this, "AddressLine", addressLine);
        this.postcode = new SimpleStringProperty(this, "PostCode", postCode);
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty roleProperty() {
        return role;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public ObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public StringProperty addressLineProperty() {
        return addressLine;
    }

    public StringProperty postcodeProperty() {
        return postcode;
    }
}
