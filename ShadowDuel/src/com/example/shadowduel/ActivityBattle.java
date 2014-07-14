package com.example.shadowduel;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
import com.example.shadowduel.MainActivity.myCatalogoTask;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.TransitionDrawable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBattle extends Activity {
		private String connPath="http://shadowduel.altervista.org";
		private String connPathalt="http://shadowduel.netne.net";
	 	private Integer[] field={null,null,null,null,null};
	 	private	Boolean[] selectedhand={false,false,false,false,false};
	 	private Boolean[] enemyselectedhand={null,null,null,null,null};
	    private int myscore=0;
	    private int enemyscore=0;
	    private ArrayList<Card> enemyhand= new ArrayList<Card>();
	    private Integer[] deck={null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,50};
	    private String json=null;
	    private int myw=0,myf=0,mye=0,mya=0,mys=0,myl=0,enemyw=0,enemyf=0,enemye=0,enemya=0,enemys=0,enemyl=0;
		//deck
	    private ArrayList<Card> Catalogo = new ArrayList<Card>();
		private ArrayList<Card> Deck=new ArrayList<Card>();;
		private ArrayList<Card> currDeck=new ArrayList<Card>();;
		private ArrayList<Card> hand=new ArrayList<Card>();;
		private Random rand;
		private int LENGHT;
		private int currlenght;

		private static JSONArray jsArrayd = null;
		private static JSONArray jsArrayc = null;

	     ///finedeck
	    private View frame1,frame2,frame3,frame4,frame5;
	    private View enemyhand1,enemyhand2,enemyhand3,enemyhand4,enemyhand5,placehold1,placehold2,placehold3,placehold4;
	    private int[] x41=new int[2],x42=new int[2],x43=new int[2],x44=new int[2];
	    private int[] x51=new int[2],x52=new int[2],x53=new int[2],x54=new int[2],x55=new int[2];
	    private int[] myatthistoy=new int[3],mydifhistoy=new int[3],enemyatthistoy=new int[3],enemydifhistoy=new int[3];
	    private Button myhand1,myhand2,myhand3,myhand4,myhand5,gioca;
	    private int myatt=0,mydif=0,enemyatt=0,enemydif=0;
	    private boolean started=true;
	    float scale;
	    TextView textareamyatt;
	    TextView textareamydif;
	    TextView textareaenemyatt;
	    TextView textareaenemydif;
	    TextView textareaenemy;

	    public Intent intent;
		TranslateAnimation tra0 = null;
		TranslateAnimation tra1 = null;
		TranslateAnimation tra2 = null;
		TranslateAnimation tra3 = null;
		TranslateAnimation tra4 = null;
	    ////storage
	    private String username;
	    private String userload;
	    private String password;
	    private String passload;
	    private static final String PREFS_NAME = "storage_var";
		private String jsondeck;
		private String jsondeckload;
		private String jsoncat;
		private String jsoncatload;
		//private afkTask afk1= new afkTask();/////////////startare la task all'interno di enemyphase task nel secondo if del postexecute
		private waitflipTask flip= new waitflipTask();
		//prova
		private int phase1=0,phase2=0,phase3=3,score,newcardid=0;
		private Card newcard=null;
		private boolean battlestart=true;
		private Toast toastscegli,toastfase1,toastfase2,toastfase3,toastattendi;
		private boolean running=true;
		private int phase=0;
	       private Animation myanimationflip1,myanimationflip2,myanimationflip3,myanimationflip4,myanimationflip5;
	       private Animation enemyanimationflip1,enemyanimationflip2,enemyanimationflip3,enemyanimationflip4,enemyanimationflip5;
	       private int timer=30;
	       Handler handler = new Handler();
	       Handler handler1 = new Handler();
	       private String jsonrat=null;
	       private int rating;
		// Variabili di connesione al server e JSON
		private static JSONArray jsArray = null;
		private HttpPost httpPost;
		private DefaultHttpClient httpClient;
		private  Context context;
		private String enemyname;
		private Integer[] selectedhandint={0,0,0,0,0};
		private EnemyPhaseTask ult= new EnemyPhaseTask();
		private endBattle endbattle = new endBattle();
		private int crashTimer=65;
		private int waitTimer=4;
		//Timer timerafk = new Timer();
		private waitTask waittask= new waitTask();
		@Override
		public void onCreate(Bundle savedInstanceState) {
			load();
			try {
				jsArrayd = new JSONArray(jsondeck);
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	fetchJSONuser(jsArrayd);
        	
			try {
				jsArrayc = new JSONArray(jsoncat);
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	fetchJSONCatalogo(jsArrayc);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_battle);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
			     enemyname= extras.getString("enemyname");
			}
			textareaenemy=new TextView(this);
			textareaenemy = (TextView)findViewById(R.id.enemyname);  // tv is id in XML file for TextView
			textareaenemy.setTextColor(Color.WHITE);
			textareaenemy.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			textareaenemy.setText(enemyname);
			context = this;
			scale = getResources().getDisplayMetrics().density;
			 toastscegli=new Toast(this);
					toastfase1=new Toast(this);
							
					toastfase2=new Toast(this);
					toastfase3=new Toast(this);
					toastattendi=new Toast(this);
			createToastscegli();
			createToastfase1();

			
			
			

			super.onCreate(savedInstanceState);


	    }

		private void  mytypecheckadd(int i){
			String ftype;
					ftype=hand.get(i).getType();
					if(ftype.equalsIgnoreCase("acqua")){
						myw++;
						if(myw>1){
							myatt=myatt+1;
							mydif=mydif+1;

						}
					}
					if(ftype.equalsIgnoreCase("fuoco")){
						myf++;
						if(myf>1){
							myatt=myatt+1;
							mydif=mydif+1;

						}
					}
					if(ftype.equalsIgnoreCase("terra")){
						mye++;
						if(mye>1){
							myatt=myatt+1;
							mydif=mydif+1;

						}
					}
					if(ftype.equalsIgnoreCase("vento")){
						mya++;
						if(mya>1){
							myatt=myatt+1;
							mydif=mydif+1;

						}
					}
					if(ftype.equalsIgnoreCase("ombra")){
						mys++;
						if(mys>1){
							myatt=myatt+1;
							mydif=mydif+1;

						}
					}
					if(ftype.equalsIgnoreCase("fulmine")){
						myl++;
						if(myl>1){
							myatt=myatt+1;
							mydif=mydif+1;

						}
					}
		}
		private void  mytypecheckremove(int i){
			String ftype;
			ftype=hand.get(i).getType();
			if(ftype.equalsIgnoreCase("acqua")){
				if(myw>1){
					myatt=myatt-1;
					mydif=mydif-1;

				}
				myw--;
		
			}
			if(ftype.equalsIgnoreCase("fuoco")){
				if(myf>1){
					myatt=myatt-1;
					mydif=mydif-1;

				}
				myf--;
			}
			if(ftype.equalsIgnoreCase("terra")){
				if(mye>1){
					myatt=myatt-1;
					mydif=mydif-1;

				}
				mye--;
			}
			if(ftype.equalsIgnoreCase("vento")){
				if(mya>1){
					myatt=myatt-1;
					mydif=mydif-1;

				}
				mya--;
			}
			if(ftype.equalsIgnoreCase("ombra")){
				if(mys>1){
					myatt=myatt-1;
					mydif=mydif-1;

				}
				mys--;
			}
			if(ftype.equalsIgnoreCase("fulmine")){
				if(myl>1){
					myatt=myatt-1;
					mydif=mydif-1;

				}
				myl--;
			}
		}
		
		private void  enemytypecheck(){
			String ftype;
			int bonus=0;
			ftype=enemyhand.get(0).getType();
			for(int i=0;i<5;i++){
				if(enemyselectedhand[i]==true){
					ftype=enemyhand.get(i).getType();
					if(ftype.equalsIgnoreCase("acqua")){
						enemyw++;
					}
					if(ftype.equalsIgnoreCase("fuoco")){
						enemyf++;
					}
					if(ftype.equalsIgnoreCase("terra")){
						enemye++;
					}
					if(ftype.equalsIgnoreCase("vento")){
						enemya++;
					}
					if(ftype.equalsIgnoreCase("ombra")){
						enemys++;
					}
					if(ftype.equalsIgnoreCase("fulmine")){
						enemyl++;
					}
				}	
				
			}	
			if(enemyw>1){
				bonus+=enemyw;
			}
			if(enemyf>1){
				bonus+=enemyf;

			}
			if(enemye>1){
				bonus+=enemye;

			}
			if(enemya>1){
				bonus+=enemya;

			}
			if(enemyl>1){
				bonus+=enemyl;

			}
			if(enemys>1){
				bonus+=enemys;

			}
			enemyatt=enemyatt+bonus;
			enemydif=enemydif+bonus;
			int bonus2=0;
			bonus2=bonus2+(myw-enemyf)+(myf-enemya)+(mya-enemye)+(mye-enemys)+(mys-enemyl)+(myl-enemyw);
			if(bonus2>0){
				myatt=myatt+bonus2;
				mydif=mydif+bonus2;

			}
			if(bonus2<0){
				enemyatt=enemyatt+bonus2;
				enemydif=enemydif+bonus2;
			}
		}
		

		
	    private String load(){
	        SharedPreferences u = getSharedPreferences(PREFS_NAME, 0);
	        username = u.getString("username", userload);
	        SharedPreferences d = getSharedPreferences(PREFS_NAME, 3);
	        jsondeck = d.getString("jsondeck", jsondeckload);
	        SharedPreferences p = getSharedPreferences(PREFS_NAME, 1);

	        password = p.getString("password", passload);
	        SharedPreferences c = getSharedPreferences(PREFS_NAME, 4);
	        jsoncat = d.getString("jsondeck", jsoncatload);
	        return username;

	    }
 
		public void playMyCard(boolean reverse){
			int m130 =((getResources().getDisplayMetrics().heightPixels)/(5));
			findViewById(R.id.framehand1).setVisibility(View.GONE);
			findViewById(R.id.framehand2).setVisibility(View.GONE);
			findViewById(R.id.framehand3).setVisibility(View.GONE);
			findViewById(R.id.framehand4).setVisibility(View.GONE);
			findViewById(R.id.framehand5).setVisibility(View.GONE);

			if(reverse){
				battlestart=true;
				myhand1.clearAnimation();		
				myhand2.clearAnimation();
				myhand3.clearAnimation();
				myhand4.clearAnimation();
				myhand5.clearAnimation();
				for(int i=0;i<5;i++){
					if(selectedhand[i]==true){
						hand.remove(i);
						hand.add(i, pickRandomCard()); 
						
					}
				}
			}
			
			//////////////////////////selezioni a 1//////////////
			if(selectedhand[0]&&!selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
				tra0= new TranslateAnimation(0,((x53[0]-x51[0])) , 0, -m130);
				tra0.setDuration(1000);
				tra0.setFillAfter(true);
				myhand1.startAnimation(tra0);

				}
			if(!selectedhand[0]&&selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
				tra1= new TranslateAnimation(0, ((x53[0]-x52[0])), 0, -m130);
				tra1.setDuration(1000);
				tra1.setFillAfter(true);
				myhand2.startAnimation(tra1);
				}
			if(!selectedhand[0]&&!selectedhand[1]&&selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
				tra2= new TranslateAnimation(0, 0, 0, -m130);
				tra2.setDuration(1000);
				tra2.setFillAfter(true);
				myhand3.startAnimation(tra2);
				}
			if(!selectedhand[0]&&!selectedhand[1]&&!selectedhand[2]&&selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
				tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
				tra3.setDuration(1000);
				tra3.setFillAfter(true);
				myhand4.startAnimation(tra3);
				}
			if(!selectedhand[0]&&!selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&selectedhand[4]&&started&&!reverse){
				tra4= new TranslateAnimation(0, ((x53[0]-x55[0])), 0, -m130);
				tra4.setDuration(1000);
				tra4.setFillAfter(true);
				myhand5.startAnimation(tra4);
				}
			/////////////////////////////////////////////////selezioni a 2
			if(selectedhand[0]&&selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0, ((x53[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setStartOffset(100);
			tra1.setFillAfter(true);
			myhand1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			}
			if(selectedhand[0]&&selectedhand[2]&&!selectedhand[1]&&!selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra2= new TranslateAnimation(0, ((x53[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setStartOffset(100);
			tra2.setFillAfter(true);
			myhand1.startAnimation(tra0);
			myhand3.startAnimation(tra2);
			}
			if(selectedhand[0]&&selectedhand[3]&&!selectedhand[1]&&!selectedhand[2]&&!selectedhand[4]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			myhand1.startAnimation(tra0);
			myhand4.startAnimation(tra3);
			}
			if(selectedhand[0]&&selectedhand[4]&&!selectedhand[1]&&!selectedhand[2]&&!selectedhand[3]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra4= new TranslateAnimation(0, ((x53[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			myhand1.startAnimation(tra0);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[1]&&selectedhand[2]&&!selectedhand[0]&&!selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
			tra1= new TranslateAnimation(0, ((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra2= new TranslateAnimation(0, ((x53[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setStartOffset(100);
			tra2.setFillAfter(true);
			myhand2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			}
			if(selectedhand[1]&&selectedhand[3]&&!selectedhand[0]&&!selectedhand[2]&&!selectedhand[4]&&started&&!reverse){
			tra1= new TranslateAnimation(0, ((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			myhand2.startAnimation(tra1);
			myhand4.startAnimation(tra3);
			}
			if(selectedhand[1]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[2]&&!selectedhand[3]&&started&&!reverse){
			tra1= new TranslateAnimation(0,((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra4= new TranslateAnimation(0, ((x53[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			myhand2.startAnimation(tra1);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[2]&&selectedhand[3]&&!selectedhand[0]&&!selectedhand[1]&&!selectedhand[4]&&started&&!reverse){
			tra1= new TranslateAnimation(0, ((x52[0]-x53[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra4= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			myhand3.startAnimation(tra1);
			myhand4.startAnimation(tra4);
			}
			if(selectedhand[2]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[1]&&!selectedhand[3]&&started&&!reverse){
			tra2= new TranslateAnimation(0, ((x52[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra4= new TranslateAnimation(0, ((x53[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			myhand3.startAnimation(tra2);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[3]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[1]&&!selectedhand[2]&&started&&!reverse){
			tra3= new TranslateAnimation(0, ((x52[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra4= new TranslateAnimation(0, ((x53[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			myhand4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			}
			
			//////////////////////////////////////////////selezioni a 3
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[2]&&!selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0,((x53[0]-x52[0])) , 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			tra2= new TranslateAnimation(0, ((x54[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			myhand1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			}
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[3]&&!selectedhand[2]&&!selectedhand[4]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0, ((x53[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			tra3= new TranslateAnimation(0, ((x54[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			myhand1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			myhand4.startAnimation(tra3);
			}
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[4]&&!selectedhand[2]&&!selectedhand[3]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0, ((x53[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[0]&&selectedhand[2]&&selectedhand[3]&&!selectedhand[1]&&!selectedhand[4]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra2= new TranslateAnimation(0,((x53[0]-x53[0])) , 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			tra3= new TranslateAnimation(0, ((x54[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			myhand1.startAnimation(tra0);
			myhand3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			}
			if(selectedhand[0]&&selectedhand[2]&&selectedhand[4]&&!selectedhand[1]&&!selectedhand[3]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0,((x53[0]-x53[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand1.startAnimation(tra0);
			myhand3.startAnimation(tra1);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[0]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[1]&&!selectedhand[2]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x52[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(100);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand1.startAnimation(tra0);
			myhand4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[1]&&selectedhand[2]&&selectedhand[3]&&!selectedhand[0]&&!selectedhand[4]&&started&&!reverse){
			tra1= new TranslateAnimation(0, ((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra2= new TranslateAnimation(0, ((x53[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			tra3= new TranslateAnimation(0, ((x54[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			myhand2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			}
			if(selectedhand[1]&&selectedhand[2]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[3]&&started&&!reverse){
			tra1= new TranslateAnimation(0, ((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra2= new TranslateAnimation(0, ((x53[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[1]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[2]&&started&&!reverse){
			tra1= new TranslateAnimation(0, ((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand2.startAnimation(tra1);
			myhand4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[2]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[0]&&!selectedhand[1]&&started&&!reverse){
			tra2= new TranslateAnimation(0, ((x52[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(100);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			myhand3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			}
			
			/////////////////////////////////////////////selezioni a 4
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[2]&&selectedhand[3]&&!selectedhand[4]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x51[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0, ((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			tra2= new TranslateAnimation(0, ((x53[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			tra3= new TranslateAnimation(0, ((x54[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(300);
			myhand1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			}
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[2]&&selectedhand[4]&&!selectedhand[3]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x51[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0, ((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			tra2= new TranslateAnimation(0, ((x53[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			myhand1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[1]&&selectedhand[2]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[0]&&started&&!reverse){
			tra1= new TranslateAnimation(0, ((x51[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra2= new TranslateAnimation(0, ((x52[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			myhand2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[0]&&selectedhand[2]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[1]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x51[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra2= new TranslateAnimation(0, ((x52[0]-x53[0])), 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			myhand1.startAnimation(tra0);
			myhand3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			}
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[3]&&selectedhand[4]&&!selectedhand[2]&&started&&!reverse){
			tra0= new TranslateAnimation(0, ((x51[0]-x51[0])), 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0, ((x52[0]-x52[0])), 0, -m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			tra3= new TranslateAnimation(0, ((x53[0]-x54[0])), 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			tra4= new TranslateAnimation(0, ((x54[0]-x55[0])), 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			myhand1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			myhand4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			}
			
			//////////////////////////////////////selezione a 5
			if(selectedhand[0]&&selectedhand[1]&&selectedhand[2]&&selectedhand[3]&&selectedhand[4]&&started&&!reverse){
			tra0= new TranslateAnimation(0, 0, 0, -m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			tra1= new TranslateAnimation(0, 0, 0, -m130);
			tra1.setDuration(1000);
			tra1.setStartOffset(100);
			tra1.setFillAfter(true);
			tra2= new TranslateAnimation(0, 0, 0, -m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			tra3= new TranslateAnimation(0, 0, 0, -m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(300);
			tra4= new TranslateAnimation(0, 0, 0, -m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(400);
			myhand1.startAnimation(tra0);
			myhand2.startAnimation(tra1);
			myhand3.startAnimation(tra2);
			myhand4.startAnimation(tra3);
			myhand5.startAnimation(tra4);
			}
			

//			if(reverse==false){
//			for(int i=0;i<5;i++){
//				if(selectedhand[i]==true){
//					myatt+=hand.get(i).getAtt();
//					mydif+=hand.get(i).getDif();
//				}
//				
//			}
//			}

		
			

		}
		public void enemyplaycard(Boolean[] enemyselectedhand,boolean reverse){

			int m130 =(getResources().getDisplayMetrics().heightPixels)/(5);

			if(reverse){
				enemyhand1.clearAnimation();
				enemyhand2.clearAnimation();
				enemyhand3.clearAnimation();
				enemyhand4.clearAnimation();
				enemyhand5.clearAnimation();
			}
			
			if(reverse==false){
				enemyhand1.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(0).getIdImg(), "drawable", getPackageName()));
				enemyhand2.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(1).getIdImg(), "drawable", getPackageName()));
				enemyhand3.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(2).getIdImg(), "drawable", getPackageName()));
				enemyhand4.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(3).getIdImg(), "drawable", getPackageName()));
				enemyhand5.setBackgroundResource(getResources().getIdentifier("c_"+enemyhand.get(4).getIdImg(), "drawable", getPackageName()));
				
			//////////////////////////selezioni a 1//////////////
			if(enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
				tra0= new TranslateAnimation(0,(x53[0]-x51[0]) , 0, m130);
				tra0.setDuration(1000);
				tra0.setFillAfter(true);
				enemyhand1.startAnimation(tra0);
				}
			if(!enemyselectedhand[0]&&enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
				tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
				tra1.setDuration(1000);
				tra1.setFillAfter(true);
				enemyhand2.startAnimation(tra1);
				}
			if(!enemyselectedhand[0]&&!enemyselectedhand[1]&&enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
				tra2= new TranslateAnimation(0,(x53[0]-x53[0]) , 0, m130);
				tra2.setDuration(1000);
				tra2.setFillAfter(true);
				enemyhand3.startAnimation(tra2);
				}
			if(!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
				tra3= new TranslateAnimation(0,(x53[0]-x54[0]) , 0, m130);
				tra3.setDuration(1000);
				tra3.setFillAfter(true);
				enemyhand4.startAnimation(tra3);
				}
			if(!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&enemyselectedhand[4]&&started){
				tra4= new TranslateAnimation(0,(x53[0]-x55[0]) , 0, m130);
				tra4.setDuration(1000);
				tra4.setFillAfter(true);
				enemyhand5.startAnimation(tra4);
				}
			/////////////////////////////////////////////////selezioni a 2
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			 tra0= new TranslateAnimation(0,(x53[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0,(x54[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setStartOffset(100);
			tra1.setFillAfter(true);
			enemyhand1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[2]&&!enemyselectedhand[1]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			 tra0= new TranslateAnimation(0,(x53[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra2= new TranslateAnimation(0,(x54[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setStartOffset(100);
			tra2.setFillAfter(true);
			enemyhand1.startAnimation(tra0);
			enemyhand3.startAnimation(tra2);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[3]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[4]&&started){
			 tra0= new TranslateAnimation(0,(x53[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra3= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			enemyhand1.startAnimation(tra0);
			enemyhand4.startAnimation(tra3);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[4]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&started){
			 tra0= new TranslateAnimation(0,(x53[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			enemyhand1.startAnimation(tra0);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[2]&&!enemyselectedhand[0]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			 tra2= new TranslateAnimation(0,(x54[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setStartOffset(100);
			tra2.setFillAfter(true);
			enemyhand2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[3]&&!enemyselectedhand[0]&&!enemyselectedhand[2]&&!enemyselectedhand[4]&&started){
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			 tra3= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			enemyhand2.startAnimation(tra1);
			enemyhand4.startAnimation(tra3);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&started){
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]), 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			enemyhand2.startAnimation(tra1);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[4]&&started){
			 tra1= new TranslateAnimation(0,(x53[0]-x53[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			 tra4= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			enemyhand3.startAnimation(tra1);
			enemyhand4.startAnimation(tra4);
			}
			if(enemyselectedhand[2]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[3]&&started){
			 tra2= new TranslateAnimation(0,(x53[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			enemyhand3.startAnimation(tra2);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&started){
			 tra3= new TranslateAnimation(0,(x53[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setStartOffset(100);
			tra4.setFillAfter(true);
			enemyhand4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			}
			
			//////////////////////////////////////////////selezioni a 3
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[2]&&!enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			 tra0= new TranslateAnimation(0, (x52[0]-x51[0]), 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			 tra2= new TranslateAnimation(0,(x54[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[3]&&!enemyselectedhand[2]&&!enemyselectedhand[4]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			 tra3= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			enemyhand4.startAnimation(tra3);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[4]&&!enemyselectedhand[2]&&!enemyselectedhand[3]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[1]&&!enemyselectedhand[4]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra2= new TranslateAnimation(0,(x53[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			 tra3= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			enemyhand3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[2]&&enemyselectedhand[4]&&!enemyselectedhand[1]&&!enemyselectedhand[3]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0,(x53[0]-x53[0]), 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			enemyhand3.startAnimation(tra1);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[1]&&!enemyselectedhand[2]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra3= new TranslateAnimation(0,(x53[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(100);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand1.startAnimation(tra0);
			enemyhand4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[0]&&!enemyselectedhand[4]&&started){
			 tra1= new TranslateAnimation(0,(x52[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			 tra2= new TranslateAnimation(0,(x53[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			 tra3= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			enemyhand2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[3]&&started){
			 tra1= new TranslateAnimation(0,(x52[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			 tra2= new TranslateAnimation(0,(x53[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[2]&&started){
			 tra1= new TranslateAnimation(0,(x52[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			 tra3= new TranslateAnimation(0,(x53[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setStartOffset(100);
			tra3.setFillAfter(true);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand2.startAnimation(tra1);
			enemyhand4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[2]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&!enemyselectedhand[1]&&started){
			 tra2= new TranslateAnimation(0,(x52[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			 tra3= new TranslateAnimation(0,(x53[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(100);
			 tra4= new TranslateAnimation(0,(x54[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(200);
			enemyhand3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			}
			
			/////////////////////////////////////////////selezioni a 4
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[3]&&!enemyselectedhand[4]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			 tra2= new TranslateAnimation(0,(x54[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			 tra3= new TranslateAnimation(0,(x55[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(300);
			enemyhand1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[4]&&!enemyselectedhand[3]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			 tra2= new TranslateAnimation(0,(x54[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			 tra4= new TranslateAnimation(0,(x55[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			enemyhand1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[0]&&started){
			 tra1= new TranslateAnimation(0,(x52[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			 tra2= new TranslateAnimation(0,(x53[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			 tra3= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			 tra4= new TranslateAnimation(0,(x55[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			enemyhand2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[2]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[1]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra2= new TranslateAnimation(0,(x53[0]-x53[0]) , 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(100);
			 tra3= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			 tra4= new TranslateAnimation(0,(x55[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			enemyhand1.startAnimation(tra0);
			enemyhand3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			}
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[3]&&enemyselectedhand[4]&&!enemyselectedhand[2]&&started){
			 tra0= new TranslateAnimation(0,(x52[0]-x51[0]) , 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0,(x53[0]-x52[0]) , 0, m130);
			tra1.setDuration(1000);
			tra1.setFillAfter(true);
			tra1.setStartOffset(100);
			 tra3= new TranslateAnimation(0,(x54[0]-x54[0]) , 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(200);
			 tra4= new TranslateAnimation(0,(x55[0]-x55[0]) , 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(300);
			enemyhand1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			enemyhand4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			}
			
			//////////////////////////////////////selezione a 5
			if(enemyselectedhand[0]&&enemyselectedhand[1]&&enemyselectedhand[2]&&enemyselectedhand[3]&&enemyselectedhand[4]&&started){
			 tra0= new TranslateAnimation(0, 0, 0, m130);
			tra0.setDuration(1000);
			tra0.setFillAfter(true);
			 tra1= new TranslateAnimation(0, 0, 0, m130);
			tra1.setDuration(1000);
			tra1.setStartOffset(100);
			tra1.setFillAfter(true);
			 tra2= new TranslateAnimation(0, 0, 0, m130);
			tra2.setDuration(1000);
			tra2.setFillAfter(true);
			tra2.setStartOffset(200);
			 tra3= new TranslateAnimation(0, 0, 0, m130);
			tra3.setDuration(1000);
			tra3.setFillAfter(true);
			tra3.setStartOffset(300);
			 tra4= new TranslateAnimation(0, 0, 0, m130);
			tra4.setDuration(1000);
			tra4.setFillAfter(true);
			tra4.setStartOffset(400);

			enemyhand1.startAnimation(tra0);
			enemyhand2.startAnimation(tra1);
			enemyhand3.startAnimation(tra2);
			enemyhand4.startAnimation(tra3);
			enemyhand5.startAnimation(tra4);
			}
			
			for(int i=0;i<5;i++){
				if(enemyselectedhand[i]==true){
					enemyatt+=enemyhand.get(i).getAtt();
					enemydif+=enemyhand.get(i).getDif();
				}
				
			}}
			
			textareaenemydif.setText(String.valueOf(enemydif));
			textareaenemyatt.setText(String.valueOf(enemyatt));

		}
		public void drawbattle(){

			textareamyatt = (TextView)findViewById(R.id.myatt);  // tv is id in XML file for TextView
			textareamyatt.setTextColor(Color.WHITE);
			textareamyatt.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			textareamydif = (TextView)findViewById(R.id.mydif);  // tv is id in XML file for TextView
			textareamydif.setTextColor(Color.WHITE);
			textareamydif.setTypeface(Typeface.SERIF, Typeface.ITALIC);	
			textareaenemyatt = (TextView)findViewById(R.id.enemyatt);  // tv is id in XML file for TextView
			textareaenemyatt.setTextColor(Color.WHITE);
			textareaenemyatt.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			
			textareaenemydif = (TextView)findViewById(R.id.enemydif);  // tv is id in XML file for TextView
			textareaenemydif.setTextColor(Color.WHITE);
			textareaenemydif.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			LinearLayout lenemy = (LinearLayout)findViewById(R.id.enemyhand);
			RelativeLayout lme = (RelativeLayout)findViewById(R.id.myhand);
			LayoutParams enemyp = lenemy.getLayoutParams();
			LayoutParams myp = lme.getLayoutParams();
			LinearLayout lplace=(LinearLayout)findViewById(R.id.myhandplaceholder);
			lplace.setGravity(Gravity.CENTER_HORIZONTAL);
			
			
			View llplace=(View)findViewById(R.id.myhandholder);

			LayoutParams holdpar = llplace.getLayoutParams();
			holdpar.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5));
			//holdpar.height=0;

			enemyp.height = (int) ((getResources().getDisplayMetrics().heightPixels)/(2.5));
			myp.height = (int) ((getResources().getDisplayMetrics().heightPixels)/(2.5));
			frame1=findViewById(R.id.framehand1);
			frame2=findViewById(R.id.framehand2);
			frame3=findViewById(R.id.framehand3);
			frame4=findViewById(R.id.framehand4);
			frame5=findViewById(R.id.framehand5);
			placehold1=findViewById(R.id.placeholder1);
			placehold2=findViewById(R.id.placeholder2);
			placehold3=findViewById(R.id.placeholder3);
			placehold4=findViewById(R.id.placeholder4);
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
		     LayoutParams h1 = placehold1.getLayoutParams();
		     LayoutParams h2 = placehold2.getLayoutParams();
		     LayoutParams h3 = placehold3.getLayoutParams();
		     LayoutParams h4 = placehold4.getLayoutParams();

		     

		     h1.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     h2.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     h3.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     h4.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     



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
		     myhand1.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(0).getIdImg(), "drawable", getPackageName()));
				myhand2.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(1).getIdImg(), "drawable", getPackageName()));
				myhand3.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(2).getIdImg(), "drawable", getPackageName()));
				myhand4.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(3).getIdImg(), "drawable", getPackageName()));
				myhand5.setBackgroundResource(getResources().getIdentifier("c_"+hand.get(4).getIdImg(), "drawable", getPackageName()));
				if(battlestart==true){
					myhand1.setBackgroundResource(getResources().getIdentifier("c_0", "drawable", getPackageName()));
					myhand2.setBackgroundResource(getResources().getIdentifier("c_0", "drawable", getPackageName()));
					myhand3.setBackgroundResource(getResources().getIdentifier("c_0", "drawable", getPackageName()));
					myhand4.setBackgroundResource(getResources().getIdentifier("c_0", "drawable", getPackageName()));
					myhand5.setBackgroundResource(getResources().getIdentifier("c_0", "drawable", getPackageName()));
				}

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

		
		}
	
		public void addListenerOnButton() {
			
			gioca.setBackgroundResource(R.drawable.gioca);
			gioca.setOnClickListener(new OnClickListener() {
				
				//boolean a= false;
				
				@Override
				public void onClick(View v) {

				     placehold1.getLocationOnScreen(x41);
				     placehold2.getLocationOnScreen(x42);
				     placehold3.getLocationOnScreen(x43);
				     placehold4.getLocationOnScreen(x44);
				     myhand1.getLocationOnScreen(x51);
				     myhand2.getLocationOnScreen(x52);
				     myhand3.getLocationOnScreen(x53);
				     myhand4.getLocationOnScreen(x54);
				     myhand5.getLocationOnScreen(x55);
						handler.removeCallbacksAndMessages(null);
						handler1.removeCallbacksAndMessages(null);

					
					createToastwait();
					for(int i=0;i<selectedhand.length;i++){
						if(selectedhand[i]==true){
							field[i]=(hand.get(i).getIdImg());
							//f++;
							//selectedhand[i]=false;
							selectedhandint[i]=1;
						}else{
							field[i]=0;
							//f++;
							selectedhandint[i]=0;

						}
						
					}


				

					
					
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
					playMyCard(false);
				       ult = new EnemyPhaseTask();
				        
				        ult.execute();

			        //attivogioca=true;
					
				
				}
					
				
			});
			

			myhand1.setOnClickListener(new OnClickListener() {	
				boolean a=false;
				public void onClick(View arg0) {
					if(selectedhand[0]==false){
						myatt+=hand.get(0).getAtt();
						mydif+=hand.get(0).getDif();
						mytypecheckadd(0);
						

						textareamyatt.setText(String.valueOf(myatt));
						textareamydif.setText(String.valueOf(mydif));
					selectedhand[0]=true;}
					else
					{
						myatt-=hand.get(0).getAtt();
						mydif-=hand.get(0).getDif();
						mytypecheckremove(0);
						textareamyatt.setText(String.valueOf(myatt));
						textareamydif.setText(String.valueOf(mydif));
						selectedhand[0]=false;}
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
						myatt+=hand.get(1).getAtt();
						mydif+=hand.get(1).getDif();
						mytypecheckadd(1);						
						textareamyatt.setText(String.valueOf(myatt));
						textareamydif.setText(String.valueOf(mydif));
					selectedhand[1]=true;}
					else
					{
						myatt-=hand.get(1).getAtt();
						mydif-=hand.get(1).getDif();
						mytypecheckremove(1);
						textareamyatt.setText(String.valueOf(myatt));
						textareamydif.setText(String.valueOf(mydif));
						selectedhand[1]=false;}

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
						myatt+=hand.get(2).getAtt();
						mydif+=hand.get(2).getDif();
						mytypecheckadd(2);						

						textareamyatt.setText(String.valueOf(myatt));
						textareamydif.setText(String.valueOf(mydif));
					selectedhand[2]=true;}
					else
					{
						myatt-=hand.get(2).getAtt();
						mydif-=hand.get(2).getDif();
						mytypecheckremove(2);						

						textareamyatt.setText(String.valueOf(myatt));
						textareamydif.setText(String.valueOf(mydif));
						selectedhand[2]=false;}

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
							myatt+=hand.get(3).getAtt();
							mydif+=hand.get(3).getDif();
							mytypecheckadd(3);						

							textareamyatt.setText(String.valueOf(myatt));
							textareamydif.setText(String.valueOf(mydif));
							selectedhand[3]=true;}
							else
							{
								myatt-=hand.get(3).getAtt();
								mydif-=hand.get(3).getDif();
								mytypecheckremove(3);						

								textareamyatt.setText(String.valueOf(myatt));
								textareamydif.setText(String.valueOf(mydif));
								selectedhand[3]=false;}
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
							myatt+=hand.get(4).getAtt();
							mydif+=hand.get(4).getDif();
							mytypecheckadd(4);						

							textareamyatt.setText(String.valueOf(myatt));
							textareamydif.setText(String.valueOf(mydif));
							selectedhand[4]=true;}
							else
							{
								myatt-=hand.get(4).getAtt();
								mydif-=hand.get(4).getDif();
								mytypecheckremove(4);						

								textareamyatt.setText(String.valueOf(myatt));
								textareamydif.setText(String.valueOf(mydif));
								selectedhand[4]=false;}
						if(a==false){	
					findViewById(R.id.framehand5).setVisibility(View.VISIBLE);
					a=true;
					}else{
						findViewById(R.id.framehand5).setVisibility(View.GONE);
						a=false;
					}}});
			
		}
		public void afkTask()  {
			
				Button gioca = (Button) findViewById(R.id.button_gioca);
				Button myhand1 = (Button) findViewById(R.id.myhand1);	 
				Button myhand2 = (Button) findViewById(R.id.myhand2);
				Button myhand3 = (Button) findViewById(R.id.myhand3);
				Button myhand4 = (Button) findViewById(R.id.myhand4);
				Button myhand5 = (Button) findViewById(R.id.myhand5);
				View	frame1=findViewById(R.id.framehand1);
				View	frame2=findViewById(R.id.framehand2);
				View	frame3=findViewById(R.id.framehand3);
				View	frame4=findViewById(R.id.framehand4);
				View	frame5=findViewById(R.id.framehand5);
				// TODO Auto-generated method stub
				for(int i=0;i<selectedhand.length;i++){
					if(selectedhand[i]==true){
						field[i]=(hand.get(i).getIdImg());
			
						selectedhandint[i]=1;
					}else{
						field[i]=0;
						
						selectedhandint[i]=0;

					}
					
				}
			
				//ult.cancel(true);
			       ult = new EnemyPhaseTask();
			        
			        ult.execute();
			      

				gioca.setBackgroundResource(R.drawable.inizia_grey);

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
				playMyCard(false);
			
		}
		
		public class waitTask extends AsyncTask<Void, Void, Boolean> {

			@Override
			protected Boolean doInBackground(Void... arg0) {
				while(waitTimer>0){
					waitTimer--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
				
				return null;
			}
			
			
			
			
			  @Override
		        protected void onPostExecute(final Boolean success) {
				  if(waitTimer==0){
				  intent = new Intent(context,ActivityAnimationBattle.class);
		    		//invio dati alla activity Battaglia
	                intent.putExtra("my1", field[0]);
	                intent.putExtra("my2", field[1]);
	                intent.putExtra("my3", field[2]);
	                intent.putExtra("my4", field[3]);
	                intent.putExtra("my5", field[4]);
	                intent.putExtra("en1", enemyhand.get(0).getIdImg());
	                intent.putExtra("en2", enemyhand.get(1).getIdImg());
	                intent.putExtra("en3", enemyhand.get(2).getIdImg());
	                intent.putExtra("en4", enemyhand.get(3).getIdImg());
	                intent.putExtra("en5", enemyhand.get(4).getIdImg());
	                intent.putExtra("myatt", myatt);
	                intent.putExtra("mydif", mydif);
	                intent.putExtra("enemyatt", enemyatt);
	                intent.putExtra("enemydif", enemydif);
					//copre carte nemico
					enemyhand1.setBackgroundResource(R.drawable.c_0);
					enemyhand2.setBackgroundResource(R.drawable.c_0);
					enemyhand3.setBackgroundResource(R.drawable.c_0);
					enemyhand4.setBackgroundResource(R.drawable.c_0);
					enemyhand5.setBackgroundResource(R.drawable.c_0);
					//start activity Battaglia
					startActivity(intent);
					//setta il bottone battaglia a visible
				  }
			  }
		}
		public class waitflipTask extends AsyncTask<Void, Void, Boolean> {

			@Override
			protected Boolean doInBackground(Void... arg0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				return null;
			}

			  @Override
		        protected void onPostExecute(final Boolean success) {
				  

//				  waitaddbuttonTask addbutton = new waitaddbuttonTask();
//				  addbutton.execute();
				  battlestart=false;
				  //drawbattle();
				  flipmycard();
				  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  addListenerOnButton();

			  }
			  
		}
		
		public class endBattle extends AsyncTask<Void, Void, Boolean> {
				int endsend=10;
			@Override
			protected Boolean doInBackground(Void... arg0) {
				while(endsend>0){
						try {
							sendEndBattle();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				endsend--;
				}
				return true;
			}

			  @Override
		        protected void onPostExecute(final Boolean success) {
				  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
			  }
			  
		}
	
	    public void sendEndBattle() throws JSONException, InterruptedException {
	        
			
        	String url = "http://shadowduel.altervista.org/app/app_end_play.php";		 
	        try {
	  			httpClient = new DefaultHttpClient();
	  			httpPost = new HttpPost(url);
	  			//inserimento dati in un array da inviare tramite Json
	  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	  		    nameValuePairs.add(new BasicNameValuePair("username", ""+username));
	  		    nameValuePairs.add(new BasicNameValuePair("password", ""+password));
	  		    nameValuePairs.add(new BasicNameValuePair("phase1", ""+phase1));
	  		    nameValuePairs.add(new BasicNameValuePair("phase2", ""+phase2));
	  		    nameValuePairs.add(new BasicNameValuePair("phase3", ""+phase3));
	  		    nameValuePairs.add(new BasicNameValuePair("newcard", ""+newcardid));
	  		    nameValuePairs.add(new BasicNameValuePair("score", ""+score));

	  		    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	  		    // Execute HTTP Post Request and receive Json
	  		    HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            jsonrat = EntityUtils.toString(httpEntity);
	 
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //Creazione JsonArray dal json
			jsArray = new JSONArray(jsonrat);
			if(jsonrat!=null)
        	fetchJSONrating(jsArray);
			


	    }
   
	    public void fetchJSONrating(JSONArray jsonArrayd){

	    
	      	   for (int i = 0; i < jsonArrayd.length(); i++) {
	      		   
	      	       try {
					rating = jsonArrayd.getJSONObject(i).getInt("rating");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      	       
	      
	      	
	}
	    }
		public class waitaddbuttonTask extends AsyncTask<Void, Void, Boolean> {

			@Override
			protected Boolean doInBackground(Void... arg0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				return null;
			}

			  @Override
		        protected void onPostExecute(final Boolean success) {
				  addListenerOnButton();

			  }
			  
		}
		public boolean isTaskCancelled(){
			if(ult.isCancelled()){
			return true;}else{
				return false;
			}
			
		}
		//////////
	    public class EnemyPhaseTask extends AsyncTask<Void, Void, Boolean> {
	    	
	    	
	        @Override
	        protected Boolean doInBackground(Void... params) {
	        	//timer viene decrementato ad ogni tentativo di connessione, se arriva a 0 e la connessione
	        	//non ha restituito valori accettabili viene generato un timeout e "eventualmente" inviato un
	        	//array di carte vuoto in modo da far perdere il match al giocatore che non è riuscito
	        	//ad inviare i dati o non li ha inviati di sua spontanea volontà
	        	json = null;

	        	///TIMER VA LEVATO
//	        	while(enemyselectedhand[4]==null&&timer>0){
//	            try { sendLoginAndGetJsonFromUrl();   } catch (JSONException e) {e.printStackTrace();
//					} catch (InterruptedException e) {e.printStackTrace();
//				}
//	            timer--;
//	            }
	      		enemyhand=new ArrayList<Card>();
	        	while(enemyhand.size()<4&&crashTimer!=0){
	                if (isTaskCancelled()){
	                    return false;
	                 }
		            try { sendLoginAndGetJsonFromUrl();   } catch (JSONException e) {e.printStackTrace();
						} catch (InterruptedException e) {e.printStackTrace();
					
	            	}
	            	
	            	
		            }
	        	return true;
	        	}
	            	    
	        @Override
	        protected void onPostExecute(final Boolean success) {
	        	if(success==true){
	        	if(json!=null&&crashTimer>0){
	    			enemytypecheck();

	        		enemyplaycard(enemyselectedhand,false);
				       waittask = new waitTask();
				        waittask.execute();	
				        json=null;
	        		}
	   
	        	if(crashTimer==0){
	        		enemyNoCard();	
	        		crashTimer=65;
	        	}
	        	
	        }
	        }
	    }  
   
	    public void sendLoginAndGetJsonFromUrl() throws JSONException, InterruptedException {
	        
			
        	String url = "http://shadowduel.altervista.org/app/app_game_player_hand_field.php";		 
	        try {
	  			httpClient = new DefaultHttpClient();
	  			httpPost = new HttpPost(url);
	  			//inserimento dati in un array da inviare tramite Json
	  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	  		    nameValuePairs.add(new BasicNameValuePair("username", ""+username));
	  		    nameValuePairs.add(new BasicNameValuePair("password", ""+password));
	  		    nameValuePairs.add(new BasicNameValuePair("phase", ""+phase));
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

	  		    // Execute HTTP Post Request and receive Json
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
	        //Creazione JsonArray dal json
			jsArray = new JSONArray(json);
			if(json!=null)
        	fetchJSON(jsArray);
			


	    }
	    public void enemyNoCard(){
  

	      	   for (int i = 0; i < 5; i++) { 	      	
	      	       enemyhand.add(new Card(0, 0, "Dorso Carta", 0, 0,"dorso"));
	      	       enemyselectedhand[i] = false;
	      	       }
     		enemyplaycard(enemyselectedhand,false);
			       waittask = new waitTask();
			        
			        waittask.execute();
	    }
	    public void fetchJSON(JSONArray jsonArrayd){
int bool=1;
			
	      	try {
	      		 enemyhand=new ArrayList<Card>();

	      	   for (int i = 0; i < jsonArrayd.length(); i++) {
	
	      	
	      	       enemyhand.add(new Card(jsonArrayd.getJSONObject(i).getInt("c_"+(i+1)) , 
	      	    		   jsonArrayd.getJSONObject(i).getInt("c_"+(i+1)) , 
	      	    		   jsonArrayd.getJSONObject(i).getString("name") , 
	      	    		   jsonArrayd.getJSONObject(i).getInt("attack"),
	      	    		   jsonArrayd.getJSONObject(i).getInt("defense"),
	      	    		 jsonArrayd.getJSONObject(i).getString("element")
	      	    		   ));
	      	       bool = jsonArrayd.getJSONObject(i).getInt("hand"+(i+1));
	      	       if(bool==0){
	      	       enemyselectedhand[i] = false;}
	      	       else{
	      	    	 enemyselectedhand[i] = true;
	      	       }
	      	}
	      	
	       		//Toast.makeText(context, ""+intero,Toast.LENGTH_LONG).show();

	      	} catch (Exception e) {
	      	     e.printStackTrace();
	      	}
	}
	   
	    
	    public void fetchJSONuser(JSONArray jsonArrayd){

	      	try {
	      	   for (int i = 0; i < jsonArrayd.length(); i++) {
	      		   
	      	       deck[i] = jsonArrayd.getJSONObject(i).getInt("c_"+(i+1));
	      	       Deck.add(new Card(deck[i], deck[i],
	      	    		   jsonArrayd.getJSONObject(i).getString("name") , 
	      	    		   jsonArrayd.getJSONObject(i).getInt("attack"),
	      	    		   jsonArrayd.getJSONObject(i).getInt("defense"),
	      	    		 jsonArrayd.getJSONObject(i).getString("element")
	      	    		   ));
	      	    }
	      	   currDeck=Deck;
	   		LENGHT=deck.length;
			currlenght=LENGHT;
			hand=pickRandomHand();
	      	} catch (Exception e) {
	      	     e.printStackTrace();
	      	}
	      	
	}
	    public void fetchJSONCatalogo(JSONArray jsonArrayc){

	      	try {
	      	   for (int i = 0; i < jsonArrayc.length(); i++) {
	      		   
	      	       Catalogo.add(new Card(jsonArrayc.getJSONObject(i).getInt("c_"+(i+1)), 
	      	    		   jsonArrayc.getJSONObject(i).getInt("c_"+(i+1)),
	      	    		   jsonArrayc.getJSONObject(i).getString("name") , 
	      	    		   jsonArrayc.getJSONObject(i).getInt("attack"),
	      	    		   jsonArrayc.getJSONObject(i).getInt("defense"),
	      	    		 jsonArrayc.getJSONObject(i).getString("element")
	      	    		   ));
	      	    }
	      
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
		
		public ArrayList<Card>  pickRandomHand(){
			int c;
			rand= new Random();
			currlenght=currDeck.size();
			if(currlenght>4){
			for(int i=0;i<5;i++){
				currlenght=currDeck.size();
				c=rand.nextInt(currlenght);
				hand.add(currDeck.remove(c));

			}
			return hand;
			}else{
				if(currlenght>0){
					for(int i=0;i<currlenght;i++){
						currlenght=currDeck.size();
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
		private void createToastfase1(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_fase1,(ViewGroup) findViewById(R.id.relativeLayout1));
		        toastfase1 = new Toast(this);
		        toastfase1.setView(view);
		        toastfase1.setDuration(5);
		        toastfase1.setGravity(Gravity.CENTER, 0, 0);
		        toastfase1.show();
		}
		private void createToastfase2(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_fase2,(ViewGroup) findViewById(R.id.relativeLayout1));
		        Toast toastfase2 = new Toast(this);
		        toastfase2.setView(view);
		        toastfase2.setDuration(5);
		        toastfase2.setGravity(Gravity.CENTER, 0, 0);
		        toastfase2.show();
		}
		private void createToastsconfitta(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_sconfitta,(ViewGroup) findViewById(R.id.relativeLayout1));
		        Toast toastfase2 = new Toast(this);
		        toastfase2.setView(view);
		        toastfase2.setDuration(10);
		        toastfase2.setGravity(Gravity.CENTER, 0, 0);
		        toastfase2.show();
		}
		private void createToastvittoria(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_vittoria,(ViewGroup) findViewById(R.id.relativeLayout1));
		        Toast toastfase2 = new Toast(this);
		        toastfase2.setView(view);
		        toastfase2.setDuration(10);
		        toastfase2.setGravity(Gravity.CENTER, 0, 0);
		        toastfase2.show();
		}
		private void createToastpareggio(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_pareggio,(ViewGroup) findViewById(R.id.relativeLayout1));
		        Toast toastfase2 = new Toast(this);
		        toastfase2.setView(view);
		        toastfase2.setDuration(10);
		        toastfase2.setGravity(Gravity.CENTER, 0, 0);
		        toastfase2.show();
		}
		private void createToastfase3(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_fase3,(ViewGroup) findViewById(R.id.relativeLayout1));
		        toastfase3 = new Toast(this);
		        toastfase3.setView(view);
		        toastfase3.setDuration(5);
		        toastfase3.setGravity(Gravity.CENTER, 0, 0);
		        toastfase3.show();
		}

		private void createToastwait(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_attendi,(ViewGroup) findViewById(R.id.relativeLayout1));
		       	toastattendi = new Toast(this);
		       	toastattendi.setView(view);
		       	toastattendi.setDuration(5);
		       	toastattendi.setGravity(Gravity.CENTER, 0, 0);
		       	toastattendi.show();
		}
		private void createToastscegli(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_scegli,(ViewGroup) findViewById(R.id.relativeLayout1));
		        toastscegli = new Toast(this);
		        toastscegli.setView(view);
		        toastscegli.setDuration(5);
		        toastscegli.setGravity(Gravity.CENTER, 0, 0);
		        toastscegli.show();
		}
		public void interruptToast(){
			toastfase1.cancel();
			toastfase2.cancel();
			toastfase3.cancel();
			toastscegli.cancel();
			toastattendi.cancel();

		}
		@Override
		public void onDestroy(){
			handler.removeCallbacksAndMessages(null);
			handler1.removeCallbacksAndMessages(null);
			waittask.cancel(true);
			//ult.cancel(true);
			interruptToast();
			getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

			super.onDestroy();

		}
		public void resetData(){
			selectedhand[0]=false;
			selectedhand[1]=false;
			selectedhand[2]=false;
			selectedhand[3]=false;
			selectedhand[4]=false;	
			enemyselectedhand[0]=null;
			enemyselectedhand[1]=null;
			enemyselectedhand[2]=null;
			enemyselectedhand[3]=null;
			enemyselectedhand[4]=null;
			myw=0;
			myf=0;
			mye=0;
			mya=0;
			mys=0;
			myl=0;
			enemyw=0;
			enemyf=0;
			enemye=0;
			enemya=0;
			enemys=0;
			enemyl=0;

		}
		public void canceltask(){
			handler.removeCallbacksAndMessages(null);
			handler1.removeCallbacksAndMessages(null);

			waittask.cancel(true);
			ult.cancel(true);
		}
		public void repaint(boolean myhand,boolean enemyhand){
	   		enemyplaycard(enemyselectedhand,enemyhand);
				playMyCard(myhand);
				drawbattle();
				addListenerOnButton();
		}
		@Override
		public void onPause(){
			super.onPause();
			canceltask();
			repaint(true,true);
			interruptToast();
			resetData();
			

		}
		public void flipmycard(){
            myanimationflip1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
            myanimationflip1.setAnimationListener(new FlipAnimation(this, myhand1, hand.get(0).getIdImg()));
            myanimationflip1.setDuration(500);
            
            myanimationflip2 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
            myanimationflip2.setAnimationListener(new FlipAnimation(this, myhand2, hand.get(1).getIdImg()));
            myanimationflip2.setDuration(500);
            myanimationflip2.setStartOffset(400);
            
            myanimationflip3 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
            myanimationflip3.setAnimationListener(new FlipAnimation(this, myhand3, hand.get(2).getIdImg()));
            myanimationflip3.setDuration(500);
            myanimationflip3.setStartOffset(800);
            
            myanimationflip4 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
            myanimationflip4.setAnimationListener(new FlipAnimation(this, myhand4, hand.get(3).getIdImg()));
            myanimationflip4.setDuration(500);
            myanimationflip4.setStartOffset(1200);
            
            myanimationflip5 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
            myanimationflip5.setAnimationListener(new FlipAnimation(this, myhand5, hand.get(4).getIdImg()));
            myanimationflip5.setDuration(600);
            myanimationflip5.setStartOffset(1600);
            
            myhand1.startAnimation(myanimationflip1);
            myhand2.startAnimation(myanimationflip2);
            myhand3.startAnimation(myanimationflip3);
            myhand4.startAnimation(myanimationflip4);
            myhand5.startAnimation(myanimationflip5);
		}
		@Override
		public void onResume() {
			super.onResume();
		    textareamyatt= new TextView(this);
		    textareamydif=new TextView(this);
		    textareaenemyatt=new TextView(this);
		   textareaenemydif=new TextView(this);
		   textareamyatt = (TextView)findViewById(R.id.myatt);  // tv is id in XML file for TextView
			textareamyatt.setTextColor(Color.WHITE);
			textareamyatt.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			textareamydif = (TextView)findViewById(R.id.mydif);  // tv is id in XML file for TextView
			textareamydif.setTextColor(Color.WHITE);
			textareamydif.setTypeface(Typeface.SERIF, Typeface.ITALIC);	
			textareaenemyatt = (TextView)findViewById(R.id.enemyatt);  // tv is id in XML file for TextView
			textareaenemyatt.setTextColor(Color.WHITE);
			textareaenemyatt.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			
			textareaenemydif = (TextView)findViewById(R.id.enemydif);  // tv is id in XML file for TextView
			textareaenemydif.setTextColor(Color.WHITE);
			textareaenemydif.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			textareaenemydif.setText("0");
			textareaenemyatt.setText("0");
			textareamydif.setText("0");
			textareamyatt.setText("0");
			//nemico vince il turno
			if((enemydif-myatt)>(mydif-enemyatt)&&phase==1){
				findViewById(R.id.enemystar1).setBackgroundResource(R.drawable.gold_star);
				phase1=0;
				enemyscore++;
			}
			//io vinco il turno
			if((enemydif-myatt)<(mydif-enemyatt)&&phase==1){
				findViewById(R.id.mystar1).setBackgroundResource(R.drawable.gold_star);
				phase1=1;

				myscore++;
				
			}
			//pareggio
			if((enemydif-myatt)==(mydif-enemyatt)&&phase==1){
				findViewById(R.id.enemystar1).setBackgroundResource(R.drawable.gold_star);
				findViewById(R.id.mystar1).setBackgroundResource(R.drawable.gold_star);
				phase1=2;
				myscore++;
				enemyscore++;
			}
			if((enemydif-myatt)>(mydif-enemyatt)&&phase==2){
				if(enemyscore==1){
				findViewById(R.id.enemystar2).setBackgroundResource(R.drawable.gold_star);}
				if(enemyscore==0){
					findViewById(R.id.enemystar1).setBackgroundResource(R.drawable.gold_star);
				}
				phase2=0;
				enemyscore++;
			}
			//io vinco il turno
			if((enemydif-myatt)<(mydif-enemyatt)&&phase==2){
				if(myscore==1){
				findViewById(R.id.mystar2).setBackgroundResource(R.drawable.gold_star);}
				if(myscore==0){
					findViewById(R.id.mystar1).setBackgroundResource(R.drawable.gold_star);
				}
				phase2=1;
				myscore++;
				
			}
			//pareggio
			if((enemydif-myatt)==(mydif-enemyatt)&&phase==2){

				if(myscore==0&&enemyscore==1){
					findViewById(R.id.mystar1).setBackgroundResource(R.drawable.gold_star);
					findViewById(R.id.enemystar2).setBackgroundResource(R.drawable.gold_star);
				}
				if(myscore==1&&enemyscore==0){
					findViewById(R.id.mystar2).setBackgroundResource(R.drawable.gold_star);
					findViewById(R.id.enemystar1).setBackgroundResource(R.drawable.gold_star);
				}
				if(myscore==1&&enemyscore==1){
					findViewById(R.id.mystar2).setBackgroundResource(R.drawable.gold_star);
					findViewById(R.id.enemystar2).setBackgroundResource(R.drawable.gold_star);
				}
				phase2=2;
				myscore++;
				enemyscore++;
			}
			if((enemydif-myatt)>(mydif-enemyatt)&&phase==3){
				enemyscore++;
				phase3=0;
				findViewById(R.id.enemystar2).setBackgroundResource(R.drawable.gold_star);
				
				
			}
			//io vinco il turno
			if((enemydif-myatt)<(mydif-enemyatt)&&phase==3){
				phase3=1;
				myscore++;
				
				findViewById(R.id.mystar2).setBackgroundResource(R.drawable.gold_star);

				
				
			}
			//pareggio
			if((enemydif-myatt)==(mydif-enemyatt)&&phase==3){
				myscore++;
				enemyscore++;
				phase3=2;
				//creatost pareggio
				findViewById(R.id.enemystar2).setBackgroundResource(R.drawable.gold_star);
				findViewById(R.id.mystar2).setBackgroundResource(R.drawable.gold_star);

			}

			
			if(myscore!=2&&enemyscore!=2){
				crashTimer=65;
			flip = new waitflipTask();
			flip.execute();
			waitTimer=3;
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					afkTask();
					
				}
			}, 60000);
			handler1.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					ult.cancel(true);
				
					crashTimer=0;
					ult= new EnemyPhaseTask();
					ult.execute();
					
				}
			}, 65000);
			

			}

			if(phase>0){
			myatthistoy[phase-1]=myatt;
			mydifhistoy[phase-1]=mydif;
			enemyatthistoy[phase-1]=enemyatt;
			enemydifhistoy[phase-1]=enemydif;
			}

			if(phase==1){
				createToastfase2();}
			if(phase==2){
				createToastfase3();}
		
				
			
					
				if(myscore==2&&enemyscore<2){
					score=1;
					gameData(score);

					flip.cancel(true);
					canceltask();
					createToastvittoria();
				}
				
				if(myscore<2&&enemyscore==2){
					score=0;
					gameData(score);

					flip.cancel(true);
					canceltask();
					createToastsconfitta();
				}
				
				if(myscore==2&&enemyscore==2){
					score=2;
					gameData(score);

					flip.cancel(true);
					canceltask();
					createToastpareggio();
				}


				myatt=0;
				mydif=0;
				enemyatt=0;
				enemydif=0;
				textareaenemydif.setText(String.valueOf(enemydif));
				textareaenemyatt.setText(String.valueOf(enemydif));
				textareamydif.setText(String.valueOf(enemydif));
				textareamyatt.setText(String.valueOf(enemydif));
				phase++;
				drawbattle();

		}
		private Card getWinCard(){
			Card card;
			int catlenght=Catalogo.size();
			int c;
		
			rand= new Random();
			c=rand.nextInt(catlenght);
			card=Catalogo.get(c);
			return card;
			
		
		
		}			
		private void gameData(int result){
			if(score==1||score==2){
				newcard= getWinCard();
				newcardid=newcard.getId();
			}else{
				newcardid=0;
			}

			endbattle.execute();

		}
	
		
		@Override
		public void onBackPressed() {
		    new AlertDialog.Builder(this)
		        .setIcon(android.R.drawable.ic_dialog_alert)
		        .setTitle("Uscita")
		        .setInverseBackgroundForced(true)
		        .setMessage("Sei sicuro di voler uscire?\nUscendo perderai aumaticamente la partita")
		        .setPositiveButton("Si", new DialogInterface.OnClickListener()
		    {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		            phase1=0;
		            phase2=0;
		            phase3=0;
		            score=0;
		            newcardid=0;
					endbattle.execute();
					
					
					//finish();  
		        }

		    })
		    .setNegativeButton("No", null)
		    .show();
		}

		
}