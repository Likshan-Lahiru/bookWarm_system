package org.example.bo;


import org.example.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes {
        USER, BOOK, BRANCH, ADMIN, BORROW
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case BOOK:
                return new BookBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case ADMIN:
                return new AdminBOImpl();
            case BORROW:
                return new BorrowBookBOImpl();
            default:
                return null;
        }
    }
}
