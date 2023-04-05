package com.fhirtester;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.SampledData;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting program");

        // Build FHIR-context (only once, because this step is very resource-demanding!)
        FhirContext ctx = FhirContext.forR4();

        System.out.println("Context successfully started!\n" + ctx + "\n");

        String jsonContents = JSONReader.getFileContent();

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
