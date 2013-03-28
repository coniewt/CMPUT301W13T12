package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;

import org.apache.http.client.HttpClient;

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
	/**
	 * 
	 */
	public WebController() {
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

	private boolean isNetworkAvailable(Context co) {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) co.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
