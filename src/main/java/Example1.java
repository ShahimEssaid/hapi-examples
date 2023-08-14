import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Patient;

public class Example1 {

    public static void main(String[] args) {
        FhirContext context = FhirContext.forR4();
        IGenericClient client = context.newRestfulGenericClient("https://hapi.fhir.org/baseR4");
        Patient execute = client.read().resource(Patient.class).withId("592502").execute();

        System.out.println(execute);


    }
}
