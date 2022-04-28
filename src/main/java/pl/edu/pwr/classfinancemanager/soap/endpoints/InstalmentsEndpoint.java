package pl.edu.pwr.classfinancemanager.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.edu.pwr.classfinancemanager.data.entities.InstalmentEntity;
import pl.edu.pwr.classfinancemanager.data.services.InstalmentService;
import pl.edu.pwr.classfinancemanager.soap.Mapper;
import pl.edu.pwr.classfinancemanager.soap.generateddata.CreateInstalmentRequest;
import pl.edu.pwr.classfinancemanager.soap.generateddata.GetInstalmentsByPersonRequest;
import pl.edu.pwr.classfinancemanager.soap.generateddata.GetInstalmentsRequest;
import pl.edu.pwr.classfinancemanager.soap.generateddata.GetInstalmentsResponse;

@Endpoint
@RequiredArgsConstructor
public class InstalmentsEndpoint {

    private static final String NAMESPACE_URI = "http://www.classfinancemanager.pwr.edu.pl/soap/generateddata";

    private final InstalmentService instalmentService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInstalmentsRequest")
    @ResponsePayload
    public GetInstalmentsResponse getInstalments(@RequestPayload GetInstalmentsRequest request) {
        GetInstalmentsResponse response = new GetInstalmentsResponse();
        for(InstalmentEntity i : instalmentService.getInstalments()){
            response.getInstalmentsList().add(Mapper.mapInstalment(i));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createInstalmentRequest")
    @ResponsePayload
    public GetInstalmentsResponse createInstalment(@RequestPayload CreateInstalmentRequest request) {
        GetInstalmentsResponse response = new GetInstalmentsResponse();
        instalmentService.createInstalment(new String[]{
                Long.toString(request.getInstalment().getEventId()),
                Long.toString(request.getInstalment().getInstalmentNumber()),
                request.getInstalment().getDeadline(),
                Double.toString(request.getInstalment().getAmount())
        });
        for(InstalmentEntity i : instalmentService.getInstalments()){
            response.getInstalmentsList().add(Mapper.mapInstalment(i));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInstalmentsByPersonRequest")
    @ResponsePayload
    public GetInstalmentsResponse getInstalmentsByPerson(@RequestPayload GetInstalmentsByPersonRequest request) {
        GetInstalmentsResponse response = new GetInstalmentsResponse();
        for(InstalmentEntity i : instalmentService.getInstalmentsByPerson(request.getPersonId())){
            response.getInstalmentsList().add(Mapper.mapInstalment(i));
        }
        return response;
    }


}

