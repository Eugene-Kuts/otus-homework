package ru.otus.hw05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataCollector {
    private int minorGC = 0;
    private int majorGC = 0;
    private long minorGCTimeInMillis = 0;
    private long majorGCTimeInMillis = 0;
    private long totalTime = 0;
    private static String path = ".\\hw05-GCCompare\\data\\";

    public static void main(String[] args) throws FileNotFoundException {
        new DataCollector().collectData("collected_data.txt");
    }

    private void collectData(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(path + fileName);
        collectDataForOneGCType(writer, "UseG1GC");
        collectDataForOneGCType(writer, "UseConcMarkSweepGC");
        collectDataForOneGCType(writer, "UseParallelGC");
        collectDataForOneGCType(writer, "UseSerialGC");
        writer.close();
    }

    private void collectDataForOneGCType(PrintWriter writer, String gcType) throws FileNotFoundException {
        collectDataFromFile(gcType);
        writer.println("Тип GC: " + gcType + ", " + "кол-во young сборок:" + minorGC + ", время:"
                + minorGCTimeInMillis + ", " + "кол-во old сборок:" + majorGC+ ", время:"
                + majorGCTimeInMillis + ", общее кол-во сборок: " + (minorGC + majorGC)
                + ", общее время сборки: " + (minorGCTimeInMillis + majorGCTimeInMillis)
                + ", Общее время работы приложения: " + totalTime);
    }

    private void collectDataFromFile(String fileName) throws FileNotFoundException {
        minorGC = 0;
        majorGC = 0;
        minorGCTimeInMillis = 0;
        majorGCTimeInMillis = 0;
        totalTime=0;

        Scanner inputFile = new Scanner(new File(path + fileName));
        while (inputFile.hasNext()) {
            final String nextLine = inputFile.nextLine();
            String[] subStrings = nextLine.split(",");
            String[] subSubStringsForDuration = subStrings[1].split(":");
            if (subStrings[0].equals("GC Action:end of minor GC")) {
                minorGC++;
                minorGCTimeInMillis += Integer.parseInt(subSubStringsForDuration[1]);
            }
            if (subStrings[0].equals("GC Action:end of major GC")) {
                majorGC++;
                majorGCTimeInMillis += Integer.parseInt(subSubStringsForDuration[1]);
            }
            if(!inputFile.hasNext()){
                String[] subSubStringsTotalTime = subStrings[3].split(":");
                totalTime = Integer.parseInt(subSubStringsTotalTime[1]);
            }
        }
        inputFile.close();
    }
}
