package com.learning.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryData {
    
    @NotEmpty(message = "Name is required")
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
