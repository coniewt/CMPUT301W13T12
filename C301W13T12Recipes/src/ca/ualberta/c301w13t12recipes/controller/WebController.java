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
import org.json.JSONArray;

import java.net.HttpURLConnection;
import java.net.URL;

import ca.ualberta.c301w13t12recipes.model.LocalDB;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.model.ElasticSearchResponse;
import ca.ualberta.c301w13t12recipes.model.ElasticSearchSearchResponse;
import ca.ualberta.c301w13t12recipes.model.WebService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	public WebController(){
		webservice = new WebService();
		localdb= new LocalDB(null);
	}
	public static void publishToWeb(Recipe re){
		webservice.insertRecipe(re);
		
	}
}
