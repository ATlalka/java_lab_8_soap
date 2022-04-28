package pl.edu.pwr.classfinancemanager.data.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate payDay;

    @Column
    private double amount;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @ManyToOne
    @JoinColumn(name = "installment_id")
    private InstalmentEntity instalmentEntity;

    @Override
    public String toString() {
        return
                "[" + id +"] "+ payDay+" - " +
                amount + "zl - "+ personEntity.getName()+" "+ personEntity.getSurname()+" - "+ instalmentEntity.getEventEntity().getName() + " - "+ instalmentEntity.getNumber();
    }
}
