package ca.ualberta.c301w13t12recipes.view;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.ShareController;
import ca.ualberta.c301w13t12recipes.controller.GalleryAdapter;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.controller.WebStream;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.view.AddIngredWizardActivity.AddIngredDiaglogFragment;

/**
 * This is the activity, which is provided a view of each entry includes the
 * image , name of user , description
 * 
 * @author YUWEI DUAN
 */
public class ViewDetailedRecipeActivity extends Activity {
	private WebStream stream;
	private Gallery gallery;
	private ImageButton optionsButton;
	private IngredientsAdapter adapter;
	private Recipe recipe;
	private TextView titleTextView;
	private TextView descTextView;
	private ListView ingredListView;
	private PopupMenu popupMenu;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_entry);

		setupWidgets();
		getRecipe();

		showName();
		showDescription();

		refreshGallery();
		refreshList();

		optionsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//
				showPopup(v);
				popupMenu
						.setOnMenuItemClickListener(new OnMenuItemClickListener() {

							@Override
							public boolean onMenuItemClick(MenuItem item) {
								// TODO listen the any response from menu
								switch (item.getItemId()) {
								case R.id.pop_delete:

									return true;
								case R.id.pop_edit:
									if (!recipe.getPassWord().equals("")) {
										DialogFragment newFragment = new AuthenticationDiaglogFragment();
										newFragment.show(getFragmentManager(),
												"AUTHENTICATION");
									} else {
										jumpToAddTitleDescWizardActivity();
									}
									return true;
								case R.id.pop_publish:
									try {
										stream = new WebStream();
										stream.insertRecipe(recipe);
									} catch (IllegalStateException e) {

										Toast toast = Toast
												.makeText(
														getApplicationContext(),
														"The Recipe can't be published! Please Check Internet Connection",
														3);
										toast.show();
									} catch (IOException e) {
										Toast toast = Toast
												.makeText(
														getApplicationContext(),
														"The Recipe can't be published! Please Check Internet Connection",
														3);
										toast.show();
									}
									return true;
								case R.id.pop_share:
									startActivity(ShareController
											.SendEmail(recipe));
									return true;
								default:
									return false;
								}

							}
						});

			}

		});

	}

	/**
	 * Set up the new component
	 */
	private void setupWidgets() {
		gallery = (Gallery) findViewById(R.id.view_entry_gallery);

		optionsButton = (ImageButton) findViewById(R.id.view_share_imageButton);
		titleTextView = (TextView) findViewById(R.id.view_textView_title);
		descTextView = (TextView) findViewById(R.id.view_textView_description);
		ingredListView = (ListView) findViewById(R.id.view_ingredients_listView);
	}

	private void getRecipe() {
		recipe = (Recipe) getIntent().getSerializableExtra("LOCAL_RECIPE");
	}

	private void refreshList() {
		adapter = new IngredientsAdapter();
		ingredListView.setAdapter(adapter.getAdapter(this,
				recipe.getIngredients()));

	}

	private void refreshGallery() {
		gallery.setAdapter(new GalleryAdapter(ViewDetailedRecipeActivity.this,
				(ArrayList<Image>) recipe.getImage()));
	}

	private void showDescription() {
		descTextView.setText(new String(recipe.getDirections()));
	}

	private void showName() {
		titleTextView.setText(new String(recipe.getName()));
	}

	private void showPopup(View v) {
		popupMenu = new PopupMenu(this, v);
		MenuInflater inflater = popupMenu.getMenuInflater();
		inflater.inflate(R.menu.view_detail_popup_menu, popupMenu.getMenu());
		popupMenu.show();
	}

	class AuthenticationDiaglogFragment extends DialogFragment {
		private EditText passwordEditText;
		String password;

		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			final View v = inflater.inflate(
					R.layout.dialog_edit_authentication, null);
			builder.setView(v);
			passwordEditText = (EditText) v
					.findViewById(R.id.view_authentication_textEdit);
			inflater.inflate(R.layout.dialog_edit_authentication, null);
			builder.setPositiveButton("Confirm",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							checkPassword(passwordEditText.getText().toString());
						}
					}).setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							AuthenticationDiaglogFragment.this.getDialog()
									.cancel();

						}
					});
			return builder.create();
		}

	}

	private void checkPassword(String prompt) {
		if (recipe.getPassWord().equals(prompt)) {
			jumpToAddTitleDescWizardActivity();
		} else {
			Toast warning = Toast.makeText(getApplicationContext(),
					"Your password is incorrect.Please try again", 2);
			warning.show();
		}
	}

	private void jumpToAddTitleDescWizardActivity() {

		Intent intent = new Intent();
		intent.setClass(ViewDetailedRecipeActivity.this,
				AddTitleDescWizardActivity.class);
		startActivity(intent);
	}
}
