package uz.apelsin.apelsintask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/3/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(optional = false)
//    @JoinColumn(name = "cust_id")
    private CustomerEntity cust;


}
