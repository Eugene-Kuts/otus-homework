package ru.otus.hw10.dataClasses;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

//Чтобы класс мог быть @Entity, к нему предъявляются следующие требования:
//      Должен иметь пустой конструктор (public или protected);
//      Не может быть вложенным, интерфейсом или enum;
//      Не может быть final и не может содержать final-полей/свойств;
//      Должен содержать хотя бы одно @Id-поле.
//При этом @Entity может:
//      Содержать непустые конструкторы;
//      Наследоваться и быть наследованным
//      Содержать другие методы и реализовывать интерфейсы.

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

    //если название столбца совпадает с названием поля, то не обязательно указывать @Column
    @Column(name = "userName")
    private String name;

    //если название столбца совпадает с названием поля, то не обязательно указывать @Column
    @Column(name = "userAge")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressTable_id")
    private AddressDataSet addressDataSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "phoneTable_id", nullable = false)
    private List<PhoneDataSet> phoneDataSet=null;

    public User(String name, int age, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSet) {
        this.name = name;
        this.age = age;
        this.addressDataSet = addressDataSet;
        this.phoneDataSet = phoneDataSet;
    }
}