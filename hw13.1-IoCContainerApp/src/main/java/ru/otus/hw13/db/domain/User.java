package ru.otus.hw13.db.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usersTable")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "userName")
    private String name;

    @Column(name = "userAge")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressTable_id")
    private AddressDataSet addressDataSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "phoneTable_id", nullable = true)
    private List<PhoneDataSet> phoneDataSet = new ArrayList<>();

    public User(String name, int age, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSet) {
        this.name = name;
        this.age = age;
        this.addressDataSet = addressDataSet;
        this.phoneDataSet = phoneDataSet;
    }
}