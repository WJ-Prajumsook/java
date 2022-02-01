package org.wj.prajumsook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wj.prajumsook.model.Currency;

/**
 * CurrencyRepository
 */

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

  Optional<Currency> findByName(String name);
  Optional<Currency> findByCode(String code);

}
