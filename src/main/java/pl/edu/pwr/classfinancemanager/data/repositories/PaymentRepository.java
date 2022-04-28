package pl.edu.pwr.classfinancemanager.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.classfinancemanager.data.entities.PaymentEntity;

import java.util.List;


public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findAllBy();
    List<PaymentEntity> findAllByPersonEntityId(Long personId);
}
