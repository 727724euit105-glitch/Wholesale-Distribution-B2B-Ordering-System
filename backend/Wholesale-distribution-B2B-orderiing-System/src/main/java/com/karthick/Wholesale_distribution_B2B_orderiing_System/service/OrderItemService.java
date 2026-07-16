package com.karthick.Wholesale_distribution_B2B_orderiing_System.service;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderItemRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderItemResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.OrderResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Order;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.OrderItem;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Product;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.OrderItemRepository;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.OrderRepository;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    public OrderItemResponseDTO addOrderItem(OrderItemRequestDTO dto){
        Order order=orderRepository.findById(dto.getOrderId()).orElseThrow(()->
                new RuntimeException("Order Not Found"));
        Product product= productRepository.findById(dto.getProductId()).orElseThrow(()->
                new RuntimeException("Product Not Found"));
        OrderItem orderItem=new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setUnitPrice(product.getPrice());
        orderItem.setSubtotal(dto.getQuantity()* product.getPrice());
        OrderItem savedOrderItem=orderItemRepository.save(orderItem);
        order.setTotalAmount(order.getTotalAmount()+ savedOrderItem.getSubtotal());
        orderRepository.save(order);
        return convertToDTO(savedOrderItem);
    }
    public List<OrderItemResponseDTO> getAllOrderItems(){
        List<OrderItem> orderItems=orderItemRepository.findAll();
        List<OrderItemResponseDTO> response=new ArrayList<>();
        for (OrderItem orderItem:orderItems){
            response.add(convertToDTO(orderItem));
        }
        return response;
    }
    public OrderItemResponseDTO getOrderItemById(Long id){
        OrderItem orderItem=orderItemRepository.findById(id).orElseThrow(()->
                new RuntimeException("Order item Not Found"));
        return convertToDTO(orderItem);
    }
    public void deleteOrderItem(Long id){
        OrderItem orderItem=orderItemRepository.findById(id).orElseThrow(()->
                new RuntimeException("Order Item Not Found"));
        Order order= orderItem.getOrder();
        order.setTotalAmount(order.getTotalAmount()- orderItem.getSubtotal()); // because when we delete product the totalAmount has reduced
        orderRepository.save(order);
        orderItemRepository.delete(orderItem);
    }
    public OrderItemResponseDTO updateOrderItem(Long id,OrderItemRequestDTO dto){
        OrderItem orderItem=orderItemRepository.findById(id).orElseThrow(()->
                new RuntimeException("Order Item Not Found"));
        Product product=productRepository.findById(dto.getProductId()).orElseThrow(()->
                new RuntimeException("Product Not Found"));
        Order order= orderItem.getOrder();
        //remove old subtotal
        order.setTotalAmount(order.getTotalAmount()- orderItem.getSubtotal());
        //update quantity
        orderItem.setQuantity(dto.getQuantity());
        //recalculate subtotal
        orderItem.setSubtotal(orderItem.getUnitPrice()* orderItem.getQuantity());
        //Add new totalAmount
        order.setTotalAmount(order.getTotalAmount()+ orderItem.getSubtotal());

        orderRepository.save(order);
        OrderItem updatedOrderItem=orderItemRepository.save(orderItem);
        return convertToDTO(updatedOrderItem);
    }
    public List<OrderItemResponseDTO> getItemsByOrder(Long orderId){
        List<OrderItem> orderItems=orderItemRepository.findByOrderId(orderId);
        List<OrderItemResponseDTO> response=new ArrayList<>();
        for(OrderItem orderItem:orderItems){
            response.add(convertToDTO(orderItem));
        }
        return response;
    }
    private OrderItemResponseDTO convertToDTO(OrderItem orderItem){
        OrderItemResponseDTO dto=new OrderItemResponseDTO();
        dto.setId(orderItem.getId());
        dto.setProductName(orderItem.getProduct().getProductName());
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        dto.setSubtotal(orderItem.getSubtotal());
        return dto;
    }

}
