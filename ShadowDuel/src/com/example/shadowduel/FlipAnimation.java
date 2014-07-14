package com.example.shadowduel;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class FlipAnimation implements AnimationListener{
    private Animation animation1;
    private Animation animation2;
    private boolean isBackOfCardShowing = true;
    private Context context;
    private View card;
    private int imageid;
    private boolean a=true;
    public FlipAnimation(Context con,View v,int id){
    	imageid=id;
    	card=v;
    	this.context=con;
        animation1 = AnimationUtils.loadAnimation(context, R.anim.to_middle);
        animation1.setAnimationListener(this);
        animation1.setDuration(600);
        animation2 = AnimationUtils.loadAnimation(context, R.anim.from_middle);
        animation2.setAnimationListener(this);
        animation2.setDuration(600);

    }
    
    

     @Override
     public void onAnimationEnd(Animation animation) {
           if (a) {
                  if (isBackOfCardShowing) {
     card.setBackgroundResource(context.getResources().getIdentifier("c_"+imageid, "drawable", context.getPackageName()));////
                    } else {
                    	 card.setBackgroundResource(context.getResources().getIdentifier("c_0", "drawable", context.getPackageName()));                    }	
                    card.clearAnimation();
      card.setAnimation(animation2);

      card.startAnimation(animation2);
	  a=false;

              } else {
            	  a=true;
                     isBackOfCardShowing=!isBackOfCardShowing;
              }
    	 //if(animation==animation1){
    	 //((ImageView)card).setImageDrawable((context.getResources().getDrawable(R.drawable.c_0)));
//    	 card.setBackgroundResource(context.getResources().getIdentifier("c_0", "drawable", context.getPackageName()));
//    	 
//    	 card.setAnimation(animation2);
//    	 card.startAnimation(animation2);//}
     }
     @Override
     public void onAnimationRepeat(Animation animation) {
            card.clearAnimation();
     }
     @Override
     public void onAnimationStart(Animation animation) {
     // TODO Auto-generated method stub
     }
}
