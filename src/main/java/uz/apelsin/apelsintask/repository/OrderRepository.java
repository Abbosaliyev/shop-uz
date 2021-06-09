package uz.apelsin.apelsintask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.apelsin.apelsintask.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/4/2021
*/
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value =
            "select *\n" +
                    "from orders o\n" +
                    "inner join (\n" +
                    "\tselect o.id \n" +
                    "\tfrom orders o\n" +
                    "\texcept\n" +
                    "\tselect d.ord_id \n" +
                    "\tfrom detail d\n" +
                    ") res \n" +
                    "on o.id=res.id\n" +
                    "and o.date < ?1",
            nativeQuery = true)
    List<OrderEntity> getOrdersWithoutDetails(LocalDateTime date);

    @Query(value =
            "select res.country_name, res.total_orders\n" +
                    "from(\n" +
                    "select distinct cus.country as country_name, (" +
                    "select sum(\n" +
                    "(select count(*) " +
                    " from orders as o " +
                    " where o.cust_id=c.id and " +
                    " date between '2016-01-01 00:00:00' and '2016-12-31 23:59:59.999')\n" +
                    ") as all_orders " +
                    "from customer as c " +
                    "where c.country=cus.country" +
                    ") as total_orders " +
                    "from customer as cus" +
                    ") as res" +
                    "where res.total_orders>0",
            nativeQuery = true)
    List<String[]> getNumberOfProductsInYear();

    @Query(value =
            "select distinct on(o.id) o.id as order_id, o.date as date, (select sum(res.total_price) from(select (p.price * d.quantity) as total_price \n" +
                    "from product as p\n" +
                    "inner join detail as d on d.ord_id=o.id\n" +
                    "where p.id=d.pr_id) as res\n" +
                    ") as total_price\n" +
                    "from orders as o\n" +
                    "inner join detail as d on d.ord_id=o.id \n" +
                    "where o.id in(\n" +
                    "select id \n" +
                    "from orders \n" +
                    "except \n" +
                    "select ord_id \n" +
                    "from invoice\n" +
                    ")",
            nativeQuery = true)
    List<String[]> getOrdersWithoutInvoices();

}
