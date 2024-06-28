package org.example.controller;

import org.example.model.Blog;
import org.example.model.Category;
import org.example.service.imp.CategoryService;
import org.example.service.imp.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> listCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ModelAndView listBlog(
            @PathVariable(value = "id") Long id,@PageableDefault(value = 5)
            Pageable pageable
    ) {
        Page<Blog> blogs = blogService.findByCategoryId(pageable, id);
        ModelAndView modelAndView = new ModelAndView("/blog/list", "blog", blogs);
        return modelAndView;
    }
//    @GetMapping("/allblog")
//    public ModelAndView listBlog(){
//        Iterable<Blog> blog = blogService.findAll();
//        ModelAndView modelAndView = new ModelAndView("/blog/list"
//                ,"blog",blog);
//        return modelAndView;
//    }
    @GetMapping("/content/{id}")
    public ModelAndView content(@PathVariable(value = "id") Long id){
        System.out.println("cc");
       Optional<Blog> blog = blogService.findById(id);
       if(blog.isPresent()){
           ModelAndView modelAndView = new ModelAndView("/blog/content"
                   ,"blog",blog.get());
           return modelAndView;
       }
       return new ModelAndView("redirect:/category");
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/blog/new"
                ,"blog",new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute Blog blog) {
        blog.setCreatedAt(LocalDateTime.now());
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/category");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        blogService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/category");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormUpdate(@PathVariable("id") Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blog/edit"
                    , "blog", blog.get());
            return modelAndView;
        }
        return new ModelAndView("redirect:/category");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute Blog blog) {
        blog.setUpdatedAt(LocalDateTime.now());
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/category");
        return modelAndView;
    }

}
