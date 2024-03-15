package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BookDAO;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean save(Book ent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ent);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Book ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Book search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Book> searchByTitle(String title, String branch) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Book> searchByBranch(String branch) throws SQLException, ClassNotFoundException {
        return null;
    }
}
