package uz.apelsin.apelsintask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/4/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.valueOf(8,2);

    @ManyToOne(optional = false)
//    @JoinColumn(name = "inv_id")
    private InvoiceEntity inv;


}
