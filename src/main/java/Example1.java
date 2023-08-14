import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.*;
import org.monarchinitiative.hapiphenocore.phenopacket.PhenotypicFeature;

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

        PhenotypicFeature feature = new PhenotypicFeature();

        DateTimeType date = new DateTimeType();
        date.setPrecision(TemporalPrecisionEnum.DAY);
        BaseDateTimeType baseDateTimeType = date.setYear(2022).setDay(1).setMonth(1);
        feature.setEffective(baseDateTimeType);
        feature.setHpoSevere();

        MethodOutcome featureOutcome = client.create().resource(feature).execute();

        System.out.println(featureOutcome.getResource().getIdElement());

    }
}
