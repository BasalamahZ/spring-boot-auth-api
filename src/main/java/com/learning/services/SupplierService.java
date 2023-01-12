package com.learning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.models.SupplierModel;
import com.learning.repositories.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierModel save(SupplierModel supplier) {
        return supplierRepository.save(supplier);
    }

    public Iterable<SupplierModel> findAll() {
        return supplierRepository.findAll();
    }

    public SupplierModel findOne(int id) {
        return supplierRepository.findById(id).get();
    }

    public SupplierModel findByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    public List<SupplierModel> findByNameContains(String name) {
        return supplierRepository.findByNameContains(name);
    }
}
