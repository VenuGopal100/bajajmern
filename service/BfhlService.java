// service/BfhlService.java
package com.example.bfhl.service;

import com.example.bfhl.model.BfhlResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlService {
    
    // Replace these with your actual details
    private static final String USER_ID = "venu_2829";
    private static final String EMAIL = "venu.gopal2022@vitstudent.ac.in";
    private static final String ROLL_NUMBER = "22BCE2829";
    
    public BfhlResponse processData(List<String> data) {
        try {
            List<String> oddNumbers = new ArrayList<>();
            List<String> evenNumbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> specialCharacters = new ArrayList<>();
            StringBuilder allAlphabets = new StringBuilder();
            long sum = 0;
            
            for (String item : data) {
                if (isNumeric(item)) {
                    long num = Long.parseLong(item);
                    if (num % 2 == 0) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                    sum += num;
                } else if (isAlphabetic(item)) {
                    alphabets.add(item.toUpperCase());
                    allAlphabets.append(item);
                } else {
                    specialCharacters.add(item);
                }
            }
            
            String concatString = generateConcatString(allAlphabets.toString());
            
            return new BfhlResponse(
                true, 
                USER_ID, 
                EMAIL, 
                ROLL_NUMBER,
                oddNumbers, 
                evenNumbers, 
                alphabets,
                specialCharacters, 
                String.valueOf(sum), 
                concatString
            );
            
        } catch (Exception e) {
            // Return error response
            return new BfhlResponse(
                false, 
                USER_ID, 
                EMAIL, 
                ROLL_NUMBER,
                new ArrayList<>(), 
                new ArrayList<>(), 
                new ArrayList<>(),
                new ArrayList<>(), 
                "0", 
                ""
            );
        }
    }
    
    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]+");
    }
    
    private String generateConcatString(String allAlphabets) {
        // Reverse the string
        String reversed = new StringBuilder(allAlphabets).reverse().toString();
        
        // Apply alternating case
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char ch = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(ch));
            } else {
                result.append(Character.toLowerCase(ch));
            }
        }
        
        return result.toString();
    }
}