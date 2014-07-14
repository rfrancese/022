package com.example.shadowduel;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;

public class ActivityMenu extends Activity {
//	   public static final String PREFS_NAME = "storage_var";
//	   public String jsondeck;
//	   public String jsondeckload;
	Button buttonreturn;
	Button buttoncat;
	Button buttondeck;
	Button button;
	float scale;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_menu);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//	        load();
			scale = getResources().getDisplayMetrics().density;

	        addListenerOnButton();
	        
	    
	    }
//	   private void load(){
//	        SharedPreferences d = getSharedPreferences(PREFS_NAME, 3);
//	        jsondeck = d.getString("jsondeck", jsondeckload);
//
//
//	    }
		public void addListenerOnButton() {
			 
			final Context context = this;
			buttonreturn = (Button) findViewById(R.id.buttonExit);
			buttoncat = (Button) findViewById(R.id.buttonCatalogue);
			buttondeck = (Button) findViewById(R.id.buttonDeck);
			button = (Button) findViewById(R.id.buttonStartGame);

	        LayoutParams returnpar = buttonreturn.getLayoutParams();
	        LayoutParams catpar = buttoncat.getLayoutParams();
	        LayoutParams deckpar = buttondeck.getLayoutParams();
	        LayoutParams giocapar = button.getLayoutParams();
	        LayoutParams holderpar = findViewById(R.id.holdermenu).getLayoutParams();
	        LayoutParams title = findViewById(R.id.titlemenu).getLayoutParams();
	        LayoutParams amici = findViewById(R.id.buttonAmici).getLayoutParams();
	        
	        title.height=(int) (80*scale+0.5f);
	        title.width=(int) (300*scale+0.5f);

	        holderpar.height=(int) (30*scale+0.5f);

	        returnpar.height=(int) (55*scale+0.5f);
	        catpar.height=(int) (55*scale+0.5f);
	        deckpar.height=(int) (55*scale+0.5f);
	        giocapar.height=(int) (55*scale+0.5f);
	        returnpar.width=(int) (110*scale+0.5f);
	        amici.height=(int) (55*scale+0.5f);
	        amici.width=(int) (110*scale+0.5f);
	        catpar.width=(int) (200*scale+0.5f);
	        deckpar.width=(int) (140*scale+0.5f);
	        giocapar.width=(int) (130*scale+0.5f);
			button.setEnabled(true);
		   

			
			buttonreturn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonreturn.setOnClickListener(null);
					buttoncat.setOnClickListener(null);
					buttondeck.setOnClickListener(null);
					button.setOnClickListener(null);

					finish();
				}
			});
			buttoncat.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonreturn.setOnClickListener(null);
					buttoncat.setOnClickListener(null);
					buttondeck.setOnClickListener(null);
					button.setOnClickListener(null);
					Intent intent = new Intent(context,ActivityCatalogue.class);
					startActivity(intent);
				}
			});

			buttondeck.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonreturn.setOnClickListener(null);
					buttoncat.setOnClickListener(null);
					buttondeck.setOnClickListener(null);
					button.setOnClickListener(null);
					Intent intent = new Intent(context,ActivityDeck.class);
					startActivity(intent);
				}
			});

			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					buttonreturn.setOnClickListener(null);
					buttoncat.setOnClickListener(null);
					buttondeck.setOnClickListener(null);
					button.setOnClickListener(null);
				    Intent intent = new Intent(context, ActivityGame.class);
	                            startActivity(intent);   
				}
	 
			});
			
		}
		@Override
		public void onResume() {
			super.onResume();
			addListenerOnButton();
			
			
			
	}
		@Override
		public void onDestroy(){
			super.onDestroy();
		getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}
}
