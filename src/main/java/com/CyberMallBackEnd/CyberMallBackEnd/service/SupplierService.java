package com.CyberMallBackEnd.CyberMallBackEnd.service;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    void deletesupplier(Long supplierId);
    List<Supplier> getAllsuppliers();
    Optional<Supplier> getsupplierById(Long supplierId);
    Supplier savesupplier( String supplierName, String email,String phoneNumber, String address);
    boolean updatesupplier(Long supplierId, String supplierName, String email, String phoneNumber, String address);

}
