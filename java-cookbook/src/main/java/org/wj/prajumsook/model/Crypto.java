package org.wj.prajumsook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Crypto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Crypto {

  private String date;
  private String open;
  private String high;
  private String low;
  private String close;
  private String adjClose;
  private String volume;

}
