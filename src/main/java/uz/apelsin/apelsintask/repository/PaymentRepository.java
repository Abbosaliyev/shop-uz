package uz.apelsin.apelsintask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.apelsin.apelsintask.entity.PaymentEntity;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/4/2021
*/
public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {
}
