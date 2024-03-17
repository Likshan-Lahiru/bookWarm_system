package org.example.controller.adminController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BranchBO;
import org.example.dto.BookDTO;
import org.example.dto.BranchDTO;
import org.example.dto.tm.BookTm;
import org.example.util.SystemAlert;

import java.sql.SQLException;
import java.util.List;

public class BookManageController {

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtBookAuthor;

    @FXML
    private JFXTextField txtBookGnere;

    @FXML
    private JFXComboBox<String> cmbBookStatus;

    @FXML
    private JFXComboBox<String> cmbBranchSelector;

    @FXML
    private TableView<BookTm> tblBook;
    BookBO bookBOImpl = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    BranchBO branchBOImpl = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() throws SQLException, ClassNotFoundException {
        geneateNextId();
        loadComboBox();
        loadtable();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        tblBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadtable() {
        tblBook.getItems().clear();
        try {
            List<BookDTO> all = bookBOImpl.getAll();
            for (BookDTO bookDTO : all) {
                tblBook.getItems().add(new BookTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getStatus()));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadComboBox() {
        cmbBranchSelector.getItems().clear();
        cmbBookStatus.getItems().clear();
        try {
            List<BranchDTO> all = branchBOImpl.getAll();
            for (BranchDTO branchDTO : all) {
                cmbBranchSelector.getItems().add(branchDTO.getLocation());
                /*cmbBookStatus.getItems().add(branchDTO.getLocation());*/
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        cmbBookStatus.getItems().addAll("Available", "Not Available");
    }

    private void geneateNextId() throws SQLException, ClassNotFoundException {
        String nextId = bookBOImpl.generateNextId();
        txtBookId.setText(nextId);
    }

    @FXML
    void btnBookDeleteOnAction(ActionEvent event) {
        String text = txtBookId.getText();
        try {
            boolean delete = bookBOImpl.delete(text);
            if (delete) {
                new SystemAlert(Alert.AlertType.INFORMATION, "Success", "Book Deleted", ButtonType.OK).show();
                loadtable();

            } else {
                new SystemAlert(Alert.AlertType.ERROR, "Error", "Book Not Deleted", ButtonType.OK).show();
            }
        }catch (Exception e){
            new SystemAlert(Alert.AlertType.ERROR,"Error!","Something went wrong", ButtonType.OK).show();
        }

    }

    @FXML
    void btnBookEditOnAction(ActionEvent event) {
        txtBookId.clear();
        txtBookName.clear();
        txtBookAuthor.clear();
        txtBookGnere.clear();
        cmbBookStatus.getItems().clear();
        cmbBranchSelector.getItems().clear();

    }

    @FXML
    void btnBookSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String bookIdText = txtBookId.getText();
        String bookNameText = txtBookName.getText();
        String bookAuthorText = txtBookAuthor.getText();
        String bookGnereText = txtBookGnere.getText();
        String bookStatusText = cmbBookStatus.getValue();
        String bookBranchText = cmbBranchSelector.getValue();

        if (bookBranchText.isEmpty()||bookIdText.isEmpty()||bookNameText.isEmpty()||bookAuthorText.isEmpty()||bookGnereText.isEmpty()||bookStatusText.isEmpty()) {
            new SystemAlert(Alert.AlertType.ERROR, "Error", "Empty Field", ButtonType.OK).show();
            return;
        }
        BranchDTO branchDTO = branchBOImpl.searchByLocation(bookBranchText);
        BookDTO bookDTO = new BookDTO(bookIdText, bookNameText, bookAuthorText, bookGnereText, bookStatusText, branchDTO);
        try {
            boolean save = bookBOImpl.save(bookDTO);
            if (save) {
                new SystemAlert(Alert.AlertType.CONFIRMATION, "Success", "Saved", ButtonType.OK).show();
                loadtable();
                clearFields();
                geneateNextId();
            }
        }catch (Exception e){
            new SystemAlert(Alert.AlertType.ERROR,"Error!","Something went wrong", ButtonType.OK).show();
        }

    }

    private void clearFields() {
        txtBookName.clear();
        txtBookAuthor.clear();
        txtBookGnere.clear();
        cmbBookStatus.getSelectionModel().clearSelection();
        cmbBranchSelector.getSelectionModel().clearSelection();
    }

    @FXML
    void btnBookUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String bookIdText = txtBookId.getText();
        String bookNameText = txtBookName.getText();
        String bookAuthorText = txtBookAuthor.getText();
        String bookGnereText = txtBookGnere.getText();
        String bookStatusText = cmbBookStatus.getValue();
        String bookBranchText = cmbBranchSelector.getValue();

        if (bookBranchText.isEmpty()||bookIdText.isEmpty()||bookNameText.isEmpty()||bookAuthorText.isEmpty()||bookGnereText.isEmpty()||bookStatusText.isEmpty()) {
            new SystemAlert(Alert.AlertType.ERROR, "Error", "Empty Field", ButtonType.OK).show();
            return;
        }
        BranchDTO branchDTO = branchBOImpl.searchByLocation(bookBranchText);
        BookDTO bookDTO = new BookDTO(bookIdText, bookNameText, bookAuthorText, bookGnereText, bookStatusText, branchDTO);
        try {
            boolean update = bookBOImpl.update(bookDTO);
            if (update) {
                new SystemAlert(Alert.AlertType.CONFIRMATION, "Success", "Updated", ButtonType.OK).show();
                loadtable();
                clearFields();
                geneateNextId();
            }

        }catch (Exception e){
            new SystemAlert(Alert.AlertType.ERROR,"Error!","Something went wrong", ButtonType.OK).show();
        }
    }

}
