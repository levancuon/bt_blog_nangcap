package org.example.service;

import org.example.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService extends IGenericService<Category> {
    Page<Category> findAll(Pageable pageable);


    Page<Category> findByNameEndingWith(Pageable pageable, String search);

    Page<Category> findAllAndSort(String sort);
}
