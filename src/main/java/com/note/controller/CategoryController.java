package com.note.controller;

import com.note.model.Category;
import com.note.model.Note;
import com.note.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/categorys")
    public ModelAndView list (Pageable pageable) {
        ModelAndView mv = new ModelAndView("category/list");
        Iterable<Category> category = categoryService.findAll(pageable);
        mv.addObject("categorys",category );
        return mv;
    }

    @GetMapping ("/create-category")
    public ModelAndView create (Category category) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category/create");
        mv.addObject("category",new Category());
        return mv;
    }

    @PostMapping ("/save-category")
    public ModelAndView save (@ModelAttribute("category") Category category) {
        ModelAndView mv = new ModelAndView();
        categoryService.save(category);
        mv.setViewName("category/create");
        mv.addObject("msg", "create success");
        mv.addObject("category", new Category());
        return mv;
    }

    @GetMapping ("/edit-category/{id}")
    public ModelAndView edit (@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Optional<Category> category = categoryService.findById(id);
        mv.setViewName("category/edit");
        mv.addObject("category", category);
        return mv;
    }

    @PostMapping ("/update-category")
    public ModelAndView update (@ModelAttribute("category") Category category) {
        ModelAndView mv = new ModelAndView();
        categoryService.save(category);
        mv.setViewName("category/edit");
        mv.addObject("msg", "update success");
        mv.addObject("category", new Category());
        return mv;
    }

    @GetMapping ("/delete-category/{id}")
    public ModelAndView delete (@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
//        Optional<Category> category = categoryService.findById(id);
        categoryService.delete(id);
        mv.setViewName("redirect:/categorys");
        //mv.addObject("category", category);
        return mv;
    }






}
