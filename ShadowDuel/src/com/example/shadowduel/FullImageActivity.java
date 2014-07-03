package com.example.shadowduel;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
public class FullImageActivity extends Activity{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fullimage);

    ImageView myimage = (ImageView) findViewById(R.id.full);

Intent intent = getIntent();


 int imageId = R.drawable.c_1;
  System.out.println("********"+imageId +"**********");
  InputStream is = this.getResources().openRawResource(imageId);
  Bitmap originalBitmap = BitmapFactory.decodeStream(is);
  Matrix imageMatrix = new Matrix();
  Bitmap scaledBitmap = Bitmap.createBitmap(originalBitmap, myimage.getWidth(), myimage.getHeight(), originalBitmap.getWidth(), originalBitmap.getHeight(), imageMatrix, false);
  myimage.setImageBitmap(scaledBitmap);
  myimage.setScaleType(ScaleType.CENTER_CROP);
  //provarotation
//  RotateAnimation animation = new RotateAnimation(100, -10000);
//  animation.setDuration(10000);
//  animation.setFillAfter(true);
//  myimage.startAnimation(animation);
  myimage.setOnClickListener(new OnClickListener() {
		 
		@Override
		public void onClick(View arg0) {
			
			finish();

		}

});
	}

}
