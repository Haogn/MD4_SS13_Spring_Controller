package rikke.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import rikke.academy.model.entity.Category;
import rikke.academy.model.entity.Product;
import rikke.academy.model.service.CategoryService;
import rikke.academy.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    public String create(@ModelAttribute("product") Product product,
                         @RequestParam("fileImage")MultipartFile file ,
                         HttpServletRequest request){
        // B1 : Khai báo biến lưu trữ path
        String path = request.getServletContext().getRealPath("uploads/images");
        String fileName = file.getOriginalFilename();
        // lưu trữ
        File destination = new File(path+"/"+fileName);
        try {
            file.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setImage(fileName);
        System.out.println(fileName);
        productService.create(product);
        return "redirect:/product";
    }

    @RequestMapping("delete-pro/{id}")
    public String delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/product";
    }
    @RequestMapping("/edit-pro/{id}")
    public String edit(@PathVariable("id") Integer id , Model model) {
        List<Category> categoryList = categoryService.findAll();
        Product product = productService.findById(id);
        System.out.println(product.getImage());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product",product);
        return "product/edit";
    }

    @RequestMapping("/update-product/{id}")
    public String update( @PathVariable("id") Integer id,
                          @ModelAttribute("product") Product product,
                         HttpServletRequest request,
                         @RequestParam("img_upload") MultipartFile file){
        // B1 : Khai báo biến lưu trữ path
        String path = request.getServletContext().getRealPath("uploads/images");

        // Nếu có file được chọn , thực hiện lưu trữ hình ảnh
        if ( file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            // Lưu trữ
            File destination = new File(path+"/"+fileName);
            try {
                file.transferTo(destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImage(fileName);
            System.out.println(fileName);
        } else {
            // người dùng không chọn hình ảnh mới , giữ nguyên giá trị cũ của image
            Product existingProduct = productService.findById(id);
            product.setImage(existingProduct.getImage());
        }
        productService.update(product,id);
        return "redirect:/product";
    }
}
