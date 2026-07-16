package com.karthick.Wholesale_distribution_B2B_orderiing_System.service;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.InventoryRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.InventoryResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Inventory;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Product;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.InventoryRepository;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;
    public InventoryResponseDTO addInventory(InventoryRequestDTO dto){
        Product product=productRepository.findById(dto.getProductId()).orElseThrow(()->
                new RuntimeException("Product Not Found"));
        Inventory inventory=convertToEntity(dto,product);
        Inventory savedInventory=inventoryRepository.save(inventory);
        return convertToDTO(savedInventory);
    }
    private Inventory convertToEntity(InventoryRequestDTO dto, Product product){
        Inventory inventory=new Inventory();
        inventory.setProduct(product);
        inventory.setCurrentStock(dto.getCurrentStock());
        inventory.setMinimumStock(dto.getMinimumStock());
        inventory.setLastUpdated(LocalDateTime.now());
        if(dto.getCurrentStock()==0){
            inventory.setStockStatus("OUT_OF_STOCK");
        }else if(dto.getCurrentStock()<=dto.getMinimumStock()){
            inventory.setStockStatus("OUT_OF_STOCK");
        }
        else {
            inventory.setStockStatus("IN_STOCK");
        }
        return  inventory;
    }
    private InventoryResponseDTO convertToDTO(Inventory inventory){
        InventoryResponseDTO dto=new InventoryResponseDTO();
        dto.setId(inventory.getId());
        dto.setProductName(inventory.getProduct().getProductName());
        dto.setCurrentStock(inventory.getCurrentStock());
        dto.setMinimumStock(inventory.getMinimumStock());
        dto.setStockStatus(inventory.getStockStatus());
        dto.setLastUpdated(inventory.getLastUpdated());
        return dto;
    }
}
