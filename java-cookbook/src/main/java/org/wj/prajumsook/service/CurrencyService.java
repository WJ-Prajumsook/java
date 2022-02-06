package org.wj.prajumsook.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.wj.prajumsook.model.Currency;
import org.wj.prajumsook.model.CurrencyDTO;
import org.wj.prajumsook.repository.CurrencyRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * CurrencyService
 */
@Service
@Slf4j
public class CurrencyService {

  @Autowired
  private CurrencyRepository repository;

  public Optional<List<Currency>> findAll() {
    try {
      //return Optional.of(repository.findAll());
      // Use Supplier and method reference
      Supplier<List<Currency>> currencies = repository::findAll;
      return Optional.of(currencies.get());
    } catch(DataAccessException de) {
      Supplier<String> message = de::getMessage;
      log.error("Error finding currencies ", message);
      return Optional.empty();
    }
  }

  public Optional<Currency> finByName(String name) {
    try {
      return repository.findByName(name);
    } catch(DataAccessException de) {
      log.error("Error finding currency by name " + name, de);
      return Optional.empty();
    }
  }

  public Optional<Currency> finByCode(String code) {
    try {
      return repository.findByCode(code);
    } catch(DataAccessException de) {
      log.error("Error finding currency by code " + code, de);
      return Optional.empty();
    }
  }

  public Optional<Currency> finById(Long id) {
    try {
      return repository.findById(id);
    } catch(DataAccessException de) {
      log.error("Error finding currency by id " + id, de);
      return Optional.empty();
    }
  }

  public Optional<List<String>> convertToCode() {
    try {
      List<Currency> currencies = repository.findAll();
      List<String> codes = currencies.stream().map(Currency::getCode)
        .collect(Collectors.toList());
      return Optional.of(codes);
    } catch(DataAccessException de) {
      return Optional.empty();
    }
  }

  public Optional<List<CurrencyDTO>> convertToDTO() {
    try {
      List<Currency> currencies = repository.findAll();

      List<String> selectedCode = Arrays.asList("EUR", "USD", "GBP");
      Predicate<Currency> isInList = c -> selectedCode.contains(c.getCode());

      List<CurrencyDTO> dtos = currencies.stream()
        .filter(isInList)
        .map(CurrencyDTO::new)
        .collect(Collectors.toList());

      return Optional.of(dtos);
    } catch(DataAccessException de) {
      return Optional.empty();
    }
  }
}
