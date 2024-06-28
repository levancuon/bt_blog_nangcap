package org.example.repository;

import org.example.model.Blog;
import org.example.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Page<Blog> findByCategoryId(Pageable pageable, Long categoryId);

}
