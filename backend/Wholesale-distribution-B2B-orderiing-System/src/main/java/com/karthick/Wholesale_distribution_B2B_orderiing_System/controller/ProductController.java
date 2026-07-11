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

    @Autowired //injects objects from securityConfig using @Bean object .
    private ProductService productService;
    @PostMapping
    public String addProduct(@Valid @RequestBody ProductRequestDTO dto){

         productService.addProduct(dto);
         return "Success";
    }
    @GetMapping
    public Page<ProductResponseDTO> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "asc") String direction){
        return productService.getAllProducts( page, size,sortBy,direction);
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
    @GetMapping("/price/{minPrice}/{maxPrice}")
    public List<ProductResponseDTO> getProductsByPriceRange(@PathVariable Double minPrice,@PathVariable Double maxPrice){
        return productService.getProductByPriceRange(minPrice,maxPrice);
    }
    @GetMapping("/filter") //category+brand
    public List<ProductResponseDTO> getProductByCategoryAndBrand(@RequestParam String category,@RequestParam String brand){
        return productService.getProductsByCategoryAndBrand(category, brand);
    }
    @GetMapping("/search") //productname+brand
    public List<ProductResponseDTO> searchProducts(@RequestParam String keyword){
        return productService.searchProducts(keyword);
    }
    @GetMapping("/filter-price") //category+price
    public List<ProductResponseDTO> getProductsByCategoryAndPrice(@RequestParam String category,@RequestParam Double minPrice,@RequestParam Double maxPrice){
        return productService.getProductsByCategoryAndPrice(category,minPrice,maxPrice);
    }
    @GetMapping("/category/{category}/price-asc")
    public List<ProductResponseDTO> getProductsByCategoryOrderByPriceAsc(@PathVariable String category){
        return productService.getProductsByCategoryPriceAsc(category);
    }
    @GetMapping("/category/{category}/price-desc")
    public List<ProductResponseDTO> getProductsByCategoryOrderByPriceDesc(@PathVariable String category){
        return productService.getProductsByCategoryPriceDesc(category);
    }
}
