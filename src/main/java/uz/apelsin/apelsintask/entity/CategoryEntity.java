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
@Entity(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 250)
    private String name;
}
