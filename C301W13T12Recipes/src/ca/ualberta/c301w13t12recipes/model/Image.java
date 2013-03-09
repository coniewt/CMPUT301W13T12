package ca.ualberta.c301w13t12recipes.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

/**
 * @author YUWEI DUAN
 *
 */
public class Image {
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
	 * @return jsonobject a
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
	 *@return the String containing the year hour  
	 */
	
	
	public String getTime(){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
    	return sdf.format(cal.getTime());
	}
	/**
	 * @return the path of image
	 */
	
	public String getPath(){
		return path;
	}/**
	 * @return	the name of image
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
