package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import ca.ualberta.c301w13t12recipes.model.LocalDB;
import ca.ualberta.c301w13t12recipes.model.Recipe;

import com.google.gson.Gson;

/**
 * Class for handling web services
 * @author shihao1
 * 
 */

public class WebController extends AsyncTask<String, Void, Recipe> {

	// HTTP Connector
	protected HttpClient httpclient;
	private WebStream webservice ;

	// JSON Utilities

	protected Gson gson;

	/**
	 * The constructor of web controller
	 */
	public WebController() {

		httpclient = new DefaultHttpClient();

		gson = new Gson();

		webservice = new WebStream();
		new LocalDB(null);
	}

	/**
	 * Publish recipe to web server
	 * @param Recipe Recipe user wishes to publish
	 */
	public void publishToWeb(Recipe re) {
		webservice.insertRecipe(re);

	}

	/**
	 * Grab all recipes from web server
	 * @return ArrayList<Recipe> An arraylist of all recipes from web server
	 */
	public ArrayList<Recipe> getAllRecipeFromWeb() {
		return new ArrayList<Recipe>();
	}
	
	
	/**
	 * Check if there is an active Internet connection
	 * 
	 * @param Context
	 * @return boolean - Return true if there is an active Internet connection, false otherwise
	 */
	public boolean isNetworkAvailable(Context co) {
		ConnectivityManager connectivityManager = (ConnectivityManager) co
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	@Override
	protected Recipe doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}
}
