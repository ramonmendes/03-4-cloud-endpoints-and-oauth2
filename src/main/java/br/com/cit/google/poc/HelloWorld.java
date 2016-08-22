package br.com.cit.google.poc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletRequest;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.appengine.api.users.User;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(
    name = "helloworld",
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE, CalendarScopes.CALENDAR, CalendarScopes.CALENDAR_READONLY},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.CALENDAR_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
    audiences = {Constants.ANDROID_AUDIENCE}
)
public class HelloWorld {

  @ApiMethod(name = "helloworld.calendars", path = "helloworld/calendars", httpMethod = HttpMethod.POST)
  public java.util.List<String> getCalendars(final User user,final HttpServletRequest request) throws GeneralSecurityException, IOException, URISyntaxException {
	
	
	//http and json factory
	NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
	
	//Token authorizator
	String authorizationHeader = request.getHeader("Authorization");
	String token = authorizationHeader.substring(7);
	GoogleCredential credential = new GoogleCredential().setAccessToken(token);

	//Build calendar Api
	Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credential).setApplicationName("my-cit-lab").build();
	
	//Get calendar list
	CalendarList execute = service.calendarList().list().execute();
	
	//Return 
	return Lists.transform(execute.getItems(), new Function<CalendarListEntry, String>() {
		@Override
		public String apply(CalendarListEntry arg0) {
			return arg0.getSummary();
		}
	});
  }
}
