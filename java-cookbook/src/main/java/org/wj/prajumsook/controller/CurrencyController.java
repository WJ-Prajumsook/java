package org.wj.prajumsook.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wj.prajumsook.model.Currency;
import org.wj.prajumsook.model.CurrencyDTO;
import org.wj.prajumsook.service.CurrencyService;


@RestController
@RequestMapping("/currency")
public class CurrencyController {

  @Autowired
  private CurrencyService service;

  @GetMapping("/all")
  public List<CurrencyDTO> findAll() {
    Optional<List<CurrencyDTO>> currencies = service.convertToDTO(); //service.findAll();
    if(currencies.isPresent()) {
      return currencies.get();
    } else {
      return Collections.emptyList();
    }
  }

  @GetMapping("/id/{id}")
  public Currency findById(@PathVariable(name = "id")long id) {
    Optional<Currency> opt = service.finById(id);
    if(opt.isPresent()) {
      return opt.get();
    } else {
      return new Currency();
    }
  }
  @GetMapping("/code/{code}")
  public Currency findByCode(@PathVariable(name = "code")String code) {
    Optional<Currency> opt = service.finByCode(code);
    if(opt.isPresent()) {
      return opt.get();
    } else {
      return new Currency();
    }
  }
  @GetMapping("/name/{name}")
  public Currency findByName(@PathVariable(name = "name")String name) {
    Optional<Currency> opt = service.finByName(name);
    if(opt.isPresent()) {
      return opt.get();
    } else {
      return new Currency();
    }
  }

  @GetMapping("/code")
  public List<String> convertToCode() {
    Optional<List<String>> opt = service.convertToCode();
    if(opt.isPresent()) {
      return opt.get();
    } else {
      return Collections.emptyList();
    }
  }
}
