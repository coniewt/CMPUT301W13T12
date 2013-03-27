package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Recipe;
/** Basic email class with email addresses, subject and body filled in automatically.
 * 
 */
public class Email {
	/** Fills in email fields (sender & recipient email addresses, subject, body).
	 *  Puts them into an intent.
	 * 
	 * @param Recipe
	 * @return intent
	 */
	private static Intent FillEmailText(Intent intent, Recipe re){
		String[] emailReceipient = {re.getUser()};
		String emailSubject;
		String RecipeIdMsg = re.getDirections().toString();
		String emailBody = re.toString();
			emailSubject = "Recipe Done: " + RecipeIdMsg;
		intent.putExtra(android.content.Intent.EXTRA_EMAIL, emailReceipient);  
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);  
		intent.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);
		
		return intent;
	}
	
	private static Intent AttachMedia(Intent intent, Recipe re){
		if (re.getImage().size()>0){
			ArrayList<Image> arr = (ArrayList<Image>) re.getImage();
			ArrayList<Uri> uris = new ArrayList<Uri>();
			Uri uri = null;
			for (int i = 0; i < arr.size(); i++){
			    String string = arr.get(i).getTN_Path();
			    uri = Uri.parse(string);
			    uris.add(uri);
			}
			intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
		}
		return intent;
	}
	
        
	/** Parses Recipe and creates an email from Recipe attributes.
	 *  Lets user choose email client to use to send email.
	 * 
	 * @param  Intent - intent with info to send/call email client
	 * @param  Recipe - Recipe to get text and/or media path from
	 */
	public static Intent SendEmail(Recipe Recipe){
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("plain/text");
		
		//Parse Recipe text
		intent = FillEmailText(intent, Recipe);
		//Attach media attachments
		intent = AttachMedia(intent, Recipe);
		
		//Choose which email client to use to handle intent
		intent = Intent.createChooser(intent, "Choose email client");
		return intent;
	}
}
