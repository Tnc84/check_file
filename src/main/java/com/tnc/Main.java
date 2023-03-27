package com.tnc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String readFile = "Emails_updates_23_03_2023 - Copy";
    //        ArrayList<String> difArray = new ArrayList<>();
//        File fileForRead = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/" + readFile + ".txt");
//        File fileForWrite = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/CheckFile.txt");
    static File fileForRead = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/" + readFile + ".txt");
    static File fileForWrite = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/test.txt");
    static File difFile = new File("C:/Users/a830083/Desktop/Migration_Project/email-Gabi/difFile.txt");

    public static void main(String[] args) throws IOException {
        ArrayList<String> readArray = new ArrayList<>();
        ArrayList<String> writeArray = new ArrayList<>();
        readFromTheFile(readArray, fileForRead);
        writeToTheFile(writeArray, fileForWrite);
        appendToTheBaseFile(readArray, writeArray, fileForWrite, difFile);
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

    private static void appendToTheBaseFile(ArrayList<String> rows, ArrayList<String> writeArray, File file2, File difFile) throws IOException {
        int number = 0;
        try (FileOutputStream writeToBaseFile = new FileOutputStream(file2, true)) {
            try (FileOutputStream writeToDifFile = new FileOutputStream(difFile, true)) {
                for (String word : rows) {
                    StringBuilder addString = new StringBuilder();
                    var charArr = word.toCharArray();
                    for (char c : charArr) {
                        if (!Character.isDigit(c)) {
                            addString.append(c);
                        }
                    }
                    if (!writeArray.contains(addString) && !writeArray.contains(addString.toString())) {
                        writeArray.add("\n" + addString);
                        writeToBaseFile.write(("\n" + addString).getBytes());
                        writeToDifFile.write(("\n" + readFile.substring(15, 23) +  "\n " + "row number " + number++ + " " + addString +"\n").getBytes());
                    }
                }
            }
        }
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