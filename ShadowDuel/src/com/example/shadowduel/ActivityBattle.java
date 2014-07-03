package com.example.shadowduel;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import com.example.shadowduel.Deck.DeckTask;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.TransitionDrawable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBattle extends Activity {
		private boolean attivogioca=false;

	 	private Integer[] field={null,null,null,null,null};
	 	private	Boolean[] selectedhand={false,false,false,false,false};
	 	private Boolean[] enemyselectedhand={false,true,true,false,true};
	    private int myscore=0;
	    private int enemyscore=0;
	    private ArrayList<Card> enemyhand= new ArrayList<Card>();
	    private String intero="ff";
	    private Integer[] deck={0,11,2,3,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,50};
		
		//deck
		private ArrayList<Card> Deck=new ArrayList<Card>();;
		private ArrayList<Card> currDeck=new ArrayList<Card>();;
		private ArrayList<Card> hand=new ArrayList<Card>();;
		private Random rand;
		private int LENGHT;
		private int currlenght;

		private static JSONArray jsArrayd = null;
	     ///finedeck
	    private int timer=0;
	    private View frame1,frame2,frame3,frame4,frame5;
	    private View enemyhand1,enemyhand2,enemyhand3,enemyhand4,enemyhand5;
	    private Button myhand1,myhand2,myhand3,myhand4,myhand5,gioca;
	    private int myatt=0,mydif=0,enemyatt=0,enemydif=0;
	    private boolean started=false;
	    float scale;
	    
	    
	    ////storage
	    private String username;
	    private String userload;
	    private static final String PREFS_NAME = "storage_var";
		private String jsondeck;
		private String jsondeckload;
		
		
		// Variabili di connesione al server e JSON
		private static JSONArray jsArray = null;
		private HttpPost httpPost;
		private DefaultHttpClient httpClient;
		private  Context context;

		private Integer[] selectedhandint={0,0,0,0,0};
		private EnemyPhaseTask ult;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			load();
			try {
				jsArrayd = new JSONArray(jsondeck);
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	fetchJSONuser(jsArrayd);

			super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_battle);
			context = this;
			scale = getResources().getDisplayMetrics().density;
			drawbattle();
			addListenerOnButton();

	    }
		

	    private String load(){
	        SharedPreferences u = getSharedPreferences(PREFS_NAME, 0);
	        username = u.getString("username", userload);
	        SharedPreferences d = getSharedPreferences(PREFS_NAME, 3);
	        jsondeck = d.getString("jsondeck", jsondeckload);
	        return username;

	    }
		public void playMyCard(){
			int m110 =(int) ( 110*scale+0.5f);

			int m130 =(getResources().getDisplayMetrics().heightPixels)/(5);
			int m50 =(int) ( 50*scale+0.5f);
			int m20 =(int) ( 20*scale+0.5f);
			int m60 =(int) ( 60*scale+0.5f);
			int m40 =(int) ( 40*scale+0.5f);
			int m70 =(int) ( 70*scale+0.5f);
			int m80 =(int) ( 80*scale+0.5f);
			int m90 =(int) ( 90*scale+0.5f);

			int m100 =(int) ( 100*scale+0.5f);
			int m15 =(int) ( 15*scale+0.5f);
			int m5 =(int) ( 5*scale+0.5f);
			int m30 =(int) ( 30*scale+0.5f);
			int m25 =(int) ( 25*scale+0.5f);
			int m45 =(int) ( 45*scale+0.5f);
			int m10 =(int) ( 10*scale+0.5f);
			int m55 =(int) ( 55*scale+0.5f);
			findViewById(R.id.framehand1).setVisibility(View.GONE);
			findViewById(R.id.framehand2).setVisibility(View.GONE);
			findViewById(R.id.framehand3).setVisibility(View.GONE);
			findViewById(R.id.framehand4).setVisibility(View.GONE);
			findViewById(R.id.framehand5).setVisibility(View.GONE);
			//////////////////////////selezioni a 1//////////////
			if(selectedhand[0]&&!selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started){
				TranslateAnimation tra0= new TranslateAnimation(0, m110, 0, -m130);
				tra0.setDuration(1000);
				tra0.setFillAfter(true);
				myhand1.startAnimation(tra0);
			
				}
			if(!selectedhand[0]&&selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started){
				TranslateAnimation tra1= new TranslateAnimation(0, m55, 0, -m130);
				tra1.setDuration(1000);
				tra1.setFillAfter(true);
				myhand2.startAnimation(tra1);
				//frame2.startAnimation(tra1);
				}
			if(!selectedhand[0]&&!selectedhand[1]&&selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started){
				TranslateAnimation tra2= new TranslateAnimation(0, 0, 0, -m130);
				tra2.setDuration(1000);
				tra2.setFillAfter(true);
				myhand3.startAnimation(tra2);
				//frame3.startAnimation(tra2);
				}
			if(!selectedhand[0]&&!selectedhand[1]&&!selectedhand[2]&&selectedhand[3]&&!selectedhand[4]&&started){
				TranslateAnimation tra3= new TranslateAnimation(0, -m55, 0, -m130);
				tra3.setDuration(1000);
				tra3.setFillAfter(true);
				myhand4.startAnimation(tra3);
				//frame4.startAnimation(tra3);
				}
			if(!selectedhand[0]&&!selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&selectedhand[4]&&started){
				TranslateAnimation tra4= new TranslateAnimation(0, -m110, 0, -m130);
				tra4.setDuration(1000);
				tra4.setFillAfter(true);
				myhand5.startAnimation(tra4);
				//frame5.startAnimation(tra4);
				}
			/////////////////////////////////////////////////selezioni a 2
			if(selectedhand[0]&&selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m90, 0, -m130);
			tra1.setDuration(1000);
			tra1.setStartOffset(100);
			tra1.setFillAfter(true);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			}
			if(selectedhand[0]&&selectedhand[2]&&!selectedhand[1]&&!selectedhand[3]&&!selectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, m50, 0, -m130);
			tra2.setDuration(1000);
			tra2.setStartOffset(100);
			tra2.setFillAfter(true);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			}
			if(selectedhand[0]&&selectedhand[3]&&!selectedhand[1]&&!selectedhand[2]&&!selectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m20, 0, -m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(selectedhand[0]&&selectedhand[4]&&!selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m80, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra4= new TranslateAnimation(0, -m50, 0, -m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[1]&&selectedhand[2]&&!selectedhand[0]&&!selectedhand[3]&&!selectedhand[4]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m10, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, m40, 0, -m130);
			tra2.setDuration(1000);
			tra2.setStartOffset(100);
			tra2.setFillAfter(true);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			}
			if(selectedhand[1]&&selectedhand[3]&&!selectedhand[0]&&!selectedhand[2]&&!selectedhand[4]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m10, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m10, 0, -m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(selectedhand[1]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[2]&&!selectedhand[3]&&started){
			TranslateAnimation traaa1= new TranslateAnimation(0,m10, 0, -m130);
			traaa1.setDuration(1000);
			traaa1.setFillAfter(true);
			TranslateAnimation traaa4= new TranslateAnimation(0, -m70, 0, -m130);
			traaa4.setDuration(1000);
			traaa4.setStartOffset(100);
			traaa4.setFillAfter(true);
			myhand2.startAnimation(traaa1);
			//frame2.startAnimation(traaa1);
			myhand5.startAnimation(traaa4);
			//frame5.startAnimation(traaa4);
			}
			if(selectedhand[2]&&selectedhand[3]&&!selectedhand[0]&&!selectedhand[1]&&!selectedhand[4]&&started){
			TranslateAnimation traaa1= new TranslateAnimation(0, -m50, 0, -m130);
			traaa1.setDuration(1000);
			traaa1.setFillAfter(true);
			TranslateAnimation traaa4= new TranslateAnimation(0, -m20, 0, -m130);
			traaa4.setDuration(1000);
			traaa4.setStartOffset(100);
			traaa4.setFillAfter(true);
			myhand3.startAnimation(traaa1);
			//frame3.startAnimation(traaa1);
			myhand4.startAnimation(traaa4);
			//frame4.startAnimation(traaa4);
			}
			if(selectedhand[2]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[1]&&!selectedhand[3]&&started){
			TranslateAnimation traa2= new TranslateAnimation(0, -m50, 0, -m130);
			traa2.setDuration(1000);
			traa2.setFillAfter(true);
			TranslateAnimation traa4= new TranslateAnimation(0, -m60, 0, -m130);
			traa4.setDuration(1000);
			traa4.setStartOffset(100);
			traa4.setFillAfter(true);
			myhand3.startAnimation(traa2);
			//frame3.startAnimation(traa2);
			myhand5.startAnimation(traa4);
			//frame5.startAnimation(traa4);
			}
			if(selectedhand[3]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[1]&&!selectedhand[2]&&started){
			TranslateAnimation traa3= new TranslateAnimation(0, -m100, 0, -m130);
			traa3.setDuration(1000);
			traa3.setFillAfter(true);
			TranslateAnimation traa4= new TranslateAnimation(0, -m60, 0, -m130);
			traa4.setDuration(1000);
			traa4.setStartOffset(100);
			traa4.setFillAfter(true);
			myhand4.startAnimation(traa3);
			//frame4.startAnimation(traa3);
			myhand5.startAnimation(traa4);
			//frame5.startAnimation(traa4);
			}
			
			//////////////////////////////////////////////selezioni a 3
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0,m60 , 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra2= new TranslateAnimation(0, m60, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			}
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[3]&&!selectedhand[2]&&!selectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m60, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, m10, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[4]&&!selectedhand[2]&&!selectedhand[3]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m60, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m50, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[0]&&selectedhand[2]&&selectedhand[3]&&!selectedhand[1]&&!selectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m70, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0,m15 , 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, m20, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(selectedhand[0]&&selectedhand[2]&&selectedhand[4]&&!selectedhand[1]&&!selectedhand[3]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0,m15, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m40, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand3.startAnimation(tra1);
			//frame3.startAnimation(tra1);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[0]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[1]&&!selectedhand[2]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m50, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m50, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[1]&&selectedhand[2]&&selectedhand[3]&&!selectedhand[0]&&!selectedhand[4]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m15, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, m20, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, m20, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(selectedhand[1]&&selectedhand[2]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[3]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m5, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, m10, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m40, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[1]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[2]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m10, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m40, 0, -m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			TranslateAnimation tra4= new TranslateAnimation(0, -m40, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[2]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[1]&&started){
			TranslateAnimation tra2= new TranslateAnimation(0, -m50, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m50, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m45, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			
			/////////////////////////////////////////////selezioni a 4
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[2]&&selectedhand[3]&&!selectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m30, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m30, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra2= new TranslateAnimation(0, m30, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			TranslateAnimation tra3= new TranslateAnimation(0, m30, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(300);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[2]&&selectedhand[4]&&!selectedhand[3]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m30, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m30, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra2= new TranslateAnimation(0, m30, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			TranslateAnimation tra4= new TranslateAnimation(0, -m20, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[1]&&selectedhand[2]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[0]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, -m20, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, -m15, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, -m15, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			TranslateAnimation tra4= new TranslateAnimation(0, -m15, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[0]&&selectedhand[2]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[1]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m30, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, -m25, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, -m25, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			TranslateAnimation tra4= new TranslateAnimation(0, -m25, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[2]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m30, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m30, 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, -m20, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			TranslateAnimation tra4= new TranslateAnimation(0, -m20, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			
			//////////////////////////////////////selezione a 5
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[2]&&selectedhand[3]&&selectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, 0, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, 0, 0, -m130);
			tra1.setDuration(1000);
			tra1.setStartOffset(100);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, 0, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			TranslateAnimation tra3= new TranslateAnimation(0, 0, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(300);
			TranslateAnimation tra4= new TranslateAnimation(0, 0, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(400);
			myhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			
			
			
			for(int i=0;i<5;i++){
				if(selectedhand[i]==true){
					myatt+=hand.get(i).getAtt();
					mydif+=hand.get(i).getDif();
				}
			}

			TextView textarea = (TextView)findViewById(R.id.myatt);  // tv is id in XML file for TextView
			textarea.setTextColor(Color.rgb(0xff, 0, 0));
			textarea.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			textarea.setText(String.valueOf(myatt));
			TextView textarea2 = (TextView)findViewById(R.id.mydif);  // tv is id in XML file for TextView
			textarea2.setTextColor(Color.rgb(0xff, 0, 0));
			textarea2.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			textarea2.setText(String.valueOf(mydif));
		}
		public void enemyplaycard(Boolean[] enemyselectedhand){
			int m110 =(int) ( 110*scale+0.5f);

			int m130 =(getResources().getDisplayMetrics().heightPixels)/(5);
			int m50 =(int) ( 50*scale+0.5f);
			int m20 =(int) ( 20*scale+0.5f);
			int m60 =(int) ( 60*scale+0.5f);
			int m40 =(int) ( 40*scale+0.5f);
			int m70 =(int) ( 70*scale+0.5f);
			int m80 =(int) ( 80*scale+0.5f);
			int m90 =(int) ( 90*scale+0.5f);

			int m100 =(int) ( 100*scale+0.5f);
			int m15 =(int) ( 15*scale+0.5f);
			int m5 =(int) ( 5*scale+0.5f);
			int m30 =(int) ( 30*scale+0.5f);
			int m25 =(int) ( 25*scale+0.5f);
			int m45 =(int) ( 45*scale+0.5f);
			int m10 =(int) ( 10*scale+0.5f);
			int m55 =(int) ( 55*scale+0.5f);

			enemyhand1.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(0).getIdImg(), "drawable", getPackageName()));
			enemyhand2.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(1).getIdImg(), "drawable", getPackageName()));
			enemyhand3.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(2).getIdImg(), "drawable", getPackageName()));
			enemyhand4.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(3).getIdImg(), "drawable", getPackageName()));
			enemyhand5.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(4).getIdImg(), "drawable", getPackageName()));

			
			
			
			
			//////////////////////////selezioni a 1//////////////
			if(enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
				TranslateAnimation tra0= new TranslateAnimation(0, m110, 0, m130);
				tra0.setDuration(1000);
				tra0.setFillAfter(true);
				enemyhand1.startAnimation(tra0);

				 //frame1.startAnimation(tra0);
				
				}
			if(!enemyselectedhand[0]&&enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
				TranslateAnimation tra1= new TranslateAnimation(0, m55, 0, m130);
				tra1.setDuration(1000);
				tra1.setFillAfter(true);
				enemyhand2.startAnimation(tra1);
				//frame2.startAnimation(tra1);
				}
			if(!enemyselectedhand[0]&&!enemyselectedhand[1]&&enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
				TranslateAnimation tra2= new TranslateAnimation(0, 0, 0, m130);
				tra2.setDuration(1000);
				tra2.setFillAfter(true);
				enemyhand3.startAnimation(tra2);
				//frame3.startAnimation(tra2);
				}
			if(!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
				TranslateAnimation tra3= new TranslateAnimation(0, -m55, 0, m130);
				tra3.setDuration(1000);
				tra3.setFillAfter(true);
				enemyhand4.startAnimation(tra3);
				//frame4.startAnimation(tra3);
				}
			if(!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&enemyselectedhand[4]&&started){
				TranslateAnimation tra4= new TranslateAnimation(0, -m110, 0, m130);
				tra4.setDuration(1000);
				tra4.setFillAfter(true);
				enemyhand5.startAnimation(tra4);
				//frame5.startAnimation(tra4);
				}
			/////////////////////////////////////////////////selezioni a 2
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m90, 0, m130);
			tra1.setDuration(1000);
			tra1.setStartOffset(100);
			tra1.setFillAfter(true);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[2]&&!enemyselectedhand[1]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, m50, 0, m130);
			tra2.setDuration(1000);
			tra2.setStartOffset(100);
			tra2.setFillAfter(true);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[3]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m20, 0, m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[4]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m80, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra4= new TranslateAnimation(0, -m50, 0, m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[2]&&!enemyselectedhand[0]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m10, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, m40, 0, m130);
			tra2.setDuration(1000);
			tra2.setStartOffset(100);
			tra2.setFillAfter(true);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[3]&&!enemyselectedhand[0]&&!enemyselectedhand[2]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m10, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m10, 0, m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&started){
			TranslateAnimation traaa1= new TranslateAnimation(0,m10, 0, m130);
			traaa1.setDuration(1000);
			traaa1.setFillAfter(true);
			TranslateAnimation traaa4= new TranslateAnimation(0, -m70, 0, m130);
			traaa4.setDuration(1000);
			traaa4.setStartOffset(100);
			traaa4.setFillAfter(true);
			enemyhand2.startAnimation(traaa1);
			//frame2.startAnimation(traaa1);
			enemyhand5.startAnimation(traaa4);
			//frame5.startAnimation(traaa4);
			}
			if(enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[4]&&started){
			TranslateAnimation traaa1= new TranslateAnimation(0, -m50, 0, m130);
			traaa1.setDuration(1000);
			traaa1.setFillAfter(true);
			TranslateAnimation traaa4= new TranslateAnimation(0, -m20, 0, m130);
			traaa4.setDuration(1000);
			traaa4.setStartOffset(100);
			traaa4.setFillAfter(true);
			enemyhand3.startAnimation(traaa1);
			//frame3.startAnimation(traaa1);
			enemyhand4.startAnimation(traaa4);
			//frame4.startAnimation(traaa4);
			}
			if(enemyselectedhand[2]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[3]&&started){
			TranslateAnimation traa2= new TranslateAnimation(0, -m50, 0, m130);
			traa2.setDuration(1000);
			traa2.setFillAfter(true);
			TranslateAnimation traa4= new TranslateAnimation(0, -m60, 0, m130);
			traa4.setDuration(1000);
			traa4.setStartOffset(100);
			traa4.setFillAfter(true);
			enemyhand3.startAnimation(traa2);
			//frame3.startAnimation(traa2);
			enemyhand5.startAnimation(traa4);
			//frame5.startAnimation(traa4);
			}
			if(enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&started){
			TranslateAnimation traa3= new TranslateAnimation(0, -m100, 0, m130);
			traa3.setDuration(1000);
			traa3.setFillAfter(true);
			TranslateAnimation traa4= new TranslateAnimation(0, -m60, 0, m130);
			traa4.setDuration(1000);
			traa4.setStartOffset(100);
			traa4.setFillAfter(true);
			enemyhand4.startAnimation(traa3);
			//frame4.startAnimation(traa3);
			enemyhand5.startAnimation(traa4);
			//frame5.startAnimation(traa4);
			}
			
			//////////////////////////////////////////////selezioni a 3
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0,m60 , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra2= new TranslateAnimation(0, m60, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[3]&&!enemyselectedhand[2]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m60, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, m10, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[4]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m60, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m50, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[1]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m70, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0,m15 , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, m20, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[2]&&enemyselectedhand[4]&&!enemyselectedhand[1]&&!enemyselectedhand[3]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0,m15, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m40, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand3.startAnimation(tra1);
			//frame3.startAnimation(tra1);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m60, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m50, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m50, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[0]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m15, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, m20, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, m20, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[3]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m5, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, m10, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m40, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[2]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, m10, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m40, 0, m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			TranslateAnimation tra4= new TranslateAnimation(0, -m40, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[2]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[1]&&started){
			TranslateAnimation tra2= new TranslateAnimation(0, -m50, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			TranslateAnimation tra3= new TranslateAnimation(0, -m50, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(100);
			TranslateAnimation tra4= new TranslateAnimation(0, -m45, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			
			/////////////////////////////////////////////selezioni a 4
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m30, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m30, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra2= new TranslateAnimation(0, m30, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			TranslateAnimation tra3= new TranslateAnimation(0, m30, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(300);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[4]&&!enemyselectedhand[3]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m30, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m30, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra2= new TranslateAnimation(0, m30, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			TranslateAnimation tra4= new TranslateAnimation(0, -m20, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&started){
			TranslateAnimation tra1= new TranslateAnimation(0, -m20, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, -m15, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, -m15, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			TranslateAnimation tra4= new TranslateAnimation(0, -m15, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[2]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[1]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m30, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, -m25, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, -m25, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			TranslateAnimation tra4= new TranslateAnimation(0, -m25, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[2]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, m30, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, m30, 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			TranslateAnimation tra3= new TranslateAnimation(0, -m20, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			TranslateAnimation tra4= new TranslateAnimation(0, -m20, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}

			//////////////////////////////////////selezione a 5
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[3]&&enemyselectedhand[4]&&started){
			TranslateAnimation tra0= new TranslateAnimation(0, 0, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			TranslateAnimation tra1= new TranslateAnimation(0, 0, 0, m130);
			tra1.setDuration(1000);
			tra1.setStartOffset(100);
			tra1.setFillAfter(true);
			TranslateAnimation tra2= new TranslateAnimation(0, 0, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			TranslateAnimation tra3= new TranslateAnimation(0, 0, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(300);
			TranslateAnimation tra4= new TranslateAnimation(0, 0, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(400);
			enemyhand1.startAnimation(tra0);
			//frame1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			//frame2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			//frame3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			//frame4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			//frame5.startAnimation(tra4);
			}
			
			for(int i=0;i<5;i++){
				if(enemyselectedhand[i]==true){
					enemyatt+=enemyhand.get(i).getAtt();
					enemydif+=enemyhand.get(i).getDif();
				}
			}

			TextView textarea = (TextView)findViewById(R.id.enemyatt);  // tv is id in XML file for TextView
			textarea.setTextColor(Color.rgb(0xff, 0, 0));
			textarea.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			textarea.setText(String.valueOf(enemyatt));
			TextView textarea2 = (TextView)findViewById(R.id.enemydif);  // tv is id in XML file for TextView
			textarea2.setTextColor(Color.rgb(0xff, 0, 0));
			textarea2.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			textarea2.setText(String.valueOf(enemydif));
		}
		@SuppressLint("NewApi")
		public void drawbattle(){


			LinearLayout lenemy = (LinearLayout)findViewById(R.id.enemyhand);
			LinearLayout lme = (LinearLayout)findViewById(R.id.myhand);
			LayoutParams enemyp = lenemy.getLayoutParams();
			LayoutParams myp = lme.getLayoutParams();

			enemyp.height = (int) ((getResources().getDisplayMetrics().heightPixels)/(2.5));
			myp.height = (int) ((getResources().getDisplayMetrics().heightPixels)/(2.5));
			frame1=findViewById(R.id.framehand1);
			frame2=findViewById(R.id.framehand2);
			frame3=findViewById(R.id.framehand3);
			frame4=findViewById(R.id.framehand4);
			frame5=findViewById(R.id.framehand5);
			findViewById(R.id.framehand1).setVisibility(View.GONE);
			findViewById(R.id.framehand2).setVisibility(View.GONE);
			findViewById(R.id.framehand3).setVisibility(View.GONE);
			findViewById(R.id.framehand4).setVisibility(View.GONE);
			findViewById(R.id.framehand5).setVisibility(View.GONE);
			gioca = (Button) findViewById(R.id.button_gioca);
		     myhand1 = (Button) findViewById(R.id.myhand1);	 
		     myhand2 = (Button) findViewById(R.id.myhand2);
		     myhand3 = (Button) findViewById(R.id.myhand3);
		     myhand4 = (Button) findViewById(R.id.myhand4);
		     myhand5 = (Button) findViewById(R.id.myhand5);
		     LayoutParams a1 = myhand1.getLayoutParams();
		     LayoutParams a2 = myhand2.getLayoutParams();
		     LayoutParams a3 = myhand3.getLayoutParams();
		     LayoutParams a4 = myhand4.getLayoutParams();
		     LayoutParams a5 = myhand5.getLayoutParams();

	
		     a1.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     a2.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     a3.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     a4.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     a5.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     a1.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     a2.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     a3.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     a4.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     a5.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));


		     LayoutParams b1 = frame1.getLayoutParams();
		     LayoutParams b2 = frame2.getLayoutParams();
		     LayoutParams b3 = frame3.getLayoutParams();
		     LayoutParams b4 = frame4.getLayoutParams();
		     LayoutParams b5 = frame5.getLayoutParams();

	
		     b1.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     b2.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     b3.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     b4.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     b5.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     b1.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     b2.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     b3.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     b4.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     b5.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));

		     enemyhand1= findViewById(R.id.enemyhand1);
		     enemyhand2= findViewById(R.id.enemyhand2);
		     enemyhand3= findViewById(R.id.enemyhand3);
		     enemyhand4= findViewById(R.id.enemyhand4);
		     enemyhand5= findViewById(R.id.enemyhand5);
		     LayoutParams c1 = enemyhand1.getLayoutParams();
		     LayoutParams c2 = enemyhand2.getLayoutParams();
		     LayoutParams c3 = enemyhand3.getLayoutParams();
		     LayoutParams c4 = enemyhand4.getLayoutParams();
		     LayoutParams c5 = enemyhand5.getLayoutParams();

	
		     c1.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     c2.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     c3.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     c4.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     c5.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
		     c1.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     c2.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     c3.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     c4.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     c5.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));

		    myhand1.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(0).getIdImg(), "drawable", getPackageName()));
			myhand2.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(1).getIdImg(), "drawable", getPackageName()));
			myhand3.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(2).getIdImg(), "drawable", getPackageName()));
			myhand4.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(3).getIdImg(), "drawable", getPackageName()));
			myhand5.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(4).getIdImg(), "drawable", getPackageName()));
		
		}
		
		public void addListenerOnButton() {
			gioca.setOnClickListener(new OnClickListener() {
				//boolean a= false;
				
				@Override
				public void onClick(View v) {
				       ult = new EnemyPhaseTask();
				        
				        ult.execute();
					if(started==false){
						started=true;
					}else{
						started=false;
					}
					int f=0;
					for(int i=0;i<selectedhand.length;i++){
						if(selectedhand[i]==true){
							field[f]=(hand.get(i).getIdImg());
							f++;
							selectedhandint[i]=1;
						}else{
							field[f]=0;
							selectedhandint[i]=0;

						}
						
					}
					
					if(attivogioca==false){
					gioca.setBackgroundResource(R.drawable.gioca_disattivo);
					gioca.setOnClickListener(null);
					frame1.setVisibility(View.GONE);
					frame2.setVisibility(View.GONE);
					frame3.setVisibility(View.GONE);
					frame4.setVisibility(View.GONE);
					frame5.setVisibility(View.GONE);
					
					myhand1.setOnClickListener(null);
					myhand2.setOnClickListener(null);
					myhand3.setOnClickListener(null);
					myhand4.setOnClickListener(null);
					myhand5.setOnClickListener(null);
					playMyCard();


			        attivogioca=true;
					}else{
						gioca.setBackgroundResource(R.drawable.gioca_game);

					}
				
				}
					
				
			});
			
			myhand1.setOnClickListener(new OnClickListener() {	
				boolean a=false;
				public void onClick(View arg0) {
					if(selectedhand[0]==false){
					selectedhand[0]=true;}
					else
					{selectedhand[0]=false;}
					if(a==false){						
					findViewById(R.id.framehand1).setVisibility(View.VISIBLE);
					a=true;
					}else{
						findViewById(R.id.framehand1).setVisibility(View.GONE);
						a=false;
					}}});
			myhand2.setOnClickListener(new OnClickListener() {	
				boolean a=false;

				public void onClick(View arg0) {
					if(selectedhand[1]==false){
					selectedhand[1]=true;}
					else
					{selectedhand[1]=false;}

					if(a==false){						
					findViewById(R.id.framehand2).setVisibility(View.VISIBLE);
					a=true;
					}else{
						findViewById(R.id.framehand2).setVisibility(View.GONE);
						a=false;
					}}});
			myhand3.setOnClickListener(new OnClickListener() {	
				boolean a=false;

				public void onClick(View arg0) {
					if(selectedhand[2]==false){
					selectedhand[2]=true;}
					else
					{selectedhand[2]=false;}

					if(a==false){						
					findViewById(R.id.framehand3).setVisibility(View.VISIBLE);
					a=true;
					}else{
						findViewById(R.id.framehand3).setVisibility(View.GONE);
						a=false;
					}}});
			myhand4.setOnClickListener(new OnClickListener() {	
				boolean a=false;

				public void onClick(View arg0) {
						
						if(selectedhand[3]==false){
							selectedhand[3]=true;}
							else
							{selectedhand[3]=false;}
						if(a==false){
					findViewById(R.id.framehand4).setVisibility(View.VISIBLE);
					a=true;
					}else{
						findViewById(R.id.framehand4).setVisibility(View.GONE);
						a=false;
					}}});
			myhand5.setOnClickListener(new OnClickListener() {	
				boolean a=false;

				public void onClick(View arg0) {
					
						if(selectedhand[4]==false){
							selectedhand[4]=true;}
							else
							{selectedhand[4]=false;}
						if(a==false){	
					findViewById(R.id.framehand5).setVisibility(View.VISIBLE);
					a=true;
					}else{
						findViewById(R.id.framehand5).setVisibility(View.GONE);
						a=false;
					}}});
			
		}
		
		
		
		//////////
	    public class EnemyPhaseTask extends AsyncTask<Void, Void, Boolean> {
	    	
	    	String json = "";
	    	
	        @Override
	        protected Boolean doInBackground(Void... params) {
	            // TODO: attempt authentication against a network service.
	        	while(enemyselectedhand[0]==false&&timer<10){
	        		timer++;
	        	//intero=""+enemyselectedhand[0];
	            try {
	            	try {
						sendLoginAndGetJsonFromUrl();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            	// Simulate network access.
	                Thread.sleep(100);
	            }
	            catch (InterruptedException e) {
	                return false;
	            } 


	        }


return false;
	        }
	        @Override
	        protected void onPostExecute(final Boolean success) {
	       		Toast.makeText(context, ""+enemyhand.get(0).getIdImg(),Toast.LENGTH_LONG).show();
	        	if(enemyselectedhand[0]==true){
	        	 	Toast.makeText(context, "Ricevuto",Toast.LENGTH_LONG).show();
	        		enemyplaycard(enemyselectedhand);}
 
	        }

	        @Override
	        protected void onCancelled() {

	        }

	    }  
	    public void sendLoginAndGetJsonFromUrl() throws JSONException, InterruptedException {
	        
			String json = "a";
        	String url = "http://shadowduel.altervista.org/app/app_game_player_hand_field.php";	

	
		 
	        try {

	  			httpClient = new DefaultHttpClient();
	  			httpPost = new HttpPost(url);
	  			


	  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	  		    nameValuePairs.add(new BasicNameValuePair("username", ""+username));
	  		    nameValuePairs.add(new BasicNameValuePair("hand1", ""+selectedhandint[0]));
	  		    nameValuePairs.add(new BasicNameValuePair("hand2", ""+selectedhandint[1]));
	  		    nameValuePairs.add(new BasicNameValuePair("hand3", ""+selectedhandint[2]));
	  		    nameValuePairs.add(new BasicNameValuePair("hand4", ""+selectedhandint[3]));
	  		    nameValuePairs.add(new BasicNameValuePair("hand5", ""+selectedhandint[4]));
	  		    nameValuePairs.add(new BasicNameValuePair("card1", ""+field[0]));
	  		    nameValuePairs.add(new BasicNameValuePair("card2", ""+field[1]));
	  		    nameValuePairs.add(new BasicNameValuePair("card3", ""+field[2]));
	  		    nameValuePairs.add(new BasicNameValuePair("card4", ""+field[3]));
	  		    nameValuePairs.add(new BasicNameValuePair("card5", ""+field[4]));
	  		    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	  		    // Execute HTTP Post Request
	  		    HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            json = EntityUtils.toString(httpEntity);
	 
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
   	       intero = json;

			jsArray = new JSONArray(json);
			
        	fetchJSON(jsArray);

	    }
	    public void fetchJSON(JSONArray jsonArrayd){
int bool=1;

	      	try {
	      	   for (int i = 0; i < jsonArrayd.length(); i++) {
	      		   
	
	      	
	      	       enemyhand.add(new Card(jsonArrayd.getJSONObject(i).getInt("c_"+(i+1)) , 
	      	    		   jsonArrayd.getJSONObject(i).getInt("c_"+(i+1)) , 
	      	    		   jsonArrayd.getJSONObject(i).getString("name") , 
	      	    		   jsonArrayd.getJSONObject(i).getInt("attack"),
	      	    		   jsonArrayd.getJSONObject(i).getInt("defense")));
	      	       
	      	       bool = jsonArrayd.getJSONObject(i).getInt("hand"+(i+1));
	      	       if(bool==0){
	      	       enemyselectedhand[i] = false;}
	      	       else{
	      	    	 enemyselectedhand[i] = true;
	      	       }

	      	    }
	       		Toast.makeText(context, ""+intero,Toast.LENGTH_LONG).show();


	      	} catch (Exception e) {
	      	     e.printStackTrace();
	      	}
	      	
	}
	   
	    
	    public void fetchJSONuser(JSONArray jsonArrayd){

	      	try {
	      	   for (int i = 0; i < jsonArrayd.length(); i++) {
	      		   
	      	       deck[i] = jsonArrayd.getJSONObject(i).getInt("c_"+(i+1));
	      	      // intero = jsonArray.getJSONObject(i).getString("name");

	      	       Deck.add(new Card(deck[i], deck[i],
	      	    		   jsonArrayd.getJSONObject(i).getString("name") , 
	      	    		   jsonArrayd.getJSONObject(i).getInt("attack"),
	      	    		   jsonArrayd.getJSONObject(i).getInt("defense")));
	      	    }
	      	   currDeck=Deck;
	   		LENGHT=deck.length;
			currlenght=LENGHT;
			hand=pickRandomHand();
			//lunghezzajson=jsonArrayd.length();
	      	} catch (Exception e) {
	      	     e.printStackTrace();
	      	}
	      	
	}

	    
	    public Card pickRandomCard(){
			Card card;
			currlenght=currDeck.size();
			int c;
			if(currlenght>0){
			rand= new Random();
			c=rand.nextInt(currlenght);
			card=currDeck.remove(c);
			return card;}else{
				return null;
			}
		}
		
		public ArrayList<Card> pickRandomHand(){
			int c;
			rand= new Random();
			currlenght=currDeck.size();
			if(currlenght>4){
			for(int i=0;i<5;i++){
				c=rand.nextInt(currlenght);
				hand.add(currDeck.remove(c));
				//decklenght-1
			}
			return hand;
			}else{
				if(currlenght>0){
					for(int i=0;i<currlenght;i++){
						
						c=rand.nextInt(currlenght);
						hand.add(currDeck.remove(c));
					}
				return hand;
			}
				else{
				return null;
			}
		}

		}
		@Override
		public void onDestroy(){
			ult.cancel(true);
		}
}