package com.learning.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.configs.ErrorParsingUtility;
import com.learning.dto.CategoryData;
import com.learning.helpers.ResponseData;
import com.learning.helpers.SearchData;
import com.learning.models.CategoryModel;
import com.learning.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<CategoryModel>> create(@Valid @RequestBody CategoryData categoryData,
            Errors errors) {
        ResponseData<CategoryModel> responseData = new ResponseData<>();
        try {

            if (errors.hasErrors()) {
                responseData.setMessages(ErrorParsingUtility.parse(errors));
                responseData.setStatus(false);
                responseData.setPayload(null);
                return ResponseEntity.badRequest().body(responseData);
            }

            CategoryModel categoryModel = modelMapper.map(categoryData, CategoryModel.class);

            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(categoryService.save(categoryModel));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<CategoryModel>>> findAll() {
        ResponseData<Iterable<CategoryModel>> responseData = new ResponseData<>();
        try {
            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(categoryService.findAll());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<CategoryModel>> findOne(@PathVariable("id") int id) {
        ResponseData<CategoryModel> responseData = new ResponseData<>();
        try {
            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(categoryService.findOne(id));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<CategoryModel>> update(@Valid @RequestBody CategoryData categoryData,
            Errors errors) {
        ResponseData<CategoryModel> responseData = new ResponseData<>();
        try {
            if (errors.hasErrors()) {
                responseData.setMessages(ErrorParsingUtility.parse(errors));
                responseData.setStatus(false);
                responseData.setPayload(null);
                return ResponseEntity.badRequest().body(responseData);
            }

            CategoryModel categoryModel = modelMapper.map(categoryData, CategoryModel.class);

            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(categoryService.save(categoryModel));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping("/search/{size}/{page}")
    public ResponseEntity<ResponseData<Iterable<CategoryModel>>> getCategoryByName(@RequestBody SearchData searchData,
            @PathVariable("size") int size, @PathVariable("page") int page) {
        ResponseData<Iterable<CategoryModel>> responseData = new ResponseData<>();
        Pageable pageable = PageRequest.of(page, size);
        try {
            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(categoryService.findByName(searchData.getSearchKey(), pageable));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }
}
