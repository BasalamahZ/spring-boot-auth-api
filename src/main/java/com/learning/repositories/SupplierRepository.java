package com.learning.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.learning.models.SupplierModel;

public interface SupplierRepository extends CrudRepository<SupplierModel, Integer> {
    SupplierModel findByEmail(String email);

    List<SupplierModel> findByNameContains(String name);
}
