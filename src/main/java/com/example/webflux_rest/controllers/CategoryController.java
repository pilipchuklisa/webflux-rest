package com.example.webflux_rest.controllers;

import com.example.webflux_rest.domain.Category;
import com.example.webflux_rest.services.CategoryService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createCategory(@RequestBody Publisher<Category> category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Category> getCategoryById(@PathVariable Mono<Long> id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Category> updateCategoryById(@PathVariable Mono<Long> id, @RequestBody Mono<Category> category) {
        return categoryService.updateCategoryById(id, category);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Category> patchCategoryById(@PathVariable Mono<Long> id, @RequestBody Mono<Category> category) {
        return categoryService.patchCategoryById(id, category);
    }
}
