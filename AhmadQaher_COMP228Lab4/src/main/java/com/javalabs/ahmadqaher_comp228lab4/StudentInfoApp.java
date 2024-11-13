package com.javalabs.ahmadqaher_comp228lab4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentInfoApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information");

        VBox firstPane = new VBox(10);
        firstPane.setAlignment(Pos.TOP_LEFT);
        firstPane.getChildren().addAll(
                new Label("Name:"),
                new Label("Address:"),
                new Label("Province:"),
                new Label("City:"),
                new Label("Postal Code:"),
                new Label("Phone Number:"),
                new Label("Email:")
        );

        VBox secondPane = new VBox(10);
        secondPane.setAlignment(Pos.TOP_LEFT);
        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField provinceField = new TextField();
        TextField cityField = new TextField();
        TextField postalCodeField = new TextField();
        TextField phoneNumberField = new TextField();
        TextField emailField = new TextField();
        secondPane.getChildren().addAll(nameField, addressField, provinceField, cityField, postalCodeField, phoneNumberField, emailField);

        VBox thirdPane = new VBox(10);
        thirdPane.setAlignment(Pos.TOP_LEFT);
        CheckBox studentCouncilCheckBox = new CheckBox("Student Council");
        CheckBox volunteerWorkCheckBox = new CheckBox("Volunteer Work");
        thirdPane.getChildren().addAll(new Label("Activities:"), studentCouncilCheckBox, volunteerWorkCheckBox);

        VBox fourthPane = new VBox(10);
        fourthPane.setAlignment(Pos.TOP_LEFT);

        Label departmentLabel = new Label("Department: ");
        ToggleGroup departmentGroup = new ToggleGroup();
        RadioButton csRadioButton = new RadioButton("Computer Science");
        csRadioButton.setToggleGroup(departmentGroup);
        RadioButton businessRadioButton = new RadioButton("Business");
        businessRadioButton.setToggleGroup(departmentGroup);

        ComboBox<String> courseComboBox = new ComboBox<>();
        courseComboBox.setPromptText("Select Course");

        TextArea selectionArea = new TextArea();
        selectionArea.setEditable(false);
        selectionArea.setPrefRowCount(4);
        selectionArea.setWrapText(true);

        csRadioButton.setOnAction(e -> {
            courseComboBox.getItems().clear();
            courseComboBox.getItems().addAll("Java", "Python", "C#", "JavaScript");
            selectionArea.clear();
        });
        businessRadioButton.setOnAction(e -> {
            courseComboBox.getItems().clear();
            courseComboBox.getItems().addAll("Accounting", "Marketing", "Finance", "Management");
            selectionArea.clear();
        });

        courseComboBox.setOnAction(e -> {
            String selection = courseComboBox.getValue();
            if (selection != null && !selectionArea.getText().contains(selection)) {
                selectionArea.appendText(selection + "\n");
            }
        });

        fourthPane.getChildren().addAll(departmentLabel, csRadioButton, businessRadioButton, courseComboBox, selectionArea);

        HBox mainPane = new HBox(10, firstPane, secondPane, thirdPane, fourthPane);
        mainPane.setPadding(new Insets(10, 10, 10, 10));

        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setWrapText(true);
        displayArea.setPrefHeight(100);
        displayArea.setPrefColumnCount(4);

        Button displayButton = new Button("Display");
        displayButton.setOnAction(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String province = provinceField.getText();
            String city = cityField.getText();
            String postalCode = postalCodeField.getText();
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();

            boolean studentCouncil = studentCouncilCheckBox.isSelected();
            boolean volunteerWork = volunteerWorkCheckBox.isSelected();
            String department = ((RadioButton) departmentGroup.getSelectedToggle()).getText();
            String selectedCourses = selectionArea.getText().isEmpty() ? "None" : selectionArea.getText().trim();

            String formattedOutput = String.format(
                    "%s, %s, %s, %s, %s, %s, %s\nDepartment: %s\nStudent Council: %s\nVolunteer Work: %s\nCourses:\n%s",
                    name, address, city, province, postalCode, phoneNumber, email,
                    department,
                    studentCouncil ? "Yes" : "No",
                    volunteerWork ? "Yes" : "No",
                    selectedCourses
            );
            displayArea.setText(formattedOutput);

            nameField.clear();
            addressField.clear();
            provinceField.clear();
            cityField.clear();
            postalCodeField.clear();
            phoneNumberField.clear();
            emailField.clear();
            studentCouncilCheckBox.setSelected(false);
            volunteerWorkCheckBox.setSelected(false);
            selectionArea.clear();

        });

        VBox subPane = new VBox(10, displayButton, displayArea);
        subPane.setAlignment(Pos.CENTER);
        subPane.setPadding(new Insets(10, 10, 10, 10));

        VBox root = new VBox(10, mainPane, subPane);
        Scene scene = new Scene(root, 875, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
