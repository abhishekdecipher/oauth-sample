package com.decipherzone.oauthsample.core.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public final class DateUtil {

  private static final DateTimeZone DATE_TIME_ZONE = DateTimeZone.UTC;

  private DateUtil() {
  }

  public static Long nowInMillis() {
    return new DateTime(DATE_TIME_ZONE).getMillis();
  }

}
