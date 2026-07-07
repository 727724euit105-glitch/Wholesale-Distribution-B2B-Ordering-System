package com.karthick.Wholesale_distribution_B2B_orderiing_System.controller;


import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.ProductRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.ProductResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public Page<ProductResponseDTO> getAllProducts(@RequestParam int page, @RequestParam int size){
        return productService.getAllProducts( page, size);
    }
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequestDTO dto){
        productService.updateProduct(id,dto);
        return "Product Updated Successfully";
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "Product Deleted Successfully";
    }
    @GetMapping("/category/{category}")
    public List<ProductResponseDTO> getProductsByCategory(@PathVariable String category){
        return productService.getProductsByCategory(category);
    }
    @GetMapping("/brand/{brand}")
    public List<ProductResponseDTO> getProductByBrand(@PathVariable String brand){
        return productService.getProductByBrand(brand);
    }
    @GetMapping("/search/{productName}")
    public List<ProductResponseDTO> searchProduct(@PathVariable String productName){
        return productService.searchProduct(productName);
    }
}
