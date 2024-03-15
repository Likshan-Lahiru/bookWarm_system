package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControlller {
    @FXML
    private AnchorPane root1;
    @FXML
    private JFXComboBox cmbSelect;
    @FXML
    private JFXPasswordField txtLoginPagePassword;

    @FXML
    private JFXTextField txtLoginPageEmail;

    @FXML
    private AnchorPane root;

    public void initialize() {

        cmbSelect.getItems().addAll("Admin", "User");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userForms/userMainForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage =(Stage)root1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("SignUp Page");
        stage.centerOnScreen();
    }


    @FXML
    public void btnSign(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signUpForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage =(Stage)root1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("SignUp Page");
        stage.centerOnScreen();
    }
}

