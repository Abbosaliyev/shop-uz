package uz.apelsin.apelsintask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.apelsin.apelsintask.entity.ProductEntity;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/4/2021
*/
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
}
