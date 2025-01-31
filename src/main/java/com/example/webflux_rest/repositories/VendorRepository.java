package com.example.webflux_rest.repositories;

import com.example.webflux_rest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends ReactiveMongoRepository<Vendor, Long> {
}
