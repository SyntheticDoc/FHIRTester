package com.fhirtester;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONReader {
    private static final String path = "src/main/resources/";

    public static String getFileContent(String file) {
        StringBuilder result = new StringBuilder();
        String filename = path + file + ".json";

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public static String getFileContent() {
        return getFileContent("observation-example-sample-data");
    }

    public static ArrayList<String> getFileContentAsArrayList(String file) {
        ArrayList<String> result = new ArrayList<>();
        String filename = path + file + ".json";

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while((line = reader.readLine()) != null) {
                result.add(line + "\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
