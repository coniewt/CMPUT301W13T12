package ca.ualberta.c301w13t12recipes.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Converting images to json object in order to publish it online
 * 
 * @author YUWEI DUAN
 * 
 */
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	String tn_path;
	String hd_path;
	String name;

	/**
	 * @param path
	 */
	public Image(String hd_path,String tnpath) {
		this.tn_path = tnpath;
		this.hd_path = hd_path;
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
			js.put("HD_path", getHD_Path());
			js.put("TN_path", getTN_Path());
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
	 * Get the path of the hd image
	 * 
	 * @return Path of image
	 */
	public String getHD_Path() {
		return hd_path;
	}
	/**
	 * Get the path of the TN image
	 * @return
	 */
	public String getTN_Path() {
		return tn_path;
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
	 * @param path
	 *            of image
	 */
	public void setHDPath(String path) {
		this.hd_path = path;
	}
	/**
	 * @param path
	 */
	public void setTNPath(String path) {
		this.tn_path = path;
	}

	/**
	 * Converting name and path into single string
	 * 
	 * @return Single string containing image name and path
	 */
}
