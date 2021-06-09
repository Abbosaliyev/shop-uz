package uz.apelsin.apelsintask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/4/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "detail")
public class DetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
  //  @JoinColumn(name = "ord_id")
    private OrderEntity ord;

    @ManyToOne(optional = false)
   // @JoinColumn(name = "pr_id")
    private ProductEntity pr;

    @Column(nullable = false)
    private Short quantity;



}
