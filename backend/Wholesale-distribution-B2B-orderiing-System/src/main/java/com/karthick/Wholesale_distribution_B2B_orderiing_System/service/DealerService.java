package com.karthick.Wholesale_distribution_B2B_orderiing_System.service;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.DealerRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.DealerResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.entity.Dealer;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.exception.ResourceNotFoundException;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DealerService {

    @Autowired
    private DealerRepository dealerRepository;

    public void addDealer(DealerRequestDTO dto){
        Dealer dealer=convertToEntity(dto);
        dealerRepository.save(dealer);
    }
    public Page<DealerResponseDTO> getAllDealers(int page,int size,String sortBy,String direction){

        Sort sort;
        if(direction.equalsIgnoreCase("desc")){
            sort=Sort.by(sortBy).descending();
        }else {
            sort=Sort.by(sortBy).ascending();
        }
        Pageable pageable= PageRequest.of(page, size, sort);
        Page<Dealer> dealerPage=dealerRepository.findAll(pageable);
        return dealerPage.map(this::convertToDTO);
    }
    public List<DealerResponseDTO> getDealerByName(String keyword){
        List<Dealer> dealers=dealerRepository.findByDealerNameContainingIgnoreCase(keyword);
        List<DealerResponseDTO> responseList=new ArrayList<>();
        for(Dealer dealer:dealers){
            responseList.add(convertToDTO(dealer));
        }
        return responseList;
    }
    public DealerResponseDTO getDealerById(Long id){
        Optional<Dealer> optionalDealer=dealerRepository.findById(id);
        if (optionalDealer.isPresent()){
            Dealer dealer=optionalDealer.get();
            return convertToDTO(dealer);
        }
        else{
            throw new RuntimeException("Dealer not Found");
        }
    }
    public void updateDealer(Long id,DealerRequestDTO dto){
        Optional<Dealer> optionalDealer=dealerRepository.findById(id);
        if(optionalDealer.isPresent()){
            Dealer dealer=optionalDealer.get();
            dealer.setDealerName(dto.getDealerName());
            dealer.setShopName(dto.getShopName());
            dealer.setMobile(dto.getMobile());
            dealer.setEmail(dto.getEmail());
            dealer.setGstNumber(dto.getGstNumber());
            dealer.setAddress(dto.getAddress());
            dealerRepository.save(dealer);
        }else{
            throw new ResourceNotFoundException("Product Not Found");
        }
    }
    public void deleteDealer(Long id){
        Optional<Dealer> dealer=dealerRepository.findById(id);
        if(dealer.isPresent()){
            dealerRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Dealer Not Found");
        }
    }

    private Dealer convertToEntity(DealerRequestDTO dto){
        Dealer dealer=new Dealer();
        dealer.setDealerName(dto.getDealerName());
        dealer.setShopName(dto.getShopName());
        dealer.setMobile(dto.getMobile());
        dealer.setEmail(dto.getEmail());
        dealer.setGstNumber(dto.getGstNumber());
        dealer.setAddress(dto.getAddress());
        return dealer;
    }
    private DealerResponseDTO convertToDTO(Dealer dealer){
        DealerResponseDTO dto=new DealerResponseDTO();
        dto.setId(dealer.getId());
        dto.setDealerName(dealer.getDealerName());
        dto.setShopName(dealer.getShopName());
        dto.setMobile(dealer.getMobile());
        dto.setEmail(dealer.getEmail());
        dto.setGstNumber(dealer.getGstNumber());
        dto.setAddress(dealer.getAddress());
        return dto;
    }
}
