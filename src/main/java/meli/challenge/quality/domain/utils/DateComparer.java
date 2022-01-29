package meli.challenge.quality.domain.utils;

import java.util.Date;

public class DateComparer {
  public static final boolean compareGreatThan(Date toEvaluate, Date comparedTo) {
    return toEvaluate.after(comparedTo);
  }

  public static final boolean compareGreatThanOrEqual(Date toEvaluate, Date comparedTo) {
    return compareGreatThan(toEvaluate, comparedTo) || compareEquals(toEvaluate, comparedTo);
  }

  public static final boolean compareLessThan(Date toEvaluate, Date comparedTo) {
    return toEvaluate.before(comparedTo);
  }

  public static final boolean compareLessThanOrEqual(Date toEvaluate, Date comparedTo) {
    return compareLessThan(toEvaluate, comparedTo) || compareEquals(toEvaluate, comparedTo);
  }

  public static final boolean compareEquals(Date toEvaluate, Date comparedTo) {
    return toEvaluate.equals(comparedTo);
  }
}
