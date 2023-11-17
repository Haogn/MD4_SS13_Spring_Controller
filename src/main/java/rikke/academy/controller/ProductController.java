package rikke.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rikke.academy.model.entity.Category;
import rikke.academy.model.entity.Product;
import rikke.academy.model.service.CategoryService;
import rikke.academy.model.service.ProductService;

import java.util.List;

@Controller

public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/product")
    public String index(Model model){
        List<Product> list = productService.findAll();
        model.addAttribute("list",list);
        return "product/index";
    }
    @RequestMapping("/add-product")
    public String add(Model model){
        List<Category> list = categoryService.findAll();
        Product product = new Product();
        model.addAttribute("list", list);
        model.addAttribute("product", product);
        return "product/add";
    }
    @RequestMapping("/create-product")
    public String create(@ModelAttribute("product") Product product){
        productService.create(product);
        return "redirect:/product";
    }

    @RequestMapping("delete-pro/{id}")
    public String delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/product";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id , Model model) {
        List<Category> categoryList = categoryService.findAll();
        Product product = productService.findById(id);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product",product);
        return "product/edit";
    }

    @RequestMapping("/")
    public String update(){
        return "redirect:/product";
    }
}
