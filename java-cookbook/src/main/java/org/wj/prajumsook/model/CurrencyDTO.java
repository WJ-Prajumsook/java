package org.wj.prajumsook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CurrencyDTO
 */
@Data
@NoArgsConstructor
public class CurrencyDTO {

  private String name;
  private String code;
  private String symbol;

  public CurrencyDTO(Currency currency) {
    this.name = currency.getName();
    this.code = currency.getCode();
    this.symbol = currency.getSymbol();
  }

}
