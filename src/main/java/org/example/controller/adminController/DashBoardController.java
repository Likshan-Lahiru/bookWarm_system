package org.example.controller.adminController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.BookDTO;
import org.example.dto.tm.UserBorrowTm;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DashBoardController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;


    @FXML
    private Label lblMemberCount;

    @FXML
    private Label lblBookCount;

    @FXML
    private Label lblBranchCount;

    @FXML
    private TableView<UserBorrowTm> tblBook;
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);



    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateandTime();
        loadTables();
        loadTables();
        setCellValueFactory();
        setLabels();
    }

    private void setCellValueFactory() {
        tblBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

    private void setLabels() throws SQLException, ClassNotFoundException {
        lblMemberCount.setText(String.valueOf(userBO.getAll().size()));
        lblBookCount.setText(String.valueOf(bookBO.getAll().size()));
        lblBranchCount.setText(String.valueOf(branchBO.getAll().size()));


    }

    private void loadTables() throws SQLException, ClassNotFoundException {
        List<BookDTO> bookDTOS = bookBO.getAll();
        tblBook.getItems().clear();

        for (BookDTO bookDTO : bookDTOS) {
            tblBook.getItems().add(new UserBorrowTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre()));
        }
    }

    private void loadDateandTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalTime.now().format(format2));
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}