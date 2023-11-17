package rikke.academy.model.dao;

import org.springframework.stereotype.Repository;
import rikke.academy.model.entity.Category;
import rikke.academy.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAO_Ipml implements CategoryDAO {
    @Override
    public List<Category> findAll() {
        Connection connection = null;
        List<Category> list = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setCategotyId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setStatus(rs.getBoolean("status"));
                list.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }
        return list;
    }

    @Override
    public Category findById(Integer id) {

        Connection connection = null;
        Category category = new Category();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category Where category_id = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                category.setCategotyId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }
        return category;
    }

    @Override
    public Boolean create(Category category) {
        Boolean isCheck = false;
        Connection connection = null;

        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO category(category_name , status) VALUES (?,?)");
            preparedStatement.setString(1,category.getCategoryName());
            preparedStatement.setBoolean(2,category.getStatus());
            int check  = preparedStatement.executeUpdate();
            if (check > 0 ){
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }
        return isCheck;
    }

    @Override
    public Boolean delete(Integer id) {
        Boolean isCheck = false;
        Connection connection = null;

        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM category WHERE category_id = ?");
            preparedStatement.setInt(1,id);
            int check  = preparedStatement.executeUpdate();
            if (check > 0 ){
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }
        return isCheck;
    }

    @Override
    public Boolean update(Category category, Integer id) {
        Boolean isCheck = false ;
        Connection connection = null ;

        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE category SET category_name = ?, status = ? Where category_id=?");
            preparedStatement.setString(1,category.getCategoryName());
            preparedStatement.setBoolean(2,category.getStatus());
            preparedStatement.setInt(3,id);
            int check =preparedStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }

        return isCheck ;
    }
}
