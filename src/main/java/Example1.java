import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;

import java.util.Date;

public class Example1 {

    public static void main(String[] args) {
        FhirContext context = FhirContext.forR4();
        IGenericClient client = context.newRestfulGenericClient("https://hapi.fhir.org/baseR4");
        Patient patient = client.read().resource(Patient.class).withId("592502").execute();

        patient.getNameFirstRep().addGiven("hello " + new Date());
        MethodOutcome patientResult = client.update().resource(patient).execute();


        System.out.println(patientResult.getResource().getIdElement());

        Observation o = new Observation();
        o.getCode().getCodingFirstRep().setCode("hello");
        //o.setCode(new CodeableConcept().addCoding(new Coding().setCode("hello")));

        MethodOutcome obsResult = client.create().resource(o).execute();
        System.out.println(obsResult.getResource().getIdElement().getValue());


    }
}
