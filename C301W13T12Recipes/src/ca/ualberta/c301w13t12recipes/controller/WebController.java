package ca.ualberta.c301w13t12recipes.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;

/**
 * Class for handling web access (place holder)
 *
 */

public class WebController {
	
	// HTTP Connector
	protected HttpClient httpclient;

	// JSON Utilities
	protected Gson gson;
	
	
	public WebController(){
		
		httpclient = new DefaultHttpClient();
		
		gson = new Gson();

	}
	
	String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:"+json);
		return json;
	}
	
}
