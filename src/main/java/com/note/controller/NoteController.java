package com.note.controller;

import com.note.model.Category;
import com.note.model.Note;
import com.note.service.CategoryService;
import com.note.service.impl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {
    @Autowired
    private NoteServiceImpl noteService;

    @Autowired
    private CategoryService categoryService;

//    @ModelAttribute ("categorys")
//    public List<Category> categories(Pageable pageable) {
//         Page<Category> categoryPage = categoryService.findAll(pageable);
//         return  categoryPage.getContent();
//    }
    @ModelAttribute("categorys")
    public  Page<Category> categories(Pageable pageable){
        return  categoryService.findAll(pageable);
    }

    @GetMapping ("/notes")
    public ModelAndView list (@PageableDefault(size = 5) Pageable pageable,
                              @RequestParam ("mid") Optional<String> mid) {
        ModelAndView modelAndView = new ModelAndView();
        //Page<Note> notes = noteService.findAllByTitle(search.get) ;
        Page<Note> notes;
        if (mid.isPresent()) {
            notes = noteService.findAllByTitleContainingOrContentContaining(mid.get(),mid.get(), pageable);
        } else {
            notes = noteService.findAll(pageable);
        }
        modelAndView.setViewName("note/list");
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @GetMapping ("/create-note")
    public ModelAndView create () {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("note/create");
        mv.addObject("note", new Note());
        return mv;
    }

    @PostMapping ("/save-note")
    public ModelAndView save (@ModelAttribute("note") Note note) {
        ModelAndView mv = new ModelAndView();
        noteService.save(note);
        mv.setViewName("note/create");
        mv.addObject("msg", "create success");
        mv.addObject("note", new Note());
        return mv;
    }

    @GetMapping ("/edit-note/{id}")
    public ModelAndView edit (@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
       Optional<Note> note= noteService.findById(id);
        mv.setViewName("note/edit");
        mv.addObject("note",note);
        return mv;
    }

    @PostMapping ("/update-note")
    public ModelAndView update (@ModelAttribute("note") Note note) {
        ModelAndView mv = new ModelAndView();
        noteService.save(note);
        mv.setViewName("note/edit");
        mv.addObject("msg", "update success");
        mv.addObject("note", new Note());
        return mv;
    }

    @GetMapping ("/delete-note/{id}")
    public ModelAndView delete (@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
       // Optional<Note> note = noteService.findById(id);
        noteService.delete(id);
        mv.setViewName("redirect:/notes");
      //  mv.addObject("note", note);
        return mv;
    }

//    @PostMapping ("/delete-note")
//    public ModelAndView delete (@ModelAttribute("note") Note note) {
//        ModelAndView mv = new ModelAndView();
//        noteService.delete(note.getId());
//        mv.setViewName("note/delete");
//        mv.addObject("msg", "delete success");
//        return mv;
//    }

    @GetMapping ("/view-note/{id}")
    public ModelAndView view (@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Optional<Note> note = noteService.findById(id);
        mv.setViewName("note/view");
        mv.addObject("note", note);
        return mv;
    }
}
