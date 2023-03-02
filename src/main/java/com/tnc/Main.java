package com.tnc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        File fileForRead = new File("src/main/java/com/tnc/readFile.txt");
//        File fileForWrite = new File("src/main/java/com/tnc/writeFile.txt");
        File fileForRead = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/Emails_24_01_2023.txt");
        ArrayList<String> readArray = new ArrayList<>();
        ArrayList<String> writeArray = new ArrayList<>();
        ArrayList<String> difArray = new ArrayList<>();
        File fileForWrite = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/Emails_multiple_days.txt");
        readFromTheFile(readArray, fileForRead);
        writeToTheFile(writeArray, fileForWrite);
        appendToTheBaseFile(readArray, writeArray, difArray, fileForWrite);
    }

    public static void deleteRowsThatStartWithCreated(ArrayList<String> readArray, String row) {
        ArrayList<String> toRemove = new ArrayList<>();
        if (row.startsWith("    - Creation")) {
            toRemove.add(row);
            toRemove.clear();
        } else {
            readArray.add(row);
        }
    }

    private static void readFromTheFile(ArrayList<String> readArray, File fileForRead) {
        try {
            Scanner read = new Scanner(fileForRead);
            while (read.hasNextLine()) {
                String dataRead = read.nextLine();
                deleteRowsThatStartWithCreated(readArray, dataRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendToTheBaseFile(ArrayList<String> readArray, ArrayList<String> writeArray, ArrayList<String> difArray, File file2) {
        for (String files : readArray) {
            try {
                FileOutputStream wr = new FileOutputStream(file2, true);
                if (!writeArray.contains(files)) {
                    writeArray.add(files);
                    difArray.add(files);
                    wr.write(("\n" + files).getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(difArray);
    }

    private static void writeToTheFile(ArrayList<String> writeArray, File file2) {
        try {
            Scanner write = new Scanner(file2);
            while (write.hasNextLine()) {
                String dataWrite = write.nextLine();
                writeArray.add(dataWrite);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}