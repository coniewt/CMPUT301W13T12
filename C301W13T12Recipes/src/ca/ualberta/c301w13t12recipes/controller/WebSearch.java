package ca.ualberta.c301w13t12recipes.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
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
		//ArrayList<String> list = new ArrayList<String>();
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource().convertToLocalRecipe();
			//list.add(recipe.toJson().toString());
			recipes.add(recipe);
			System.out.println("hello" + recipe.toString());
		}
		/*// use sharedpreference to store the list into file
		SharedPreferences tempShare = co.getSharedPreferences(
				"Temp_recipe_list", co.MODE_WORLD_READABLE);
		SharedPreferences.Editor tempshare_edit = tempShare.edit();
		tempshare_edit.clear();	
		tempshare_edit.putStringSet(StrResource.SHARE_PREFERENCE_KEY, new HashSet<String> (list));
		tempshare_edit.commit();
		//end
*/		return recipes;
	}
	/**
	 * Search recipes,which includes the ingredient
	 * @param str
	 * @return the list of searched recipe
	 */
	public ArrayList<Recipe> searchRecipesByIngredient(
			ArrayList<String> ingredients,Context co) 
			{
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		try{
		for(String st:ingredients){
			ArrayList<Recipe> tep= searchRecipes(st,co);
			for(Recipe re:tep){
				if(re.isIncluded(st))
					recipes.add(re);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
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
