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
 *
 */
/**
 * @author frank
 *
 */
public class Recipe implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String user;
	private String name;
	private List<Ingredient> ingredients;
	private List<Image> ImageCollection;
	private String directions;
	private int status;// shows whether the recipe is complete or not 

	/**
	 * @param id
	 * @param user
	 * @param name
	 * @param list
	 * @param directions
	 */
	public Recipe(String id, String user, String name,String directions) {
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
	 * TODO add an ingredient to the recipe
	 * @param name
	 * @param amount
	 */
	public void addIngredient(String name,String amount){
		this.ingredients.add(new Ingredient(name,amount));
	}
	/**
	 * @param path
	 */
	public void addImage(String path){
		this.ImageCollection.add(new Image(path));
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	/**
	 * @return user
	 */
	public String getUser() {
		return this.user;
	}
	/**
	 * @return the status of recipe
	 */
	public boolean isComplete(){
		if(this.status == 0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * @param none
	 */
	public void setStatus(){
		this.status = 1;
	}
	/**
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return list of image
	 */
	public List<Image> getImage() {
		return this.ImageCollection;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return a list of ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return directions
	 */
	public String getDirections() {
		return directions;
	}

	/**
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
    public Recipe[] newArray(int size) {
		// TODO create an array of Recipe objects
		return new Recipe[size];
	}  
	public String getUniqueId() {
		String id = "";
		id = "local@" + UUID.randomUUID().toString();
			//while (this.localIdExists(id));
		return id;
	} 

}
