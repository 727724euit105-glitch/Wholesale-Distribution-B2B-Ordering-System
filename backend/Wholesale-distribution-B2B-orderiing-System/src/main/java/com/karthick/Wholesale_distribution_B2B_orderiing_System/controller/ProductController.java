package com.karthick.Wholesale_distribution_B2B_orderiing_System.controller;


import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.ProductRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping
    public String addProduct(@Valid @RequestBody ProductRequestDTO dto){

         productService.addProduct(dto);
         return "Success";
    }
}
