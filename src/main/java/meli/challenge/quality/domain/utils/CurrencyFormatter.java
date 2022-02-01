package meli.challenge.quality.domain.utils;

public class CurrencyFormatter {
  private static final String CURRENCY_STRING_PATTERN = "$%d";
  private static final String CURRENCY_SYMBOL = "$";

  public static final int getIntValue(String currencyString) {
    int value = Integer.parseInt(currencyString.replace(CURRENCY_SYMBOL, "").replace(".", ""));
    return value;
  }

  public static String formatToString(int value) {
    return String.format(CURRENCY_STRING_PATTERN, value);
  }
}
