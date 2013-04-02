package ca.ualberta.c301w13t12recipes.model;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import ca.ualberta.c301w13t12recipes.controller.ImageManager;

/**
 * Recipe class for basic structure and functionality of the recipe object
 * @author YUWEI DUAN
 * @author frank
 * 
 */
public class Recipe implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String user;
	private String passward;
	private String name;
	private List<Ingredient> ingredients;
	private List<Image> ImageCollection;
	private String directions;
	private HashMap<String, String> bitmap_String;

	/**
	 * Constructor for building structure of new recipe
	 * 
	 * @param String User name for creating this recipe
	 * @param String Name of the recipe
	 * @param String Directions for how to cook the specific recipe
	 */
	public Recipe(String user, String name, String directions) {
		this.id = getUniqueId();
		this.user = user;
		this.passward = "";
		this.name = name;
		this.ImageCollection = new ArrayList<Image>();
		this.ingredients = new ArrayList<Ingredient>();
		this.directions = directions;
	}

	/**
	 * Constructor for building structure of existed recipe
	 * @param String ID of the recipe
	 * @param String User name for creating this recipe
	 * @param String Name of the recipe
	 * @param List<Ingredient> List of Ingredient associated with the recipe
	 * @param String Directions for how to cook the specific recipe
	 */
	public Recipe(String id, String user, String name, List<Ingredient> ar,
			String directions) {
		this.id = id;
		this.user = user;
		this.passward = "";
		this.name = name;
		this.ImageCollection = new ArrayList<Image>();
		this.ingredients = ar;
		this.directions = directions;
	}

	/**
	 * This construct is used to construct objects with bitmaps.
	 * 
	 * @param String ID of the recipe
	 * @param String User name for creating this recipe
	 * @param String Name of the recipe
	 * @param List<Ingredient> List of Ingredient associated with the recipe
	 * @param String Directions for how to cook the specific recipe
	 * @param HashMap<String, String> hashmap of the bitmap associated with this recipe
	 */
	public Recipe(String id, String user, String name, List<Ingredient> ar,
			String directions, HashMap<String, String> bitmap_hashmap) {
		this.id = id;
		this.user = user;
		this.passward = "";
		this.name = name;
		this.ingredients = ar;
		this.directions = directions;
		this.bitmap_String = bitmap_hashmap;
	}

	/**
	 * Default constructor
	 */
	public Recipe() {
		this.id = getUniqueId();
		this.user = "";
		this.passward = "";
		this.name = "";
		this.ImageCollection = new ArrayList<Image>();
		this.ingredients = new ArrayList<Ingredient>();
		this.directions = "";
	}

	/**
	 * Add an ingredient to the recipe
	 * 
	 * @param String Name of the ingredient
	 * @param String Amount of the ingredient
	 */
	public void addIngredient(String name, String amount) {
		this.ingredients.add(new Ingredient(name, amount));
	}

	/**
	 * Add Image to associated recipes
	 * 
	 * @param String Path to the high definition image associated with this recipe
	 * @param StringPath to the thumb nail image associated with this recipe
	 */
	public void addImage(String hd_path, String TN_path) {
		this.ImageCollection.add(new Image(hd_path, TN_path, Image.getTime()));
	}

	/**
	 * Set the Image list of the Recipe
	 * 
	 * @param ArrayList<Image> Arraylist of the image object
	 */
	public void setImageList(ArrayList<Image> ar) {
		this.ImageCollection = ar;
	}

	/**
	 * Set password to the recipe
	 * @param String Password
	 */
	public void setPassword(String pw) {
		this.passward = pw;
	}

	/**
	 * Get password of the recipe
	 * @return String - Password
	 */
	public String getPassword() {
		return this.passward;
	}

	/**
	 * To check if the user is creator of the recipe by comparing user name and password
	 * @param String User Name
	 * @param String Password
	 * @return boolean - True if authentication is successful, False otherwise
	 */
	public boolean Authentication(String user, String pw) {
		return this.user.compareTo(user) == 0
				&& this.passward.compareTo(pw) == 0;
	}

	/**
	 * Get recipe ID
	 * @return String - ID of the recipe
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set recipe ID
	 * @param String ID of the recipe
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get user name of this recipe
	 * @return String - User name
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * Set user name of current recipe
	 * @param String User name
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Retrieve the name of current recipe
	 * @return String - Name of recipe
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the list of images associated with current recipe
	 * @return List<Image> - List of image
	 */
	public List<Image> getImage() {
		return this.ImageCollection;
	}

	/**
	 * Get specific image of the designated index
	 * @param int Index of the image with to retrieve
	 * @return Image - Image object of that image
	 */
	public Image getImage(int index) {
		return this.ImageCollection.get(index);
	}

	/**
	 * Set the name of current recipe
	 * @param String Name of the recipe
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieve the list of ingredients from current recipe
	 * @return List<Ingredient> - List of ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Set the ingredients to current recipe
	 * @param ArrayList<Ingredient> Ingredients with to associate to the recipe
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Get direction of current recipe
	 * @return String - Directions of the recipe
	 */
	public String getDirections() {
		return directions;
	}

	/**
	 * Set direction of current recipe
	 * @param String Directions of the recipe
	 */
	public void setDirections(String directions) {
		this.directions = directions;
	}

	/**
	 * Convert recipe to a readable string
	 * @return String - A string of recipe containing all the information
	 */
	@Override
	public String toString() {
		return "Recipe [ " + user + ", " + name + ", ingredients:"
				+ ingredients.toString() + ", " + ImageCollection.toString()
				+ directions + "]";
	}

	/**
	 * Convert recipe object to JSON object
	 * @return JSONObject - A JSON object containing all information of the recipe
	 */
	public JSONObject toJson() {
		JSONObject jsonObject = new JSONObject();
		ArrayList<Image> image_list = (ArrayList<Image>) this.getImage();
		ArrayList<Ingredient> ingre_list = (ArrayList<Ingredient>) this
				.getIngredients();
		try {
			jsonObject.put("name", getName());
			jsonObject.put("user", getUser());
			jsonObject.put("password", getPassword());
			jsonObject.put("directions", getDirections());
			jsonObject.put("id", getId());
			JSONArray ingre_arr = new JSONArray();
			JSONArray image_arr = new JSONArray();
			if (image_list.size() >= 1) {
				for (Image im : image_list) {
					image_arr.put(im.toJson());
				}
			}
			jsonObject.put("image", image_arr);
			for (Ingredient in : ingre_list) {
				JSONObject jo = new JSONObject();
				jo.put("name", in.getName());
				jo.put("amount", in.getAmount());
				ingre_arr.put(jo);
			}
			jsonObject.put("Ingredients", ingre_arr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * Create an array of Recipe objects
	 * @param int Size of the array
	 * @return Recipe[] - Array of Recipe objects
	 */
	public Recipe[] newArray(int size) {
		// TODO create an array of Recipe objects
		return new Recipe[size];
	}

	/**
	 * set unique UUID
	 * @return String - UUID
	 */
	public String getUniqueId() {
		String id = "";
		id = "local@" + UUID.randomUUID().toString();
		// while (this.localIdExists(id));
		return id;
	}

	/**
	 * Remove a specific item from ingredients list by index
	 * @param int Index of the ingredient wish to remove
	 */
	public void removeIngredientByIndex(int pos) {

		this.ingredients.remove(pos);
	}

	/**
	 * Remove a specific image from the list by index
	 * 
	 * @param int Index of the image wish to remove
	 */
	public void removeImage(int pos) {

		this.ImageCollection.remove(pos);
	}

	/**
	 * Remove all ingredient of the recipe
	 */
	public void removeAllIngredient() {
		if (this.getIngredients().size() > 1) {
			for (int i = 0; i < this.getIngredients().size(); i++) {
				this.removeIngredientByIndex(i);
			}
		}
	}

	/**
	 * Get hashmap from a bitmap
	 * @return HashMap<String, String> - Hashmap of the bitmap
	 */
	public HashMap<String, String> getImageBitmapHashMap() {
		return this.bitmap_String;
	}

	/**
	 * Get ingredient name from specific position
	 * 
	 * @param int Index user wishes to retrieve
	 * @return String - Name of the ingredient
	 */
	public String getIngredientName(int pos) {
		return this.ingredients.get(pos).getName();
	}

	/**
	 * Converting the image to hashmap
	 * 
	 * @return Recipe - Recipe contains the hashmap of Bitmaps
	 */
	public Recipe convertTobitmapRecipe() {
		HashMap<String, String> list = new HashMap<String, String>();
		ArrayList<Image> image_list = (ArrayList<Image>) this.getImage();
		ImageManager im = new ImageManager();
		for (Image image : image_list) {
			list.put(
					image.getName(),
					convertBitmapToString(im.decodeBitmapFromFile(
							im.createImage(image.getTN_Path()), 500, 500)));
		}
		return new Recipe(getId(), getUser(), getName(), getIngredients(),
				getDirections(), list);
	}

	/**
	 * Convert the online recipe to local recipe
	 * 
	 * @return Recipe - Local recipe
	 */
	public Recipe convertToLocalRecipe() {
		HashMap<String, String> map = this.getImageBitmapHashMap();
		ArrayList<Image> image_list = (ArrayList<Image>) this.getImage();
		ImageManager im = new ImageManager();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			image_list.add(new Image("", im.convertFromBitmapToFilePath(
					getBitMapFromString(pair.getValue()), pair.getKey()), pair
					.getKey()));
			it.remove();
		}
		Recipe re = new Recipe(getId(), getUser(), getName(), getIngredients(),
				getDirections());
		re.setImageList(image_list);
		return re;
	}

	/**
	 * Convert Bitmap to String
	 * 
	 * @param Bitmap Bitmap wish to convert to string
	 * @return String - String form of the bitmap
	 */
	public static String convertBitmapToString(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] b = baos.toByteArray();
		String temp = Base64.encodeToString(b, Base64.DEFAULT);
		return temp;
	}

	/**
	 * Convert String to bitmap
	 * 
	 * @param String String form of the bitmap
	 * @return bitmap - Bitmap wish to restore from string
	 */
	public static Bitmap getBitMapFromString(String encodedString) {
		try {
			byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
					encodeByte.length);
			return bitmap;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	/**
	 * Check if the ingredient is existed in the ingredients list
	 * @param String Ingredient wish to check
	 * @return boolean - True of the ingredient is already existed in the list, False otherwise
	 */
	public boolean isIncluded(String ar) {
		ArrayList<Ingredient> list = (ArrayList<Ingredient>) this
				.getIngredients();
		for (Ingredient in : list) {
			if (in.getName().compareTo(ar) != 0)
				return false;
		}
		return false;
	}
}
