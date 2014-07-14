package com.example.shadowduel;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;	
import android.content.SharedPreferences;
import android.content.SyncRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
 
public class ActivityGame extends Activity {
	MediaPlayer player;

	private String connPath="http://shadowduel.altervista.org";
	private String connPathalt="http://shadowduel.netne.net";
	ProgressDialog progressBar;
	private int progressStatus = 0;
	Button buttonReturn;
	Button buttonStart;
	Button buttonFind;
	public String enemyname=null;
	public boolean trovato=false;
	public Context context;
	public int idgame=0;
    private String username;
    private String userload;
    private String password;
    private String passload;
    public static final String PREFS_NAME = "storage_var";
    private int timer=0;
	// Variabili di connesione al server e JSON
	public static JSONArray jsArray = null;
	public HttpPost httpPost;
	public DefaultHttpClient httpClient;
	FindTask ult= new FindTask();
	public String usertemp;
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	//connPath=connPathalt;

		super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);

		addListenerOnButton();
		load();
	    
    }
	public void addListenerOnButton() {
		 
		context = this;
		
		buttonReturn = (Button) findViewById(R.id.buttonReturn);
//		buttonStart = (Button) findViewById(R.id.buttonGame);
		buttonFind = (Button) findViewById(R.id.buttonFind);
//		buttonStart.setEnabled(false);
//		buttonStart.setVisibility(View.INVISIBLE);
		buttonReturn.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				finish();
 
			}
 
		});
		buttonFind.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				
				progressBar = new ProgressDialog(context);
				
				progressBar.setCancelable(true);
				
				progressBar.setMessage("Sto cercando un avversario ...");
				progressBar.setProgress(0);
				progressBar.setMax(100);
				progressBar.setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        try {
                            ult.cancel(true);
                            if(trovato==false)
                            sendUserAndremoveFromMatch(1);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                    }
                });
				progressBar.show();
				ult = new FindTask();
				
		        
		        	ult.execute();
				

		        
				
 
			}
 
		});
//		buttonStart.setOnClickListener(new OnClickListener() {
//			 
//			@Override
//			public void onClick(View arg0) {
//				
//				Intent intent = new Intent(context,ActivityBattle.class);
//				intent.putExtra("enemyname",enemyname );
//				startActivity(intent);
//				
// 
//			}
// 
//		});
	}
    private void load(){
        SharedPreferences u = getSharedPreferences(PREFS_NAME, 0);
        username = u.getString("username", userload);
        SharedPreferences p = getSharedPreferences(PREFS_NAME, 1);
        password = p.getString("password", passload);
    }
    private void save(){
        SharedPreferences id = getSharedPreferences(PREFS_NAME, 2);
       SharedPreferences.Editor editorid = id.edit();
       editorid.putInt("idgame", idgame);
       editorid.commit();

    }
    public class FindTask extends AsyncTask<Void, Void, Boolean> {
    	
    	String json = "";
    	
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
        	for(;timer<100;timer++){
        		 progressStatus += 1;
        		 progressBar.setProgress(progressStatus);
       	       if(idgame!=0){
      	    	   timer=148;
      	       }
            try {
            	try {
					sendUserAndGetJsonFromUrl();
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
      
        	if(idgame==0){
            		timer=0;
            		progressBar.cancel();
			    	Toast.makeText(context, "Nessun avversario trovato.",Toast.LENGTH_LONG).show();
			    	try {
						sendUserAndremoveFromMatch(1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
        	 	}
        	else{
        		save();
        		try {
					sendUserAndremoveFromMatch(2);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		finish();
        		trovato=true;
        		progressBar.cancel();
        		
        		
        		Intent intent = new Intent(context,ActivityBattle.class);
					intent.putExtra("enemyname",enemyname);
					startActivity(intent);
        	}	    
        }
    }  
    
   public void sendUserAndremoveFromMatch(int find) throws JSONException, InterruptedException {

//   	String json = null;
   	String url = "http://shadowduel.altervista.org/app/app_confirm_matchmaking.php";	



//       try {

 			httpClient = new DefaultHttpClient();
 			httpPost = new HttpPost(url);



 		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
 		    nameValuePairs.add(new BasicNameValuePair("username", username));
 		    nameValuePairs.add(new BasicNameValuePair("password", password));
 		    nameValuePairs.add(new BasicNameValuePair("find", ""+find));

 		    try {
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

 		    // Execute HTTP Post Request
// 		    HttpResponse httpResponse = httpClient.execute(httpPost);
//           HttpEntity httpEntity = httpResponse.getEntity();
//           json = EntityUtils.toString(httpEntity);

//       } catch (UnsupportedEncodingException e) {
//           e.printStackTrace();
//       } catch (ClientProtocolException e) {
//           e.printStackTrace();
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
       


   }
    public void sendUserAndGetJsonFromUrl() throws JSONException, InterruptedException {

    	String json = null;
    	String url = "http://shadowduel.altervista.org/app/app_start_matchmaking.php";	



        try {

  			httpClient = new DefaultHttpClient();
  			httpPost = new HttpPost(url);



  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
  		    nameValuePairs.add(new BasicNameValuePair("username", username));
  		    nameValuePairs.add(new BasicNameValuePair("password", password));

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
        
    	jsArray = new JSONArray(json);
    	fetchJSON(jsArray);

    }
    
    
    
    public void fetchJSON(JSONArray jsonArray){

      	try {
      	   for (int i = 0; i < jsonArray.length(); i++) {
      	       usertemp = jsonArray.getJSONObject(i).getString("username");
      	       if(username.equalsIgnoreCase(usertemp)){
      	    	 idgame = Integer.parseInt(jsonArray.getJSONObject(i).getString("vsid"));
      	    	
      	       }
      	    }
      	 int idtemp=idgame;
      	 for (int i = 0; i < jsonArray.length(); i++) {
      		 	if(Integer.parseInt(jsonArray.getJSONObject(i).getString("vsid"))==idtemp&&!jsonArray.getJSONObject(i).getString("username").equalsIgnoreCase(username)){
      		 		enemyname = jsonArray.getJSONObject(i).getString("username");
      		 	}
    	      
    	     
    	    }
      	   
      	   
      	   
      	} catch (Exception e) {
      	     e.printStackTrace();
      	}
      	
      	
      	
      	
      	
      	
}
	@Override
	public void onResume() {
		super.onResume();
		
		
		
		
}
	@Override
	public void onPause(){
		super.onPause();
		ult.cancel(true);
		

	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		ult.cancel(true);
		

	}
    
}