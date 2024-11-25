package com.CyberMallBackEnd.CyberMallBackEnd.service.impl;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.SupplierDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Supplier;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.SupplierRepository;
import com.CyberMallBackEnd.CyberMallBackEnd.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ModelMapper mapper;


    public List<SupplierDto> getAllsuppliers() {
        List<SupplierDto> supplierDtos=new ArrayList<>();
        for(Supplier supplier: supplierRepository.findAll()){
            if(supplier!=null){
                supplierDtos.add(mapper.map(supplier, SupplierDto.class));

            }

        }
        return supplierDtos;
    }

    public Optional<SupplierDto> getsupplierById(Long supplierId) {

        for(SupplierDto supplierDto:getAllsuppliers()){
            if(supplierId.equals(supplierDto.getSupplierId())){
                return Optional.of(supplierDto);
            }
        }


        return null;
    }

    public Supplier savesupplier( String supplierName, String email,String phoneNumber, String address) {
        Supplier supplier = new Supplier();


        supplier.setSupplierName(supplierName);
        supplier.setEmail(email);
        supplier.setPhoneNumber(phoneNumber);
        supplier.setAddress(address);

        return  supplierRepository.save(supplier);
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
