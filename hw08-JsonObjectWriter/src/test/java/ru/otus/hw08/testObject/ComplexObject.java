package ru.otus.hw08.testObject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ComplexObject {

    private String stringField = "Happy Day";

    private byte byteField = 10;

    private Byte byteOField = 20;

    private short shortField = 30;

    private Short shortOField = 40;

    private int intField = 50;

    private Integer integerOField = 60;

    private long longField = 70;

    private Long longOField = 80L;

    private boolean booleanField = false;

    private Boolean booleanOField = true;

    private char charField = 'z';

    private Character characterOField = 'x';

    private float floatField = 1.1f;

    private Float floatOField = 2.2f;

    private double doubleField = 3.3;

    private Double doubleOField = 4.4;

    private int[] array = new int[]{1, 2, 3};

    private List list =  Collections.singletonList("Victory");

}
