package uz.apelsin.apelsintask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.apelsin.apelsintask.entity.CustomerEntity;

import java.util.List;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/4/2021
*/
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    @Query(value =
            "select distinct on(c.id) * \n" +
                    "from customer as c\n" +
                    "inner join orders as o on c.id=o.cust_id\n" +
                    "where c.id in(" +
                    "select c.id " +
                    "from customer as c" +
                    "where c.id not in(" +
                    "select o.cust_id " +
                    "from orders as o " +
                    "where o.date between '2016-01-01 00:00:00' and '2016-12-31 23:59:59.999'" +
                    ")" +
                    ")",
            nativeQuery = true)
    List<CustomerEntity> getCustomersWithoutOrders();

    @Query(value =
            "select distinct on(res.id) res.id, res.name, res.date \n" +
                    "from(\n" +
                    "select c.id, c.name, o.date\n" +
                    "from customer c\n" +
                    "inner join orders o\n" +
                    "on c.id=o.cust_id\n" +
                    "order by o.date desc\n" +
                    ") res",
            nativeQuery = true)
    List<String[]> getCustomersAndCustomersLastOrders();
}
