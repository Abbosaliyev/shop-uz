package uz.apelsin.apelsintask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.apelsin.apelsintask.entity.DetailEntity;
import uz.apelsin.apelsintask.entity.OrderEntity;

import java.util.List;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/4/2021
*/
public interface DetailRepository extends JpaRepository<DetailEntity, Integer> {
    List<DetailEntity> findAllByOrd_Id(Integer order_id);

    @Query(value =
            "select res.product_id, res.quantity \n" +
                    "from(\n" +
                    "select p.id as product_id, (\n" +
                    "select sum(quantity) \n" +
                    "from detail as d \n" +
                    "where d.pr_id=p.id\n" +
                    ")as quantity \n" +
                    "from product as p) as res \n" +
                    "where res.quantity>10",
            nativeQuery = true)
    List<String[]> getHighDemandProducts();

    @Query(value =
            "select res.product_id, p.price\n" +
                    "from product as p\n" +
                    "inner join(\n" +
                    "select p.id as product_id, (\n" +
                    "select avg(d.quantity) \n" +
                    "from detail as d \n" +
                    "where d.pr_id=p.id\n" +
                    ") as avarage_quantity \n" +
                    "from product as p\n" +
                    ") as res on p.id=res.product_id\n" +
                    "where res.avarage_quantity>=8",
            nativeQuery = true)
    List<String[]> getBulkProducts();
}
