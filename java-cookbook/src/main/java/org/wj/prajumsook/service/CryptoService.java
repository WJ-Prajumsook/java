package org.wj.prajumsook.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.wj.prajumsook.model.Crypto;


/**
 * CryptoService
 */
@Service
public class CryptoService {

  public Optional<List<Crypto>> readCSVFile() throws IOException {
    Pattern pattern = Pattern.compile(",");
    try (
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("BTC-USD.csv");
        Stream<String> lines = new BufferedReader(new InputStreamReader(in)).lines();
        ) {
      List<Crypto> cryptos = lines.skip(1).map(line -> {
        String[] strArr = pattern.split(line);
        return new Crypto()
          .setDate(strArr[0])
          .setOpen(strArr[1])
          .setHigh(strArr[2])
          .setLow(strArr[3])
          .setClose(strArr[4])
          .setAdjClose(strArr[5])
          .setVolume(strArr[6]);
      }).collect(Collectors.toList());

      return Optional.of(cryptos);
        }
  }

}
