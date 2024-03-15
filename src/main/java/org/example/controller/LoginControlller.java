package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginControlller {
    @FXML
    private AnchorPane root1;
    @FXML
    private JFXComboBox cmbSelect;
    @FXML
    private JFXPasswordField txtLoginPagePassword;

    @FXML
    private JFXTextField txtLoginPageUser;

    @FXML
    private AnchorPane root;

    AdminBO adminBoImpl = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);
    UserBO userBoImpl = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {

        cmbSelect.getItems().addAll("Admin", "User");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        String userText = txtLoginPageUser.getText();
        String passwordText = txtLoginPagePassword.getText();
        Object value = cmbSelect.getValue();
        System.out.print(value);

        if(txtLoginPageUser.getText().isEmpty() || txtLoginPagePassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Missing Information").show();
            return;
        }else {

            if(value.equals("User")){
                UserDTO search = userBoImpl.search(userText);
                System.out.print(search.getPassword());
                System.out.print(search.getName());
                System.out.print("check");
                if (search == null){
                    new Alert(Alert.AlertType.ERROR, "User Not Found").show();
                    return;
                }else {
                    if (search.getPassword().equals(passwordText)) {
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userForms/userMainForm.fxml"));
                        Scene scene = new Scene(anchorPane);
                        Stage stage =(Stage)root1.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("SignUp Page");
                        stage.centerOnScreen();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
                    }
                }
            }else {
                AdminDTO search = adminBoImpl.search(userText);
                System.out.print("check");
                if (search == null){
                    new Alert(Alert.AlertType.ERROR, "User Not Found").show();
                    return;
                }else {
                    if (search.getPassword().equals(passwordText)) {
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/admin/adminMainForm.fxml"));
                        Scene scene = new Scene(anchorPane);
                        Stage stage =(Stage)root1.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("SignUp Page");
                        stage.centerOnScreen();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
                    }
                }
            }
        }


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

