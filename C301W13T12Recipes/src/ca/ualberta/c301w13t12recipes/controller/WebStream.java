package ca.ualberta.c301w13t12recipes.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import ca.ualberta.c301w13t12recipes.model.Recipe;

import com.google.gson.Gson;

/**
 * Fulfill the basic web service functionality including connections and up/download recipes
 * @author shihao1
 *
 */
public class WebStream{
	
	protected HttpClient httpclient = new DefaultHttpClient() ;
	protected Gson gson=new Gson();
	
	/**
	 * Constructor
	 * 
	 */
	public WebStream(){	
	}
	
	/**
	 * Publish an recipe to web service
	 * @param Recipe The recipe user wish to publish to web server
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void insertRecipe(Recipe recipe) {
		
		/*URL url = new URL("http://cmput301.softwareprocess.es:8080/cmput301w13t12/"+recipe.getId());
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();*/
		recipe = recipe.convertTobitmapRecipe();
		HttpPost httpPost = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w13t12/recipe/"+recipe.getId().substring(6, recipe.getId().length()-1)+ "?op_type=create");
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(recipe));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			httpPost.setHeader("Accept","application/json");
			httpPost.setEntity(stringentity);
			HttpResponse response = null;
			System.out.println(httpPost.getURI().getPath());
			response = httpclient.execute(httpPost);
			String status = response.getStatusLine().toString();
			System.out.println(status);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
			String output;
			//System.err.println("Output from Server -> ");
			while ((output = br.readLine()) != null) {
				System.err.println(output);
			}
			//EntityUtils.consume(entity);
			entity.consumeContent();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		}
	
	
	/**
	 * Ppdate the recipe on web database
	 * @param Recipe The recipe user with to update
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void updateRecipes(Recipe recipe) throws ClientProtocolException, IOException {
		
		//deleteRecipe();
		insertRecipe(recipe);
		
		/*HttpPost updateRequest = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w13t12/"+recipe.getId()+"/_update");
		String query = 	"{\"script\" : \"ctx._source." + str + "}";
		StringEntity stringentity = new StringEntity(query);

		updateRequest.setHeader("Accept","application/json");
		updateRequest.setEntity(stringentity);

		HttpResponse response = httpclient.execute(updateRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);
		//updateRequest.releaseConnection();*/
	}	


	/**
	 * Delete an recipe from web server
	 * @throws IOException
	 */
	public void deleteRecipe() throws IOException {
		HttpDelete httpDelete = new HttpDelete("http://cmput301.softwareprocess.es:8080/cmput301w13t05/recipe");
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
	
	/**
	 * Get entity from current content
	 * @param HttpResponse HttpResponse coming from the connection
	 * @return String Entity stripped from the content
	 * @throws IOException
	 */
	String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		StringBuffer json = new StringBuffer();
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json.append(output) ;
		}
		System.err.println("JSON:"+json.toString());
		return json.toString();
	}
	
	class publishRecipe extends AsyncTask<Integer, Integer, String>{

		@Override
		protected String doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
