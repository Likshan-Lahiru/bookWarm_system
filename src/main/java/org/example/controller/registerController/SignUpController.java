package org.example.controller.registerController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.util.SystemAlert;

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
            new SystemAlert(Alert.AlertType.ERROR, "Error!", "Please Fill All Field", ButtonType.OK).show();
        } else if (!signPasswordText.equals(rePassword)) {
            new SystemAlert(Alert.AlertType.ERROR, "Error!", "Password Not Match", ButtonType.OK).show();
        } else {

            if (typeValue.equals("User")) {
                if (cmbBranch.getValue() == null) {
                    new SystemAlert(Alert.AlertType.ERROR, "Error!", "Please Select Branch", ButtonType.OK).show();
                } else {
                    try {
                        BranchDTO branchDTO = branchBO.searchByLocation(cmbBranch.getValue());
                        boolean save = userBoImpl.save(new UserDTO(signNameText, signEmailText, signPasswordText, telText, branchDTO));
                        if (save){
                            new SystemAlert(Alert.AlertType.INFORMATION, "Success", "User Created", ButtonType.OK).show();
                        }else {
                            new SystemAlert(Alert.AlertType.ERROR,"Error!","Something went wrong", ButtonType.OK).show();
                        }

                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else {
                try {
                    boolean save = adminBoImpl.save(new AdminDTO(signNameText, signEmailText, telText, signPasswordText));
                    if (save){
                        new SystemAlert(Alert.AlertType.INFORMATION, "Success", "Admin Created", ButtonType.OK).show();
                    }else {
                        new SystemAlert(Alert.AlertType.ERROR,"Error!","Something went wrong", ButtonType.OK).show();
                    }

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }





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