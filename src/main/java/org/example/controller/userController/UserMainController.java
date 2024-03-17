package org.example.controller.userController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class UserMainController {

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane mainPain;


    @FXML
    void btnBookMenuOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/userForms/book_form.fxml"));

        this.mainPain.getChildren().clear();
        this.mainPain.getChildren().add(node);
    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/admin/dashBoard_forms.fxml"));

        this.mainPain.getChildren().clear();
        this.mainPain.getChildren().add(node);

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage =(Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();

    }

    @FXML
    void btnMyLibraryOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/userForms/my_libraray-form.fxml"));
        this.mainPain.getChildren().clear();
        this.mainPain.getChildren().add(node);
    }

}