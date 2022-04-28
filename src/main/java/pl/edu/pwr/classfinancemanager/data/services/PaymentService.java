package pl.edu.pwr.classfinancemanager.data.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.edu.pwr.classfinancemanager.CustomDate;
import pl.edu.pwr.classfinancemanager.data.entities.InstalmentEntity;
import pl.edu.pwr.classfinancemanager.data.entities.PaymentEntity;
import pl.edu.pwr.classfinancemanager.data.entities.PersonEntity;
import pl.edu.pwr.classfinancemanager.data.repositories.PaymentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InstalmentService instalmentService;
    private final PersonService personService;

    public List<PaymentEntity> getPayments() {
        return paymentRepository.findAllBy();
    }

    public List<PaymentEntity> getPaymentsByPerson(Long personId) {
        return paymentRepository.findAllByPersonEntityId(personId);
    }

    private static final Logger logger = LogManager.getLogger(PaymentService.class);

    public PaymentEntity createPayment(String args[]) {
        try {
            args[1] = args[1].replace(".","/");
            PaymentEntity.PaymentEntityBuilder builder = PaymentEntity.builder();
            PaymentEntity paymentEntity = builder.amount(Double.parseDouble(args[0]))
                    .payDay(CustomDate.parse(args[1]))
                    .personEntity(PersonEntity.builder().id(Long.parseLong(args[2])).build())
                    .instalmentEntity(InstalmentEntity.builder().id(Long.parseLong(args[3])).build())
                    .build();
            paymentRepository.saveAndFlush(paymentEntity);
            savePayment();
            return paymentEntity;
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

    public void checkShortgages(LocalDate date){
        for(PersonEntity p : personService.getPersons()){
            List<InstalmentEntity> instalmentEntities = instalmentService.getInstalments();
            List<PaymentEntity> paymentEntities = getPaymentsByPerson(p.getId());
            while(!paymentEntities.isEmpty()){
                long id = paymentEntities.get(0).getInstalmentEntity().getId();
                double paidAmount = paymentEntities.get(0).getAmount();
                for(InstalmentEntity i : instalmentEntities){
                    if(i.getId() == id){
                        if(paidAmount < i.getAmount() && i.getDeadline().isBefore(date)){
                            logger.log(Level.getLevel("Shortgage"), "Person "+p.toString() +" paid too little in "+i.getNumber()+". for "+i.getEventEntity().getName());
                        }
                        instalmentEntities.remove(i);
                        break;
                    }
                }
                paymentEntities.remove(0);
            }

            for (InstalmentEntity i : instalmentEntities){
                if(i.getDeadline().isBefore(date))
                    logger.log(Level.getLevel("Shortgage"), "Person "+p.toString() +" has not paid "+i.getNumber()+". for "+i.getEventEntity().getName());
            }
        }

    }

    public void savePayment(){
        logger.log(Level.getLevel("Income"), "New income: "+getPayments().get(getPayments().size()-1));
    }

}

