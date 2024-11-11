package com.gp.doctorreview.Models;

import com.gp.doctorreview.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;

    // User Section
    private final User user;
    private boolean userLoginSuccessFlag;

    // General Section
    private final ObservableList<Doctor> doctors;
    private final ObservableList<String> specializations;
    private final ObservableList<Feedback> doctorFeedbacks;
    private final ObservableList<Message> messages;

    private final Doctor selectedDoctor;
    private final Message selectedMessage;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        // User Section
        this.user = new User(0, "", "", "", null, "", "");
        this.userLoginSuccessFlag = false;

        // General Section
        this.doctors = FXCollections.observableArrayList();
        this.specializations = FXCollections.observableArrayList();
        this.doctorFeedbacks = FXCollections.observableArrayList();
        this.messages = FXCollections.observableArrayList();

        this.selectedDoctor = new Doctor(0, "", "", 0, 0);
        this.selectedMessage = new Message(0, 0, "", "", "", "", null);
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
    public DatabaseDriver getDatabaseDriver() {return databaseDriver;}

    // User Methods Section
    public User getUser() {
        return user;
    }

    public boolean isUserLoginSuccessFlag() {
        return userLoginSuccessFlag;
    }

    public void setUserLoginSuccessFlag(boolean userLoginSuccessFlag) {
        this.userLoginSuccessFlag = userLoginSuccessFlag;
    }

    public void evaluateUserCredentials(String email, String password) {
        ResultSet resultSet = databaseDriver.getUserData(email, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.user.IDProperty().set(resultSet.getInt("userID"));
                this.user.nameProperty().set(resultSet.getString("name"));
                this.user.roleProperty().set(resultSet.getString("role"));
                this.user.emailProperty().set(resultSet.getString("email"));
                String[] dateParts = resultSet.getString("dateOfBirth").split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);
                LocalDate dob = LocalDate.of(year, month, day);
                this.user.dateOfBirthProperty().set(dob);
                this.user.addressLineProperty().set(resultSet.getString("addressLine1"));
                this.user.postcodeProperty().set(resultSet.getString("postcode"));

                this.userLoginSuccessFlag = true;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        }
    }

    public boolean isUserExist(String email) {
        boolean exist;
        ResultSet resultSet = databaseDriver.userExist(email);

        try {
            exist = resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exist;
    }

    // General Methods Section
    public ObservableList<Doctor> getDoctors() {
        return doctors;
    }

    public void filterDoctorsByName(String doctorName) {
        ObservableList<Doctor> filteredDoctors = FXCollections.observableArrayList();
        doctors.clear();
        setDoctors();
        if (!doctors.isEmpty()) {
            for(Doctor doctor:doctors) {
                if (doctor.nameProperty().get().toLowerCase().contains(doctorName.toLowerCase())) {
                    filteredDoctors.add(doctor);
                }
            }
            doctors.clear();
            doctors.addAll(filteredDoctors);
        }
    }

    public void sortDoctorsByReviewAsc() {
        FXCollections.sort(doctors, Comparator.comparingDouble(doctor -> doctor.reviewPointProperty().get()));
    }

    public void sortDoctorsByReviewDesc() {
        FXCollections.sort(doctors, (doctor1, doctor2) -> Double.compare(doctor2.reviewPointProperty().get(), doctor1.reviewPointProperty().get()));
    }

    public void sortDoctorsByNameAsc() {
        FXCollections.sort(doctors, Comparator.comparing(doctor -> doctor.nameProperty().get()));
    }

    public void sortDoctorsByNameDesc() {
        FXCollections.sort(doctors, (doctor1, doctor2) -> doctor2.nameProperty().get().compareTo(doctor1.nameProperty().get()));
    }

    public void filterDoctorsBySpec(String specialization) {
        ObservableList<Doctor> filteredDoctors = FXCollections.observableArrayList();
        doctors.clear();
        setDoctors();
        if (!doctors.isEmpty()) {
            for(Doctor doctor:doctors) {
                if (doctor.specializationProperty().get().equals(specialization)) {
                    filteredDoctors.add(doctor);
                }
            }
            doctors.clear();
            doctors.addAll(filteredDoctors);
        }
    }

    public void setDoctors() {
        ResultSet resultSet = databaseDriver.getAllDoctorsData();

        try {
            while (resultSet.next()) {
                int doctorID = resultSet.getInt("doctorID");
                String doctorName = resultSet.getString("doctorName");
                String specialization = resultSet.getString("specialization");
                double reviewRating = resultSet.getDouble("reviewRating");
                int totalReviews = resultSet.getInt("totalReviews");

                doctors.add(new Doctor(doctorID, doctorName, specialization, reviewRating, totalReviews));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDoctorFeedbacks(int doctorID) {
        ResultSet resultSet = databaseDriver.getDoctorFeedbacks(doctorID);

        try {
            while (resultSet.next()) {
                int feedbackID = resultSet.getInt("feedbackID");
                int senderID = resultSet.getInt("senderId");
                String userName = resultSet.getString("userName");
                String doctorName = resultSet.getString("doctorName");

                String[] dateParts = resultSet.getString("dateSent").split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);
                LocalDate dateSent = LocalDate.of(year, month, day);

                String feedbackTitle = resultSet.getString("feedbackTitle");
                String feedbackMessage = resultSet.getString("feedbackMessage");
                double rewPoint = resultSet.getDouble("newReviewPoint");

                doctorFeedbacks.add(new Feedback(feedbackID, senderID, userName, doctorID, doctorName, dateSent, feedbackTitle, feedbackMessage, rewPoint));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Feedback> getDoctorFeedbacks() {
        return doctorFeedbacks;
    }

    public void setSpecializations() {
        ResultSet resultSet = databaseDriver.getDistinctSpec();

        try {
            while (resultSet.next()) {
                String spec = resultSet.getString("specialization");
                specializations.add(spec);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<String> getSpecializations() {
        return specializations;
    }

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public Message getSelectedMessage() {
        return selectedMessage;
    }

    public ObservableList<Message> getMessages() {
        return messages;
    }

    public void setMessages() {
        ResultSet resultSet = databaseDriver.getAllMessages();

        try {
            while (resultSet.next()) {
                int messageID = resultSet.getInt("messageID");
                int senderID = resultSet.getInt("senderID");
                String senderName = resultSet.getString("senderName");
                String senderEmail = resultSet.getString("senderEmail");
                String messageTitle = resultSet.getString("messageTitle");
                String message = resultSet.getString("message");
                String[] dateParts = resultSet.getString("dateSent").split("-");

                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);
                LocalDate dateSent = LocalDate.of(year, month, day);

                messages.add(new Message(messageID, senderID, senderName, senderEmail, messageTitle, message, dateSent));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
