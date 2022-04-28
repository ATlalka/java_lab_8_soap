package pl.edu.pwr.classfinancemanager.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.edu.pwr.classfinancemanager.data.entities.PersonEntity;
import pl.edu.pwr.classfinancemanager.data.services.PersonService;
import pl.edu.pwr.classfinancemanager.soap.Mapper;
import pl.edu.pwr.classfinancemanager.soap.generateddata.CreatePersonRequest;
import pl.edu.pwr.classfinancemanager.soap.generateddata.GetPersonsResponse;
import pl.edu.pwr.classfinancemanager.soap.generateddata.GetPersonsRequest;


@Endpoint
@RequiredArgsConstructor
public class PersonsEndpoint {

    private static final String NAMESPACE_URI = "http://www.classfinancemanager.pwr.edu.pl/soap/generateddata";

    private final PersonService personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonsRequest")
    @ResponsePayload
    public GetPersonsResponse getPersons(@RequestPayload GetPersonsRequest request) {
        GetPersonsResponse response = new GetPersonsResponse();
        for(PersonEntity pe : personService.getPersons()){
            response.getPersonsList().add(Mapper.mapPerson(pe));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPersonRequest")
    @ResponsePayload
    public GetPersonsResponse createPerson(@RequestPayload CreatePersonRequest request) {
        GetPersonsResponse response = new GetPersonsResponse();
        personService.createPerson(new String[]{
                request.getPerson().getName(),
                request.getPerson().getSurname()
        });
        for(PersonEntity pe : personService.getPersons()){
            response.getPersonsList().add(Mapper.mapPerson(pe));
        }
        return response;
    }
}

