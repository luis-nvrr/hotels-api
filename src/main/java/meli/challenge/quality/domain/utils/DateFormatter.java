package meli.challenge.quality.domain.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import meli.challenge.quality.domain.exceptions.InvalidDateException;

public class DateFormatter {
  private static final String DATE_PATTERN = "dd/MM/yyyy";
  private static final DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

  public static Date formatStringToDate(String stringDate) throws InvalidDateException {
    try {
      return formatter.parse(stringDate);
    } catch (ParseException e) {
      throw new InvalidDateException("invalid date format");
    }
  }
}
