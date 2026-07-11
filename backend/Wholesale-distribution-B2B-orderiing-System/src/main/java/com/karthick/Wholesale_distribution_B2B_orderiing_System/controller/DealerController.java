package com.karthick.Wholesale_distribution_B2B_orderiing_System.controller;

import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.DealerRequestDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.dto.DealerResponseDTO;
import com.karthick.Wholesale_distribution_B2B_orderiing_System.service.DealerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealers")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @PostMapping
    public String addDealer(@Valid @RequestBody DealerRequestDTO dto){
        dealerService.addDealer(dto);
        return "Dealer Added Successfully.";
    }
    @GetMapping
    public List<DealerResponseDTO> getAllDealers(){
        return dealerService.getAllDealers();
    }
    @GetMapping("/{id}")
    public DealerResponseDTO getDealerById(@PathVariable Long id){
        return dealerService.getDealerById(id);
    }
    @PutMapping("/{id}")
    public String updateDealer(@PathVariable Long id,@Valid@RequestBody DealerRequestDTO dto){
        dealerService.updateDealer(id,dto);
        return "Dealer Updated Successfully";
    }
    @DeleteMapping("/{id}")
    public String deleteDealer(@PathVariable Long id){
        dealerService.deleteDealer(id);
        return "Dealer Deleted Successfully";
    }
}
