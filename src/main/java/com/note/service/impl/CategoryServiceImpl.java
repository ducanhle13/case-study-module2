package com.note.service.impl;

import com.note.model.Category;
import com.note.repository.CategoryRepository;
import com.note.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {return categoryRepository.findById(id); }

    @Override
    public void save(Category category) {categoryRepository.save(category); }

    @Override
    public void delete(Long id) {categoryRepository.deleteById(id); }


}
