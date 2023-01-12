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
import com.learning.dto.SupplierData;
import com.learning.helpers.ResponseData;
import com.learning.helpers.SearchData;
import com.learning.models.SupplierModel;
import com.learning.services.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<SupplierModel>> create(@Valid @RequestBody SupplierData supplierData,
            Errors errors) {

        ResponseData<SupplierModel> responseData = new ResponseData<>();
        try {
            if (errors.hasErrors()) {
                responseData.setMessages(ErrorParsingUtility.parse(errors));
                responseData.setStatus(false);
                responseData.setPayload(null);
                return ResponseEntity.badRequest().body(responseData);
            }

            SupplierModel supplierModel = modelMapper.map(supplierData, SupplierModel.class);

            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(supplierService.save(supplierModel));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<SupplierModel>>> findAll() {
        ResponseData<Iterable<SupplierModel>> responseData = new ResponseData<>();
        try {
            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(supplierService.findAll());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<SupplierModel>> findOne(@PathVariable("id") Integer id) {
        ResponseData<SupplierModel> responseData = new ResponseData<>();
        try {
            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(supplierService.findOne(id));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<SupplierModel>> update(@Valid @RequestBody SupplierData supplierData,
            Errors errors) {

        ResponseData<SupplierModel> responseData = new ResponseData<>();
        try {
            if (errors.hasErrors()) {
                responseData.setMessages(ErrorParsingUtility.parse(errors));
                responseData.setStatus(false);
                responseData.setPayload(null);
                return ResponseEntity.badRequest().body(responseData);
            }

            SupplierModel supplierModel = modelMapper.map(supplierData, SupplierModel.class);

            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(supplierService.save(supplierModel));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping("/search/email")
    public ResponseEntity<ResponseData<SupplierModel>> getSupplierByEmail(@RequestBody SearchData searchData) {
        ResponseData<SupplierModel> responseData = new ResponseData<>();
        try {
            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(supplierService.findByEmail(searchData.getSearchKey()));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping("/search/nameLike")
    public ResponseEntity<ResponseData<List<SupplierModel>>> getSupplierByName(@RequestBody SearchData searchData) {
        ResponseData<List<SupplierModel>> responseData = new ResponseData<>();
        try {
            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(supplierService.findByNameContains(searchData.getSearchKey()));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }
}
