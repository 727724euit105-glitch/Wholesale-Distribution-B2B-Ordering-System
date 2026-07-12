package com.karthick.Wholesale_distribution_B2B_orderiing_System.service;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Dealer;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Order;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Product;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.DealerRepository;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DealerRepository dealerRepository;

    public void addOrder(OrderRequestDTO dto){
        Dealer dealer=dealerRepository.findById(dto.getDealerId()).orElseThrow(()
        ->new RuntimeException("Dealer not found."));
        Order order=convertToEntity(dto,dealer);
        orderRepository.save(order);

    }
    public List<OrderResponseDTO> getAllOrders(){
        List<Order> orders=orderRepository.findAll();
        List<OrderResponseDTO> responseList=new ArrayList<>();
        for(Order order:orders){
            responseList.add(convertToDTO(order));
        }
        return responseList;
    }
    private Order convertToEntity(OrderRequestDTO dto,Dealer dealer){
        Order order=new Order();
        order.setDealer(dealer);
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        order.setTotalAmount(0.0);
        return order;
    }
    private OrderResponseDTO convertToDTO(Order order){
        OrderResponseDTO dto=new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setDealerName(order.getDealer().getDealerName());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        return dto;
    }
}
