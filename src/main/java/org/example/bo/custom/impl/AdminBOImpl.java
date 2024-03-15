package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;

import java.sql.SQLException;
import java.util.List;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDaoImpl = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public boolean save(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDaoImpl.save(new Admin(dto.getName(),dto.getEmail(),dto.getTelephone(),dto.getPassword()));
    }

    @Override
    public boolean update(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AdminDTO search(String id) throws SQLException, ClassNotFoundException {
        Admin search = adminDaoImpl.search(id);

        if (search == null) {
            return null;
        } else {
            return new AdminDTO(search.getName(),search.getEmail(),search.getTelephone(),search.getPassword());
        }
    }

    @Override
    public List<AdminDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
