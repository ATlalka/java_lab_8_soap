package pl.edu.pwr.classfinancemanager.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.edu.pwr.classfinancemanager.data.entities.EventEntity;
import pl.edu.pwr.classfinancemanager.data.services.EventService;
import pl.edu.pwr.classfinancemanager.soap.Mapper;
import pl.edu.pwr.classfinancemanager.soap.generateddata.CreateEventRequest;
import pl.edu.pwr.classfinancemanager.soap.generateddata.GetEventsRequest;
import pl.edu.pwr.classfinancemanager.soap.generateddata.GetEventsResponse;

@Endpoint
@RequiredArgsConstructor
public class EventsEndpoint {
    private static final String NAMESPACE_URI = "http://www.classfinancemanager.pwr.edu.pl/soap/generateddata";

    private final EventService eventService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventsRequest")
    @ResponsePayload
    public GetEventsResponse getEvents(@RequestPayload GetEventsRequest request) {
        GetEventsResponse response = new GetEventsResponse();
        for(EventEntity e : eventService.getEvents()){
            response.getEventsList().add(Mapper.mapEvent(e));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEventRequest")
    @ResponsePayload
    public GetEventsResponse createEvent(@RequestPayload CreateEventRequest request) {
        GetEventsResponse response = new GetEventsResponse();
        eventService.createEvent(new String[]{
                request.getEvent().getName(),
                request.getEvent().getPlace(),
                request.getEvent().getDate()
        });

        for(EventEntity e : eventService.getEvents()){
            response.getEventsList().add(Mapper.mapEvent(e));
        }
        return response;
    }
}
