package org.example.controller.adminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMainController {
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane MainRoot;

    @FXML
    void btnBrancherOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/admin/branches_forms.fxml"));

        this.MainRoot.getChildren().clear();
        this.MainRoot.getChildren().add(node);
    }
    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/admin/bookManage_form.fxml"));

        this.MainRoot.getChildren().clear();
        this.MainRoot.getChildren().add(node);
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
    void btnUserOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/admin/userManage_forms.fxml"));

        this.MainRoot.getChildren().clear();
        this.MainRoot.getChildren().add(node);
    }

}
