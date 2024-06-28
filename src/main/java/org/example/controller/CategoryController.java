package org.example.controller;

import org.example.model.Category;
import org.example.service.imp.CategoryService;
import org.example.service.imp.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("")
    public ModelAndView listCategory(@PageableDefault(value = 5)Pageable pageable){
        Page<Category> categories = categoryService.findAll(pageable);
        return new ModelAndView("/category/list","categories",categories);
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/category/new"
                ,"category",new Category());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("redirect:/category");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")  Long id){
        categoryService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/category");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormUpdate(@PathVariable("id") Long id){
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/category/edit"
                    ,"category",category.get());
            return modelAndView;
        }
        return new ModelAndView("redirect:/category");
    }
    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute Category category ){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("redirect:/category");
        return modelAndView;
    }
    @GetMapping("/search")
    public String search(
            @RequestParam(value = "search", defaultValue = "") String search,
            Model model,
            @PageableDefault(value = 5) Pageable pageable
    ) {
        if (!search.isEmpty()) {
            Page<Category> categories = categoryService.findByNameEndingWith(pageable, search);
            model.addAttribute("categories", categories);
            return "/category/search";
        }
        return "redirect:/category";
    }
    @GetMapping("/sort")
    public ModelAndView sort(@RequestParam String sort) {
        ModelAndView modelAndView = new ModelAndView("/category/sort");
        Page<Category> categories = categoryService.findAllAndSort(sort);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

}
