package ru.otus.hw16.frontend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private int age;
    private String address;
    private String phone;
}