package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Supplier;
import com.CyberMallBackEnd.CyberMallBackEnd.service.impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierServiceImpl supplierServiceImpl;

    @GetMapping
    public List<Supplier> getAllsuppliers() {
        return supplierServiceImpl.getAllsuppliers();
    }


    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getsupplierById(@PathVariable Long supplierId) {
        Optional<Supplier> supplier = supplierServiceImpl.getsupplierById(supplierId);
        return supplier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addS")
    public ResponseEntity<String> savesupplier(@RequestBody Supplier supplier) {
        supplierServiceImpl.savesupplier(supplier.getSupplierName(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getAddress());
        return ResponseEntity.ok("supplier saved successfully!");
    }

    // PUT method for updating an existing supplier
    @PutMapping("/{supplierId}")
    public ResponseEntity<String> updatesupplier(@PathVariable Long supplierId, @RequestBody Supplier supplier) {
        boolean isUpdated = supplierServiceImpl.updatesupplier(supplierId, supplier.getSupplierName(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getAddress());
        if (isUpdated) {
            return ResponseEntity.ok("Supplier updated successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If supplier with the given ID is not found
        }
    }


    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deletesupplier(@PathVariable Long supplierId) {
        supplierServiceImpl.deletesupplier(supplierId);
        return ResponseEntity.noContent().build();
    }
}
