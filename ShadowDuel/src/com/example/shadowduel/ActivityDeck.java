package com.example.shadowduel;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import android.widget.AdapterView.OnItemClickListener;


public class ActivityDeck extends Activity {

    public static final String PREFS_NAME = "storage_var";
    private String jsondeck;
    private String jsondeckload;
    private Integer[] deck={null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
	Button buttonReturn;
	public ArrayList<Card> Deck;
	public static JSONArray jsArrayd = null;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	//connPath=connPathalt;

		super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		load();
		
		//Toast.makeText(context, ""+jsondeck,Toast.LENGTH_LONG).show();

		Deck=new ArrayList<Card>();
        try {
			jsArrayd = new JSONArray(jsondeck);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	fetchJSONdeck(jsArrayd);
      
        
    
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this,Deck));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            	
            	//Toast.makeText(context, ""+v.getId()+id,Toast.LENGTH_LONG).show();

//            	View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toastLayout));
//            	ImageView imageView = (ImageView)toastView.findViewById(R.id.image);
//
//            	imageView.setImageResource(R.drawable.drago);
////            	    imageView.setBackgroundDrawable(bitmapDrawable);
//
//            	TextView textView = (TextView)toastView.findViewById(R.id.text);
//
//            	textView.setText("Yes, a Toast with an image!");
//
//Toast toast = new Toast(context);
//
//toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//toast.setDuration(20);
//toast.setView(toastView);
//
//toast.show();
                Intent fullScreenIntent = new Intent(v.getContext(),FullImageActivity.class);
                fullScreenIntent.putExtra("ID", deck[position]);
                ActivityDeck.this.startActivity(fullScreenIntent); 

            }
        });
		
		addListenerOnButton();
	    
    }
    private void load(){

        SharedPreferences d = getSharedPreferences(PREFS_NAME, 3);
        jsondeck = d.getString("jsondeck", jsondeckload);


    }
    public void fetchJSONdeck(JSONArray jsonArrayd){
  		String a=null;
  		int num=0;
      	try {
      	   for (int i = 0; i < jsonArrayd.length(); i++) {
      		 num=jsonArrayd.getJSONObject(i).getInt("c_"+(i+1));
      		deck[i]  = getResources().getIdentifier("c_"+num, "drawable",getPackageName());
       		a=a+num;

      	       Deck.add(new Card(deck[i], deck[i],
      	    		   jsonArrayd.getJSONObject(i).getString("name") , 
      	    		   jsonArrayd.getJSONObject(i).getInt("attack"),
      	    		   jsonArrayd.getJSONObject(i).getInt("defense"),
      	    		 jsonArrayd.getJSONObject(i).getString("element")
      	    		   ));
      	    }
 
      	} catch (Exception e) {
      	     e.printStackTrace();
      	}
      	
		

}
	public void addListenerOnButton() {
		buttonReturn = (Button) findViewById(R.id.buttonReturn);

		buttonReturn.setOnClickListener(new OnClickListener() {
			 
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