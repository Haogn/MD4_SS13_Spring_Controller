package rikke.academy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import rikke.academy.model.dao.CategoryDAO;
import rikke.academy.model.dao.CategoryDAO_Ipml;
import rikke.academy.model.entity.Category;

import java.util.List;

@Service
public class CategoryService_Ipml implements CategoryService{
    @Autowired
   private CategoryDAO categoryDAO ;
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public Boolean delete(Integer id) {
        return categoryDAO.delete(id);
    }

    @Override
    public Boolean update(Category category, Integer id) {
        return categoryDAO.update(category,id);
    }
}
