package rikke.academy.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikke.academy.model.entity.Category;
import rikke.academy.model.entity.Product;
import rikke.academy.model.service.CategoryService;
import rikke.academy.model.service.ProductService;
import rikke.academy.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAO_Ipml implements ProductDAO{
    @Autowired
    private CategoryService categoryService ;
    @Override
    public List<Product> findAll() {
        Connection connection = null;
        List<Product> list = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                Category category = categoryService.findById(rs.getInt("category_id"));
                product.setCategory(category);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Connection connection = null;
        Product product = new Product();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product where product_id = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                Category category = categoryService.findById(rs.getInt("category_id"));
                product.setCategory(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }
        return product;
    }

    @Override
    public Boolean create(Product product) {
        Boolean isCheck = false ;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (product_name , price, image , category_id  ) VALUES (?,?,?,?)");
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getImage());
            preparedStatement.setInt(4,product.getCategory().getCategotyId());

            int check = preparedStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true;
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
        Boolean isCheck = false ;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product where product_id = ?");
            preparedStatement.setInt(1,id);
            int check = preparedStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }
        return isCheck;
    }

    @Override
    public Boolean update(Product product, Integer id) {
        Boolean isCheck = false ;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET product_name = ? , price = ? , image = ? ,category_id = ? where product_id = ?");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3,product.getImage() != null ? product.getImage() : "");
            preparedStatement.setInt(4,product.getCategory().getCategotyId());
            preparedStatement.setInt(5,id);
            int check = preparedStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closConnection(connection);
        }
        return isCheck;
    }
}
