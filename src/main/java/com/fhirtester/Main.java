package com.fhirtester;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        writeEmptyJSONExample();
        //writeJSONExample();
        //fhirTest("example_test1");
    }

    public static void writeEmptyJSONExample() {
        ArrayList<String> fileContents1 = CSVConverter.getFileContent(1);
        StringBuilder dataBuilder = new StringBuilder();

        for(String s : fileContents1) {
            if(!s.isBlank()) {
                dataBuilder.append(0).append(" ");
                dataBuilder.append(0).append(" ");
                dataBuilder.append(0).append(" ");
            }
        }

        String data = dataBuilder.toString();

        JSONBuilder.buildJSON(data, "example_test_empty1");
    }

    public static void writeJSONExample() {
        ArrayList<String> fileContents1 = CSVConverter.getFileContent(1);
        StringBuilder dataBuilder = new StringBuilder();

        ArrayList<String> fileContents2 = CSVConverter.getFileContent(2);
        ArrayList<String> fileContents3 = CSVConverter.getFileContent(3);

        for(String s : fileContents1) {
            if(!s.isBlank()) {
                dataBuilder.append(((int) (Double.parseDouble(s) * 300)) + 1900).append(" ");
            }
        }

        for(String s : fileContents2) {
            if(!s.isBlank()) {
                dataBuilder.append(((int) (Double.parseDouble(s) * 300)) + 1900).append(" ");
            }
        }

        for(String s : fileContents3) {
            if(!s.isBlank()) {
                dataBuilder.append(((int) (Double.parseDouble(s) * 300)) + 1900).append(" ");
            }
        }

        String data = dataBuilder.toString();

        JSONBuilder.buildJSON(data, "example_test1");
    }

    public static void csvTest() {
        // CSVConverter.convertFiles();
        ArrayList<String> fileContents1 = CSVConverter.getFileContent(1);
        StringBuilder dataBuilder = new StringBuilder();

        ArrayList<String> fileContents2 = CSVConverter.getFileContent(2);
        ArrayList<String> fileContents3 = CSVConverter.getFileContent(3);

        for(String s : fileContents1) {
            if(!s.isBlank()) {
                dataBuilder.append(((int) (Double.parseDouble(s) * 300)) + 1900).append(" ");
            }
        }

        for(String s : fileContents2) {
            if(!s.isBlank()) {
                dataBuilder.append(((int) (Double.parseDouble(s) * 300)) + 1900).append(" ");
            }
        }

        for(String s : fileContents3) {
            if(!s.isBlank()) {
                dataBuilder.append(((int) (Double.parseDouble(s) * 300)) + 1900).append(" ");
            }
        }

        String data = dataBuilder.toString();

        String[] comps = new String[6];
        comps[0] = "All files";
        comps[1] = data;
        comps[2] = comps[0];
        comps[3] = data;
        comps[4] = comps[0];
        comps[5] = data;

        GraphicsModule g = new GraphicsModule();
        g.drawECG(comps);
    }

    public static void fhirTest(String filename) {
        System.out.println("Starting program");

        // Build FHIR-context (only once, because this step is very resource-demanding!)
        FhirContext ctx = FhirContext.forR4();

        System.out.println("Context successfully started!\n" + ctx + "\n");

        String jsonContents = JSONReader.getFileContent(filename);

        IParser parser = ctx.newJsonParser();

        Observation obs = parser.parseResource(Observation.class, jsonContents);

        System.out.println("Device: " + obs.getDevice().getDisplay() + "\n");

        Observation.ObservationComponentComponent c0 = obs.getComponent().get(0);
        Observation.ObservationComponentComponent c1 = obs.getComponent().get(1);
        Observation.ObservationComponentComponent c2 = obs.getComponent().get(2);

        String[] comps = new String[6];
        comps[0] = c0.getCode().getCoding().get(0).getDisplay();
        comps[1] = c0.getValueSampledData().getData();
        comps[2] = c1.getCode().getCoding().get(0).getDisplay();
        comps[3] = c1.getValueSampledData().getData();
        comps[4] = c2.getCode().getCoding().get(0).getDisplay();
        comps[5] = c2.getValueSampledData().getData();

        GraphicsModule g = new GraphicsModule();
        g.drawECG(comps);
    }

    public static void test1(FhirContext ctx) {
        // Create a Patient resource to serialize
        Patient patient = new Patient();
        patient.addName().setFamily("Simpson").addGiven("James");

        // Instantiate a new JSON parser
        IParser parser = ctx.newJsonParser();
        parser.setPrettyPrint(true);

        // Serialize it
        String serialized = parser.encodeResourceToString(patient);
        System.out.println(serialized);
    }
}
