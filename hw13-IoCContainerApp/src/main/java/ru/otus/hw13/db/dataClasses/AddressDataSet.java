package ru.otus.hw13.db.dataClasses;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
@Table(name = "addressTable")
public class AddressDataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //если название столбца совпадает с названием поля, то не обязательно указывать @Column
    @Column(name = "street")
    private String street;

    public AddressDataSet(String street) {
        this.street = street;
    }

}

