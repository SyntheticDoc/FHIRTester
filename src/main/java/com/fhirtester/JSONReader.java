package com.fhirtester;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    private static final String filename = "src/main/resources/observation-example-sample-data.json";

    public static String getFileContent() {
        StringBuilder result = new StringBuilder();

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
}
