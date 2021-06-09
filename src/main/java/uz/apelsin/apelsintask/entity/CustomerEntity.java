package uz.apelsin.apelsintask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/3/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)//, length = 14)
    private String name;

    @Column(nullable = false)//, length = 3)
    private String country;

    @Column(columnDefinition = "text")
    private String address;

    @Column(nullable = false, length = 50)
    private String phone;

}
