package ru.otus.hw09.dataClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.hw09.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private long id;
    private String name;
    private int age;
}
