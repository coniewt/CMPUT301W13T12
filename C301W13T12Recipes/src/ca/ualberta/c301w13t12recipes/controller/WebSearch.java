package ca.ualberta.c301w13t12recipes.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import ca.ualberta.c301w13t12recipes.model.ElasticSearchResponse;
import ca.ualberta.c301w13t12recipes.model.ElasticSearchSearchResponse;
import ca.ualberta.c301w13t12recipes.model.Recipe;

import com.google.gson.reflect.TypeToken;

/**
 * @author 
 *
 */
public class WebSearch extends WebController{
	
	protected ArrayList<Recipe> recipes;
	
	/**
	 * 
	 */
	public WebSearch(){
		
		recipes = new ArrayList<Recipe>();
	}
	
	
	
	/**
	 * @param str
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<Recipe> searchRecipes(String str) throws ClientProtocolException, IOException {
		HttpGet searchRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301w13t12/_search?pretty=1&q=" +
				java.net.URLEncoder.encode(str,"UTF-8"));
		searchRequest.setHeader("Accept","application/json");
		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = (new WebStream()).getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>(){}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();
			
			recipes.add(recipe);
			//System.err.println(recipe);
		}
		
		return recipes;
		//searchRequest.releaseConnection();
	}
	/**
	 * @param str
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<Recipe> searchsearchRecipes(String str) throws ClientProtocolException, IOException {
		HttpPost searchRequest = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w13t12/_search?pretty=1");
		String query = 	"{\"query\" : {\"query_string\" : {\"default_field\" : \"ingredients\",\"query\" : \"" + str + "\"}}}";
		StringEntity stringentity = new StringEntity(query);

		searchRequest.setHeader("Accept","application/json");
		searchRequest.setEntity(stringentity);

		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = (new WebStream()).getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>(){}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();
			
			recipes.add(recipe);
			//System.err.println(recipe);
		}
		//searchRequest.releaseConnection();
		
		return recipes;
	}	
	
	/**
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ArrayList<Recipe> grabAllRecipe() throws ClientProtocolException, IOException{
		
		HttpGet searchRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301w13t12/_search?pretty=1");
		searchRequest.setHeader("Accept","application/json");
		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = (new WebStream()).getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>(){}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();
			recipes.add(recipe);
			//System.err.println(recipe);
		}
		return recipes;
	}
	
}
