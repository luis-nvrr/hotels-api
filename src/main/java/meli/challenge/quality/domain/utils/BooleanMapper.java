package meli.challenge.quality.domain.utils;

import java.util.Hashtable;
import java.util.Map;

public class BooleanMapper {
  private static final Map<String, Boolean> booleanValues;

  static {
    booleanValues = new Hashtable<>();
    booleanValues.put("si", true);
    booleanValues.put("yes", true);
    booleanValues.put("no", false);
  }

  public static final boolean getBooleanFromString(String query) {
    return booleanValues.get(query.toLowerCase().trim());
  }
}
