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
	 * @param list
	 * @param directions
	 */
	public Recipe(String id, String user, String name, String directions) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.ImageCollection = new ArrayList<Image>();
		this.ingredients = new ArrayList<Ingredient>();
		this.directions = directions;
		this.status = 0;
	}
	
	public Recipe(String id, String user, String name,
			List<Ingredient> list, String directions) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.ImageCollection = new ArrayList<Image>();
		this.ingredients = list;
		this.directions = directions;
		this.status = 0;
	}
	
	/**
	 * Add an ingredient to the recipe
	 * @param name
	 * @param amount
	 */
	public void addIngredient(String name,String amount){
		this.ingredients.add(new Ingredient(name,amount));
	}
	
	/**
	 * Add Images to associated recipes
	 * @param path
	 */
	public void addImage(String path){
		this.ImageCollection.add(new Image(path));
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
	 * @return user
	 */
	public String getUser() {
		return this.user;
	}
	
	/**
	 * Get the status of current recipe
	 * @return true/false
	 */
	public boolean isComplete(){
		if(this.status == 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Set status of current recipe
	 */
	public void setStatus(){
		this.status = 1;
	}
	
	/**
	 * Set user of current recipe
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Retrieve the name of current recipe
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the list of images associated with curent recipe
	 * @return list of image
	 */
	public List<Image> getImage() {
		return this.ImageCollection;
	}

	/**
	 * Set the name of current recipe
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieve the list of ingredients from current recipe
	 * @return list of ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Set the ingredients to current recipe
	 * @param ingredients
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Get direction
	 * @return directions
	 */
	public String getDirections() {
		return directions;
	}

	/**
	 * Set direction
	 * @param directions
	 */
	public void setDirections(String directions) {
		this.directions = directions;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", user=" + user + ", name=" + name
				+ ", ingredients=" + ingredients + ", directions=" + directions
				+ "]";
	}

	/**
	 * convert recipe object to jsonobject
	 * @return Json object
	 */
	public JSONObject toJson() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("name", getName());
			jsonObject.put("user", getUser());
			jsonObject.put("directions", getDirections());
			jsonObject.put("id", getId());
			
			JSONArray arr = new JSONArray();
			JSONArray image_arr = new JSONArray();
			if (getImage().size()>1){
				for(Image im:getImage()){
					image_arr.put(im.toJson());
				}
			}
			jsonObject.put("image",image_arr );
			for (Ingredient in : getIngredients()) {
				JSONObject jo = new JSONObject();
				jo.put("name", in.getName());
				jo.put("amount", in.getAmount());
				arr.put(jo);
			}
			jsonObject.put("Ingredients", arr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	/*
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		// TODO write each field of recipe object 
		// into parcel
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(user);
		dest.writeList(ingredients);
		dest.writeList(ImageCollection);
		dest.writeString(directions);
		dest.writeInt(status);
		
	}
	public static final Parcelable.Creator<Recipe> CREATOR = new Creator<Recipe>() {  
        public Recipe createFromParcel(Parcel source) {
        	// TODO create a new Recipe object and then change 
        	//its attributes to one that just passed in
            Recipe recipe = new Recipe("","","","");
            recipe.id = source.readString();
            recipe.name = source.readString();
            recipe.user = source.readString();
            source.readList(recipe.ingredients, Ingredient.class.getClassLoader());
            source.readList(recipe.ImageCollection, Image.class.getClassLoader());
            recipe.directions = source.readString();
            recipe.status = source.readInt();
            return recipe;
        }
		@Override
		
    }; 
    */ 
	
	/**
	 * Create an array of Recipe objects
	 * @param size
	 * @return array of Recipe objects
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
	 * remove item from ingredients list
	 * @return
	 */
	public void removeIngredient(int pos){
		
		this.ingredients.remove(pos);
	}
}
