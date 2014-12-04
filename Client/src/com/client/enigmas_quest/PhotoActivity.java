package com.client.enigmas_quest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoActivity extends Activity {
	private TextView textViewPhoto;
	private ImageView imageViewPhoto;
	private Button buttonPhoto;

	private Bitmap bitmapPhoto = null;
	private Uri photoUri = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);

		textViewPhoto = (TextView) findViewById(R.id.textViewPhoto);
		imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
		buttonPhoto = (Button) findViewById(R.id.buttonPhoto);

		buttonPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				open();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void open() {
		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

		File photoFile = null;

		try {
			photoFile = createImageFile();
		} catch (IOException ex) {
			// Error occurred while creating the File
		}

		if (photoFile != null) {
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
			photoUri = Uri.fromFile(photoFile);
			startActivityForResult(intent, 0);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == -1) {

			try {
				bitmapPhoto = MediaStore.Images.Media.getBitmap(
						getContentResolver(), photoUri);

				imageViewPhoto.setImageBitmap(bitmapPhoto);
				sendPhoto();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, ".jpg", storageDir);

		return image;
	}

	private void sendPhoto() {
		// Envoyer la photo au serveur
		if (bitmapPhoto != null) {

		}
	}

	public Bitmap getBitmapPhoto() {
		return bitmapPhoto;
	}
}