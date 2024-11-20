package com.CyberMallBackEnd.CyberMallBackEnd.service;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Supplier;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllsuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getsupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId);
    }

    public Supplier savesupplier( String supplierName, String email,String phoneNumber, String address) {
        Supplier supplier = new Supplier();


        supplier.setSupplierName(supplierName);
        supplier.setEmail(email);
        supplier.setPhoneNumber(phoneNumber);
        supplier.setAddress(address);

        return supplierRepository.save(supplier);
    }
    public boolean updatesupplier(Long supplierId, String supplierName, String email, String phoneNumber, String address) {
        Optional<Supplier> optionalsupplier = supplierRepository.findById(supplierId);

        if (optionalsupplier.isPresent()) {
            Supplier supplier = optionalsupplier.get();
            supplier.setSupplierName(supplierName);
            supplier.setEmail(email);
            supplier.setPhoneNumber(phoneNumber);
            supplier.setAddress(address);
            supplierRepository.save(supplier);
            return true;
        } else {
            return false;
        }
    }

    public void deletesupplier(Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
