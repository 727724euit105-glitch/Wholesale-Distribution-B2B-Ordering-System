package com.karthick.Wholesale_distribution_B2B_orderiing_System.repository;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
