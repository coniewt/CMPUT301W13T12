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
/** 
 * Apply basic email functionality. Provide email addresses, subject and body filled in automatically.
 * 
 */
public class ShareController {

	/** 
	 * Fills in email fields (sender & recipient email addresses, subject, body),
	 * puts them into an intent.
	 * 
	 * @param Intent Email operation to be performed
	 * @param Recipe Recipe wish to email
	 * @return Intent - Email operation to be performed
	 */
	private static Intent FillEmailText(Intent intent, Recipe re){
		
		String[] emailReceipient = {re.getUser()};
		String emailSubject ="";
		String name = re.getName().toString();
		String emailBody = re.getDirections().toString();
			emailSubject = "Recipe:" + name;
		intent.putExtra(android.content.Intent.EXTRA_EMAIL, emailReceipient);  
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);  
		intent.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);
		
		return intent;
	}
	
	/**
	 * Add corresponding recipe image as attachment to email
	 * @param Intent Email operation to be performed
	 * @param Recipe Recipe wish to email
	 * @return Intent - Email operation to be performed
	 */
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
	
        
	/** 
	 * Parses Recipe and creates an email from Recipe attributes. Lets user choose email client to send email.
	 * 
	 * @param Intent Email operation to be performed
	 * @param Recipe Recipe wish to email
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
