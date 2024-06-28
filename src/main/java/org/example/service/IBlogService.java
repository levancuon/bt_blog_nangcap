package org.example.service;

import org.example.model.Blog;
import org.example.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService extends IGenericService<Blog>{
    Page<Blog> findByCategoryId(Pageable pageable, Long categoryId);
}
