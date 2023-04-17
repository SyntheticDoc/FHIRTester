package com.fhirtester;

import java.io.*;
import java.util.ArrayList;

public class JSONBuilder {
    public static void buildJSON(String data, String filename) {
        String file = "src/main/resources/" + filename + ".json";
        StringBuilder content = new StringBuilder();
        ArrayList<String> JSONexample = JSONReader.getFileContentAsArrayList("observation-example-sample-data");

        for(String s : JSONexample) {
            if(!s.isBlank()) {
                if(s.startsWith("      \"data\" :")) {
                    content.append("      \"data\" : \"").append(data).append("\"\n");
                } else {
                    content.append(s);
                }
            }
        }

        System.out.println("Generated JSON-content: " + "\n" + content);
        writeJSON(content, file);
    }

    private static void writeJSON(StringBuilder content, String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            System.out.println("Writing content to " + filename + "...");
            writer.write(content.toString());
        } catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("Content successfully written!");
    }
}
