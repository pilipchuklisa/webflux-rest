package com.example.webflux_rest.services;

import com.example.webflux_rest.domain.Vendor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {

    Flux<Vendor> getAllVendors();

    Mono<Vendor> getVendorById(Long id);

    Mono<Void> createVendor(Publisher<Vendor> vendor);

    Mono<Vendor> updateVendorById(Mono<Long> id, Mono<Vendor> vendor);

    Mono<Vendor> patchVendorById(Mono<Long> id, Mono<Vendor> vendor);
}
