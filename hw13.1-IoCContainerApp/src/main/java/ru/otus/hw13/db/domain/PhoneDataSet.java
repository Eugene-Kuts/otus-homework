package ru.otus.hw13.db.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "phoneTable")
public class PhoneDataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phoneNumber")
    private String number;

    public PhoneDataSet(String number) {
        this.number = number;
    }

}


