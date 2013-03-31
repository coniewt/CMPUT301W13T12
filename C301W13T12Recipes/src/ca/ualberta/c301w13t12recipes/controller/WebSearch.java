package ca.ualberta.c301w13t12recipes.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;
import android.util.LruCache;
import ca.ualberta.c301w13t12recipes.model.ElasticSearchResponse;
import ca.ualberta.c301w13t12recipes.model.ElasticSearchSearchResponse;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.model.StrResource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author
 * 
 */
public class WebSearch extends WebController {
	/**
	 * 
	 */
	public WebSearch() {

	}

	/**
	 * @param str
	 * @return an array list of recipe
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<Recipe> searchRecipes(String str, Context co)
			throws ClientProtocolException, IOException {
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		HttpGet searchRequest = new HttpGet(
				"http://cmput301.softwareprocess.es:8080/cmput301w13t12/_search?pretty=1&q="
						+ java.net.URLEncoder.encode(str, "UTF-8"));
		Log.v("Name::" + str, java.net.URLEncoder.encode(str, "UTF-8"));
		searchRequest.setHeader("Accept", "application/json");
		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = (new WebStream()).getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>() {
		}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json,
				elasticSearchSearchResponseType);
		System.err.println(esResponse);
		//initial a json array;
		JSONArray jsonarray = new JSONArray();
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();
			jsonarray.put(recipe.toJson());
			recipes.add(recipe.convertToLocalRecipe());
			System.out.println("hello" + recipe.toString());
		}
		// use sharedpreference to store the list into file
		SharedPreferences tempShare = co.getSharedPreferences(
				"Temp_recipe_list", co.MODE_PRIVATE);
		SharedPreferences.Editor tempshare_edit = tempShare.edit();
		tempshare_edit.clear();	
		tempshare_edit.putString(StrResource.SHARE_PREFERENCE_KEY, jsonarray.toString());
		tempshare_edit.commit();
		//end
		return recipes;
	}
	/**
	 * @param str
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<Recipe> searchsearchRecipes(String str)
			throws ClientProtocolException, IOException {
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		HttpPost searchRequest = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301w13t12/_search?pretty=1");
		Log.v("PATH::::", searchRequest.getURI().getPath());
		String query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"ingredients\",\"query\" : \""
				+ str + "\"}}}";
		StringEntity stringentity = new StringEntity(query);
		searchRequest.setHeader("Accept", "application/json");
		searchRequest.setEntity(stringentity);
		HttpResponse response = httpclient.execute(searchRequest);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = (new WebStream()).getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>() {
		}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json,
				elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();

			recipes.add(recipe.convertToLocalRecipe());
			// System.err.println(recipe);
		}
		// searchRequest.releaseConnection();

		return recipes;
	}

	/**
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<Recipe> grabAllRecipe() throws ClientProtocolException,
			IOException {
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		HttpGet searchRequest = new HttpGet(
				"http://cmput301.softwareprocess.es:8080/cmput301w13t12/_search?pretty=1");
		searchRequest.setHeader("Accept", "application/json");
		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = (new WebStream()).getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>() {
		}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json,
				elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();
			recipes.add(recipe);
			// System.err.println(recipe);
		}
		return recipes;
	}

}
