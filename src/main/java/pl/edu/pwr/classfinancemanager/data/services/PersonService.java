package pl.edu.pwr.classfinancemanager.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.classfinancemanager.data.repositories.PersonRepository;
import pl.edu.pwr.classfinancemanager.data.entities.PersonEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    public List<PersonEntity> getPersons() {
        return personRepository.findAllBy();
    }

    public PersonEntity createPerson(String args[]) {
        try {
            PersonEntity.PersonEntityBuilder builder = PersonEntity.builder();
            PersonEntity personEntity = builder.name(args[0])
                    .surname(args[1])
                    .build();
            personRepository.saveAndFlush(personEntity);
            return personEntity;
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

}
