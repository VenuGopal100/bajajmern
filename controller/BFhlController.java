// controller/BfhlController.java
package com.example.bfhl.controller;

import com.example.bfhl.model.BfhlRequest;
import com.example.bfhl.model.BfhlResponse;
import com.example.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins = "*")
public class BfhlController {
    
    @Autowired
    private BfhlService bfhlService;
    
    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processBfhl(@Valid @RequestBody BfhlRequest request) {
        try {
            BfhlResponse response = bfhlService.processData(request.getData());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // Return error response with 500 status
            BfhlResponse errorResponse = new BfhlResponse();
            errorResponse.setSuccess(false);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Optional: GET method for health check
    @GetMapping("/bfhl")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("BFHL API is running", HttpStatus.OK);
    }
}