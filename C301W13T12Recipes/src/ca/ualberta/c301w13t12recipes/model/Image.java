package ca.ualberta.c301w13t12recipes.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author YUWEI DUAN
 *
 */
public class Image {
	String path;
	String name;
	/**
	 * @param path
	 * @param id
	 */
	public Image(String path,String id){
		this.path=path;
		this.name=id+"_"+getTime();
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
	public void setName(String name){
		this.name=name;
	}
	
	/**
	 * @param path
	 */
	public void setPath(String path){
		this.path=path;
	}
}
