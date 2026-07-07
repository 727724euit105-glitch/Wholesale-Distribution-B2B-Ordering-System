package com.karthick.Wholesale_distribution_B2B_orderiing_System.service;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.ProductRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.ProductResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Product;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.exception.ProductNotFoundException;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductRequestDTO dto) {
        //post all products dto-to-entity conversion
        Product product = convertToEntity(dto);

        productRepository.save(product);
    }
        public Page<ProductResponseDTO> getAllProducts(int page,int size) {
        //getAllproducts
            Pageable pageable= PageRequest.of(page, size);
            Page<Product> productPage=productRepository.findAll(pageable);
            return productPage.map(this::convertToDTO);
        }
    public List<ProductResponseDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        List<ProductResponseDTO> responseList = new ArrayList<>();
        for (Product product : products) {
            responseList.add(convertToDTO(product));
        }
        return responseList;
    }
    public List<ProductResponseDTO> getProductByBrand(String brand){
        List<Product> products=productRepository.findByBrand(brand);
        List<ProductResponseDTO> responseList=new ArrayList<>();
        for(Product product:products){
            responseList.add(convertToDTO(product));
        }
        return responseList;
    }
    public List<ProductResponseDTO> searchProduct(String productName){
        List<Product> products=productRepository.findByProductNameContaining(productName);
        List<ProductResponseDTO> responseList=new ArrayList<>();
        for(Product product:products){
            responseList.add(convertToDTO(product));
        }
        return responseList;
    }
        public ProductResponseDTO getProductById(Long id){
        Optional<Product> optionalProduct= productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            //convert entity to DTO
            return convertToDTO(product);
        }else {
            throw new ProductNotFoundException("Product not found");
        }
        }
        public void updateProduct(Long id,ProductRequestDTO dto){
            Optional<Product> optionalProduct= productRepository.findById(id);
            if(optionalProduct.isPresent()){
                Product product= optionalProduct.get();
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
            else{
                throw new ProductNotFoundException("Product Not Found");
            }
        }
        public void deleteProduct(Long id){
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
        }else{
            throw new ProductNotFoundException("Product Not Found.");
        }
        }
    private ProductResponseDTO convertToDTO(Product product) {
        //dto-to-entity conversion
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setBrand(product.getBrand());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setPackSize(product.getPackSize());
        dto.setGstRate(product.getGstRate());
        dto.setHsnCode(product.getHsnCode());
        dto.setDescription(product.getDescription());
        return dto;
    }
    private Product convertToEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setBrand(dto.getBrand());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setPackSize(dto.getPackSize());
        product.setGstRate(dto.getGstRate());
        product.setHsnCode(dto.getHsnCode());
        product.setDescription(dto.getDescription());
        return product;
    }
}
