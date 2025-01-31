package com.example.webflux_rest.services;

import com.example.webflux_rest.domain.Category;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<Category> getAllCategories();

    Mono<Category> getCategoryById(Mono<Long> id);

    Mono<Void> createCategory(Publisher<Category> category);

    Mono<Category> updateCategoryById(Mono<Long> id, Mono<Category> category);

    Mono<Category> patchCategoryById(Mono<Long> id, Mono<Category> category);
}
