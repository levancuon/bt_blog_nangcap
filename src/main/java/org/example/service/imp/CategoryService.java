package org.example.service.imp;

import org.example.model.Category;
import org.example.repository.CategoryRepo;
import org.example.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    @Override
    public Page<Category> findByNameEndingWith(Pageable pageable, String search) {
        return categoryRepo.findByNameEndingWith(pageable,search);
    }

    @Override
    public Page<Category> findAllAndSort(String sort) {
        String[] sortt = sort.split(" ");
        Sort sortS = Sort.by(sortt[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortt[0]);
        Pageable pageable = PageRequest.of(0, 1000, sortS);
        return categoryRepo.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepo.deleteById(id);
    }
}
