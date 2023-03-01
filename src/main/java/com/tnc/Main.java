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
        readFromTheFile(readArray, fileForRead);
        writeToTheFile(writeArray, fileForWrite);
        appendToTheBaseFile(readArray, writeArray, difArray, fileForWrite);
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

    private static void readFromTheFile(ArrayList<String> readArray, File fileForRead) {
        try {
            Scanner read = new Scanner(fileForRead);
            while (read.hasNextLine()) {
                String dataRead = read.nextLine();
                readArray.add(dataRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}