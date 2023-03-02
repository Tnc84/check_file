package com.tnc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> readArray = new ArrayList<>();
        ArrayList<String> writeArray = new ArrayList<>();
        ArrayList<String> difArray = new ArrayList<>();
        File fileForRead = new File("src/main/java/com/tnc/readFile.txt");
        File fileForWrite = new File("src/main/java/com/tnc/writeFile.txt");
//        cleanedRows(fileForRead);
        readFromTheFile(readArray, fileForRead);
        writeToTheFile(writeArray, fileForWrite);
        appendToTheBaseFile(readArray, writeArray, difArray, fileForWrite);
//        appendToTheBaseFile(cleanedRows(String.valueOf(readArray)), writeArray, difArray, fileForWrite);
    }

    private static void readFromTheFile(ArrayList<String> readArray, File fileForRead) {
        try {
            Scanner read = new Scanner(fileForRead);
            while (read.hasNextLine()) {
                String dataRead = read.nextLine();
                deleteRowsThatStartWithCreated(readArray, dataRead);
//                cleanedRows(dataRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteRowsThatStartWithCreated(ArrayList<String> readArray, String row) {
        ArrayList<String> toRemove = new ArrayList<>();
        if (row.startsWith("+ Created")) {
            toRemove.add(row);
            toRemove.clear();
        } else {
            readArray.add(row);
        }
    }

    public static ArrayList<String> cleanedRows(String file) throws FileNotFoundException {
//        ArrayList<String> newRows = new ArrayList<>();
//        Scanner read = new Scanner(file);


//        public static String removeAllDigit(String str)
//        {
//            return str.replaceAll("\\d", "");
//    }


//        while (read.hasNext()) {
//            String fileRead = read.nextLine();

        char[] fileReadCharArray = file.toCharArray();
        ArrayList<String> numberChecker = new ArrayList<>();
//            StringBuilder numberChecker = new StringBuilder();
        String stringAdd = "";
        for (int i = 0; i < fileReadCharArray.length; i++) {
            if (!Character.isDigit(fileReadCharArray[i])) {
                stringAdd += String.valueOf(fileReadCharArray[i]);
            }
        }
        numberChecker.add(stringAdd);
//            newRows.add(String.valueOf(numberChecker));
        return numberChecker;
    }
//        System.out.printf(String.valueOf(newRows));
//        return newRows;
//    }


    private static void appendToTheBaseFile(ArrayList<String> rows, ArrayList<String> writeArray, ArrayList<String> difArray, File file2) throws IOException {
//    private static void appendToTheBaseFile(ArrayList<String> readArray, ArrayList<String> writeArray, ArrayList<String> difArray, File file2) {
        for (String file : rows) {
            FileOutputStream wr = new FileOutputStream(file2, true);
            if (!writeArray.contains(file)) {
                var charArr = file.toCharArray();
                    String addString = "";
                for (int i = 0; i < charArr.length; i++) {
                    //                    ArrayList<String> addString = new ArrayList<>();

                    if (!Character.isDigit(charArr[i])) {
                        addString += (String.valueOf(charArr[i]));
                    }
                }
                    writeArray.add("\n" + addString);
                    difArray.add("\n" + addString);
                    wr.write(("\n" + addString).getBytes());
//            if (!writeArray.contains(file) && !Character.isDigit(Integer.parseInt(file))) {
//                if (!Character.isDigit(Integer.parseInt(file)))
//                    var newFile = cleanedRows(files);
            } else {
                continue;
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