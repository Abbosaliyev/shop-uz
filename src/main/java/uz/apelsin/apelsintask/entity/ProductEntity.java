package uz.apelsin.apelsintask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/3/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)//,length = 10) error: value too long for type character varying(10)
    private String name;

    @ManyToOne(optional = false)

    private CategoryEntity category;

//    @Column(length = 20)
    private String description;

    @Column(nullable = false)
    private BigDecimal price =BigDecimal.valueOf(6,2);

    @Column(length = 1024)
    private String photo;
}

