package com.learning.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.learning.models.ProductModel;
import com.learning.models.SupplierModel;

import jakarta.websocket.server.PathParam;

public interface ProductRepository extends CrudRepository<ProductModel, Integer>{
    @Query("SELECT p FROM ProductModel p WHERE p.name = :name")
    public ProductModel findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM ProductModel p WHERE p.name LIKE :name")
    public List<ProductModel> findProductByNameLike(@PathParam("name") String name);
    
    @Query("SELECT p FROM ProductModel p WHERE p.category.id = :categoryId")
    public List<ProductModel> findProductByCategoryId(@PathParam("categoryId") int categoryId);

    @Query("SELECT p FROM ProductModel p WHERE :supplier MEMBER OF p.suppliers")
    public List<ProductModel> findProductBySupplier(@PathParam("supplier") SupplierModel supplier);
}
