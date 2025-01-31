package com.example.webflux_rest.services;

import com.example.webflux_rest.domain.Category;
import com.example.webflux_rest.repositories.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> getCategoryById(Mono<Long> id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Mono<Void> createCategory(Publisher<Category> category) {
        return categoryRepository.saveAll(category).then();
    }

    @Override
    public Mono<Category> updateCategoryById(Mono<Long> id, Mono<Category> category) {
        return id.zipWith(category)
                .flatMap(tuple -> {
                    Category c = tuple.getT2();
                    c.setId(tuple.getT1());
                    return categoryRepository.save(c);
                });
    }

    @Override
    public Mono<Category> patchCategoryById(Mono<Long> id, Mono<Category> category) {
        return categoryRepository.findById(id)
                .zipWith(category)
                .map(tuple -> {
                    Category found = tuple.getT1();
                    Category patch = tuple.getT2();

                    if (patch.getDescription() != null) {
                        found.setDescription(patch.getDescription());
                    }

                    return found;
                });
    }
}
