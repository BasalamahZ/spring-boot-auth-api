package com.learning.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.configs.ErrorParsingUtility;
import com.learning.dto.AuthRequest;
import com.learning.dto.AuthResponse;
import com.learning.dto.RegisterRequest;
import com.learning.helpers.ResponseData;
import com.learning.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AuthResponse>> register(
        @Valid @RequestBody RegisterRequest request, Errors errors
        ) {
        ResponseData<AuthResponse> responseData = new ResponseData<>();
        try {
            if (errors.hasErrors()) {
                responseData.setStatus(false);
                responseData.setMessages(ErrorParsingUtility.parse(errors));
                responseData.setPayload(null);
                return ResponseEntity.badRequest().body(responseData);
            }

            // User user = modelMapper.map(request, User.class);

            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(authService.register(request));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<AuthResponse>> login(
        @Valid @RequestBody AuthRequest request, Errors errors
    ) {
        ResponseData<AuthResponse> responseData = new ResponseData<>();
        try {
            if (errors.hasErrors()) {
                responseData.setStatus(false);
                responseData.setMessages(ErrorParsingUtility.parse(errors));
                responseData.setPayload(null);
                return ResponseEntity.badRequest().body(responseData);
            }

            // User user = modelMapper.map(request, User.class);

            responseData.setStatus(true);
            responseData.getMessages().add("success");
            responseData.setPayload(authService.login(request));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(false);
            responseData.getMessages().add(e.getMessage());
            responseData.setPayload(null);
            return ResponseEntity.internalServerError().body(responseData);
        }
    }


}
