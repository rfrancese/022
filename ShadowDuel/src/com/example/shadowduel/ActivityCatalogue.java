package com.example.shadowduel;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class ActivityCatalogue extends Activity {
 
    public static final String PREFS_NAME = "storage_var";
    private String jsoncatalogo;
    private String jsoncatalogoload;
	Button buttonReturn;
	public ArrayList<Card> Catalogo;
	public static JSONArray jsArrayd = null;
	public ArrayList<Integer> catalogo= new ArrayList<Integer>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_catalogue);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		load();
		Catalogo=new ArrayList<Card>();
		
		
        try {
			jsArrayd = new JSONArray(jsoncatalogo);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	fetchJSONcatalogo(jsArrayd);
      
        
    
        GridView gridview = (GridView) findViewById(R.id.gridview1);
        gridview.setAdapter(new ImageAdapter(this,Catalogo));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	
                Intent fullScreenIntent = new Intent(v.getContext(),FullImageActivity.class);
               fullScreenIntent.putExtra("ID", catalogo.get(position));

                ActivityCatalogue.this.startActivity(fullScreenIntent); 

            }
        });
		
		addListenerOnButton();
    }
	private void load(){

        SharedPreferences d = getSharedPreferences(PREFS_NAME, 4);
        jsoncatalogo = d.getString("jsoncatalogo", jsoncatalogoload);


    }
	public void addListenerOnButton() {

		buttonReturn = (Button) findViewById(R.id.buttonReturnCatalogo);

		buttonReturn.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				
				finish();
 
			}
 
		});
	}
	
	public void fetchJSONcatalogo(JSONArray jsonArrayd){
int num=0;
      	try {
      	   for (int i = 0; i < jsonArrayd.length(); i++) {
      		 num=jsonArrayd.getJSONObject(i).getInt("c_"+(i+1));
       		
      		 num = getResources().getIdentifier("c_"+num, "drawable",getPackageName());
      		 catalogo.add(num);
       		Catalogo.add(new Card(catalogo.get(i), 
       				catalogo.get(i),
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
	@Override
	public void onDestroy(){
		super.onDestroy();
		

	}
}