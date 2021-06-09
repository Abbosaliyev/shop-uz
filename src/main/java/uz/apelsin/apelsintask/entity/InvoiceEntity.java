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
 6/3/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    private OrderEntity ord;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.valueOf(8, 2);

    @Column(nullable = false)
    private LocalDateTime issued; //berilgan sana

    @Column(nullable = false)
    private LocalDateTime due;

}
