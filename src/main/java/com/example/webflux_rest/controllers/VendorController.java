package com.example.webflux_rest.controllers;

import com.example.webflux_rest.domain.Vendor;
import com.example.webflux_rest.services.VendorService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createVendor(@RequestBody Publisher<Vendor> vendor) {
        return vendorService.createVendor(vendor);
    }

    @GetMapping("/{ip}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> updateVendorById(@PathVariable Mono<Long> id, @RequestBody Mono<Vendor> vendor) {
        return vendorService.updateVendorById(id, vendor);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> patchVendorById(@PathVariable Mono<Long> id, @RequestBody Mono<Vendor> vendor) {
        return vendorService.patchVendorById(id, vendor);
    }
}
