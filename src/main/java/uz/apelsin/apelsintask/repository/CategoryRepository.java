package uz.apelsin.apelsintask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.apelsin.apelsintask.entity.CategoryEntity;

import java.util.Optional;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/4/2021
*/
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value =
            "select * " +
                    "from category as c " +
                    "inner join product as p on p.category_id=c.id " +
                    "where p.id=?1",
            nativeQuery = true)
    Optional<CategoryEntity> findByProductId(Integer product_id);
}
