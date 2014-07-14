package com.example.shadowduel;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

public class ActivityAnimationBattle extends Activity {
	View v;
	RotateAnimation animation;
	AnimationSet set = new AnimationSet(true);
	View my1,my2,my3,my4,my5,en1,en2,en3,en4,en5;
	 private int[] x51=new int[2],x52=new int[2],x53=new int[2],x54=new int[2],x55=new int[2];
	 private int[] y51=new int[2],y52=new int[2],y53=new int[2],y54=new int[2],y55=new int[2];
	 private int[] z51=new int[2],z52=new int[2],z53=new int[2],z54=new int[2],z55=new int[2];
	 AnimationDrawable animationbolt= null;
	 int ymov=0;
	 private int enheight=0;
	Integer[] mycard={null,null,null,null,null};
	Integer[] enemycard={null,null,null,null,null};
	TranslateAnimation tra = null;
	TranslateAnimation trareverse0 = null,trareverse1 = null,trareverse2 = null,trareverse3 = null,trareverse4 = null;
	TranslateAnimation tra0 = null;
	TranslateAnimation tra1 = null;
	TranslateAnimation tra2 = null;
	TranslateAnimation tra3 = null;
	TranslateAnimation tra4 = null;
	int cardl=0;
	int mycardid=0;
	int enemycardid=0;
	int mycardnum=0;
	int enemycardnum=0;
	int j=0;
	private Handler handler= new Handler();
	private Handler handler1= new Handler();

	private Handler handler2 = new Handler();
	private Handler handler3 = new Handler();
	private Handler handler4 = new Handler();
	private int myatt=0,mydif=0,enemyatt=0,enemydif=0;
	private int wingame;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_animation_battle);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				for(int i=0;i<5;i++){
					mycardid=extras.getInt("my"+(i+1));
				
			     if(mycardid>0){
			    	 
			    	 mycard[j]=mycardid;
			    	 j++;
			     }
					}
				j=0;
				for(int i=0;i<5;i++){
					enemycardid=extras.getInt("en"+(i+1));
				
			     if(enemycardid>0){
			    	 
			    	 enemycard[j]=enemycardid;
			    	 j++;
			     }
					}
				myatt=extras.getInt("myatt");
				mydif=extras.getInt("mydif");
				enemyatt=extras.getInt("enemyatt");
				enemydif=extras.getInt("enemydif");

			}
			
//		     LayoutParams myholdbolt = findViewById(R.id.myholderbolt).getLayoutParams();
//		     myholdbolt.height=(int) ((getResources().getDisplayMetrics().heightPixels)/(5.3));
//			
			
			if((enemydif-myatt)>(mydif-enemyatt)){
				wingame=0;
			}
			if((enemydif-myatt)<(mydif-enemyatt)){
				wingame=1;
			}
			if((enemydif-myatt)==(mydif-enemyatt)){
				wingame=2;
			}
			
			for(int i=0;i<mycard.length;i++){
				if(mycard[i]!=null){
					mycardnum++;
				}				
			}
			for(int i=0;i<enemycard.length;i++){
				if(enemycard[i]!=null){
					enemycardnum++;
				}				
			}
		     LayoutParams c1 = findViewById(R.id.placeholder12).getLayoutParams();
		     LayoutParams c2 = findViewById(R.id.placeholder22).getLayoutParams();
		     LayoutParams c3 = findViewById(R.id.placeholder32).getLayoutParams();
		     LayoutParams c4 = findViewById(R.id.placeholder42).getLayoutParams();
		     LayoutParams c5 = findViewById(R.id.placeholder52).getLayoutParams();

	

		     c1.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     c2.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     c3.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     c4.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
		     c5.width=(int) ((getResources().getDisplayMetrics().widthPixels)/(5.5));
			
			my1= (View) findViewById(R.id.mycard1);
			my2= (View) findViewById(R.id.mycard2);
			my3= (View) findViewById(R.id.mycard3);
			my4= (View) findViewById(R.id.mycard4);
			my5= (View) findViewById(R.id.mycard5);
			en1= (View) findViewById(R.id.enemycard1);
			en2= (View) findViewById(R.id.enemycard2);
			en3= (View) findViewById(R.id.enemycard3);
			en4= (View) findViewById(R.id.enemycard4);
			en5= (View) findViewById(R.id.enemycard5);


			LayoutParams a1 = my1.getLayoutParams();
		     LayoutParams a2 = my2.getLayoutParams();
		     LayoutParams a3 = my3.getLayoutParams();
		     LayoutParams a4 = my4.getLayoutParams();
		     LayoutParams a5 = my5.getLayoutParams();

	
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


		     LayoutParams b1 = en1.getLayoutParams();
		     LayoutParams b2 = en2.getLayoutParams();
		     LayoutParams b3 = en3.getLayoutParams();
		     LayoutParams b4 = en4.getLayoutParams();
		     LayoutParams b5 = en5.getLayoutParams();

		     
	
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

		     
		     

		    
			
			handler.postDelayed(new Runnable(){

				@Override
				public void run() {

					handler2.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							
						     my1.getLocationInWindow(y51);
						     my2.getLocationInWindow(y52);
						     my3.getLocationInWindow(y53);
						     my4.getLocationInWindow(y54);
						     my5.getLocationInWindow(y55);
						     en1.getLocationInWindow(z51);
						     en2.getLocationInWindow(z52);
						     en3.getLocationInWindow(z53);
						     en4.getLocationInWindow(z54);
						     en5.getLocationInWindow(z55);
						     LayoutParams par=en1.getLayoutParams();
						     enheight=par.height;
						     ymov=(((getResources().getDisplayMetrics().heightPixels)))-((int)((getResources().getDisplayMetrics().heightPixels)/(4))*3);
					     findViewById(R.id.placeholder12).getLocationInWindow(x51);
					     findViewById(R.id.placeholder22).getLocationInWindow(x52);
					     findViewById(R.id.placeholder32).getLocationInWindow(x53);
					     findViewById(R.id.placeholder42).getLocationInWindow(x54);
					     findViewById(R.id.placeholder52).getLocationInWindow(x55);
					 	movemycard();
					     handler3.postDelayed(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
							     moveenemycard();
							     handler4.postDelayed(new Runnable() {
										
										@Override
										public void run() {
											// TODO Auto-generated method stub
										     handler.removeCallbacksAndMessages(null);
										     handler2.removeCallbacksAndMessages(null);
										     handler3.removeCallbacksAndMessages(null);
										     handler4.removeCallbacksAndMessages(null);
										     finish();

										     

										}
									}, 5000);

							}
						}, 3000);
						
						}
					}, 100);
				} 
		    	 
		     }, 200);
			drawcard();
	    }
		
		private void createToastsconfitta(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_fase_sconfitta,(ViewGroup) findViewById(R.id.relativeLayout1));
		        Toast toastfase2 = new Toast(this);
		        toastfase2.setView(view);
		        toastfase2.setDuration(10);
		        toastfase2.setGravity(Gravity.CENTER, 0, 0);
		        toastfase2.show();
		}
		private void createToastvittoria(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_fase_vittoria,(ViewGroup) findViewById(R.id.relativeLayout1));
		        Toast toastfase2 = new Toast(this);
		        toastfase2.setView(view);
		        toastfase2.setDuration(10);
		        toastfase2.setGravity(Gravity.CENTER, 0, 0);
		        toastfase2.show();
		}
		private void createToastpareggio(){
		       LayoutInflater inflater = getLayoutInflater();
		        View view = inflater.inflate(R.anim.toast_fase_pareggio,(ViewGroup) findViewById(R.id.relativeLayout1));
		        Toast toastfase2 = new Toast(this);
		        toastfase2.setView(view);
		        toastfase2.setDuration(10);
		        toastfase2.setGravity(Gravity.CENTER, 0, 0);
		        toastfase2.show();
		}
		public void drawcard(){
			 
		     if(mycardnum==1){
		    	 my1.setBackgroundResource(getResources().getIdentifier("c_"+mycard[0], "drawable", getPackageName()));
		    	 my1.setVisibility(View.VISIBLE);

		     }
		     if(mycardnum==2){

		    	 my1.setBackgroundResource(getResources().getIdentifier("c_"+mycard[0], "drawable", getPackageName()));
		    	 my2.setBackgroundResource(getResources().getIdentifier("c_"+mycard[1], "drawable", getPackageName()));

		    	 my1.setVisibility(View.VISIBLE);
		    	 my2.setVisibility(View.VISIBLE);
			

		     }
		     if(mycardnum==3){

		    	 my1.setBackgroundResource(getResources().getIdentifier("c_"+mycard[0], "drawable", getPackageName()));
		    	 my2.setBackgroundResource(getResources().getIdentifier("c_"+mycard[1], "drawable", getPackageName()));
		    	 my3.setBackgroundResource(getResources().getIdentifier("c_"+mycard[2], "drawable", getPackageName()));
		    	 my1.setVisibility(View.VISIBLE);
		    	 my2.setVisibility(View.VISIBLE);
		    	 my3.setVisibility(View.VISIBLE);
			   


		     }
		     if(mycardnum==4){

		    	 my1.setBackgroundResource(getResources().getIdentifier("c_"+mycard[0], "drawable", getPackageName()));
		    	 my2.setBackgroundResource(getResources().getIdentifier("c_"+mycard[1], "drawable", getPackageName()));
		    	 my3.setBackgroundResource(getResources().getIdentifier("c_"+mycard[2], "drawable", getPackageName()));
		    	 my4.setBackgroundResource(getResources().getIdentifier("c_"+mycard[3], "drawable", getPackageName()));
		    	 my1.setVisibility(View.VISIBLE);
		    	 my2.setVisibility(View.VISIBLE);
		    	 my3.setVisibility(View.VISIBLE);
		    	 my4.setVisibility(View.VISIBLE);
		
		     }
		     if(mycardnum==5){

		    	 my1.setBackgroundResource(getResources().getIdentifier("c_"+mycard[0], "drawable", getPackageName()));
		    	 my2.setBackgroundResource(getResources().getIdentifier("c_"+mycard[1], "drawable", getPackageName()));
		    	 my3.setBackgroundResource(getResources().getIdentifier("c_"+mycard[2], "drawable", getPackageName()));
		    	 my4.setBackgroundResource(getResources().getIdentifier("c_"+mycard[3], "drawable", getPackageName()));
		    	 my5.setBackgroundResource(getResources().getIdentifier("c_"+mycard[4], "drawable", getPackageName()));
		    	 my1.setVisibility(View.VISIBLE);
		    	 my2.setVisibility(View.VISIBLE);
		    	 my3.setVisibility(View.VISIBLE);
		    	 my4.setVisibility(View.VISIBLE);
		    	 my5.setVisibility(View.VISIBLE);

		     }
		     
		     
		     if(enemycardnum==1){
		    	 en1.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[0], "drawable", getPackageName()));
		    	 en1.setVisibility(View.VISIBLE);

		     }
		     if(enemycardnum==2){

		    	 en1.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[0], "drawable", getPackageName()));
		    	 en2.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[1], "drawable", getPackageName()));

		    	 en1.setVisibility(View.VISIBLE);
		    	 en2.setVisibility(View.VISIBLE);
	

		     }
		     if(enemycardnum==3){

		    	 en1.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[0], "drawable", getPackageName()));
		    	 en2.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[1], "drawable", getPackageName()));
		    	 en3.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[2], "drawable", getPackageName()));
		    	 en1.setVisibility(View.VISIBLE);
		    	 en2.setVisibility(View.VISIBLE);
		    	 en3.setVisibility(View.VISIBLE);
	


		     }
		     if(enemycardnum==4){

		    	 en1.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[0], "drawable", getPackageName()));
		    	 en2.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[1], "drawable", getPackageName()));
		    	 en3.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[2], "drawable", getPackageName()));
		    	 en4.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[3], "drawable", getPackageName()));
		    	 en1.setVisibility(View.VISIBLE);
		    	 en2.setVisibility(View.VISIBLE);
		    	 en3.setVisibility(View.VISIBLE);
		    	 en4.setVisibility(View.VISIBLE);

		     }
		     if(enemycardnum==5){

		    	 en1.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[0], "drawable", getPackageName()));
		    	 en2.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[1], "drawable", getPackageName()));
		    	 en3.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[2], "drawable", getPackageName()));
		    	 en4.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[3], "drawable", getPackageName()));
		    	 en5.setBackgroundResource(getResources().getIdentifier("c_"+enemycard[4], "drawable", getPackageName()));

		    	 en1.setVisibility(View.VISIBLE);
		    	 en2.setVisibility(View.VISIBLE);
		    	 en3.setVisibility(View.VISIBLE);
		    	 en4.setVisibility(View.VISIBLE);
		    	 en5.setVisibility(View.VISIBLE);
	

		     }
		    // Handler h = new Handler();
//		     h.postDelayed(new Runnable(){
//
//				@Override
//				public void run() {
//
//					handler.postDelayed(new Runnable() {
//						
//						@Override
//						public void run() {
//							
//						     my1.getLocationInWindow(y51);
//						     my2.getLocationInWindow(y52);
//						     my3.getLocationInWindow(y53);
//						     my4.getLocationInWindow(y54);
//						     my5.getLocationInWindow(y55);
//						     en1.getLocationInWindow(z51);
//						     en2.getLocationInWindow(z52);
//						     en3.getLocationInWindow(z53);
//						     en4.getLocationInWindow(z54);
//						     en5.getLocationInWindow(z55);
//					     findViewById(R.id.placeholder12).getLocationInWindow(x51);
//					     findViewById(R.id.placeholder22).getLocationInWindow(x52);
//					     findViewById(R.id.placeholder32).getLocationInWindow(x53);
//					     findViewById(R.id.placeholder42).getLocationInWindow(x54);
//					     findViewById(R.id.placeholder52).getLocationInWindow(x55);
//					 	movemycard();
//					     handler2.postDelayed(new Runnable() {
//							
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//							     moveenemycard();
//
//							}
//						}, 3000);
//						
//						}
//					}, 100);
//				}
//		    	 
//		     }, 200);



		     

	
			
				
		     
		     
		}
		public void movemycard(){
			handler1.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					if(wingame==2){
						createToastpareggio();
					}
					if(wingame==1){
						createToastvittoria();
					}
					if(wingame==0){
						createToastsconfitta();
					}
				}
			}, 3000);
						if(mycardnum==1){
					tra0= new TranslateAnimation(0,0 , 0, -ymov);
					tra0.setDuration(200);
					tra0.setFillAfter(true);
					trareverse0 = new TranslateAnimation(0, 0, 0, ymov);
					trareverse0.setDuration(200);
					trareverse0.setFillAfter(true);
					trareverse0.setStartOffset(250);
					AnimationSet set0 = new AnimationSet(false);
					set0.addAnimation(tra0);
					set0.addAnimation(trareverse0);
					my1.startAnimation(set0);

		    	 

		     }
		     if(mycardnum==2){
					tra0= new TranslateAnimation(0,((x53[0]-y51[0])) , 0, -ymov);
					tra0.setDuration(200);
					tra0.setFillAfter(true);
					trareverse0 = new TranslateAnimation(0,-(x53[0]-y51[0]), 0, ymov);
					trareverse0.setDuration(200);
					trareverse0.setFillAfter(true);
					trareverse0.setStartOffset(250);
					AnimationSet set0 = new AnimationSet(false);
					set0.addAnimation(tra0);
					set0.addAnimation(trareverse0);
					tra1= new TranslateAnimation(0,((x53[0]-y52[0])) , 0, -ymov);
					tra1.setDuration(200);
					tra1.setFillAfter(true);
					tra1.setStartOffset(500);
					trareverse1 = new TranslateAnimation(0, -(x53[0]-y52[0]), 0, ymov);
					trareverse1.setDuration(200);
					trareverse1.setFillAfter(true);
					trareverse1.setStartOffset(750);
					AnimationSet set1 = new AnimationSet(false);
					set1.addAnimation(tra1);
					set1.addAnimation(trareverse1);
		    	 my1.startAnimation(set0);
		    	 my2.startAnimation(set1);
		    	 
		     }
		     if(mycardnum==3){
					tra0= new TranslateAnimation(0,((x53[0]-y51[0])) , 0, -ymov);
					tra0.setDuration(200);
					tra0.setFillAfter(true);
					trareverse0 = new TranslateAnimation(0, -(x53[0]-y51[0]), 0, ymov);
					trareverse0.setDuration(200);
					trareverse0.setFillAfter(true);
					trareverse0.setStartOffset(250);
					AnimationSet set0 = new AnimationSet(false);
					set0.addAnimation(tra0);
					set0.addAnimation(trareverse0);
					tra1= new TranslateAnimation(0,((x53[0]-y52[0])) , 0, -ymov);
					tra1.setDuration(200);
					tra1.setFillAfter(true);
					tra1.setStartOffset(500);
					trareverse1 = new TranslateAnimation(0, -(x53[0]-y52[0]), 0, ymov);
					trareverse1.setDuration(200);
					trareverse1.setFillAfter(true);
					trareverse1.setStartOffset(750);
					AnimationSet set1 = new AnimationSet(false);
					set1.addAnimation(tra1);
					set1.addAnimation(trareverse1);
					tra2= new TranslateAnimation(0,((x53[0]-y53[0])) , 0, -ymov);
					tra2.setDuration(200);
					tra2.setFillAfter(true);
					tra2.setStartOffset(950);
					trareverse2 = new TranslateAnimation(0,-(x53[0]-y53[0]), 0, ymov);
					trareverse2.setDuration(200);
					trareverse2.setFillAfter(true);
					trareverse2.setStartOffset(1150);
					AnimationSet set2 = new AnimationSet(false);
					set2.addAnimation(tra2);
					set2.addAnimation(trareverse2);
		    	 my1.startAnimation(set0);
		    	 my2.startAnimation(set1);
		    	 my3.startAnimation(set2);
	

		     }
		     if(mycardnum==4){

					tra0= new TranslateAnimation(0,((x53[0]-y51[0])) , 0, -ymov);
					tra0.setDuration(200);
					tra0.setFillAfter(true);
					trareverse0 = new TranslateAnimation(0, -(x53[0]-y51[0]), 0, ymov);
					trareverse0.setDuration(200);
					trareverse0.setFillAfter(true);
					trareverse0.setStartOffset(250);
					AnimationSet set0 = new AnimationSet(false);
					set0.addAnimation(tra0);
					set0.addAnimation(trareverse0);
					tra1= new TranslateAnimation(0,((x53[0]-y52[0])) , 0, -ymov);
					tra1.setDuration(200);
					tra1.setFillAfter(true);
					tra1.setStartOffset(500);
					trareverse1 = new TranslateAnimation(0, -(x53[0]-y52[0]), 0, ymov);
					trareverse1.setDuration(200);
					trareverse1.setFillAfter(true);
					trareverse1.setStartOffset(750);
					AnimationSet set1 = new AnimationSet(false);
					set1.addAnimation(tra1);
					set1.addAnimation(trareverse1);
					tra2= new TranslateAnimation(0,((x53[0]-y53[0])) , 0, -ymov);
					tra2.setDuration(200);
					tra2.setFillAfter(true);
					tra2.setStartOffset(950);
					trareverse2 = new TranslateAnimation(0,-(x53[0]-y53[0]), 0, ymov);
					trareverse2.setDuration(200);
					trareverse2.setFillAfter(true);
					trareverse2.setStartOffset(1150);
					AnimationSet set2 = new AnimationSet(false);
					set2.addAnimation(tra2);
					set2.addAnimation(trareverse2);
					tra3= new TranslateAnimation(0,((x53[0]-y54[0])) , 0, -ymov);
					tra3.setDuration(200);
					tra3.setFillAfter(true);
					tra3.setStartOffset(1350);
					trareverse3 = new TranslateAnimation(0,-(x53[0]-y54[0]), 0, ymov);
					trareverse3.setDuration(200);
					trareverse3.setFillAfter(true);
					trareverse3.setStartOffset(1550);
					AnimationSet set3 = new AnimationSet(false);
					set3.addAnimation(tra3);
					set3.addAnimation(trareverse3);
		    	 my1.startAnimation(set0);
		    	 my2.startAnimation(set1);
		    	 my3.startAnimation(set2);
		    	 my4.startAnimation(set3);
	

		     }
		     if(mycardnum==5){
					tra0= new TranslateAnimation(0,((x53[0]-y51[0])) , 0, -ymov);
					tra0.setDuration(200);
					tra0.setFillAfter(true);
					trareverse0 = new TranslateAnimation(0, -(x53[0]-y51[0]), 0, ymov);
					trareverse0.setDuration(200);
					trareverse0.setFillAfter(true);
					trareverse0.setStartOffset(250);
					AnimationSet set0 = new AnimationSet(false);
					set0.addAnimation(tra0);
					set0.addAnimation(trareverse0);
					tra1= new TranslateAnimation(0,((x53[0]-y52[0])) , 0, -ymov);
					tra1.setDuration(200);
					tra1.setFillAfter(true);
					tra1.setStartOffset(500);
					trareverse1 = new TranslateAnimation(0, -(x53[0]-y52[0]), 0, ymov);
					trareverse1.setDuration(200);
					trareverse1.setFillAfter(true);
					trareverse1.setStartOffset(750);
					AnimationSet set1 = new AnimationSet(false);
					set1.addAnimation(tra1);
					set1.addAnimation(trareverse1);
					tra2= new TranslateAnimation(0,((x53[0]-y53[0])) , 0, -ymov);
					tra2.setDuration(200);
					tra2.setFillAfter(true);
					tra2.setStartOffset(950);
					trareverse2 = new TranslateAnimation(0, -(x53[0]-y53[0]), 0, ymov);
					trareverse2.setDuration(200);
					trareverse2.setFillAfter(true);
					trareverse2.setStartOffset(1150);
					AnimationSet set2 = new AnimationSet(false);
					set2.addAnimation(tra2);
					set2.addAnimation(trareverse2);
					tra3= new TranslateAnimation(0,((x53[0]-y54[0])) , 0, -ymov);
					tra3.setDuration(200);
					tra3.setFillAfter(true);
					tra3.setStartOffset(1350);
					trareverse3 = new TranslateAnimation(0, -(x53[0]-y54[0]), 0, ymov);
					trareverse3.setDuration(200);
					trareverse3.setFillAfter(true);
					trareverse3.setStartOffset(1550);
					AnimationSet set3 = new AnimationSet(false);
					set3.addAnimation(tra3);
					set3.addAnimation(trareverse3);
					tra4= new TranslateAnimation(0,((x53[0]-y55[0])) , 0, -ymov);
					tra4.setDuration(200);
					tra4.setFillAfter(true);
					tra4.setStartOffset(1750);
					trareverse4 = new TranslateAnimation(0,-(x53[0]-y55[0]), 0, ymov);
					trareverse4.setDuration(200);
					trareverse4.setFillAfter(true);
					trareverse4.setStartOffset(1950);
					AnimationSet set4 = new AnimationSet(false);
					set4.addAnimation(tra4);
					set4.addAnimation(trareverse4);
		    	 my1.startAnimation(set0);
		    	 my2.startAnimation(set1);
		    	 my3.startAnimation(set2);
		    	 my4.startAnimation(set3);
		    	 my5.startAnimation(set4);


		     }
		}

		
		public void moveenemycard(){
			if(enemycardnum==1){
				tra0= new TranslateAnimation(0,((x53[0]-z51[0])) , 0, ymov);
				tra0.setDuration(200);
				tra0.setFillAfter(true);
				
				
//		
//				findViewById(R.id.myholderbolt).setBackgroundResource(R.anim.boltanim);
//				  animationbolt = (AnimationDrawable) findViewById(R.id.myholderbolt).getBackground();
//				  
				trareverse0 = new TranslateAnimation(0, -(x53[0]-z51[0]), 0, -ymov);
				trareverse0.setDuration(200);
				trareverse0.setFillAfter(true);
				trareverse0.setStartOffset(250);
				AnimationSet set0 = new AnimationSet(false);
				set0.addAnimation(tra0);

				set0.addAnimation(trareverse0);
				en1.startAnimation(set0);
				//animationbolt.start();

	     }
	     if(enemycardnum==2){
				tra0= new TranslateAnimation(0,((x53[0]-z51[0])) , 0, ymov);
				tra0.setDuration(200);
				tra0.setFillAfter(true);
				tra1= new TranslateAnimation(0,((x53[0]-z52[0])) , 0, ymov);
				tra1.setDuration(200);
				tra1.setFillAfter(true);
				tra1.setStartOffset(500);
				trareverse0 = new TranslateAnimation(0, -(x53[0]-z51[0]), 0, -ymov);
				trareverse0.setDuration(200);
				trareverse0.setFillAfter(true);
				trareverse0.setStartOffset(250);
				AnimationSet set0 = new AnimationSet(false);
				set0.addAnimation(tra0);
				set0.addAnimation(trareverse0);

				trareverse1 = new TranslateAnimation(0, -(x53[0]-z52[0]), 0, -ymov);
				trareverse1.setDuration(200);
				trareverse1.setFillAfter(true);
				trareverse1.setStartOffset(750);
				AnimationSet set1 = new AnimationSet(false);
				set1.addAnimation(tra1);
				set1.addAnimation(trareverse1);

		    	 en1.startAnimation(set0);
		    	 en2.startAnimation(set1);


	     }
	     if(enemycardnum==3){
				tra0= new TranslateAnimation(0,((x53[0]-z51[0])) , 0, ymov);
				tra0.setDuration(200);
				tra0.setFillAfter(true);
				tra1= new TranslateAnimation(0,((x53[0]-z52[0])) , 0, ymov);
				tra1.setDuration(200);
				tra1.setFillAfter(true);
				tra1.setStartOffset(500);

				tra2= new TranslateAnimation(0,((x53[0]-z53[0])) , 0, ymov);
				tra2.setDuration(200);
				tra2.setFillAfter(true);
				tra2.setStartOffset(950);

				trareverse0 = new TranslateAnimation(0, -(x53[0]-z51[0]), 0, -ymov);
				trareverse0.setDuration(200);
				trareverse0.setFillAfter(true);
				trareverse0.setStartOffset(250);
				AnimationSet set0 = new AnimationSet(false);
				set0.addAnimation(tra0);
				set0.addAnimation(trareverse0);

				trareverse1 = new TranslateAnimation(0, -(x53[0]-z52[0]), 0, -ymov);
				trareverse1.setDuration(200);
				trareverse1.setFillAfter(true);
				trareverse1.setStartOffset(750);
				AnimationSet set1 = new AnimationSet(false);
				set1.addAnimation(tra1);
				set1.addAnimation(trareverse1);

				trareverse2 = new TranslateAnimation(0, -(x53[0]-z53[0]), 0, -ymov);
				trareverse2.setDuration(200);
				trareverse2.setFillAfter(true);
				trareverse2.setStartOffset(1150);
				AnimationSet set2 = new AnimationSet(false);
				set2.addAnimation(tra2);
				set2.addAnimation(trareverse2);

		    	 en1.startAnimation(set0);
		    	 en2.startAnimation(set1);
		    	 en3.startAnimation(set2);


	     }
	     if(enemycardnum==4){

				tra0= new TranslateAnimation(0,((x53[0]-z51[0])) , 0, ymov);
				tra0.setDuration(200);
				tra0.setFillAfter(true);
				tra1= new TranslateAnimation(0,((x53[0]-z52[0])) , 0, ymov);
				tra1.setDuration(200);
				tra1.setFillAfter(true);
				tra1.setStartOffset(500);

				tra2= new TranslateAnimation(0,((x53[0]-z53[0])) , 0, ymov);
				tra2.setDuration(200);
				tra2.setFillAfter(true);
				tra2.setStartOffset(1000);

				tra3= new TranslateAnimation(0,((x53[0]-z54[0])) , 0, ymov);
				tra3.setDuration(200);
				tra3.setFillAfter(true);
				tra3.setStartOffset(1500);

				trareverse0 = new TranslateAnimation(0, -(x53[0]-z51[0]), 0, -ymov);
				trareverse0.setDuration(200);
				trareverse0.setFillAfter(true);
				trareverse0.setStartOffset(250);
				AnimationSet set0 = new AnimationSet(false);
				set0.addAnimation(tra0);
				set0.addAnimation(trareverse0);

				trareverse1 = new TranslateAnimation(0, -(x53[0]-z52[0]), 0, -ymov);
				trareverse1.setDuration(200);
				trareverse1.setFillAfter(true);
				trareverse1.setStartOffset(750);
				AnimationSet set1 = new AnimationSet(false);
				set1.addAnimation(tra1);
				set1.addAnimation(trareverse1);

				trareverse2 = new TranslateAnimation(0, -(x53[0]-z53[0]), 0, -ymov);
				trareverse2.setDuration(200);
				trareverse2.setFillAfter(true);
				trareverse2.setStartOffset(1250);
				AnimationSet set2 = new AnimationSet(false);
				set2.addAnimation(tra2);
				set2.addAnimation(trareverse2);

				trareverse3 = new TranslateAnimation(0, -(x53[0]-z54[0]), 0, -ymov);
				trareverse3.setDuration(200);
				trareverse3.setFillAfter(true);
				trareverse3.setStartOffset(1550);
				AnimationSet set3 = new AnimationSet(false);
				set3.addAnimation(tra3);
				set3.addAnimation(trareverse3);

		    	 en1.startAnimation(set0);
		    	 en2.startAnimation(set1);
		    	 en3.startAnimation(set2);
		    	 en4.startAnimation(set3);
	
	     }
	     if(enemycardnum==5){
				tra0= new TranslateAnimation(0,((x53[0]-z51[0])) , 0, ymov);
				tra0.setDuration(200);
				tra0.setFillAfter(true);
				tra1= new TranslateAnimation(0,((x53[0]-z52[0])) , 0, ymov);
				tra1.setDuration(200);
				tra1.setFillAfter(true);
				tra1.setStartOffset(500);

				tra2= new TranslateAnimation(0,((x53[0]-z53[0])) , 0, ymov);
				tra2.setDuration(200);
				tra2.setFillAfter(true);
				tra2.setStartOffset(950);

				tra3= new TranslateAnimation(0,((x53[0]-z54[0])) , 0, ymov);
				tra3.setDuration(200);
				tra3.setFillAfter(true);
				tra3.setStartOffset(1350);

				tra4= new TranslateAnimation(0,((x53[0]-z55[0])) , 0, ymov);
				tra4.setDuration(200);
				tra4.setFillAfter(true);
				tra4.setStartOffset(1750);

				trareverse0 = new TranslateAnimation(0, -(x53[0]-z51[0]), 0, -ymov);
				trareverse0.setDuration(200);
				trareverse0.setFillAfter(true);
				trareverse0.setStartOffset(250);
				AnimationSet set0 = new AnimationSet(false);
				set0.addAnimation(tra0);
				set0.addAnimation(trareverse0);

				trareverse1 = new TranslateAnimation(0, -(x53[0]-z52[0]), 0, -ymov);
				trareverse1.setDuration(200);
				trareverse1.setFillAfter(true);
				trareverse1.setStartOffset(750);
				AnimationSet set1 = new AnimationSet(false);
				set1.addAnimation(tra1);
				set1.addAnimation(trareverse1);

				trareverse2 = new TranslateAnimation(0, -(x53[0]-z53[0]), 0, -ymov);
				trareverse2.setDuration(200);
				trareverse2.setFillAfter(true);
				trareverse2.setStartOffset(1150);
				AnimationSet set2 = new AnimationSet(false);
				set2.addAnimation(tra2);
				set2.addAnimation(trareverse2);

				trareverse3 = new TranslateAnimation(0, -(x53[0]-z54[0]), 0, -ymov);
				trareverse3.setDuration(200);
				trareverse3.setFillAfter(true);
				trareverse3.setStartOffset(1550);
				AnimationSet set3 = new AnimationSet(false);
				set3.addAnimation(tra3);
				set3.addAnimation(trareverse3);

				trareverse4 = new TranslateAnimation(0,-(x53[0]-z55[0]), 0, -ymov);
				trareverse4.setDuration(200);
				trareverse4.setFillAfter(true);
				trareverse4.setStartOffset(1950);
				AnimationSet set4 = new AnimationSet(false);
				set4.addAnimation(tra4);
				set4.addAnimation(trareverse4);
	    	 en1.startAnimation(set0);
	    	 en2.startAnimation(set1);
	    	 en3.startAnimation(set2);
	    	 en4.startAnimation(set3);
	    	 en5.startAnimation(set4);

		}
	
		}
		
		
		
	    @Override
public void onDestroy(){
			getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	    	super.onDestroy();
	    }
		}
	