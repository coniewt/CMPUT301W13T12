package ca.ualberta.c301w13t12recipes.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import java.util.ArrayList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import ca.ualberta.c301w13t12recipes.model.LocalDB;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.model.WebService;

/**
 * Class for handling web access (place holder)
 * 
 */

public class WebController {

	// HTTP Connector
	protected HttpClient httpclient;
	private static WebService webservice;
	private static LocalDB localdb;

	// JSON Utilities

	protected Gson gson;
	
	
	public WebController(){
		
		httpclient = new DefaultHttpClient();
		
		gson = new Gson();

		webservice = new WebService();
		localdb = new LocalDB(null);
	}

	/**
	 * @param re
	 */
	public static void publishToWeb(Recipe re) {
		webservice.insertRecipe(re);

	}
	/**
	 * @return
	 */
	public ArrayList<Recipe> getAllRecipeFromWeb(){
		return new ArrayList<Recipe>();
	}

	/**
	 * To check the the connection of Network
	 * @param co
	 * @return boolean if the network works, it will return true, otherwise it
	 * will return false
	 */
	public boolean isNetworkAvailable(Context co) {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) co.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
