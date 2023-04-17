package com.fhirtester;

import java.io.*;
import java.util.ArrayList;

public class CSVConverter {
    private static final String[] filenamesRaw = {"src/main/resources/ecg1raw.txt",
            "src/main/resources/ecg2raw.txt",
            "src/main/resources/ecg3raw.txt"};
    private static final String[] filenames = {"src/main/resources/ecg1.txt",
            "src/main/resources/ecg2.txt",
            "src/main/resources/ecg3.txt"};

    public static void convertFiles() {
        for(String s : filenamesRaw) {
            convertFile(s);
        }
    }

    public static void convertFile(String filename) {
        String writeFilename;

        if(filename.contains("1")) {
            writeFilename = "src/main/resources/ecg1.txt";
        } else if(filename.contains("2")) {
            writeFilename = "src/main/resources/ecg2.txt";
        } else if(filename.contains("3")) {
            writeFilename = "src/main/resources/ecg3.txt";
        } else {
            return;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(filename)); BufferedWriter writer = new BufferedWriter(new FileWriter(writeFilename))) {
            String[] buffer = reader.readLine().split(",");

            for(String s : buffer) {
                writer.write(s + "\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getFileContent(int filenum) {
        String filename = filenames[filenum-1];

        ArrayList<String> result = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while((line = reader.readLine()) != null) {
                if(!line.equals("0.000000000000000000e+00")) {
                    result.add(line);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
