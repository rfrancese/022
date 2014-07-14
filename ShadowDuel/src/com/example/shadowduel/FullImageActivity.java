package com.example.shadowduel;

import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.view.View.OnClickListener;

public class FullImageActivity extends Activity{
	int id;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fullimage);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    ImageView myimage = (ImageView) findViewById(R.id.full);


Bundle extras = getIntent().getExtras();
if (extras != null) {
     id= extras.getInt("ID");
}

int imageId = id;


InputStream is = this.getResources().openRawResource(imageId);
  Bitmap originalBitmap = BitmapFactory.decodeStream(is);
  Matrix imageMatrix = new Matrix();
  Bitmap scaledBitmap = Bitmap.createBitmap(originalBitmap, myimage.getWidth(), myimage.getHeight(), originalBitmap.getWidth(), originalBitmap.getHeight(), imageMatrix, false);
  myimage.setImageBitmap(scaledBitmap);
  myimage.setScaleType(ScaleType.CENTER_CROP);
  //provarotation
//  RotateAnimation animation = new RotateAnimation(100, -10000);

  myimage.setOnClickListener(new OnClickListener() {
		 
		@Override
		public void onClick(View arg0) {
			
			finish();

		}

});
	}
    @Override
public void onDestroy(){
		getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    	super.onDestroy();
    }

}
