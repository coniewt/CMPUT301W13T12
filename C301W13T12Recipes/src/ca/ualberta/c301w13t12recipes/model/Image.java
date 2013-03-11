package ca.ualberta.c301w13t12recipes.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

/**
 * Converting images to json object in order to publish it online
 * @author YUWEI DUAN
 *
 */
public class Image implements Serializable{

	private static final long serialVersionUID = 1L;
	String path;
	String name;
	
	/**
	 * @param path
	 */
	public Image(String path){
		this.path=path;
		this.name=getTime();
	}
	
	/**
	 * Create json object from designated image file
	 * @return json object a
	 */
	public JSONObject toJson(){
		JSONObject js = new JSONObject();
		try{
			js.put("path", getPath());
			js.put("name", getName());
		}
		catch(JSONException jse){
			jse.printStackTrace();
		}
		return js;
	}
	
	/**
	 * Get current date from system
	 * @return date
	 */
	public String getTime(){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
    	return sdf.format(cal.getTime());
	}
	
	/**
	 * Get the path of the image
	 * @return path
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Get the name of the image
	 * @return	name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @param name
	 */
/*	public void setName(String name){
		this.name=name;
	}*/
	
	/**
	 * @param path
	 */
	public void setPath(String path){
		this.path=path;
	}

	public String toString(){
		return name+" "+path;
		
	}
}
