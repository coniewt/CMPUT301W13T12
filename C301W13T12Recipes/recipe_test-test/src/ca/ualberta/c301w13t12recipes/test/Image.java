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
	 * The construct to build a new image by the parameters
	 * @param hd_path Path to store high definition images
	 * @param tn_path Path to store thumb nail images
	 * @param time Acquire system time as file name
	 */
	public Image(String hd_path,String tn_path,String time) {
		this.tn_path = tn_path;
		this.hd_path = hd_path;
		this.name = time;
	}

	/**
	 * Create JSON object from designated image file
	 * 
	 * @return JSONObject - JSON object containing the data of the image
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
	 * Get current date and time from system
	 * 
	 * @return String - Current system date and time
	 */
	public static String getTime() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * Get the path of the high definition image
	 * 
	 * @return String - Path to store the high definition image
	 */
	public String getHD_Path() {
		return hd_path;
	}
	/**
	 * Get the path of the thumb nail image
	 * @return String - Path to store the thumb nail image
	 */
	public String getTN_Path() {
		return tn_path;
	}

	/**
	 * Get the name of the image
	 * 
	 * @return String - Name of image
	 */
	public String getName() {
		return name;
	}


	/**
	 * Set path for high definition image
	 * @param String - Path of the high definition image
	 */
	public void setHDPath(String path) {
		this.hd_path = path;
	}
	
	/**
	 * Set path for thumb nail image
	 * @param String - Path of the thumb nail image
	 */
	public void setTNPath(String path) {
		this.tn_path = path;
	}
	/**
	 * Combine the path and image name to a full absolute path for the image
	 * @return String - Full path of the image
	 */
	@Override
	public String toString(){
		return this.name+", "+this.tn_path;
	}
}
