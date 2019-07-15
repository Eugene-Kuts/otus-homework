package ru.otus.hw02;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Begin Main");

        //begin of Collections.addAll
        System.out.println("_________________AddAll________________");
        try {
            //Create
            List<String> myListToAddAll = new MyArrayList<>();

            //Fill
            for (char c='a';c<='z'; c++) {
                String str = String.valueOf(c);
                myListToAddAll.add(str);
            }

            // add the specified element to specified Collections
            // using addAll() method
            boolean b = Collections.addAll(myListToAddAll, "1", "2", "3", "4", "5");

            // printing after operation
            System.out.println("result of boolean method Collection.copy: " + b);

            // printing after operation
            System.out.println("After Collections.addAll:");
            for(int i=0; i<myListToAddAll.size();i++)
                System.out.print(myListToAddAll.get(i)+ ", ");

        }catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        System.out.println("\n_________________End AddAll________________");
        //end of Collections.addAll


        //begin Collections.copy
        System.out.println("\n_________________COPY________________");
        try {
        //Create
        List<Integer> sourceList = new MyArrayList<>();
        List<Integer> destinationList = new MyArrayList<>();

        //Fill Lists
        for (int i = 1; i < 25; i++) {
            sourceList.add((int) (Math.random() * 100));
        }
        for (int i = 30; i > 1; i--) {
            destinationList.add((int) (Math.random() * 100));
        }
        System.out.println("sourceList:");
        for(int i=0; i<sourceList.size();i++)
            System.out.print(sourceList.get(i)+ ", ");

        System.out.println("\ndestinationList BEFORE copy:");
        for(int i=0; i<destinationList.size();i++)
            System.out.print(destinationList.get(i)+ ", ");

        //Copy
        Collections.copy(destinationList, sourceList);

        // printing after operation
        System.out.println("\ndestinationList AFTER copy:");
        for(int i=0; i<destinationList.size();i++)
            System.out.print(destinationList.get(i)+ ", ");

        }catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        System.out.println("\n_________________END COPY________________");
        //end of Collections.copy


        //Collection.sort
        System.out.println("\n_________________SORT________________");
        List<Integer> listToSort = new MyArrayList<>();

        // заполняем список из 30 элементов случайными числами
        for (int i = 0; i < 30; i++) {
            int randomValue = (int) (Math.random()*100);

            listToSort.add(randomValue);
            //System.out.print("i= " + i + " value= " + randomValue + ", " );
        }

        System.out.println("\nlistToSort BEFORE Collections.sort:");
        for(int i=0; i<listToSort.size();i++)
            System.out.print(listToSort.get(i)+ ", ");

        Collections.sort(listToSort);


        System.out.println("\nlistToSort AFTER Collections.sort:");
        for(int i=0; i<listToSort.size();i++)
            System.out.print(listToSort.get(i)+ ", ");
        System.out.println("\n_________________END SORT________________");
        //End of Collection.sort

        System.out.println("End Main");
    }
}
