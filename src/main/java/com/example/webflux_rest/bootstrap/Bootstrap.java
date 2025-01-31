package com.example.webflux_rest.bootstrap;

import com.example.webflux_rest.domain.Category;
import com.example.webflux_rest.domain.Vendor;
import com.example.webflux_rest.repositories.CategoryRepository;
import com.example.webflux_rest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final VendorRepository vendorRepository;
    private final CategoryRepository categoryRepository;

    public Bootstrap(VendorRepository vendorRepository, CategoryRepository categoryRepository) {
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        loadCategories();
        loadVendors();
    }

    private void loadCategories() {
        categoryRepository
                .deleteAll()
                .thenMany(
                        Flux.just("Fruits", "Nuts", "Breads", "Meats", "Eggs")
                                .map(name -> new Category(null, name))
                                .flatMap(categoryRepository::save))
                .then(categoryRepository.count())
                .subscribe(categories -> log.debug("{} categories saved", categories));
    }

    private void loadVendors() {
        vendorRepository
                .deleteAll()
                .thenMany(
                        Flux.just(
                                Vendor.builder().firstName("Joe").lastName("Buck").build(),
                                Vendor.builder().firstName("Michael").lastName("Weston").build(),
                                Vendor.builder().firstName("Jessie").lastName("Waters").build(),
                                Vendor.builder().firstName("Jimmy").lastName("Buffet").build()
                        ).flatMap(vendorRepository::save))
                .then(vendorRepository.count())
                .subscribe(vendors -> log.debug("{} vendors saved", vendors));
    }
}
