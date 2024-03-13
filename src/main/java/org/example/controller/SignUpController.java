package org.example.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private JFXTextField txtSignName;

    @FXML
    private JFXTextField txtSignEmail;

    @FXML
    private JFXPasswordField txtSignPassword;

    @FXML
    private AnchorPane root;

    @FXML
    void btnLoginSignUpPage() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage =(Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
    }

    @FXML
    void btnSignCreateAccount(ActionEvent event) throws IOException {
            btnLoginSignUpPage();
    }

}