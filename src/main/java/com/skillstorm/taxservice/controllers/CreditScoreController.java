package com.skillstorm.taxservice.controllers;

import com.skillstorm.taxservice.models.UserCreditData;
import com.skillstorm.taxservice.repositories.UserCreditDataRepository;
import com.skillstorm.taxservice.services.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit")
public class CreditScoreController {
    
    @Autowired
    private CreditScoreService creditScoreService;

    @Autowired
    private UserCreditDataRepository userCreditDataRepository;

    @GetMapping("/score/{userId}")
    public ResponseEntity<Integer> getCreditScore(@PathVariable Long userId) {
        int score = creditScoreService.calculateFICOScore(userId); 
        return ResponseEntity.ok(score);
    }

    @PostMapping("/data")
    public ResponseEntity<UserCreditData> saveCreditData(@RequestBody UserCreditData creditData) {
        UserCreditData savedData = userCreditDataRepository.save(creditData);
        return ResponseEntity.ok(savedData);
    }
}
