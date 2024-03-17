package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.BorrowBookDTO;

import java.sql.SQLException;
import java.util.List;

public interface BorrowBookBO extends SuperBO {
    boolean save(BorrowBookDTO dto) throws SQLException, ClassNotFoundException;
    boolean update(BorrowBookDTO dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    BorrowBookDTO search(String id) throws SQLException, ClassNotFoundException;
    List<BorrowBookDTO> getAll() throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;
}
