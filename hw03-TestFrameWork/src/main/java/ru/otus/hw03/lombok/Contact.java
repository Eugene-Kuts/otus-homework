package ru.otus.hw03.lombok;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Contact {
    int id;
    String firstName;
    String lastName;
}
