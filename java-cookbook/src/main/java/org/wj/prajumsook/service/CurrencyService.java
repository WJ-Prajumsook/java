package org.wj.prajumsook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.wj.prajumsook.model.Currency;
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
      return Optional.of(repository.findAll());
    } catch(DataAccessException de) {
      log.error("Error finding currencies ", de);
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
}
