package com.karthick.Wholesale_distribution_B2B_orderiing_System.repository;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerRepository extends JpaRepository<Dealer,Long> {
    List<Dealer> findByDealerNameContainingIgnoreCase(String keyword);
}
