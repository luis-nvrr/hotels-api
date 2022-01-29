package meli.challenge.quality.domain.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;

public class StringNormalizer {
  private static final String REPLACE_ALL_PATTERN = "\\p{M}";
  private static final String REPLACE_TO = "";
  private static final Form NORMALIZATION_FORM = Form.NFKD;

  public static final String normalizeStringToKey(String stringToNormalize) {
    String normalizedString = Normalizer.normalize(stringToNormalize, NORMALIZATION_FORM);
    normalizedString = normalizedString.replaceAll(REPLACE_ALL_PATTERN, REPLACE_TO);
    return normalizedString;
  }

  public static final boolean compareNormalizedStrings(String stringA, String stringB) {
    return StringNormalizer.normalizeStringToKey(stringA).equals(StringNormalizer.normalizeStringToKey(stringB));
  }
}
