package com.skillstorm.taxservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxservice.models.StateTax;
import com.skillstorm.taxservice.services.StateTaxService;

@RestController
@RequestMapping("/state-tax")
public class StateTaxController {
  
  @Autowired
  private StateTaxService stateTaxService;

  @GetMapping("/state-name/{stateName}")
  public ResponseEntity<List<StateTax>> getTaxBracketsByStateName(@PathVariable String stateName) {
    return ResponseEntity.ok(stateTaxService.getTaxBracketsByStateName(stateName));
  }

  @GetMapping("/state-code/{stateCode}")
  public ResponseEntity<List<StateTax>> getTaxBracketsByStateCode(@PathVariable String stateCode) {
    return ResponseEntity.ok(stateTaxService.getTaxBracketsByStateCode(stateCode));
  }

}
