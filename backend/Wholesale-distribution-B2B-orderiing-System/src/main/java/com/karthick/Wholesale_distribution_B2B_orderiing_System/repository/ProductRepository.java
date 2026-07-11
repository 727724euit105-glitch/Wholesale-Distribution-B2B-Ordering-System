package com.karthick.Wholesale_distribution_B2B_orderiing_System.repository;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory(String category);
    List<Product> findByBrand(String brand);
    List<Product> findByProductNameContaining(String productName);
    List<Product> findByPriceBetween(Double minPrice,Double maxPrice);
    List<Product> findByCategoryAndBrand(String category,String brand);
    List<Product> findByProductNameContainingIgnoreCaseOrBrandContainingIgnoreCase(String keyword1,String keyword2);
    //two para because of productname=? and brand=? hence each conditions require one keyword
    List<Product> findByCategoryAndPriceBetween(String category,Double minPrice,Double maxPrice);
    List<Product> findByCategoryOrderByPriceAsc(String category);
    List<Product> findByCategoryOrderByPriceDesc(String category);
}
