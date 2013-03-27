package ca.ualberta.c301w13t12recipes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author YUWEI DUAN
 * @author frank
 *
 */
public class Recipe implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String user;
	private String passward;
	private String name;
	private List<Ingredient> ingredients;
	private List<Image> ImageCollection;
	private String directions;
	private int status;// shows whether the recipe is complete or not 

	/**
	 * Structure of recipe
	 * @param id
	 * @param user
	 * @param name
	 * @param directions
	 * @param user
	 * @param passward
	 */
	public Recipe(String user, String name, String directions) {
		this.id = getUniqueId();
		this.user = user;
		this.passward = "admin";
		this.name = name;
		this.ImageCollection = new ArrayList<Image>();
		this.ingredients = new ArrayList<Ingredient>();
		this.directions = directions;
	}
	public Recipe(String id,String user,String name,List<Ingredient> ar, String directions) {
		this.id = id;
		this.user = user;
		this.passward = "admin";
		this.name = name;
		this.ImageCollection = new ArrayList<Image>();
		this.ingredients = ar;
		this.directions = directions;
	}
	/**
	 * Add an ingredient to the recipe
	 * @param Name of ingredient
	 * @param Amount of ingredient
	 */
	public void addIngredient(String name,String amount){
		this.ingredients.add(new Ingredient(name,amount));
	}
	
	/**
	 * Add Image to associated recipes
	 * @param Path of image
	 */
	public void addImage(String hd_path,String TN_path){
		this.ImageCollection.add(new Image(hd_path,TN_path,Image.getTime()));
	}
	/**
	 * Set the Image list of the Recipe
	 * @param ar arrayList,which contains the Images
	 */
	public void setImageList(ArrayList<Image> ar){
		this.ImageCollection= ar;
	}
	/**
	 * @param pw
	 */
	public void setPassWord(String pw){
		this.passward=pw;
	}
	/**
	 * @return
	 */
	public String getPassWord(){
		return this.passward;
	}
	/**
	 * @param user
	 * @param pw
	 * @return whether the pair of user and pw is the same as the pair in recipe
	 */
	public boolean Authentication(String user,String pw){
		return this.user.compareTo(user)==0&&this.passward.compareTo(pw)==0;
	}

	/**
	 * Get ID
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Get username of this recipe
	 * @return Username
	 */
	public String getUser() {
		return this.user;
	}
	
	
	/**
	 * Set username of current recipe
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Retrieve the name of current recipe
	 * @return Name of recipe
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the list of images associated with current recipe
	 * @return List of image
	 */
	public List<Image> getImage() {
		return this.ImageCollection;
	}
	/**
	 * @param index
	 * @return Image object
	 */
	public Image getImage(int index) {
		return this.ImageCollection.get(index);
	}

	/**
	 * Set the name of current recipe
	 * @param Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieve the list of ingredients from current recipe
	 * @return List of ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Set the ingredients to current recipe
	 * @param Ingredients
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Get direction of current recipe
	 * @return Directions
	 */
	public String getDirections() {
		return directions;
	}

	/**
	 * Set direction of current recipe
	 * @param Directions
	 */
	public void setDirections(String directions) {
		this.directions = directions;
	}

	/**
	 * Convert recipe to a readable string
	 * @return A string of recipe containing all the information
	 */
	@Override
	public String toString() {
		return "Recipe [id=" + id + ", user=" + user + ", name=" + name
				+ ", ingredients=" + ingredients + ", directions=" + directions
				+ "]";
	}

	/**
	 * Convert recipe object to JSON object
	 * @return JSON object
	 */
	public JSONObject toJson() {
		JSONObject jsonObject = new JSONObject();
		ArrayList<Image> image_list = (ArrayList<Image>) this.getImage();
		ArrayList<Ingredient> ingre_list = (ArrayList<Ingredient>) this.getIngredients();
		try {
			jsonObject.put("name", getName());
			jsonObject.put("user", getUser());
			jsonObject.put("password", getPassWord());
			jsonObject.put("directions", getDirections());
			jsonObject.put("id", getId());
			JSONArray ingre_arr = new JSONArray();
			JSONArray image_arr = new JSONArray();
			if (image_list.size()>=1){
				for(Image im:image_list){
					image_arr.put(im.toJson());
				}
			}
			jsonObject.put("image",image_arr );
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
	 * @param Size of the array
	 * @return Array of Recipe objects
	 */
    public Recipe[] newArray(int size) {
		// TODO create an array of Recipe objects
		return new Recipe[size];
	}  
    
    /**
     * set unique UUID
     * @return UUID
     */
	public String getUniqueId() {
		String id = "";
		id = "local@" + UUID.randomUUID().toString();
			//while (this.localIdExists(id));
		return id;
	} 
	
	/**
	 * Remove item from ingredients list
	 */
	public void removeIngredientByIndex(int pos){
		
		this.ingredients.remove(pos);
	}
	/**
	 * Remove the image from the list according to index
	 * @param index base on 0
	 */
	public void removeImage(int pos){
		
		this.ImageCollection.remove(pos);
	}
	/**
	 * Remove all ingredient of the recipe
	 */
	public void removeAllIngredient(){
		if(this.getIngredients().size()>1){
			for(int i=0;i<this.getIngredients().size();i++){
				this.removeIngredientByIndex(i);
			}
		}
	}
	
	/**
	 * Get ingredient name from specific position
	 * @param Position user wishes to retrieve
	 * @return Name of that ingredient
	 */
	public String getIngredientName(int pos){
		return this.ingredients.get(pos).getName();
	}
}
