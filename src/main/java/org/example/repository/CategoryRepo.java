package org.example.repository;

import org.example.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category,Long> {
    Page<Category> findByNameEndingWith(Pageable pageable, String search);
}
