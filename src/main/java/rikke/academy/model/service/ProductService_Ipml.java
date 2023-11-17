package rikke.academy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikke.academy.model.dao.ProductDAO;
import rikke.academy.model.entity.Product;

import java.util.List;
@Service
public class ProductService_Ipml implements ProductService{
    @Autowired
    private ProductDAO productDAO;
    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public Boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Boolean delete(Integer id) {
        return productDAO.delete(id);
    }

    @Override
    public Boolean update(Product product, Integer id) {
        return productDAO.update(product,id);
    }
}
