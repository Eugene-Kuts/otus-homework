package ru.otus.hw11.dataClasses;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

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
@Table(name = "Account")
public class
Account {
    //если название столбца совпадает с названием поля, то не обязательно указывать @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    //если название столбца совпадает с названием поля, то не обязательно указывать @Column
    @Column(name = "type")
    private String type;

    //если название столбца совпадает с названием поля, то не обязательно указывать @Column
    @Column(name = "rest")
    private BigDecimal rest;

    public Account(String type, BigDecimal rest) {
        this.type = type;
        this.rest = rest;
    }
}
