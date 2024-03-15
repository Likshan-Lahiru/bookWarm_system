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
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {


    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtSignName;

    @FXML
    private JFXTextField txtSignEmail;

    @FXML
    private JFXPasswordField txtSignPassword;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private JFXComboBox<String> cmbBranch;

    @FXML
    private JFXPasswordField txtRePassword;

    @FXML
    private JFXTextField txtTel;
    AdminBO adminBoImpl = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);
    UserBO userBoImpl = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() {
        cmbType.getItems().addAll("Admin", "User");

        try {
            for (BranchDTO branchDTO : branchBO.getAll()) {
                cmbBranch.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void btnCreateAccount(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("test sign up");
        String signPasswordText = txtSignPassword.getText();
        String rePassword = txtRePassword.getText();
        String signNameText = txtSignName.getText();
        String signEmailText = txtSignEmail.getText();
        int telText = Integer.parseInt(txtTel.getText());
        String typeValue = cmbType.getValue();
        System.out.print(typeValue);

        if (signNameText.isEmpty() || signEmailText.isEmpty() || signPasswordText.isEmpty() || rePassword.isEmpty() || ((String) typeValue).isEmpty() || txtTel.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else if (!signPasswordText.equals(rePassword)) {
            new Alert(Alert.AlertType.ERROR, "Password does not match").show();
        } else {

            if (typeValue.equals("User")) {
                if (cmbBranch.getValue() == null) {
                    new Alert(Alert.AlertType.ERROR, "Please select branch").show();
                } else {
                    try {
                        BranchDTO branchDTO = branchBO.searchByLocation(cmbBranch.getValue());
                        boolean save = userBoImpl.save(new UserDTO(signNameText, signEmailText, signPasswordText, telText, branchDTO));
                        if (save){
                            new Alert(Alert.AlertType.CONFIRMATION, "Register Complete").show();
                        }else {
                            new Alert(Alert.AlertType.ERROR, "Register Failed").show();
                        }

                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else {
                try {
                    boolean save = adminBoImpl.save(new AdminDTO(signNameText, signEmailText, telText, signPasswordText));
                    if (save){
                        new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Register Failed").show();
                    }

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }


        /*if (signNameText.isEmpty() || signEmailText.isEmpty() || signPasswordText.isEmpty() || telText.isEmpty()) {
            txtSignName.setStyle("-fx-border-color: red");
            txtSignEmail.setStyle("-fx-border-color: red");
            txtSignPassword.setStyle("-fx-border-color: red");
            txtTel.setStyle("-fx-border-color: red");
            return;
        }else {
            if (typeValue.equals("user")){
                BranchDTO branchDTO = branchBO.searchByLocation(cmbBranch.getValue());
                UserDTO userDTO = new UserDTO(signNameText, signEmailText, signPasswordText, Integer.parseInt(telText), branchDTO);
                boolean save = userBoImpl.save(userDTO);
                if (!save) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                    return;
                }
                new Alert(Alert.AlertType.INFORMATION, "User Created Successfully").show();


            }

        }*/




    }

    @FXML
    void btnSignFormLoginOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
    }
}