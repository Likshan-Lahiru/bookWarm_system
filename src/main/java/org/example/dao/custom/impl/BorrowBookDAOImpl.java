package org.example.dao.custom.impl;

import org.example.dao.custom.BorrowBookDAO;
import org.example.entity.BorrowBooks;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BorrowBookDAOImpl implements BorrowBookDAO {
    @Override
    public boolean save(BorrowBooks ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(BorrowBooks ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public BorrowBooks search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<BorrowBooks> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<BorrowBooks> getPendingList() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<BorrowBooks> getUserList(String email) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<BorrowBooks> getNotReturnList(LocalDate date) throws SQLException, ClassNotFoundException {
        return null;
    }
}
