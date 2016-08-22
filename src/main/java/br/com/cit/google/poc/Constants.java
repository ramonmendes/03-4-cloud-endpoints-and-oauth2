package br.com.cit.google.poc;

import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming the helloworld API.
 */
public class Constants {
  public static final String WEB_CLIENT_ID = "340121606461-9ln7rfv9mtvepo6iv9csua80iccki8do.apps.googleusercontent.com";
  public static final String CALENDAR_CLIENT_ID = "340121606461-9ln7rfv9mtvepo6iv9csua80iccki8do.apps.googleusercontent.com";
  public static final String CALENDAR_SECRET = "QZfaICB0WTDxemIXlPQCWu19";
  public static final String ANDROID_CLIENT_ID = "replace this with your Android client ID";
  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
  public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;

  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
}
