package com.example.shadowduel;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.os.Build;

public class ActivityMenu extends Activity {
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_menu);
	        addListenerOnButton();
	    
	    }

		public void addListenerOnButton() {
			 
			final Context context = this;
			Button buttonreturn = (Button) findViewById(R.id.buttonExit);
			Button buttoncat = (Button) findViewById(R.id.buttonCatalogue);
			Button buttondeck = (Button) findViewById(R.id.buttonDeck);
			Button button = (Button) findViewById(R.id.buttonStartGame);

			button.setEnabled(true);
		   

			
			buttonreturn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			buttoncat.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context,ActivityCatalogue.class);
					startActivity(intent);
				}
			});
			buttondeck.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context,ActivityDeck.class);
					startActivity(intent);
				}
			});

			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
				    Intent intent = new Intent(context, ActivityGame.class);
	                            startActivity(intent);   
				}
	 
			});
		}

	   

}
