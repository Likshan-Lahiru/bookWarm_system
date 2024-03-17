package org.example.controller.adminController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.dto.tm.UserTm;
import org.example.util.SystemAlert;

import java.sql.SQLException;
import java.util.List;

public class UserManageController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtUserEmail;

    @FXML
    private JFXTextField txtUserTele;

    @FXML
    private JFXComboBox<String> cmbUserBranchSelect;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtuserSearchId;

    UserBO userBo = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBo = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);
    public void initialize() {
        setCellValueFactory();
        loadTables();
        loadComboBox();
    }
    private void setCellValueFactory() {
        tblUser.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblUser.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblUser.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tblUser.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("branch"));
    }

    private void loadComboBox() {
        cmbUserBranchSelect.getItems().clear();

        try {
            List<BranchDTO> all = branchBo.getAll();
            for (BranchDTO branchDTO : all) {
                cmbUserBranchSelect.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTables() {
        tblUser.getItems().clear();
        try {
            List<UserDTO> all = userBo.getAll();
            for (UserDTO userDTO : all) {
                tblUser.getItems().add(new UserTm(userDTO.getEmail(), userDTO.getName(), userDTO.getTelephone(), userDTO.getBranch().getLocation()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void btnUserDeleteOnAction(ActionEvent event) {
        String userNameText = txtUserName.getText();
        try {
            boolean delete = userBo.delete(userNameText);
            if (delete) {
                new SystemAlert(Alert.AlertType.WARNING,"Warning!","Deleted Successfully", ButtonType.OK).show();
            } else {
                new SystemAlert(Alert.AlertType.WARNING,"Warning!","Not Deleted", ButtonType.OK).show();
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
       loadTables();


    }

    @FXML
    void btnUserEditOnAction(ActionEvent event) {
        txtUserName.clear();
        txtUserEmail.clear();
        txtUserTele.clear();
        txtuserSearchId.clear();


    }

    @FXML
    void btnUserSaveOnAction(ActionEvent event) {


    }

    @FXML
    void btnUserUpdateOnAction(ActionEvent event) {


    }

    @FXML
    void btnuserSearchOnAction(ActionEvent event) {

    }

}
