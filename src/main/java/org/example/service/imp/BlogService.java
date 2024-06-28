package org.example.service.imp;

import org.example.model.Blog;

import org.example.model.Category;
import org.example.repository.BlogRepository;
import org.example.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findByCategoryId(Pageable pageable, Long categoryId) {
        if (categoryId == null) {
            return blogRepository.findAll(pageable);
        }
        return blogRepository.findByCategoryId(pageable, categoryId);
    }
}
