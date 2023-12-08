
package com.oauth.servlet;


import java.io.IOException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.oauth.model.User;
import com.google.api.client.json.gson.GsonFactory;

public class GoogleApiServlet {
	
	
	private static final String APPLICATION_NAME = "Federation SignUp";
	
	public static void PeopleApi(String accessToken) throws Exception {

        HttpRequestInitializer requestInitializer = request -> request.getHeaders().setAuthorization("Bearer " + accessToken);
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        PeopleService peopleService = new PeopleService.Builder(httpTransport, 
        		GsonFactory.getDefaultInstance(), requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();

        getProfileInformation(peopleService);
    }
	private static void getProfileInformation(PeopleService service) throws IOException {
        
        Person profile = service.people().get("people/me").execute();

        System.out.println("Display Name: " + profile.getNames().get(0).getDisplayName());
        System.out.println("Emails: " + profile.getEmailAddresses());
        System.out.println("Emails: " + profile.getGenders());
       // User user = new User(profile.getEmailAddresses() ,profile.getNames().get(0).getDisplayName(),profile.getGenders());

    }
	
	public static void main(String args[]) throws Exception {
		GoogleApiServlet.PeopleApi("ya29.a0AfB_byCa8Y_bZvRw_P_uQfDL7XkGJHwRB0yMDfs02yqrWmUAwyAZPGJsepgPRNTNmq27tbspA1GZqBNse8liy8uIYa1u1fz0CcnF38-MIFlb-cKgqdJxT-_rM9IGar_eh2G1COck-8pn2wqNk6-ZVCdbLZm5VE58FQaCgYKARoSARISFQHGX2MicoabWgf58qj3Is_gKmLD6g0169");
	}
	
}
