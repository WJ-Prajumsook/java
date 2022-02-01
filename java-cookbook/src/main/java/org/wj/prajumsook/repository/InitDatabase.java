package org.wj.prajumsook.repository;

import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wj.prajumsook.model.Currency;

/**
 * InitDatabase
 */
@Component
public class InitDatabase {

  @Autowired
  private CurrencyRepository repository;

  @PostConstruct
  private void initDatabase() {
    try {
      // Read JSON from file.
      InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("Currency.json");
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      List<Currency> currencies = mapper.readValue(jsonNode.toString(), new TypeReference<List<Currency>>(){});

      // Put it into database.
      currencies.forEach(c -> repository.save(c));
    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }

}
