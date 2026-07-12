package com.karthick.Wholesale_distribution_B2B_orderiing_System.controller;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.OrderRepository;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<OrderResponseDTO> getAllOrders(){
        return orderService.getAllOrders();
    }
}
