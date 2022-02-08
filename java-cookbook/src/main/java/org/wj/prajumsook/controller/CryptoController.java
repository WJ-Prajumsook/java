package org.wj.prajumsook.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wj.prajumsook.model.Crypto;
import org.wj.prajumsook.service.CryptoService;

import lombok.extern.slf4j.Slf4j;

/**
 * CryptoController
 */
@RestController
@RequestMapping("/crypto")
@Slf4j
public class CryptoController {

  @Autowired
  private CryptoService cryptoService;

  @GetMapping
  public List<Crypto> historyBTC() {
    try {
      Optional<List<Crypto>> opt = cryptoService.readCSVFile();
      if(opt.isPresent()) {
        return opt.get();
      } else {
        return Collections.emptyList();
      }

    } catch(Exception ex) {
      log.error("", ex);
      return Collections.emptyList();
    }
  }

}
