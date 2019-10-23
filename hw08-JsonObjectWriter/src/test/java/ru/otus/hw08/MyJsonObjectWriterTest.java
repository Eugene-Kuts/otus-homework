package ru.otus.hw08;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.hw08.testObject.ComplexObject;
import ru.otus.hw08.testObject.SimpleObject;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyJsonObjectWriterTest {

    private Gson googleGson;
    private MyJsonObjectWriter myJsonObjectWriter;


    @BeforeEach
    void initialization() {
        googleGson = new Gson();
        myJsonObjectWriter = new MyJsonObjectWriter();
    }

    //проверка на null
    @Test
    public void nullObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(null), myJsonObjectWriter.toJson(null));
    }

    //integer
    @Test
    public void integerObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(5), myJsonObjectWriter.toJson(5));
    }

    //integerArray
    @Test
    public void integerArrayObject() throws IllegalAccessException {
        int[] array = {1, 2, 3};
        assertEquals(googleGson.toJson(array), myJsonObjectWriter.toJson(array));
    }

    //boolean
    @Test
    public void booleanObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(true), myJsonObjectWriter.toJson(true));
    }

    //booleanArray
    @Test
    public void booleanArrayObject() throws IllegalAccessException {
        boolean[] array = {true, false, true};
        assertEquals(googleGson.toJson(array), myJsonObjectWriter.toJson(array));
    }

    //double
    @Test
    public void doubleObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(10d), myJsonObjectWriter.toJson(10d));
    }

    //doubleArray
    @Test
    public void doubleArrayObject() throws IllegalAccessException {
        double[] array = {10d, 20d, 30d};
        assertEquals(googleGson.toJson(array), myJsonObjectWriter.toJson(array));
    }

    //float
    @Test
    public void floatObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(15f), myJsonObjectWriter.toJson(15f));
    }

    //String
    @Test
    public void stringObject() throws IllegalAccessException {
        String str = "Success";
        assertEquals(googleGson.toJson(str), myJsonObjectWriter.toJson(str));
    }

    //StringArray
    @Test
    public void stringArrayObject() throws IllegalAccessException {
        String[] array = {"Альфа", "Бета", "Гамма"};
        assertEquals(googleGson.toJson(array), myJsonObjectWriter.toJson(array));
    }

    //Character
    @Test
    public void characterObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson("A"), myJsonObjectWriter.toJson("A"));
    }

    //Long
    @Test
    public void longObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(5l), myJsonObjectWriter.toJson(5l));
    }

    //Byte
    @Test
    public void byteObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson((byte)55), myJsonObjectWriter.toJson((byte)55));
    }

    //Short
    @Test
    public void shortObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson((short)55), myJsonObjectWriter.toJson((short)55));
    }

    //Array
    @Test
    public void arrayObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(new int[] {1, 2, 3}), myJsonObjectWriter.toJson(new int[] {1, 2, 3}));
    }

    //Collection
    @Test
    public void collectionObject() throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");
        assertEquals(googleGson.toJson(list), myJsonObjectWriter.toJson(list));
    }

    //SimpleObject
    @Test
    public void simpleObject() throws IllegalAccessException {
        SimpleObject simpleObject = new SimpleObject(55, "Ivan");
        assertEquals(googleGson.toJson(simpleObject), myJsonObjectWriter.toJson(simpleObject));
    }

    //ComplexObject
    @Test
    public void complexObject() throws IllegalAccessException {
        ComplexObject complexObject = new ComplexObject();
        String json = myJsonObjectWriter.toJson(complexObject);
        assertEquals(googleGson.fromJson(json, ComplexObject.class), complexObject);
    }

}
