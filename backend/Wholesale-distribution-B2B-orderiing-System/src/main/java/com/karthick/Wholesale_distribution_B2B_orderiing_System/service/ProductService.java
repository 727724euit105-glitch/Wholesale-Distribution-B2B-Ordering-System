package com.karthick.Wholesale_distribution_B2B_orderiing_System.service;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.ProductRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Product;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public void addProduct(ProductRequestDTO dto){
//        System.out.println("Product Name: "+dto.getProductName());
//        System.out.println("Product Brand: "+dto.getBrand());
//        System.out.println("Product Category: "+dto.getCategory());
//        System.out.println("Product Price: "+dto.getPrice());
        Product product=new Product();
        product.setProductName(dto.getProductName());
        product.setBrand(dto.getBrand());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setPackSize(dto.getPackSize());
        product.setGstRate(dto.getGstRate());
        product.setHsnCode(dto.getHsnCode());
        product.setDescription(dto.getDescription());
        productRepository.save(product);
    }
}
