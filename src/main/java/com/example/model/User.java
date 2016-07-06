package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    public enum Gender{male, female};

    private String name;
    private Gender gender;
}
