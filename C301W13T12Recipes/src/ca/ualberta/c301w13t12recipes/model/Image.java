package ca.ualberta.c301w13t12recipes.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.gson.JsonObject;

/**
 * Converting images to json object in order to publish it online
 * 
 * @author YUWEI DUAN
 * 
 */
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	String path;
	String name;

	/**
	 * @param path
	 */
	public Image(String path) {
		this.path = path;
		this.name = getTime();
	}

	/**
	 * Create JSON object from designated image file
	 * 
	 * @return JSON object a
	 */
	public JSONObject toJson() {
		JSONObject js = new JSONObject();
		try {
			js.put("path", getPath());
			js.put("name", getName());
		} catch (JSONException jse) {
			jse.printStackTrace();
		}
		return js;
	}

	/**
	 * Get current date from system
	 * 
	 * @return Current data
	 */
	public String getTime() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		return sdf.format(cal.getTime());
	}

	/**
	 * Get the path of the image
	 * 
	 * @return Path of image
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Get the name of the image
	 * 
	 * @return Name of image
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	/*
	 * public void setName(String name){ this.name=name; }
	 */

	/**
	 * Set image path
	 * 
	 * @param path
	 *            of image
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Converting name and path into single string
	 * 
	 * @return Single string containing image name and path
	 */
	public String toString() {
		return name + " " + path;

	}
}
