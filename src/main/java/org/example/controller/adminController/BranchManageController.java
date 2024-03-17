package org.example.controller.adminController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.BranchBO;
import org.example.dto.BranchDTO;
import org.example.dto.tm.BranchTm;
import org.example.util.SystemAlert;

import java.sql.SQLException;
import java.util.List;

public class BranchManageController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtBranchId;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtLocation;

    @FXML
    private JFXTextField txtTele;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private TableView<BranchTm> tblBranch;
    @FXML
    private JFXComboBox<String> cmbBrancheSelect;
    BranchBO branchBoImpl = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() throws SQLException, ClassNotFoundException {
        generateNextId();
        loadComboBox();
        loadtable();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        tblBranch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBranch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("location"));
        tblBranch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tblBranch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadtable() {
        tblBranch.getItems().clear();
        try {
            List<BranchDTO> all = branchBoImpl.getAll();
            for (BranchDTO branchDTO : all) {
                tblBranch.getItems().add(new BranchTm(branchDTO.getId(), branchDTO.getLocation(), branchDTO.getTelephone(), branchDTO.getEmail()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadComboBox() {
        cmbBrancheSelect.getItems().clear();
        try {
            List<BranchDTO> all = branchBoImpl.getAll();
            for (BranchDTO branchDTO : all) {
                cmbBrancheSelect.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextId() throws SQLException, ClassNotFoundException {
        String generateNextId = branchBoImpl.generateNextId();
        txtBranchId.setText(generateNextId);
    }

    @FXML
    void btnBranchDeleteOnAction(ActionEvent event) {
        String branchIdText = txtBranchId.getText();
        try {
            boolean delete = branchBoImpl.delete(branchIdText);
            if (delete) {
                new SystemAlert(Alert.AlertType.CONFIRMATION, "Success", "Deleted", ButtonType.OK).show();
                loadtable();
                generateNextId();
                clearField();
            }
        }catch (Exception e){
            new SystemAlert(Alert.AlertType.ERROR,"Error!","Something went wrong", ButtonType.OK).show();
        }

    }

    @FXML
    void btnBranchEditOnAction(ActionEvent event) {
        txtBranchId.clear();
        txtLocation.clear();
        txtTele.clear();
        txtEmail.clear();
        txtAddress.clear();
        cmbBrancheSelect.getItems().clear();


    }

    @FXML
    void btnBranchSaveOnAction(ActionEvent event) {
        String branchIdText = txtBranchId.getText();
        String locationText = txtLocation.getText();
        String telephoneText = txtTele.getText();
        String emailText = txtEmail.getText();
        String addressText = txtAddress.getText();

        if(branchIdText.isEmpty() || locationText.isEmpty() || telephoneText.isEmpty() || emailText.isEmpty() || addressText.isEmpty()){
            new SystemAlert(Alert.AlertType.WARNING,"Warning!","All data are required", ButtonType.OK).show();
            return;
        }
        BranchDTO branchDTO = new BranchDTO(branchIdText, locationText, Integer.parseInt(telephoneText), emailText, addressText);
        try {
            boolean save = branchBoImpl.save(branchDTO);
            if (save) {
                new SystemAlert(Alert.AlertType.CONFIRMATION, "Success", "Saved", ButtonType.OK).show();
                loadtable();
                generateNextId();
                clearField();
                loadComboBox();
            }

        }catch (Exception e){
            new SystemAlert(Alert.AlertType.WARNING,"Warning!","Something went wrong", ButtonType.OK).show();
            return;
        }


    }

    private void clearField() {

        txtLocation.clear();
        txtTele.clear();
        txtEmail.clear();
        txtAddress.clear();
    }

    @FXML
    void btnBranchUpdateOnAction(ActionEvent event) {
        String branchIdText = txtBranchId.getText();
        String locationText = txtLocation.getText();
        String telephoneText = txtTele.getText();
        String emailText = txtEmail.getText();
        String addressText = txtAddress.getText();

        if(branchIdText.isEmpty() || locationText.isEmpty() || telephoneText.isEmpty() || emailText.isEmpty() || addressText.isEmpty()){
            new SystemAlert(Alert.AlertType.WARNING,"Warning!","All data are required", ButtonType.OK).show();
            return;
        }
        BranchDTO branchDTO = new BranchDTO(branchIdText, locationText, Integer.parseInt(telephoneText), emailText, addressText);
        try {
            boolean update = branchBoImpl.update(branchDTO);
            if (update) {
                new SystemAlert(Alert.AlertType.CONFIRMATION, "Success", "Updated", ButtonType.OK).show();
                loadtable();
                generateNextId();
                clearField();
                loadComboBox();
            }

        }catch (Exception e){
            new SystemAlert(Alert.AlertType.WARNING,"Warning!","Something went wrong", ButtonType.OK).show();
            return;
        }
    }
    @FXML
    void btnCmbBrancheSelect(ActionEvent event) {
        try {
            BranchDTO branchDTO = branchBoImpl.searchByLocation(cmbBrancheSelect.getValue());
            if (branchDTO != null) {
                txtBranchId.setText(branchDTO.getId());
                txtLocation.setText(branchDTO.getLocation());
                txtTele.setText(String.valueOf(branchDTO.getTelephone()));
                txtEmail.setText(branchDTO.getEmail());
                txtAddress.setText(branchDTO.getAddress());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
