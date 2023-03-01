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
        File file2 = new File("src/main/java/com/tnc/writeFile.txt");
        try {
            Scanner read = new Scanner(fileForRead);
            while (read.hasNextLine()) {
                String dataRead = read.nextLine();
                readArray.add(dataRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner write = new Scanner(file2);
            while (write.hasNextLine()) {
                String dataWrite = write.nextLine();
                writeArray.add(dataWrite);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (String files : readArray) {
            try {
                FileOutputStream wr = new FileOutputStream(file2, true);
                if (!writeArray.contains(files)) {
                    writeArray.add(files);
                    difArray.add(files);
                    wr.write(("\n" + files).getBytes());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(difArray);
//        System.out.println(writeArray);

    }
}