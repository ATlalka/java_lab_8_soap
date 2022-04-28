package pl.edu.pwr.classfinancemanager.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.classfinancemanager.CustomDate;
import pl.edu.pwr.classfinancemanager.data.entities.EventEntity;
import pl.edu.pwr.classfinancemanager.data.entities.InstalmentEntity;
import pl.edu.pwr.classfinancemanager.data.entities.PaymentEntity;
import pl.edu.pwr.classfinancemanager.data.repositories.InstalmentRepository;
import pl.edu.pwr.classfinancemanager.data.repositories.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstalmentService {
    private final InstalmentRepository instalmentRepository;
    private final PaymentRepository paymentRepository;

    public List<InstalmentEntity> getInstalments() {
        return instalmentRepository.findAllBy();
    }

    public List<InstalmentEntity> getInstalmentsByPerson(Long personId) {
        List<InstalmentEntity> result = new ArrayList<>();
        for (InstalmentEntity instalmentEntity : instalmentRepository.findAllBy()) {
            for (PaymentEntity paymentEntity : paymentRepository.findAllByPersonEntityId(personId)) {
                if (paymentEntity.getInstalmentEntity().getId().equals(instalmentEntity.getId())) {
                    result.add(instalmentEntity);
                }
            }
        }
        return result;
    }
    public InstalmentEntity createInstalment(String args[]) {
        try {
            args[2] = args[2].replace(".","/");
            InstalmentEntity.InstalmentEntityBuilder builder = InstalmentEntity.builder();
            InstalmentEntity instalmentEntity = builder.number(Integer.parseInt(args[0]))
                    .amount(Double.parseDouble(args[1]))
                    .deadline(CustomDate.parse(args[2]))
                    .eventEntity(EventEntity.builder().id(Long.parseLong(args[3])).build())
                    .build();
            instalmentRepository.saveAndFlush(instalmentEntity);
            return instalmentEntity;
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

}

