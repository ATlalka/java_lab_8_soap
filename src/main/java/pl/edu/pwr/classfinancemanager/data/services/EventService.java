package pl.edu.pwr.classfinancemanager.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.classfinancemanager.CustomDate;
import pl.edu.pwr.classfinancemanager.data.entities.EventEntity;
import pl.edu.pwr.classfinancemanager.data.repositories.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<EventEntity> getEvents() {
        return eventRepository.findAllBy();
    }

    public EventEntity createEvent(String args[]) {
        try {
            args[2] = args[2].replace(".","/");
            EventEntity.EventEntityBuilder builder = EventEntity.builder();
            EventEntity eventEntity = builder.name(args[0])
                    .place(args[1])
                    .date(CustomDate.parse(args[2]))
                    .build();
            eventRepository.saveAndFlush(eventEntity);
            return eventEntity;
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

}

