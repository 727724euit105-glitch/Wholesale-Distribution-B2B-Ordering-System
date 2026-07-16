package com.karthick.Wholesale_distribution_B2B_orderiing_System.controller;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.OrderRepository;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping
    public String addOrder(@Valid @RequestBody OrderRequestDTO dto){
        orderService.addOrder(dto);
        return "success";
    }
    @GetMapping
    public Page<OrderResponseDTO> getAllOrders(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "id")String sortBy,@RequestParam(defaultValue = "asc") String direction){
        return orderService.getAllOrders(page,size,sortBy,direction);
    }
    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
    @PutMapping("/{id}")
    public OrderResponseDTO updateOrder(@PathVariable Long id,@RequestBody OrderRequestDTO dto){
        return orderService.updateOrder(id, dto);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }
    @GetMapping("/status/{status}")
    public List<OrderResponseDTO> getOrdersByStatus(@PathVariable String status){
        return orderService.getOrdersByStatus(status);
    }
}
