package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.UserDAO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.entity.BorrowBooks;
import org.example.entity.Branch;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDaoImpl = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        BranchDTO branch = dto.getBranch();
        Branch branch1 = new Branch(branch.getId(), branch.getLocation(), branch.getTelephone(), branch.getEmail(), branch.getAddress(),null,null);
        List<BorrowBooks> borrow = new ArrayList<>();
        return userDaoImpl.save(new User(dto.getName(), dto.getEmail(), dto.getPassword(),dto.getTelephone(), branch1,borrow));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
        User search = userDaoImpl.search(id);

        if (search == null){
            return null;
        } else {
            return new UserDTO(search.getName(), search.getEmail(), search.getPassword(),search.getTelephone(), new BranchDTO(search.getBranch().getId(), search.getBranch().getLocation(), search.getBranch().getTelephone(), search.getBranch().getEmail(), search.getBranch().getAddress()));
        }
    }

    @Override
    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<UserDTO> searchUserByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public UserDTO searchUserByEmail(String email) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public UserDTO searchUserByTelephone(String telephone) throws SQLException, ClassNotFoundException {
        return null;
    }
}
