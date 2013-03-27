package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore.Images;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Recipe;
/** Basic email class with email addresses, subject and body filled in automatically.
 * 
 */
public class EmailController {
	/** Fills in email fields (sender & recipient email addresses, subject, body).
	 *  Puts them into an intent.
	 * 
	 * @param Recipe
	 * @return intent
	 */
	private static Intent FillEmailText(Intent intent, Recipe re){
		
		String[] emailReceipient = {re.getUser()};
		String emailSubject;
		String name = re.getName().toString();
		String emailBody = re.getDirections().toString();
			emailSubject = "Recipe:" + name;
		intent.putExtra(android.content.Intent.EXTRA_EMAIL, emailReceipient);  
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);  
		intent.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);
		
		return intent;
	}
	
	private static Intent AttachMedia(Intent intent, Recipe re){
		
		if (re.getImage().size()>0){
			
			ArrayList<Image> arr = (ArrayList<Image>) re.getImage();
			ArrayList<Uri> uriList = new ArrayList<Uri>();
			for (int i = 0; i < arr.size(); i++){
				String path = "file://"+arr.get(i).getTN_Path();
			    uriList.add(Uri.parse(path));
			}
			intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
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
		Intent intent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
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
