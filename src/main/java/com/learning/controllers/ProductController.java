package com.learning.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.configs.ErrorParsingUtility;
import com.learning.dto.ProductData;
import com.learning.helpers.ResponseData;
import com.learning.helpers.SearchData;
import com.learning.models.ProductModel;
import com.learning.models.SupplierModel;
import com.learning.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<ProductModel>> create(@Valid @RequestBody ProductData productData,
            Errors errors) {

        ResponseData<ProductModel> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.badRequest().body(responseData);
        }

        ProductModel productModel = modelMapper.map(productData, ProductModel.class);

        responseData.setStatus(true);
        responseData.setPayload(productService.save(productModel));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<ProductModel>>> findAll() {
        ResponseData<Iterable<ProductModel>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("success");
        responseData.setPayload(productService.findAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<ProductModel>> findOne(@PathVariable("id") Integer id) {
        ResponseData<ProductModel> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(productService.findOne(id));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<ProductModel>> update(@Valid @RequestBody ProductModel product, Errors errors) {
        ResponseData<ProductModel> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.badRequest().body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ResponseData<Object>> addSupplier(@RequestBody SupplierModel supplierModel,
            @PathVariable("productId") Integer productId) {
        ResponseData<Object> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("success");
        responseData.setPayload(productService.findAll());
        return ResponseEntity.ok(responseData);

    }

    @PostMapping("/search/name")
    public ResponseEntity<ResponseData<ProductModel>> getProductByName(@RequestBody SearchData searchData) {
        ResponseData<ProductModel> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("success");
        responseData.setPayload(productService.findByProductName(searchData.getSearchKey()));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/search/nameLike")
    public ResponseEntity<ResponseData<List<ProductModel>>> getProductByNameLike(@RequestBody SearchData searchData) {
        ResponseData<List<ProductModel>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("success");
        responseData.setPayload(productService.findByProductNameLike(searchData.getSearchKey()));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("search/category/{categoryId}")
    public ResponseEntity<ResponseData<List<ProductModel>>> getProductByCategoryId(
            @PathVariable("categoryId") int categoryId) {
        ResponseData<List<ProductModel>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("success");
        responseData.setPayload(productService.findByCategoryId(categoryId));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("search/supplier/{supplierId}")
    public ResponseEntity<ResponseData<List<ProductModel>>> getProductBySupplier(
            @PathVariable("supplierId") int supplierId) {
        ResponseData<List<ProductModel>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("success");
        responseData.setPayload(productService.findBySupplier(supplierId));
        return ResponseEntity.ok(responseData);
    }
}
