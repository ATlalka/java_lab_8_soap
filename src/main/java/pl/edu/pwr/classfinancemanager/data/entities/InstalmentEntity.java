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
public class InstalmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int number;

    @Column
    private LocalDate deadline;

    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

    @Override
    public String toString() {
        return "[" + id +"] "+ deadline+" - " + amount + " zl - "+
                number+". - " + eventEntity.getName();

    }
}
