package pl.edu.pwr.classfinancemanager.soap;

import pl.edu.pwr.classfinancemanager.data.entities.EventEntity;
import pl.edu.pwr.classfinancemanager.data.entities.InstalmentEntity;
import pl.edu.pwr.classfinancemanager.data.entities.PaymentEntity;
import pl.edu.pwr.classfinancemanager.data.entities.PersonEntity;
import pl.edu.pwr.classfinancemanager.soap.generateddata.Event;
import pl.edu.pwr.classfinancemanager.soap.generateddata.Instalment;
import pl.edu.pwr.classfinancemanager.soap.generateddata.Payment;
import pl.edu.pwr.classfinancemanager.soap.generateddata.Person;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Mapper {

    public static Person mapPerson(PersonEntity pe){
        Person p = new Person();
        p.setId(pe.getId());
        p.setName(pe.getName());
        p.setSurname(pe.getSurname());
        return p;
    }

    public static Event mapEvent(EventEntity ee){
        Event e = new Event();
        e.setId(ee.getId());
        e.setName(ee.getName());
        e.setDate(ee.getDate().toString());
        e.setPlace(ee.getPlace());
        return e;
    }

    public static Payment mapPayment(PaymentEntity pe){
        Payment p = new Payment();
        p.setId(pe.getId());
        p.setAmount(pe.getAmount());
        p.setInstalmentId(pe.getInstalmentEntity().getId());
        p.setPayDay(pe.getPayDay().toString());
        p.setPersonId(pe.getPersonEntity().getId());
        return p;
    }

    public static Instalment mapInstalment(InstalmentEntity ie){
        Instalment i = new Instalment();
        i.setId(ie.getId());
        i.setAmount(ie.getAmount());
        i.setInstalmentNumber(ie.getNumber());
        i.setEventId(ie.getEventEntity().getId());
        i.setDeadline(ie.getDeadline().toString());
        return i;
    }
}
