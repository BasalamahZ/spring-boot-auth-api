package com.learning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.models.ProductModel;
import com.learning.models.SupplierModel;
import com.learning.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    // Create or Update
    public ProductModel save(ProductModel product) {
        return productRepository.save(product);
    }

    // Get All
    public Iterable<ProductModel> findAll() {
        return productRepository.findAll();
    }

    // Get By ID
    public ProductModel findOne(Integer id) {
        return productRepository.findById(id).get();
    }

    // Delete
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public void addSupplier(SupplierModel supplier, Integer productId) {
        ProductModel product = findOne(productId);
        if(product == null) {
            throw new RuntimeException("Product with ID "+productId+" not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    public ProductModel findByProductName(String name) {
        return productRepository.findProductByName(name);
    }

    public List<ProductModel> findByProductNameLike(String name) {
        return productRepository.findProductByNameLike("%"+name+"%");
    }

    public List<ProductModel> findByCategoryId(int categoryId) {
        return productRepository.findProductByCategoryId(categoryId);
    }

    public List<ProductModel> findBySupplier(int supplierId) {
        SupplierModel supplierModel = supplierService.findOne(supplierId);
        if(supplierModel == null) {
            throw new RuntimeException("Product with ID "+supplierId+" not found");
        }
        return productRepository.findProductBySupplier(supplierModel);
    }
}
