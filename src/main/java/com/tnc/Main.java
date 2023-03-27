package com.tnc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String readFile = "Emails_updates_23_03_2023";
        ArrayList<String> readArray = new ArrayList<>();
        ArrayList<String> writeArray = new ArrayList<>();
        ArrayList<String> difArray = new ArrayList<>();
//        File fileForRead = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/" + readFile + ".txt");
//        File fileForWrite = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/CheckFile.txt");
        File fileForRead = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/" + readFile + ".txt");
        File fileForWrite = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/test.txt");
        File difFile = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/difFile.txt");
        readFromTheFile(readArray, fileForRead);
        writeToTheFile(writeArray, fileForWrite);
        appendToTheBaseFile(readArray, writeArray, difArray, fileForWrite);
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

    public static void deleteRowsThatStartWithCreated(ArrayList<String> readArray, String row) {
        if (!row.startsWith("    - Creation")) {
            readArray.add(row);
        }
    }

    private static void appendToTheBaseFile(ArrayList<String> rows, ArrayList<String> writeArray, ArrayList<String> difArray, File file2) throws IOException {
        for (String word : rows) {
            FileOutputStream writeToBaseFile = new FileOutputStream(file2, true);
            var charArr = word.toCharArray();
            String addString = "";
            for (char c : charArr) {
                if (!Character.isDigit(c)) {
                    addString += (String.valueOf(c));
                }
            }
            if (!writeArray.contains(word) && !writeArray.contains(addString)) {
                writeArray.add("\n" + addString);
                difArray.add("\n" + addString);
                writeToBaseFile.write(("\n" + addString).getBytes());
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