package ca.ualberta.c301w13t12recipes.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.HttpURLConnection;
import java.net.URL;

import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.model.ElasticSearchResponse;
import ca.ualberta.c301w13t12recipes.model.ElasticSearchSearchResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Class for handling web access (place holder)
 *
 */

public class WebController {
	

	
	// HTTP Connector
	private HttpClient httpclient = new DefaultHttpClient();

	// JSON Utilities
	private Gson gson = new Gson();
	
	
	public void insertRecipe(Recipe recipe) throws IllegalStateException, IOException{
		
		/*URL url = new URL("http://cmput301.softwareprocess.es:8080/cmput301w13t12/"+recipe.getId());
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();*/
		
		HttpPost httpPost = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w13t12/"+recipe.getId());
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(recipe));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setHeader("Accept","application/json");
		httpPost.setEntity(stringentity);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String status = response.getStatusLine().toString();
		System.out.println(status);
		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}
		try {
			//EntityUtils.consume(entity);
			entity.consumeContent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//httpPost.releaseConnection();
		}
		
		/*try {
			urlConnection.setDoOutput(true);
			urlConnection.setChunkedStreamingMode(0);

			OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
			writeStream(out);

			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			readStream(in);
		finally {
			urlConnection.disconnect();}
		}*/
	
	public void getRecipe(String string){
		try{
			HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301w13t12/"+string+"?pretty=1");//S4bRPFsuSwKUDSJImbCE2g?pretty=1

			getRequest.addHeader("Accept","application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Recipe>>(){}.getType();
			// Now we expect to get a Recipe response
			ElasticSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchResponseType);
			// We get the recipe from it!
			Recipe recipe = esResponse.getSource();
			System.out.println(recipe.toString());
			//getRequest.releaseConnection();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void searchRecipes(String str) throws ClientProtocolException, IOException {
		HttpGet searchRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301w13t12/_search?pretty=1&q=" +
				java.net.URLEncoder.encode(str,"UTF-8"));
		searchRequest.setHeader("Accept","application/json");
		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>(){}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();
			System.err.println(recipe);
		}
		//searchRequest.releaseConnection();
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
	
	public void searchsearchRecipes(String str) throws ClientProtocolException, IOException {
		HttpPost searchRequest = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w13t12/_search?pretty=1");
		String query = 	"{\"query\" : {\"query_string\" : {\"default_field\" : \"ingredients\",\"query\" : \"" + str + "\"}}}";
		StringEntity stringentity = new StringEntity(query);

		searchRequest.setHeader("Accept","application/json");
		searchRequest.setEntity(stringentity);

		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Recipe>>(){}.getType();
		ElasticSearchSearchResponse<Recipe> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Recipe> r : esResponse.getHits()) {
			Recipe recipe = r.getSource();
			System.err.println(recipe);
		}
		//searchRequest.releaseConnection();
	}	
	
	public void updateRecipes(Recipe recipe, String str) throws ClientProtocolException, IOException {
		HttpPost updateRequest = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w13t12/"+recipe.getId()+"/_update");
		String query = 	"{\"script\" : \"ctx._source." + str + "}";
		StringEntity stringentity = new StringEntity(query);

		updateRequest.setHeader("Accept","application/json");
		updateRequest.setEntity(stringentity);

		HttpResponse response = httpclient.execute(updateRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);
		//updateRequest.releaseConnection();
	}	


	public void deleteRecipe(Recipe recipe) throws IOException {
		HttpDelete httpDelete = new HttpDelete("http://cmput301.softwareprocess.es:8080/cmput301w13t12/"+recipe.getId());
		httpDelete.addHeader("Accept","application/json");

		HttpResponse response = httpclient.execute(httpDelete);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}
		//EntityUtils.consume(entity);
		entity.consumeContent();
		//httpDelete.releaseConnection();
	}

}
