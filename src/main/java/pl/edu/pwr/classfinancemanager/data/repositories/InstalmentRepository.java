package pl.edu.pwr.classfinancemanager.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.classfinancemanager.data.entities.InstalmentEntity;

import java.util.List;

@Repository
public interface InstalmentRepository extends JpaRepository<InstalmentEntity, Long> {
    List<InstalmentEntity> findAllBy();
}
