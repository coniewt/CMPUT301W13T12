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
 * Class for handling web access (place holder)
 * 
 * 
 */

public class WebController extends AsyncTask<String, Void, Recipe> {

	// HTTP Connector
	protected HttpClient httpclient;
	//private static WebService webservice;
	private static LocalDB localdb;
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
		localdb = new LocalDB(null);
	}

	/**
	 * @param re
	 */
	public void publishToWeb(Recipe re) {
		webservice.insertRecipe(re);

	}

	/**
	 * @return
	 */
	public ArrayList<Recipe> getAllRecipeFromWeb() {
		return new ArrayList<Recipe>();
	}

	/**
	 * To check the the connection of Network
	 * 
	 * @param co
	 * @return boolean if the network works, it will return true, otherwise it
	 *         will return false
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
