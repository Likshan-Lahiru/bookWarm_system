package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.BorrowBooks;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BorrowBookDAO extends CrudDAO<BorrowBooks> {

}
