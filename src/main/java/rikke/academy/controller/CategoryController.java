package rikke.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rikke.academy.model.entity.Category;
import rikke.academy.model.service.CategoryService;
import rikke.academy.model.service.CategoryService_Ipml;

import java.util.List;
@Controller
public class CategoryController {
    @Autowired
   private CategoryService categoryService ;
    @RequestMapping("/category")
    public String index(Model model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("list_category", list);
        return "category/index";
    }

    @RequestMapping("/add-categoty")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute(category);
        return "category/add";
    }

    @RequestMapping("/create-create")
    public String create(@ModelAttribute("category") Category category){
        categoryService.create(category);
        return "redirect:/category";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/category";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category_edit", category);
        return "category/edit";
    }

    @RequestMapping("/update-category")
    public String update(@ModelAttribute("category_edit") Category category){
        categoryService.update(category, category.getCategotyId());
        return "redirect:/category";
    }

}
