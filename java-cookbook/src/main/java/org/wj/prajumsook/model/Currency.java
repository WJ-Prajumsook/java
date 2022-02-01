package org.wj.prajumsook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "symbol")
  private String symbol;

  @Column(name = "symbolNative")
  private String symbolNative;

  @Column(name = "decimalDigits")
  private int decimalDigits;

  @Column(name = "rounding")
  private int rounding;

  @Column(name = "code")
  private String code;

  @Column(name = "namePlural")
  private String namePlural;

}
