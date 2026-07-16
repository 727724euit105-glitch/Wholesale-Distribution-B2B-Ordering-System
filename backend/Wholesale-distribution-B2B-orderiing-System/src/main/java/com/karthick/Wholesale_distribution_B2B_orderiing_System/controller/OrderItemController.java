package com.karthick.Wholesale_distribution_B2B_orderiing_System.controller;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderItemRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderItemResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.OrderItemRepository;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @PostMapping
    public OrderItemResponseDTO addOrderItem(@RequestBody OrderItemRequestDTO dto){
        return orderItemService.addOrderItem(dto);
    }
    @GetMapping
    public List<OrderItemResponseDTO> getAllOrderItems(){
        return orderItemService.getAllOrderItems();
    }
    @GetMapping("/{id}")
    public OrderItemResponseDTO getOrderItemById(@PathVariable Long id){
        return orderItemService.getOrderItemById(id);
    }
    @GetMapping("/order/{orderId}")
    public List<OrderItemResponseDTO> getItemsByOrder(@PathVariable Long orderdId){
        return orderItemService.getItemsByOrder(orderdId);
    }
    @PutMapping("/{id}")
    public OrderItemResponseDTO updateOrderItem(@PathVariable Long id,@RequestBody OrderItemRequestDTO dto){
        return orderItemService.updateOrderItem(id, dto);
    }
    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
    }
}
