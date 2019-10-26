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

    //проверка на null WithoutGson
    @Test
    public void nullObjectWithoutGson() throws IllegalAccessException {
        assertEquals("null", myJsonObjectWriter.toJson(null));
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

    //integerWithoutGson
    @Test
    public void integerObjectWithoutGson() throws IllegalAccessException {
        assertEquals("5", myJsonObjectWriter.toJson(5));
    }

    //integerArrayWithoutGson
    @Test
    public void integerArrayObjectWithoutGson() throws IllegalAccessException {
        int[] array = {1, 2, 3};
        assertEquals("[1,2,3]", myJsonObjectWriter.toJson(array));
    }

    //integerArray
    @Test
    public void integerArrayObject() throws IllegalAccessException {
        int[] array = {1, 2, 3};
        assertEquals(googleGson.toJson(array), myJsonObjectWriter.toJson(array));
    }

    //booleanWithoutGson
    @Test
    public void booleanObjectWithoutGson() throws IllegalAccessException {
        assertEquals("true", myJsonObjectWriter.toJson(true));
    }

    //boolean
    @Test
    public void booleanObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(true), myJsonObjectWriter.toJson(true));
    }

    //booleanArrayWithoutGson
    @Test
    public void booleanArrayObjectWithoutGson() throws IllegalAccessException {
        boolean[] array = {true, false, true};
        assertEquals("[true,false,true]", myJsonObjectWriter.toJson(array));
    }

    //booleanArray
    @Test
    public void booleanArrayObject() throws IllegalAccessException {
        boolean[] array = {true, false, true};
        assertEquals(googleGson.toJson(array), myJsonObjectWriter.toJson(array));
    }

    //doubleWithoutGson
    @Test
    public void doubleObjectWithoutGson() throws IllegalAccessException {
        assertEquals("10.0", myJsonObjectWriter.toJson(10d));
    }

    //double
    @Test
    public void doubleObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(10d), myJsonObjectWriter.toJson(10d));
    }

    //doubleArrayWithoutGson
    @Test
    public void doubleArrayObjectWithoutGson() throws IllegalAccessException {
        double[] array = {10d, 20d, 30d};
        assertEquals("[10.0,20.0,30.0]", myJsonObjectWriter.toJson(array));
    }

    //doubleArray
    @Test
    public void doubleArrayObject() throws IllegalAccessException {
        double[] array = {10d, 20d, 30d};
        assertEquals(googleGson.toJson(array), myJsonObjectWriter.toJson(array));
    }

    //floatWithoutGson
    @Test
    public void floatObjectWithoutGson() throws IllegalAccessException {
        assertEquals("15.0", myJsonObjectWriter.toJson(15f));
    }

    //float
    @Test
    public void floatObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(15f), myJsonObjectWriter.toJson(15f));
    }

    //StringWithoutGson
    @Test
    public void stringObjectWithoutGson() throws IllegalAccessException {
        assertEquals("\"Success\"", myJsonObjectWriter.toJson("Success"));
    }

    //String
    @Test
    public void stringObject() throws IllegalAccessException {
        String str = "Success";
        assertEquals(googleGson.toJson(str), myJsonObjectWriter.toJson(str));
    }

    //StringArrayWithoutGson
    @Test
    public void stringArrayObjectWithoutGson() throws IllegalAccessException {
        String[] array = {"Альфа", "Бета", "Гамма"};
        assertEquals("[\"Альфа\",\"Бета\",\"Гамма\"]", myJsonObjectWriter.toJson(array));
    }

    //StringArray
    @Test
    public void stringArrayObject() throws IllegalAccessException {
        String[] array = {"Альфа", "Бета", "Гамма"};
        assertEquals(googleGson.toJson(array), myJsonObjectWriter.toJson(array));
    }

    //CharacterWithoutGson
    @Test
    public void characterObjectWithoutGson() throws IllegalAccessException {
        assertEquals("\"A\"", myJsonObjectWriter.toJson("A"));
    }

    //Character
    @Test
    public void characterObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson("A"), myJsonObjectWriter.toJson("A"));
    }

    //LongWithoutGson
    @Test
    public void longObjectWithoutGson() throws IllegalAccessException {
        assertEquals("5", myJsonObjectWriter.toJson(5l));
    }

    //Long
    @Test
    public void longObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(5l), myJsonObjectWriter.toJson(5l));
    }

    //ByteWithoutGson
    @Test
    public void byteObjectWithoutGson() throws IllegalAccessException {
        assertEquals("55", myJsonObjectWriter.toJson((byte)55));
    }

    //Byte
    @Test
    public void byteObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson((byte)55), myJsonObjectWriter.toJson((byte)55));
    }

    //ShortWithoutGson
    @Test
    public void shortObjectWithoutGson() throws IllegalAccessException {
        assertEquals("55", myJsonObjectWriter.toJson((short)55));
    }

    //Short
    @Test
    public void shortObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson((short)55), myJsonObjectWriter.toJson((short)55));
    }

    //ArrayWithoutGson
    @Test
    public void arrayObjectWithoutGson() throws IllegalAccessException {
        assertEquals("[1,2,3]", myJsonObjectWriter.toJson(new int[] {1, 2, 3}));
    }

    //Array
    @Test
    public void arrayObject() throws IllegalAccessException {
        assertEquals(googleGson.toJson(new int[] {1, 2, 3}), myJsonObjectWriter.toJson(new int[] {1, 2, 3}));
    }

    //CollectionWithoutGson
    @Test
    public void collectionObjectWithoutGson() throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals("[\"1\",\"2\",\"3\"]", myJsonObjectWriter.toJson(list));
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

    //SimpleObjectWithoutGson
    @Test
    public void simpleObjectWithoutGson() throws IllegalAccessException {
        SimpleObject simpleObject = new SimpleObject(55, "Ivan");
        assertEquals("{\"age\":55,\"name\":\"Ivan\"}", myJsonObjectWriter.toJson(simpleObject));
    }

    //SimpleObject
    @Test
    public void simpleObject() throws IllegalAccessException {
        SimpleObject simpleObject = new SimpleObject(55, "Ivan");
        assertEquals(googleGson.toJson(simpleObject), myJsonObjectWriter.toJson(simpleObject));
    }

    //ComplexObjectWithoutGson
    @Test
    public void complexObjectWithoutGson() throws IllegalAccessException {
        ComplexObject complexObject = new ComplexObject();
        String json = myJsonObjectWriter.toJson(complexObject);
        assertEquals("{\"stringField\":\"Happy Day\",\"byteField\":10,\"byteOField\":20,\"shortField\":30,\"shortOField\":40,\"intField\":50,\"integerOField\":60,\"longField\":70,\"longOField\":80,\"booleanField\":false,\"booleanOField\":true,\"charField\":\"z\",\"characterOField\":\"x\",\"floatField\":1.100000023841858,\"floatOField\":2.200000047683716,\"doubleField\":3.3,\"doubleOField\":4.4,\"array\":[1,2,3],\"list\":[\"Victory\"]}", json);
    }

    //ComplexObject
    @Test
    public void complexObject() throws IllegalAccessException {
        ComplexObject complexObject = new ComplexObject();
        String json = myJsonObjectWriter.toJson(complexObject);
        assertEquals(googleGson.fromJson(json, ComplexObject.class), complexObject);
    }

}
