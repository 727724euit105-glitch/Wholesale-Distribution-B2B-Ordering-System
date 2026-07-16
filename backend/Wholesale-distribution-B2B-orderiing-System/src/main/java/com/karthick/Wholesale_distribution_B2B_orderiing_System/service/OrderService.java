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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Page<OrderResponseDTO> getAllOrders(int page,int size,String sortBy,String direction){
        Sort sort;
        if(direction.equalsIgnoreCase("desc")){
            sort=Sort.by(sortBy).descending();
        }else {
            sort=Sort.by(sortBy).ascending();
        }
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Order> orders=orderRepository.findAll(pageable);
//        List<OrderResponseDTO> responseList=new ArrayList<>();
//        for(Order order:orders){
//            responseList.add(convertToDTO(order));
//        }
//        return responseList;
        return orders.map(this::convertToDTO);
    }
    public OrderResponseDTO getOrderById(Long id){
        //Optional is used for handling null pointer exception
        Optional<Order> optionalorder=orderRepository.findById(id);
        Order order=optionalorder.orElseThrow(()->new RuntimeException("Order Not Found"));
        return convertToDTO(order);
    }
    public OrderResponseDTO updateOrder(Long id,OrderRequestDTO dto){
        Order order=orderRepository.findById(id).orElseThrow(()->new RuntimeException("Dealer Not Found"));
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        Order updateOrder=orderRepository.save(order);
        return convertToDTO(updateOrder);
    }
    public String deleteOrder(Long id){
        Order order=orderRepository.findById(id).orElseThrow(()->new RuntimeException("Dealer Not Present"));
        orderRepository.delete(order);
        return "Order Deleted Successfully";
    }
    public List<OrderResponseDTO> getOrdersByStatus(String status){
        List<Order> orders=orderRepository.findByStatus(status);
        return orders.stream()
                .map(this::convertToDTO)
                .toList();
    }
    private Order convertToEntity(OrderRequestDTO dto,Dealer dealer){
        //used for put the values to the db table
        Order order=new Order();
        order.setDealer(dealer);
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        order.setTotalAmount(0.0);
        return order;
    }
    private OrderResponseDTO convertToDTO(Order order){
        //used for fetch the values from table using ResponseDTO otherwords requests data from requestDTO
        OrderResponseDTO dto=new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setDealerName(order.getDealer().getDealerName());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        return dto;
    }
}
