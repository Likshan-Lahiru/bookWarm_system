package org.example.dao;

import org.example.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ADMIN, USER, BOOK, BRANCH, BORROW
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ADMIN:
                return new AdminDAOImpl();
            case USER:
                return new UserDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case BORROW:
                return new BorrowBookDAOImpl();
            default:
                return null;
        }
    }
}