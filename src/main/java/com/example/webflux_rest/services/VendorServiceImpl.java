package com.example.webflux_rest.services;

import com.example.webflux_rest.domain.Vendor;
import com.example.webflux_rest.repositories.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Flux<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Mono<Vendor> getVendorById(Long id) {
        return vendorRepository.findById(id);
    }

    @Override
    public Mono<Void> createVendor(Publisher<Vendor> vendor) {
        return vendorRepository.saveAll(vendor).then();
    }

    @Override
    public Mono<Vendor> updateVendorById(Mono<Long> id, Mono<Vendor> vendor) {
        return id.zipWith(vendor)
                .flatMap(tuple -> {
                    Vendor v = tuple.getT2();
                    v.setId(tuple.getT1());
                    return vendorRepository.save(v);
                });
    }

    @Override
    public Mono<Vendor> patchVendorById(Mono<Long> id, Mono<Vendor> vendor) {
        return vendorRepository.findById(id)
                .zipWith(vendor)
                .map(tuple -> {
                    Vendor found = tuple.getT1();
                    Vendor patch = tuple.getT2();

                    if (patch.getFirstName() != null) {
                        found.setFirstName(patch.getFirstName());
                    }

                    if (patch.getLastName() != null) {
                        found.setLastName(patch.getLastName());
                    }

                    return found;
                });
    }
}
