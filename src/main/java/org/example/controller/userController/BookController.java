package org.example.controller.userController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BorrowBookBO;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowBookDTO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.dto.tm.UserBorrowTm;
import org.example.util.SystemAlert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblBorrowBookId;

    @FXML
    private Label lblBorrowBookName;

    @FXML
    private Label lblBorrowBookReturnDate;

    @FXML
    private TableView<UserBorrowTm> tblBook;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXComboBox<String> cmbBranch;

    @FXML
    private JFXTextField txtBorrowId;

    @FXML
    private JFXTextField txtbookId;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtBranchLoaction;

    @FXML
    private Label txtBrrrowingDate;

    @FXML
    private Label txtReturnDate;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);
    /*BorrowBookBO borrowBookBO = (BorrowBookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);*/



    public void initialize() throws SQLException, ClassNotFoundException {
        /*tblBook.getItems().clear();
        cmbBranch.getItems().clear();
        List<BranchDTO> all = branchBO.getAll();

        for (BranchDTO branchDTO : all) {
            cmbBranch.getItems().add(branchDTO.getLocation());
        }

        String nextId = borrowBookBO.generateNextId();
        txtBorrowId.setText(nextId);
        LocalDate today = LocalDate.now();
        txtBrrrowingDate.setText(today.toString());
        txtReturnDate.setText(today.plusDays(15).toString());

        clearFields();*/


    }

    private void clearFields() {
        txtbookId.clear();
        txtEmail.clear();
        txtBookName.clear();
        txtBranchLoaction.clear();
        txtBorrowId.clear();


    }

    @FXML
    void borrowOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String txtBorrowIdText = txtBorrowId.getText();
        String txtEmailText = txtEmail.getText();
        String txtBookNameText = txtBookName.getText();
        String txtBranchLoactionText = txtBranchLoaction.getText();
        String txtbookIdText = txtbookId.getText();
        String txtBrrrowingDateText = txtBranchLoaction.getText();
        String txtReturnDateText = txtReturnDate.getText();


        if (txtBorrowIdText.isEmpty() || txtEmailText.isEmpty() || txtBookNameText.isEmpty() || txtBranchLoactionText.isEmpty() || txtbookIdText.isEmpty() || txtBrrrowingDateText.isEmpty() || txtReturnDateText.isEmpty()) {
            new SystemAlert(Alert.AlertType.INFORMATION, "Success", "Please fill all fields", ButtonType.OK).show();
        }else {
            LocalDate today = LocalDate.now();
            LocalDate returnDate = LocalDate.parse(txtReturnDateText);

            UserDTO user = userBO.search(txtEmailText);
            BookDTO book = bookBO.search(txtbookIdText);

            BorrowBookDTO borrowDTO = new BorrowBookDTO(txtBorrowIdText, user, book, today, returnDate, "Pending");




            new Alert(Alert.AlertType.INFORMATION, "Book Borrowed Successfully").show();
        }


    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (lblBorrowBookId.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Select Book First").show();
        }else {
            txtbookId.setText(lblBorrowBookId.getText());
            txtBookName.setText(lblBorrowBookName.getText());
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (cmbBranch.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Select Branch First").show();
        } else{
            String location = cmbBranch.getValue();
            BranchDTO branchDTO = branchBO.searchByLocation(location);
            List<BookDTO> bookDTOS = bookBO.searchByTitle(txtSearch.getText(), branchDTO.getId());

            tblBook.getItems().clear();
            for (BookDTO bookDTO : bookDTOS) {
                tblBook.getItems().add(new UserBorrowTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre()));
            }
            setCellValueFactory();
        }

    }
    private void setCellValueFactory() {
        tblBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

}
