package com.tnc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> readArray = new ArrayList<>();
        ArrayList<String> writeArray = new ArrayList<>();
        ArrayList<String> difArray = new ArrayList<>();
        File fileForRead = new File("src/main/java/com/tnc/readFile.txt");
        File fileForWrite = new File("src/main/java/com/tnc/writeFile.txt");
//        cleanedRows(fileForRead);
        readFromTheFile(readArray, fileForRead);
        appendToTheBaseFile(cleanedRows(String.valueOf(readArray)), writeArray, difArray, fileForWrite);
        writeToTheFile(difArray, fileForWrite);
    }

    private static void readFromTheFile(ArrayList<String> readArray, File fileForRead) {
        try {
            Scanner read = new Scanner(fileForRead);
            while (read.hasNextLine()) {
                String dataRead = read.nextLine();
                deleteRowsThatStartWithCreated(readArray, dataRead);
                cleanedRows(dataRead);
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

    public static String cleanedRows(String file) throws FileNotFoundException {
//        ArrayList<String> newRows = new ArrayList<>();
//        Scanner read = new Scanner(file);


//        public static String removeAllDigit(String str)
//        {
//            return str.replaceAll("\\d", "");
//    }


//        while (read.hasNext()) {
//            String fileRead = read.nextLine();

            char[] fileReadCharArray = file.toCharArray();
            StringBuilder numberChecker = new StringBuilder();
            for (int i = 0; i < fileReadCharArray.length; i++) {
                if (!Character.isDigit(fileReadCharArray[i])) {
                    numberChecker.append(fileReadCharArray[i]);
                }
            }
//            newRows.add(String.valueOf(numberChecker));
        return String.valueOf(numberChecker);
        }
//        System.out.printf(String.valueOf(newRows));
//        return newRows;
//    }





    private static void appendToTheBaseFile(String file, ArrayList<String> writeArray, ArrayList<String> difArray, File file2) {
//    private static void appendToTheBaseFile(ArrayList<String> readArray, ArrayList<String> writeArray, ArrayList<String> difArray, File file2) {
//        for (String file : readArray) {
            try {
                FileOutputStream wr = new FileOutputStream(file2, true);
                var s_file = file.substring();

                if (!writeArray.contains(file)) {
//                    var newFile = cleanedRows(files);
                    writeArray.add(file);
                    difArray.add(file);
                    wr.write(("\n" + file).getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        System.out.println(difArray);
        }
//    }

    private static void writeToTheFile(ArrayList<String> writeArray, File file2) {
        try {
            Scanner write = new Scanner(file2);
            while (write.hasNextLine()) {
                String dataWrite = write.nextLine();
                writeArray.add("\n" + dataWrite);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}